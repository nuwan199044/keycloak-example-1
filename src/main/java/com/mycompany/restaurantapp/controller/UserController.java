package com.mycompany.restaurantapp.controller;

import com.mycompany.restaurantapp.dto.RoleDTO;
import com.mycompany.restaurantapp.dto.UserDTO;
import com.mycompany.restaurantapp.util.KeycloakSecurityUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/keycloak")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class UserController {

    private final KeycloakSecurityUtil keycloakSecurityUtil;
    private final ModelMapper modelMapper;

    @Value("${realm}")
    private String realm;

    @GetMapping("/users")
    public ResponseEntity getUsers() {
        List<UserDTO> users = keycloakSecurityUtil.getKeycloakInstance()
                .realm(realm).users().list()
                .stream()
                .map(userRepresentation -> modelMapper.map(userRepresentation, UserDTO.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity getUsers(@PathVariable("userId") String userId) {
        UserDTO user = modelMapper.map(keycloakSecurityUtil.getKeycloakInstance()
                .realm(realm).users().get(userId)
                .toRepresentation(), UserDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody UserDTO user) {
        UserDTO userDTO = keycloakSecurityUtil.getKeycloakInstance()
                .realm(realm).users().create(modelMapper.map(user, UserRepresentation.class))
                .readEntity(UserDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @PutMapping("/user")
    public ResponseEntity updateUser(@RequestBody UserDTO user) {
        UserRepresentation userRepresentation = modelMapper.map(user, UserRepresentation.class);
        keycloakSecurityUtil.getKeycloakInstance().realm(realm).users().get(user.getId()).update(userRepresentation);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/users/{userId}/roles")
    public ResponseEntity getRoles(@PathVariable("userId") String userId) {
        List<RoleDTO> roles = keycloakSecurityUtil.getKeycloakInstance()
                .realm(realm).users().get(userId)
                .roles().realmLevel().listAll().stream().map(role -> modelMapper.map(role, RoleDTO.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }
}
