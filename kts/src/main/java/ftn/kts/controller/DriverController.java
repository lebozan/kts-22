package ftn.kts.controller;

import ftn.kts.dtos.*;
import ftn.kts.helper.DocumentMapper;
import ftn.kts.helper.DriverMapper;
import ftn.kts.helper.UserMapper;
import ftn.kts.model.*;
import ftn.kts.service.DriverService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/drivers")
public class DriverController {

    private final DriverService driverService;
    private final UserMapper userMapper;
    private final DriverMapper driverMapper;
    private final DocumentMapper documentMapper;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
        this.documentMapper = new DocumentMapper();
        this.userMapper = new UserMapper();
        this.driverMapper = new DriverMapper();
    }

    @PostMapping
    public ResponseEntity<UserDTO> createDriver(@RequestBody DriverDTO dto) {
        Driver newDriver;
        try {
            newDriver = driverService.addNewDriver(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(userMapper.toDto(newDriver), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<DriverDTO>> getDriversPage(@RequestParam int page, @RequestParam int size) {
        Page<Driver> driverPage = driverService.getDriversPage(page, size);
        Page<DriverDTO> driverDTOS = driverPage.map(driverMapper::toDto);

        return new ResponseEntity<>(driverDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DriverDTO> getDriverDetails(@PathVariable Long id) {
        Optional<Driver> driverOptional = driverService.getDriverById(id);
        if (driverOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(driverMapper.toDto(driverOptional.get()), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updateDriverDetails(@PathVariable Long id, @RequestBody UserDetailsUpdateDTO updateDTO) {
        Driver d = driverService.updateDriverDetails(id, updateDTO);

        return new ResponseEntity<>(driverMapper.toDto(d), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/documents")
    public ResponseEntity<List<DocumentDTO>> getDriversDocuments(@PathVariable Long id) {
        Optional<Driver> driverOptional = driverService.getDriverById(id);
        if (driverOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<DocumentDTO> documentDTOS = driverOptional.get().getDocuments().stream().map(documentMapper::toDto).collect(Collectors.toList());

        return new ResponseEntity<>(documentDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/documents")
    public ResponseEntity<DocumentDTO> addDriversDocument(@PathVariable Long id, @RequestBody DocumentDTO documentDTO) {
        Document newDoc = driverService.addDriversNewDocument(id, documentDTO);

        return new ResponseEntity<>(documentMapper.toDto(newDoc), HttpStatus.OK);
    }

    @DeleteMapping(value = "/documents/{documentId}")
    public ResponseEntity<String> deleteDriversDocument(@PathVariable Long documentId, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        try {
            driverService.deleteDriversDocument(currentUser.getId(), documentId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/vehicle")
    public ResponseEntity<Vehicle> getDriversVehicle(@PathVariable Long id) {
        Optional<Driver> driverOptional = driverService.getDriverById(id);
        if (driverOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(driverOptional.get().getVehicle(), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/vehicle")
    public ResponseEntity<VehicleDTO> addDriversVehicle(@PathVariable Long id, @RequestBody VehicleDTO dto) {

        VehicleDTO newDto = driverService.addDriversVehicle(id, dto);
        return new ResponseEntity<>(newDto, HttpStatus.OK);
    }

//    @PutMapping(value = "/{id}/vehicle")
//    public ResponseEntity<Vehicle> changeDriversVehicle(@PathVariable Long id, @RequestBody VehicleDTO dto) {
//
//        return null;
//    }

    @PostMapping(value = "/{id}/workingHours")
    public ResponseEntity<WorkingHours> addDriversWorkingHours(@PathVariable Long id, @RequestBody WorkingHours wh) {
        WorkingHours newWh = driverService.addNewWorkingHours(id, wh);

        return new ResponseEntity<>(newWh, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/workingHours")
    public ResponseEntity<WorkingHours> getDriversWorkingHoursHistory(@PathVariable Long id, @RequestParam int page,
                                                               @RequestParam int size, @RequestParam String sort,
                                                               @RequestParam Date from, @RequestParam Date to) {

        Page<WorkingHours> workingHoursHistory = driverService.getWorkingHoursHistoryByInterval(id, page, size, from, to);

        return null;
    }

    @GetMapping(value = "/workingHours/{id}")
    public ResponseEntity<WorkingHours> getWorkingHoursById(@PathVariable Long id, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        WorkingHours wh = driverService.getWorkingHoursById(currentUser.getId(), id);
        return new ResponseEntity<>(wh, HttpStatus.OK);

    }

    @PutMapping(value = "/workingHours/{id}")
    public ResponseEntity<WorkingHours> changeWorkingHoursById(@PathVariable Long id) {

        return null;
    }



}
