package dev.PedroFurlan.Sistema_Biblioteca.DTO;

import dev.PedroFurlan.Sistema_Biblioteca.model.User.Role;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.StatusLogin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private Long id;
    private String name;
    private String Email;
    private Role role;
    private StatusLogin status;
}
