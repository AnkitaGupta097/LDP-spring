package org.example.crudoperations.exceptions;


public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(){ super("User alerady registered"); }

}

