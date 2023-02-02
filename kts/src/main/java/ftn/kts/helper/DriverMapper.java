package ftn.kts.helper;

import ftn.kts.dtos.DriverDTO;
import ftn.kts.dtos.UserDTO;
import ftn.kts.dtos.VehicleDTO;
import ftn.kts.model.Driver;
import ftn.kts.model.User;
import ftn.kts.model.Vehicle;
import ftn.kts.model.VehicleType;

public class DriverMapper implements MapperInterface<Driver, DriverDTO> {

    @Override
    public Driver toEntity(DriverDTO dto) {
        Driver user = new Driver();
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAddress(dto.getAddress());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setProfilePicture(dto.getProfilePicture());
        Vehicle newVehicle = new Vehicle();
        newVehicle.setModel(dto.getVehicle().getModel());
        newVehicle.setPlateNumber(dto.getVehicle().getPlateNumber());
        newVehicle.setNumberOfSeats(dto.getVehicle().getNumberOfSeats());
        newVehicle.setBabyFlag(dto.getVehicle().isBabyFlag());
        newVehicle.setPetsFlag(dto.getVehicle().isPetsFlag());
        newVehicle.setVehicleType(new VehicleType(dto.getVehicle().getVehicleType()));
        user.setVehicle(newVehicle);
        return user;
    }

    @Override
    public DriverDTO toDto(Driver entity) {
        DriverDTO dto = new DriverDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setProfilePicture(entity.getProfilePicture());
        dto.setVehicle(
                new VehicleDTO(entity.getVehicle().getId(), entity.getVehicle().getModel(),
                        entity.getVehicle().getPlateNumber(), entity.getVehicle().getVehicleType().getType(),
                        entity.getVehicle().getNumberOfSeats(), entity.getVehicle().isBabyFlag(),
                        entity.getVehicle().isPetsFlag()));
        dto.setRole(entity.getUserType());

        return dto;
    }
}
