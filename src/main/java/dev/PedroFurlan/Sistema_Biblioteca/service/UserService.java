package dev.PedroFurlan.Sistema_Biblioteca.service;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.LoginRequest;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.LoginResponse;
import dev.PedroFurlan.Sistema_Biblioteca.model.User;
import dev.PedroFurlan.Sistema_Biblioteca.model.enums.StatusLogin;
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

    public Optional<User> getUser(Long id) {return repository.findById(id);}

    public LoginResponse login(LoginRequest loginRequest){
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Optional<User> optUser = repository.findByEmail(email);

        if (optUser.isPresent()) {
            User user = optUser.get();
            if (user.getPassword().equals(password)) {
                return new LoginResponse(user.getId(),user.getName(),user.getEmail(),user.getRole(),StatusLogin.SUCCESS);
            } else {
                return new LoginResponse(null, null, email,null, StatusLogin.INCORRECT_PASSWORD);
            }
        } else {
            return new LoginResponse(null, null, email, null, StatusLogin.INCORRECT_USER);
        }
    }
}
