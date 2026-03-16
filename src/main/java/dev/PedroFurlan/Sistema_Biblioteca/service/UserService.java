package dev.PedroFurlan.Sistema_Biblioteca.service;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.LoginRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.LoginResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.UserResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.AddUserRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.StatusLogin;
import dev.PedroFurlan.Sistema_Biblioteca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UserResponseDTO addUser(AddUserRequestDTO data) {

        User user = new User(data.name(), data.email(), data.password(), data.role());
        User newUser = repository.save(user);

        return new UserResponseDTO(newUser.getName(), newUser.getEmail(), newUser.getRole());
    }

    public List<UserResponseDTO> getAll() {
         List<User> users = repository.findAll();

        return users.stream().map(user -> new UserResponseDTO(user.getName(), user.getEmail(), user.getRole())).toList();
    }

    public UserResponseDTO getUser(Long id) {
        Optional<User> opUser = repository.findById(id);

        if(opUser.isPresent()){
            User user = opUser.get();
            return new UserResponseDTO(user.getName(), user.getEmail(), user.getRole());
        }
        return null; //Temporally
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequest){
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Optional<User> optUser = repository.findByEmail(email);

        //TODO: Change optionals for exceptions
        if (optUser.isPresent()) {
            User user = optUser.get();
            if (user.getPassword().equals(password)) {
                return new LoginResponseDTO(user.getId(),user.getName(),user.getEmail(),user.getRole(),StatusLogin.SUCCESS);
            } else {
                return new LoginResponseDTO(null, null, email,null, StatusLogin.INCORRECT_PASSWORD);
            }
        } else {
            return new LoginResponseDTO(null, null, email, null, StatusLogin.INCORRECT_USER);
        }
    }

    public boolean deleteById(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        } else return false;
    }
}
