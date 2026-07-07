package dev.PedroFurlan.Sistema_Biblioteca.exception;

public class BusinessRuleException extends RuntimeException{
    public BusinessRuleException(){super("Business rule broken");}

    public BusinessRuleException(String message){super(message);}
}
