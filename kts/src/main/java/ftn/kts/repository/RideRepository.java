package ftn.kts.repository;

import ftn.kts.model.Ride;
import ftn.kts.model.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    Ride findByPassengerList_IdAndStatus(Long id, RideStatus status);


}
