package com.enigmacamp.hellospring.exception;

//Dengan anotasi Response Status, Error response akan dibuatkan secara otomatis sesuai dengan http status nya
//Lalu apabil tidak diberikan anotasi apakah, aplikasi akan crash ? Tentu tidak, spring akan tetap memberikan
// error response, cuma secara default akan diberikan status 500
//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Data is not found");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
