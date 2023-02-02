package ftn.kts.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDTO {

    private Long id, driverId;

    private String model, plateNumber, vehicleType;

    private int numberOfSeats;

    private boolean babyFlag, petsFlag;

    public VehicleDTO(Long id, String model, String plateNumber, String vehicleType, int numberOfSeats, boolean babyFlag, boolean petsFlag) {
        this.id = id;
        this.model = model;
        this.plateNumber = plateNumber;
        this.vehicleType = vehicleType;
        this.numberOfSeats = numberOfSeats;
        this.babyFlag = babyFlag;
        this.petsFlag = petsFlag;
    }
}
