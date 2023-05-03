package com.tanerdundar.share5.exceptions;

public class PostException extends RuntimeException{

    public PostException() {
        super("Post not found.");
    }

    public PostException(String message) {
        super(message);
    }

}
