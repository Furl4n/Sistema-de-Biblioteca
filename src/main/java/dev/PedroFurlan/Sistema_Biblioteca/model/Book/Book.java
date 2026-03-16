package dev.PedroFurlan.Sistema_Biblioteca.model.Book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private int year;
    private String genre;
    private StatusBook status;

    @PrePersist
    protected void onCreate(){
        status = StatusBook.AVAILABLE;
    }
}