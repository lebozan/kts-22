package ftn.kts.service;

import ftn.kts.dtos.UserDTO;
import ftn.kts.dtos.UserDetailsUpdateDTO;
import ftn.kts.helper.PassengerMapper;
import ftn.kts.helper.RidePageImpl;
import ftn.kts.model.Driver;
import ftn.kts.model.Passenger;
import ftn.kts.model.Ride;
import ftn.kts.model.Role;
import ftn.kts.repository.PassengerRepository;
import ftn.kts.repository.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;
    private final PasswordEncoder passwordEncoder;
//    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;

    public PassengerService(PassengerRepository passengerRepository, PasswordEncoder passwordEncoder,
                            RoleRepository roleRepository) {
        this.passengerRepository = passengerRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.passengerMapper = new PassengerMapper();
    }

    public UserDTO addPassenger(UserDTO dto) {
        Passenger newPassenger = passengerMapper.toEntity(dto);
        newPassenger.setPassword(passwordEncoder.encode(newPassenger.getPassword()));
        newPassenger.setActive(false);
        newPassenger.setBlocked(false);
        Role passengerRole = roleRepository.findByName("ROLE_PASSENGER");
        newPassenger.setRoles(Collections.singletonList(passengerRole));

        newPassenger = passengerRepository.save(newPassenger);
        return passengerMapper.toDto(newPassenger);
    }

    public Page<UserDTO> getUsersPage(int page, int size) {
        Page<Passenger> passengerPage = passengerRepository.findAll(PageRequest.of(page, size));
        return passengerPage.map(passengerMapper::toDto);
    }

    public void activatePassenger(Long activationId) {
        Optional<Passenger> optionalPassenger = passengerRepository.findById(activationId);
        optionalPassenger.ifPresent((passenger -> {passenger.setActive(true); passengerRepository.save(passenger);}));
    }

    public UserDTO getPassengerById(Long id) {
        Optional<Passenger> optionalPassenger = passengerRepository.findById(id);
        return passengerMapper.toDto(optionalPassenger.orElseThrow());
    }

    public UserDTO updatePassengerDetails(Long id, UserDetailsUpdateDTO dto) {
        Optional<Passenger> dOpt = passengerRepository.findById(id);
        if (dOpt.isEmpty()) {
            return null;
        }
        Passenger p = dOpt.get();
        p.setAddress(dto.getNewAddress());
        p.setProfilePicture(dto.getNewProfilePicture());
        p = passengerRepository.save(p);
        return passengerMapper.toDto(p);
    }


    public Page<Ride> getUsersRidesPage(Long passengerId, int page, int size, String sortTable, Date from, Date to) {
        Optional<Passenger> optionalPassenger = passengerRepository.findById(passengerId);
        if (optionalPassenger.isEmpty()) {
            return null;
        }
        Passenger p = optionalPassenger.get();
        List<Ride> rides = null;
        switch (sortTable) {
            case "approx":
                rides = p.getRideList().stream().filter(ride -> ride.getStartTime().after(from) && ride.getEndTime().before(to))
                        .sorted(Comparator.comparing(Ride::getApproximateDuration))
                        .collect(Collectors.toList());
                break;
            case "price":
                rides = p.getRideList().stream().filter(ride -> ride.getStartTime().after(from) && ride.getEndTime().before(to))
                        .sorted(Comparator.comparing(Ride::getPrice))
                        .collect(Collectors.toList());
                break;
            case "start time":
                rides = p.getRideList().stream().filter(ride -> ride.getStartTime().after(from) && ride.getEndTime().before(to))
                        .sorted(Comparator.comparing(Ride::getStartTime))
                        .collect(Collectors.toList());
                break;
            case "end time":
                rides = p.getRideList().stream().filter(ride -> ride.getStartTime().after(from) && ride.getEndTime().before(to))
                        .sorted(Comparator.comparing(Ride::getEndTime))
                        .collect(Collectors.toList());
                break;
        }
        return new RidePageImpl<>(rides, PageRequest.of(page, size), rides.size());
    }
}
