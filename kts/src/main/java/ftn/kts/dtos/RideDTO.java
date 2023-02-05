package ftn.kts.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RideDTO {

    private List<LocationDTO> locations;
    private List<PassengerShortDTO> passengers;
    private double distanceInKm;
    private double timeInMinutes;
    private String vehicleType;
    private Date scheduledTime;
    private boolean petTransport, babyTransport;

}
