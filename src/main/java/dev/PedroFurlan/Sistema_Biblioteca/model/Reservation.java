    package dev.PedroFurlan.Sistema_Biblioteca.model;

    import dev.PedroFurlan.Sistema_Biblioteca.model.enums.StatusLoan;
    import dev.PedroFurlan.Sistema_Biblioteca.model.enums.StatusReservation;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.time.LocalDate;

    @Entity
    @Table(name="reservations")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Reservation {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @ManyToOne(optional = false)
        @JoinColumn(name = "book_id")
        private Book book;
        @ManyToOne(optional = false)
        @JoinColumn(name = "user_id")
        private User user;
        private LocalDate reservationDate;
        private LocalDate expirationDate;
        private StatusReservation status;

        public Reservation(Book book, User user, LocalDate reservationDate, LocalDate expirationDate, StatusReservation status){
            this.book = book;
            this.user = user;
            this.reservationDate = reservationDate;
            this.expirationDate = expirationDate;
            this.status = status;
        }
    }
