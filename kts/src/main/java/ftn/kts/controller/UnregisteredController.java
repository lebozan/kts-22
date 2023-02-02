package ftn.kts.controller;

import ftn.kts.dtos.RideEstimateDTO;
import ftn.kts.dtos.RideInputDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/unregisteredUsers")
public class UnregisteredController {

    @PostMapping
    public ResponseEntity<RideEstimateDTO> calculateRideEstimate(@RequestBody RideInputDTO dto) {

        return null;
    }
}
