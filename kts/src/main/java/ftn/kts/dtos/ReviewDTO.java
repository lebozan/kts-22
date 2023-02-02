package ftn.kts.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewDTO {
    private Long id;

    private int reviewRating;

    private String comment;

    private PassengerShortDTO passenger;
}
