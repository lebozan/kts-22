package ftn.kts.controller;

import com.graphhopper.GHResponse;
import com.graphhopper.ResponsePath;
import ftn.kts.dtos.RideDTO;
import ftn.kts.model.Ride;
import ftn.kts.service.GraphHopperService;
import ftn.kts.service.RideService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/rides")
public class RideController {

    private final RideService rideService;
    private final GraphHopperService graphHopperService;


    public RideController(RideService rideService, GraphHopperService graphHopperService) {
        this.rideService = rideService;
        this.graphHopperService = graphHopperService;
    }


    @PostMapping(produces = "application/json")
    public ResponseEntity<Ride> createRide(@RequestBody RideDTO rideDTO) {
        GHResponse response = graphHopperService.getGhDirectionsForCoords(rideDTO.getLocations());
        ResponsePath bestPath = response.getBest();
        double rideTimeInMinutes = bestPath.getTime() / 60000.0;
        double rideDistanceInKM = bestPath.getDistance() / 1000.0;
        rideDTO.setDistanceInKm(rideDistanceInKM);
        rideDTO.setTimeInMinutes(rideTimeInMinutes);

        Ride newRide = rideService.createNewRide(rideDTO);
        return new ResponseEntity<>(newRide, HttpStatus.OK);
    }

    @GetMapping(value = "/driver/{id}/active")
    public ResponseEntity<Ride> getActiveRideForDriver(@PathVariable Long id) {

        return null;
    }

    @GetMapping(value = "/passenger/{id}/active")
    public ResponseEntity<Ride> getActiveRideForPassenger(@PathVariable Long id) {
        Ride activeRide = rideService.getActiveRideForPassenger(id);
        return null;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Ride> getRideById(@PathVariable Long id) {
        Ride r = rideService.getRideById(id);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/cancel")
    public ResponseEntity<Ride> cancelExistingRide(@PathVariable Long id) {

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
