package dev.PedroFurlan.Sistema_Biblioteca.service;

import dev.PedroFurlan.Sistema_Biblioteca.model.User;
import dev.PedroFurlan.Sistema_Biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User addUser(User user) {return repository.save(user);}

    public List<User> getAll() {return repository.findAll();}

    public Optional<User> getUser(String email) {return repository.findByEmail(email);}
}
