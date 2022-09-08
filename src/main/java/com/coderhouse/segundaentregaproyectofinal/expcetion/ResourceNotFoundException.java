package com.coderhouse.segundaentregaproyectofinal.expcetion;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
