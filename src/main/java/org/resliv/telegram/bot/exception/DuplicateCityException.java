package org.resliv.telegram.bot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class DuplicateCityException extends RuntimeException {
    public DuplicateCityException(String message){
        super(message);
    }
}
