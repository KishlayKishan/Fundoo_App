package com.bridgelabz.fundoonote.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FunDoNotesCustomException extends RuntimeException{

    private HttpStatus httpStatus;
    private String message;
}
