package dev.PedroFurlan.Sistema_Biblioteca.service;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.User.UserResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
import dev.PedroFurlan.Sistema_Biblioteca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<UserResponseDTO> getAll() {
         List<User> users = repository.findAll();

        return users.stream().map(user -> new UserResponseDTO(user.getName(), user.getEmail(), user.getRole())).toList();
    }

    public UserResponseDTO getUser(String id) {
        Optional<User> opUser = repository.findById(id);

        if(opUser.isPresent()){
            User user = opUser.get();
            return new UserResponseDTO(user.getName(), user.getEmail(), user.getRole());
        }
        return null; //Temporally
    }

    public boolean deleteById(String id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        } else return false;
    }
}
