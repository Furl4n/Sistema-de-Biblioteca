package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.model.User;
import dev.PedroFurlan.Sistema_Biblioteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/newUser")
    public ResponseEntity<User> addUser(@RequestBody User newUser){
        User user = service.addUser(newUser);
        return new ResponseEntity<> (user, HttpStatus.CREATED);
    }

    @GetMapping("/get/All")
    public ResponseEntity<List<User>> getAll(){
        List<User> users = service.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<User> getUser(@PathVariable String email){
        Optional<User> optUser = service.getUser(email);
        if(optUser.isPresent()){
            User user = optUser.get();
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
