package dev.PedroFurlan.Sistema_Biblioteca.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){super("Resource not found");}

    public ResourceNotFoundException(String message){super(message);}
}
