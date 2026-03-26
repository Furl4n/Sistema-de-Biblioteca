package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.User.AddUserRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.User.LoginRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.User.LoginResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.config.JwtService;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
import dev.PedroFurlan.Sistema_Biblioteca.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService JwtService;

    @PostMapping("/singup")
    public ResponseEntity<Void> register(@RequestBody AddUserRequestDTO request){
        authService.singUp(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) throws Exception {
        User user = authService.authenticateUser(request);

        String token = JwtService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
