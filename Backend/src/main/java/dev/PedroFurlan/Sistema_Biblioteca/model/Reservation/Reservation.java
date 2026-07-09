    package dev.PedroFurlan.Sistema_Biblioteca.model.Reservation;

    import dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation.AddReservationRequestDTO;
    import dev.PedroFurlan.Sistema_Biblioteca.model.Book.Book;
    import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
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
        private LocalDate dueDate;
        private StatusReservation status;

        public Reservation(Book book, User user, AddReservationRequestDTO requestDTO){
            this.book = book;
            this.user = user;
            this.reservationDate = requestDTO.reservationDate();
            this.expirationDate = requestDTO.expirationDate();
            this.dueDate = requestDTO.returnDate();
            this.status = StatusReservation.RESERVED;
        }
    }
