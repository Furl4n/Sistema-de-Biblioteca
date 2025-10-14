package dev.PedroFurlan.Sistema_Biblioteca.model;

import dev.PedroFurlan.Sistema_Biblioteca.model.enums.StatusBook;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private int year;
    private String genre;
    private StatusBook status;
}