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

* DTO:
  * Changed UserId from Long to String.
  * Changed login response from Users details to JWT token.
* Spring Boot Security:
  * Added ApplicationConfig and SecurityConfig to protect the application and control requests. 
  * Now the User need to be authenticated using JWT token to make requests.
* JWT:
  * Added the JwtService e JwtAuthenticationFilter to create and confirm if the token is valid.
* Auth:
  * Added AuthController and AuthService for registration and login methods.
* User:
  * Changed the Id from int to char(54) and started using UUID for Id.
  * Removed the registration and login methods from UserService (now in Auth).
