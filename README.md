## Library Management System

Simple system for library management, initially created for a **college project**, but later transformed into a **personal project** to learn more about Java development and Spring Boot.

### Project Evolution

The initial version was built using just **Plain Java** and ran in the terminal. You can find it in the commit named ***'Versão final (trabalho)'*** or release ***'V1.0.0'***.

After this version, the project was refactored to use Spring Boot and connected to a PostgreSQL database.

### Commit Changes

* Added models and methods:
    * All
      * delete by Id
    * Loan (new):
      * newLoan
      * getAll
      * getLoanById
      * getLoansByUserId
    * Reservation (new):
      * newReservation
      * getAll
      * getReservationById
      * getReservationByUserId
      * reservationToLoan


* Added DTOs for reservation and loan request