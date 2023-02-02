package ftn.kts.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date startTime, endTime;

    @Column
    private Double price;

    @OneToOne
    private Driver driver;

    @OneToMany
    private List<Passenger> passengerList;

    @OneToMany
    private List<Directions> directionsList;

    @Column
    private Integer approximateDuration;

    @OneToMany
    @JoinTable(name = "ride_review",
            joinColumns = @JoinColumn(name = "ride_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "review_id", referencedColumnName = "id"))
    private List<Review> reviewList;

    @Column
    @Enumerated(value = EnumType.STRING)
    private RideStatus status;

    @OneToOne
    private Rejection rejection;

    @Column
    private boolean panicFlag, babyFlag, petsFlag;

    @OneToOne
    private VehicleType vehicleType;



}
