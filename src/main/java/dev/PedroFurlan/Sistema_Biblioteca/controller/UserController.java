package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.UserResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/get/all")
    public ResponseEntity<List<UserResponseDTO>> getAll(){
        List<UserResponseDTO> response = service.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String id){
        UserResponseDTO response = service.getUser(id);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String id){
        if(service.deleteById(id)) return ResponseEntity.notFound().build();
        else return ResponseEntity.noContent().build();
    }
}
