package Exceptions;
public class GuestNotFoundException extends Exception {
    private String username;
    public GuestNotFoundException(String username){
        super("Guest data was not found with this username: " + username +
                ", please make sure you entered the right credentials.");
        this.username = username;
    }
}
