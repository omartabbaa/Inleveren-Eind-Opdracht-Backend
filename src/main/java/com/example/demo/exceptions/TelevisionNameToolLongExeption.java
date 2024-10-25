package com.example.demo.exceptions;

public class TelevisionNameToolLongExeption extends RuntimeException{


    public TelevisionNameToolLongExeption(String message){
        super(message);
    }

    public TelevisionNameToolLongExeption(){
        super();
    }
}
