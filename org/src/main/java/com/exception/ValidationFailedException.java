package com.exception;

public class ValidationFailedException extends RuntimeException
{
    public ValidationFailedException()
    {
        super("Schema Validation Failed.");
    }
}
