package com.conference.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    long fieldValue;

    public ResourceNotFoundException(String resourceName, String fielsName, long fieldValue) {
        super(String.format("%s not found with %s:%s", resourceName, fielsName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fielsName;
        this.fieldValue = fieldValue;
    }
}
