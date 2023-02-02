package ftn.kts.controller;

import ftn.kts.dtos.LocationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/vehicles")
public class VehicleController {

    @PutMapping(value = "/{id}/location")
    public ResponseEntity<String> changeVehicleLocation(@PathVariable Long id, @RequestBody LocationDTO locationDTO) {

        return null;
    }
}
