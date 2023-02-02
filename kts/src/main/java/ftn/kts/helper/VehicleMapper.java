package ftn.kts.helper;

import ftn.kts.dtos.VehicleDTO;
import ftn.kts.model.Vehicle;
import ftn.kts.model.VehicleType;

public class VehicleMapper implements MapperInterface<Vehicle, VehicleDTO> {
    @Override
    public Vehicle toEntity(VehicleDTO dto) {
        Vehicle v = new Vehicle();
        v.setModel(dto.getModel());
        v.setPetsFlag(dto.isPetsFlag());
        v.setBabyFlag(dto.isBabyFlag());
        v.setNumberOfSeats(dto.getNumberOfSeats());
        v.setPlateNumber(dto.getPlateNumber());
        v.setVehicleType(new VehicleType(dto.getVehicleType()));

        return v;
    }

    @Override
    public VehicleDTO toDto(Vehicle entity) {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(entity.getId());
        dto.setVehicleType(entity.getVehicleType().getType());
        dto.setModel(entity.getModel());
        dto.setBabyFlag(entity.isBabyFlag());
        dto.setPetsFlag(entity.isPetsFlag());
        dto.setPlateNumber(entity.getPlateNumber());
        dto.setNumberOfSeats(entity.getNumberOfSeats());
        return null;
    }
}
