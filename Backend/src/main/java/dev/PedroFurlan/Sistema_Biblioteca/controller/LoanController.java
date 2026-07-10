package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Loan.AddLoanRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.Loan.LoanResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
import dev.PedroFurlan.Sistema_Biblioteca.service.LoanService;
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
@RequestMapping("/loans")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Loan", description = "Loans management endpoints")
public class LoanController {

    private final LoanService loanService;
    private final UserService userService;

    @Operation(
            summary = "Create a new loan",
            description = "Creates a new loan in the library."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Loan created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @PostMapping
    public ResponseEntity<LoanResponseDTO> createLoan(@Valid @RequestBody AddLoanRequestDTO data, Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        LoanResponseDTO response = loanService.addLoan(data, user);

        URI uri = URI.create("/loans/" + response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @Operation(
            summary = "Convert reservation to loan",
            description = "Creates a new loan by a reservation in the library."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Loan created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    @PostMapping("/reservation/{id}")
    public ResponseEntity<LoanResponseDTO> convertReservationToLoan(@PathVariable Long id, Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        LoanResponseDTO response = loanService.convertReservationToLoan(id, user);

        URI location = URI.create("/loans/" + response.id());

        return ResponseEntity.created(location).body(response);
    }

    @Operation(
            summary = "Find a Loan by ID",
            description = "Find a loan by ID in the library."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Loan found successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Loan not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDTO> getLoanById(@PathVariable Long id, Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        LoanResponseDTO response = loanService.getById(id, user);

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Find loans by user",
            description = "find a loan by user in the library."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Loan found successfully")
    })
    @GetMapping("/user")
    public ResponseEntity<List<LoanResponseDTO>> getLoansByUserId(Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        List<LoanResponseDTO> response = loanService.getByUserId(user);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Return a loan by ID",
            description = "Return a loan by ID in the library."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Loan returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Loan not found")
    })
    @PatchMapping("/{id}/return")
    public ResponseEntity<LoanResponseDTO> returnLoan(@PathVariable Long id, Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        LoanResponseDTO response = loanService.returnLoan(id, user);

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Delete a loan by ID",
            description = "Delete a loan by ID in the library."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Loan deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Loan not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id, Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        loanService.deleteById(id, user);

        return ResponseEntity.noContent().build();
    }
}