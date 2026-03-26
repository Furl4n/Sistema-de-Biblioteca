## Library Management System

Simple system for library management, initially created for a **college project**, but later transformed into a **personal project** to learn more about Java development and Spring Boot.

### Project Evolution

The initial version was built using just **Plain Java** and ran in the terminal. You can find it in the commit named ***'Versão final (trabalho)'*** or release ***'V1.0.0'***.

After this version, the project was refactored to use Spring Boot and connected to a PostgreSQL database.

## Padrão de Commits

Format:

```
[TYPE] - short description
```

Types:

* `[ADD]` New functionality
* `[FIX]` Bug fix
* `[UPD]` Improvement
* `[CFG]` Configuration
* `[DEL]` Delete

### Commit Changes

* User:
  * Added method to authenticate user using Principal
* DTOs:
  * Removed the UserId from requests
* Services:
  * Started using the new method to get the user