package com.marom.restfulwebserivces.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String s) {
        super(s);
    }
}
