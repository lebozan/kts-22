package ftn.kts.controller;

import ftn.kts.dtos.UserDTO;
import ftn.kts.dtos.UserDetailsUpdateDTO;
import ftn.kts.model.Driver;
import ftn.kts.model.Ride;
import ftn.kts.service.PassengerService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/api/passengers")
public class PassengerController {


    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createPassenger(@RequestBody UserDTO userDTO) {
        UserDTO newPassenger = passengerService.addPassenger(userDTO);

        return new ResponseEntity<>(newPassenger, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getPassengers(@RequestParam int page, @RequestParam int size) {
        Page<UserDTO> userDTOPage = passengerService.getUsersPage(page, size);
        return new ResponseEntity<>(userDTOPage, HttpStatus.OK);
    }

    @GetMapping(value = "/activate/{activationId}")
    public ResponseEntity<String> activatePassenger(@PathVariable int activationId) {
        passengerService.activatePassenger((long) activationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getPassengerById(@PathVariable Long id) {
        UserDTO p = passengerService.getPassengerById(id);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updatePassenger(@PathVariable Long id, @RequestBody UserDetailsUpdateDTO dto) {
        UserDTO p = passengerService.updatePassengerDetails(id, dto);
        if (p == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/ride")
    public ResponseEntity<Page<Ride>> getPassengerRidesPage(@PathVariable Long id, @RequestParam int page,
                                                            @RequestParam int size, @RequestParam String sort,
                                                            @RequestParam Date from, @RequestParam Date to) {
        Page<Ride> ridesPage = passengerService.getUsersRidesPage(id, page, size, sort, from, to);
        return new ResponseEntity<>(ridesPage, HttpStatus.OK);
    }
}
