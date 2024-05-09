package com.mycompany.restaurantapp.mapper;

import com.mycompany.restaurantapp.dto.UserDTO;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public List<UserDTO> mapUserRepresentationsToUsers(List<UserRepresentation> userRepresentationList) {
        return userRepresentationList.stream().map(userRepresentation -> {
            UserDTO user = new UserDTO();
            user.setId(userRepresentation.getId());
            user.setEmail(userRepresentation.getEmail());
            user.setUsername(userRepresentation.getUsername());
            user.setFirstName(userRepresentation.getFirstName());
            user.setEmailVerified(userRepresentation.isEmailVerified());
            return user;
        }).toList();
    }
}
