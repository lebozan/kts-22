package ftn.kts.controller;

import ftn.kts.dtos.ReviewDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/reviews")
public class ReviewController {

    @PostMapping(value = "/{rideId}")
    public ResponseEntity<ReviewDTO> newReviewForRide(@PathVariable long rideId) {

        return null;
    }

    @GetMapping(value = "/{rideId}")
    public ResponseEntity<ReviewDTO> getReviewsForRide(@PathVariable Long rideId) {

        return null;
    }





}
