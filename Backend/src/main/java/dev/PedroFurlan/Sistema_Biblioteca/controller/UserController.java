package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.User.UserResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get/all")
    public ResponseEntity<List<UserResponseDTO>> getAll(){
        List<UserResponseDTO> response = userService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    public ResponseEntity<UserResponseDTO> getUserDetails(Principal connectedUser){
        UserResponseDTO response = userService.getUserDetails(connectedUser);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteUser(Principal connectedUser){
        userService.deleteUser(connectedUser);

        return ResponseEntity.noContent().build();
    }
}
