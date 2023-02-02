package ftn.kts.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @Column
    private String model, plateNumber;

    @Column
    private int numberOfSeats;

    @Column
    private boolean babyFlag, petsFlag;

    @OneToOne
    private Location currentLocation;

    @OneToOne
    private VehicleType vehicleType;

    @OneToMany
    private List<Review> reviews;
}
