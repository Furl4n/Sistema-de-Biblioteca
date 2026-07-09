package dev.PedroFurlan.Sistema_Biblioteca.model.Loan;

import dev.PedroFurlan.Sistema_Biblioteca.model.Book.Book;
import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation.Reservation;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "loan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private StatusLoan status;

    public Loan(Book book, User user, LocalDate dueDate, StatusLoan status){
        this.book = book;
        this.user = user;
        this.dueDate = dueDate;
        this.status = status;

    }

    public Loan(Reservation reservation){
        this.book = reservation.getBook();
        this.user = reservation.getUser();
        this.loanDate = LocalDate.now();
        this.dueDate = reservation.getDueDate();
        this.status = StatusLoan.ACTIVE;
    }
}
