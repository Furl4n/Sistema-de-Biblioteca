package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.User.UserResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
import dev.PedroFurlan.Sistema_Biblioteca.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Users", description = "User management endpoints")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Return user details",
            description = "Return user details in library."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User details returned successfully"),
    })
    @GetMapping
    public ResponseEntity<UserResponseDTO> getUserDetails(Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        UserResponseDTO response = userService.getUserDetails(user);

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Delete a user",
            description = "Delete a user in the library."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted successfully")
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        userService.deleteUser(user);

        return ResponseEntity.noContent().build();
    }
}
