package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.User.UserResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponseDTO> getUserDetails(Principal connectedUser){
        UserResponseDTO response = userService.getUserDetails(connectedUser);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteUser(Principal connectedUser){
        userService.deleteUser(connectedUser);

        return ResponseEntity.noContent().build();
    }
}
