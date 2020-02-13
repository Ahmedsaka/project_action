package io.medalytics.projectactions.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ActionsNotFoundException extends RuntimeException{
    public ActionsNotFoundException(String s) {
        super(s);
    }

    public ActionsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
