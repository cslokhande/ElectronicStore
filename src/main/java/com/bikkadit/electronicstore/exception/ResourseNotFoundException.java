package com.bikkadit.electronicstore.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourseNotFoundException extends RuntimeException {
    public ResourseNotFoundException(String Message) {
        super(Message);

    }
}
