package ftn.kts.service;

import ftn.kts.dtos.RideDTO;
import ftn.kts.model.Ride;
import ftn.kts.model.RideStatus;
import ftn.kts.model.VehicleType;
import ftn.kts.repository.LocationRepository;
import ftn.kts.repository.RideRepository;
import ftn.kts.repository.UserRepository;
import ftn.kts.repository.VehicleTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class RideService {

    private final RideRepository rideRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final VehicleTypeRepository vehicleTypeRepository;


    public RideService(RideRepository rideRepository, LocationRepository locationRepository,
                       UserRepository userRepository, VehicleTypeRepository vehicleTypeRepository) {
        this.rideRepository = rideRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
    }


    public Ride createNewRide(RideDTO rideDTO) {
        Ride newRide = new Ride();
        newRide.setBabyFlag(rideDTO.isBabyTransport());
        newRide.setPetsFlag(rideDTO.isPetTransport());
        VehicleType vehicleType = vehicleTypeRepository.findByType(rideDTO.getVehicleType());
        newRide.setVehicleType(vehicleType);
        newRide.setPrice(120.0 * rideDTO.getDistanceInKm() + vehicleType.getPricePerKm());
        newRide.setStatus(RideStatus.PENDING);


        return newRide;
    }

    public Ride getRideById(Long id) {
        return rideRepository.findById(id).orElseThrow();
    }

    public Ride getActiveRideForPassenger(Long id) {
        return rideRepository.findByPassengerList_IdAndStatus(id, RideStatus.ACTIVE);
    }
}
