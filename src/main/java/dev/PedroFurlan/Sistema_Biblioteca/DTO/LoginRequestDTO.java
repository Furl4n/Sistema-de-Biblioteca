package dev.PedroFurlan.Sistema_Biblioteca.DTO;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
}
