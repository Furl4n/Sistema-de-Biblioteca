package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.User.AddUserRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.User.LoginRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.User.LoginResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.infra.config.JwtService;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
import dev.PedroFurlan.Sistema_Biblioteca.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Auth management endpoints")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @Operation(
            summary = "Register a new user",
            description = "Register a new user in the library."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping("/signup")
    public ResponseEntity<Void> register(@Valid @RequestBody AddUserRequestDTO request){
        authService.signUp(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Login a user",
            description = "Login a user in the library."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User connected successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        User user = authService.authenticateUser(request);

        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
