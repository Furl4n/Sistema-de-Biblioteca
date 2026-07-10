package dev.PedroFurlan.Sistema_Biblioteca.DTO.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddUserRequestDTO(@NotBlank(message = "Name is required.")
                                @Size(max = 100, message = "Name must have at most 100 characters.")
                                String name,
                                @NotBlank(message = "Email is required.")
                                @Email(message = "Invalid email.")
                                String email,
                                @NotBlank(message = "Password is required.")
                                @Size(min = 8, max = 255,
                                        message = "Password must contain between 8 and 255 characters.")
                                String password) {
}