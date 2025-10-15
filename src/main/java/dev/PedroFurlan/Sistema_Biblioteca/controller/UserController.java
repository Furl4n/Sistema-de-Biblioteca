package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.LoginRequest;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.LoginResponse;
import dev.PedroFurlan.Sistema_Biblioteca.model.User;
import dev.PedroFurlan.Sistema_Biblioteca.model.enums.StatusLogin;
import dev.PedroFurlan.Sistema_Biblioteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/new")
    public ResponseEntity<User> addUser(@RequestBody User newUser){
        User user = service.addUser(newUser);
        return new ResponseEntity<> (user, HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<User>> getAll(){
        List<User> users = service.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        //TODO: confirm if the user is logged
        Optional<User> optUser = service.getUser(id);
        if(optUser.isPresent()){
            User user = optUser.get();
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = service.login(loginRequest);

        StatusLogin status = loginResponse.getStatus();
        if(status.equals(StatusLogin.SUCCESS)){
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } else if(status.equals(StatusLogin.INCORRECT_PASSWORD)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if(status.equals(StatusLogin.INCORRECT_USER)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
