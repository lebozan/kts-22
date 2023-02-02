package ftn.kts.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DriverDTO extends UserDTO {

    private VehicleDTO vehicle;

}
