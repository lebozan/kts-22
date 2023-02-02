package ftn.kts.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocationDTO {

    private String address;
    private double latitude, longitude;
}
