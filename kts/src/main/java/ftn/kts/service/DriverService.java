package ftn.kts.service;

import ftn.kts.dtos.DocumentDTO;
import ftn.kts.dtos.DriverDTO;
import ftn.kts.dtos.UserDetailsUpdateDTO;
import ftn.kts.dtos.VehicleDTO;
import ftn.kts.helper.DriverMapper;
import ftn.kts.helper.UserMapper;
import ftn.kts.helper.VehicleMapper;
import ftn.kts.model.*;
import ftn.kts.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class DriverService {

    private final UserRepository userRepository;

    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final DocumentRepository documentRepository;
    private final WorkingHoursRepository workingHoursRepository;
    private final VehicleMapper vehicleMapper;


    private PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;
    private final DriverMapper driverMapper;

    public DriverService(UserRepository userRepository, DriverRepository driverRepository,
                         VehicleRepository vehicleRepository, VehicleTypeRepository vehicleTypeRepository,
                         DocumentRepository documentRepository, WorkingHoursRepository workingHoursRepository,
                         PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.documentRepository = documentRepository;
        this.workingHoursRepository = workingHoursRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = new UserMapper();
        this.driverMapper = new DriverMapper();
        this.vehicleMapper = new VehicleMapper();
    }

    public Driver addNewDriver(DriverDTO dto) {
        Driver newDriver = driverMapper.toEntity(dto);
        Vehicle newVehicle = newDriver.getVehicle();
        newVehicle.setVehicleType(vehicleTypeRepository.findByType(newVehicle.getVehicleType().getType()));
        vehicleRepository.save(newVehicle);
        newDriver.setVehicle(newVehicle);
        newDriver.setPassword(passwordEncoder.encode("sifra123"));
        return driverRepository.save(newDriver);
    }


    public Page<Driver> getDriversPage(int page, int size) {
        return driverRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<Driver> getDriverById(Long id) {
        return driverRepository.findById(id);
    }


    public Driver updateDriverDetails(Long id, UserDetailsUpdateDTO updateDTO) {
        Optional<Driver> dOpt = driverRepository.findById(id);
        if (dOpt.isEmpty()) {
            return null;
        }
        Driver d = dOpt.get();
        d.setAddress(updateDTO.getNewAddress());
        d.setProfilePicture(updateDTO.getNewProfilePicture());
        return driverRepository.save(d);
    }

    public void deleteDriversDocument(Long userId, Long documentId) throws Exception {
        Optional<Driver> driverOptional = driverRepository.findById(userId);
        if (driverOptional.isEmpty()) {
            throw new Exception("User not found!");
        }

        Driver d = driverOptional.get();
        d.setDocuments(d.getDocuments().stream().filter(doc -> !Objects.equals(doc.getId(), documentId)).collect(Collectors.toList()));
        driverRepository.save(d);
        documentRepository.deleteById(documentId);
    }

    public VehicleDTO addDriversVehicle(Long driverId, VehicleDTO vehicleDTO) {

        Optional<Driver> driverOptional = driverRepository.findById(driverId);
        Vehicle newVehicle = vehicleMapper.toEntity(vehicleDTO);
        VehicleType vehicleType = vehicleTypeRepository.findByType(newVehicle.getVehicleType().getType());
        newVehicle.setVehicleType(vehicleType);
        newVehicle = vehicleRepository.save(newVehicle);
        Vehicle finalNewVehicle = newVehicle;
        driverOptional.ifPresent(driver -> {
            driver.setVehicle(finalNewVehicle);
            driverRepository.save(driver);
        });
        VehicleDTO finalDto = vehicleMapper.toDto(finalNewVehicle);
        finalDto.setDriverId(driverId);
        return finalDto;
    }

    public WorkingHours getWorkingHoursById(Long driverId, Long workingHoursId) {
        Optional<Driver> driverOptional = driverRepository.findById(driverId);
        AtomicReference<List<WorkingHours>> workingHours = new AtomicReference<>();
        driverOptional.ifPresent((driver) -> workingHours.set(workingHoursRepository.findAllByDriver_Id(driverId)));

        return workingHours.get().stream().filter(wh -> wh.getId() == workingHoursId).collect(Collectors.toList()).get(0);
    }

    public WorkingHours addNewWorkingHours(Long driverId, WorkingHours wh) {
        Optional<Driver> driverOptional = driverRepository.findById(driverId);
        AtomicReference<WorkingHours> newWh = new AtomicReference<>();
        driverOptional.ifPresent((driver) -> {
            wh.setDriver(driver);
            newWh.set(workingHoursRepository.save(wh));
        });

        return newWh.get();
    }

    public Page<WorkingHours> getWorkingHoursHistoryByInterval(Long driverId, int page, int size, Date from, Date to) {
        Page<WorkingHours> history = workingHoursRepository.
                findAllByDriver_IdAndStartTimeAfterAndEndTimeBefore(driverId, from, to, PageRequest.of(page, size));

        return history;
    }

    public Document addDriversNewDocument(Long driverId, DocumentDTO documentDTO) {
        Document[] newDoc = {new Document()};
        newDoc[0].setName(documentDTO.getName());
        newDoc[0].setImage(documentDTO.getImage());
        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        optionalDriver.ifPresent(driver -> {
            newDoc[0].setDriver(driver);
            newDoc[0] = documentRepository.save(newDoc[0]);
            driver.getDocuments().add(newDoc[0]);
            driverRepository.save(driver);
        });
        return newDoc[0];
    }
}
