package dev.PedroFurlan.Sistema_Biblioteca.service;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.AddUserRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.LoginRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
import dev.PedroFurlan.Sistema_Biblioteca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public void singUp(AddUserRequestDTO request) {
        Optional<User> opUser = userRepository.findByEmail(request.email());

        if(opUser.isEmpty()){
            User user = new User(
                    request.name(),
                    request.email(),
                    passwordEncoder.encode(request.password()),
                    request.role()
            );

            userRepository.save(user);
        }
        //return error
    }


    public User authenticateUser(LoginRequestDTO request){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        return userRepository.findByEmail(request.getEmail()).orElseThrow(RuntimeException::new); //change exception
    }
}
