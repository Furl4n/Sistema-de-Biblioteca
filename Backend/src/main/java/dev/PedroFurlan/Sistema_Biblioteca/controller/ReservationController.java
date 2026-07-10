package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation.AddReservationRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation.ReservationResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
import dev.PedroFurlan.Sistema_Biblioteca.service.ReservationService;
import dev.PedroFurlan.Sistema_Biblioteca.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Reservations", description = "Reservations management endpoints")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;

    @Operation(
            summary = "Create a reservation by ID",
            description = "Create a reservation by ID in the library."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Reservation created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(@Valid @RequestBody AddReservationRequestDTO newReservation, Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        ReservationResponseDTO response = reservationService.addReservation(newReservation, user);

        URI uri = URI.create("/reservations/" + response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @Operation(
            summary = "Find a reservation by ID",
            description = "Find a reservation in the library by ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reservation found successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Long id, Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        ReservationResponseDTO response = reservationService.getById(id, user);

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Find a reservation by user",
            description = "Find a reservation in the library by user."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reservation found successfully")
    })
    @GetMapping("/user")
    public ResponseEntity<List<ReservationResponseDTO>> getReservationByUserId(Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        List<ReservationResponseDTO> response = reservationService.getByUserId(user);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Cancel a reservation by ID",
            description = "Cancel a reservation in the library by ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reservation canceled successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ReservationResponseDTO> cancelReservation(@PathVariable Long id, Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        ReservationResponseDTO response = reservationService.cancelReservation(id, user);

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Delete a reservation by ID",
            description = "Find a reservation by ID in the library."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reservation returned successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id, Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        reservationService.deleteById(id, user);
        return ResponseEntity.noContent().build();
    }
}
