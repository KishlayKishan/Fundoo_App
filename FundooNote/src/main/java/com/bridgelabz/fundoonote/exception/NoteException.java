package com.bridgelabz.fundoonote.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;

    public NoteException(String no_need_to_pin_again) {

    }
}
