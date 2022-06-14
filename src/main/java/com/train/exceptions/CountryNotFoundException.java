package com.train.exceptions;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(int id) {
        super(String.format("Country with Id %d not found", id));
    }

}