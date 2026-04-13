package Exceptions;

public class RoomDoesNotExistException extends Exception{
    private int roomNumber;

    public RoomDoesNotExistException(int roomNumber){
        super("Invalid room number entered: " + roomNumber);
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber(){
        return roomNumber;
    }
}
