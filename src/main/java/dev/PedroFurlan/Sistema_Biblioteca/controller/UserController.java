package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.LoginRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.LoginResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.UserResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.AddUserRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.StatusLogin;
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

    @PostMapping("/new")
    public ResponseEntity<UserResponseDTO> newUser(@RequestBody AddUserRequestDTO data){
        UserResponseDTO response = service.addUser(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<UserResponseDTO>> getAll(){
        List<UserResponseDTO> response = service.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id){
        UserResponseDTO response = service.getUser(id);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest){
        LoginResponseDTO response = service.login(loginRequest);

        StatusLogin status = response.getStatus();
        if(status.equals(StatusLogin.SUCCESS)){
            return ResponseEntity.ok(response);
        } else if(status.equals(StatusLogin.INCORRECT_PASSWORD)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if(status.equals(StatusLogin.INCORRECT_USER)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id){
        if(service.deleteById(id)) return ResponseEntity.notFound().build();
        else return ResponseEntity.noContent().build();
    }
}
