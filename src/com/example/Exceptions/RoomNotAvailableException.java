package com.example.Exceptions;
public class RoomNotAvailableException extends Exception {
    public RoomNotAvailableException(int roomNumber){
        super("Room number " + roomNumber + " is not available.");
    }

}
