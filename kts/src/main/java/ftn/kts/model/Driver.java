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
@DiscriminatorValue("driver")
public class Driver extends User {

    @OneToMany
    private List<Document> documents;

    @OneToMany
    private List<Ride> rideList;

    @OneToOne
    @JoinTable(name = "driver_vehicle",
            joinColumns = @JoinColumn(name = "driver_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id", referencedColumnName = "id"))
    private Vehicle vehicle;
}
