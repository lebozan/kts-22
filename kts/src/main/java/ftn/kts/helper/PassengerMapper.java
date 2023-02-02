package ftn.kts.helper;

import ftn.kts.dtos.UserDTO;
import ftn.kts.model.Passenger;

public class PassengerMapper implements MapperInterface<Passenger, UserDTO> {
    @Override
    public Passenger toEntity(UserDTO dto) {
        Passenger p = new Passenger();
        p.setFirstName(dto.getFirstName());
        p.setLastName(dto.getLastName());
        p.setEmail(dto.getEmail());
        p.setAddress(dto.getAddress());
        p.setPhoneNumber(dto.getPhoneNumber());
        p.setProfilePicture(dto.getProfilePicture());

        return p;
    }

    @Override
    public UserDTO toDto(Passenger entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setProfilePicture(entity.getProfilePicture());
        dto.setRole(entity.getUserType());
        return dto;
    }
}
