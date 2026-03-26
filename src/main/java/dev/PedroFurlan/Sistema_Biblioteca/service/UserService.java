package dev.PedroFurlan.Sistema_Biblioteca.service;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.User.UserResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
import dev.PedroFurlan.Sistema_Biblioteca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
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

    public UserResponseDTO getUserDetails(Principal connectedUser) {
        User user = getAuthenticatedUser(connectedUser);

        return new UserResponseDTO(user.getName(), user.getEmail(), user.getRole());
    }

    public void deleteUser(Principal connectedUser) {
        User user = getAuthenticatedUser(connectedUser);

        repository.delete(user);
    }

    public User getAuthenticatedUser(Principal connectedUser) {
        return (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
    }
}

