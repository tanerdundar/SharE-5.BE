package com.tanerdundar.share5.exceptions;

public class FollowException extends RuntimeException{
    public FollowException() {
        super("Follow not found.");
    }
    public FollowException(String message) {
        super(message);
    }
}
