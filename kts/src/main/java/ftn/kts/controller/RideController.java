package ftn.kts.controller;

import ftn.kts.dtos.RideDTO;
import ftn.kts.model.Ride;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/rides")
public class RideController {


    @PostMapping
    public ResponseEntity<Ride> createRide(@RequestBody RideDTO rideDTO) {

        return null;
    }

    @GetMapping(value = "/driver/{id}/active")
    public ResponseEntity<Ride> getActiveRideForDriver(@PathVariable Long id) {

        return null;
    }

    @GetMapping(value = "/passenger/{id}/active")
    public ResponseEntity<Ride> getActiveRideForPassenger(@PathVariable Long id) {

        return null;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Ride> getRideById(@PathVariable Long id) {

        return null;
    }

    @PutMapping(value = "/{id}/cancel")
    public ResponseEntity<Ride> cancelExistingRide(@PathVariable Long id) {

        return null;
    }

    @PutMapping(value = "/{id}/panic")
    public ResponseEntity<Ride> panicForExistingRide(@PathVariable Long id) {

        return null;
    }

    @PutMapping(value = "/{id}/start")
    public ResponseEntity<Ride> startExistingRide(@PathVariable Long id) {

        return null;
    }

    @PutMapping(value = "/{id}/end")
    public ResponseEntity<Ride> endExistingRide(@PathVariable Long id) {

        return null;
    }

    @PutMapping(value = "/{id}/reject")
    public ResponseEntity<Ride> rejectExistingRide(@PathVariable Long id) {

        return null;
    }

//    @PostMapping(value = "/favourites")
//    public ResponseEntity<>



}
