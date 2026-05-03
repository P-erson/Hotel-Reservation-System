package com.example.Classes;

import com.example.Exceptions.UserNotFoundException;
import com.example.Exceptions.InvalidDateOfBirthException;
import com.example.Exceptions.InvalidPasswordException;
import com.example.Exceptions.InvalidUsernameException;

import java.time.LocalDate;
import java.util.ArrayList;


enum UserTypes{
    GUEST,
    ADMIN,
    RECEPTIONIST
}

public class User {
    private String username, password;
    private LocalDate dateOfBirth;
    private UserTypes userType;

    public User() {}

    //added a custom-made exception to be handled in the main incase of an invalid username entered such as empty username ( null ya3ny)
    public void setUsername(String username) throws InvalidUsernameException {
        if (username == null || username.isBlank()){
            throw new InvalidUsernameException(username);
        } else {
            this.username = username;
        }
    }

    //added a custom-made exception to be handled in the main incase of an invalid password entered such as less characters than required or
    //not all literals are there , TO BE HANDLED IN THE MAIN USING A LOOP MOST PROBABLY SINCE CANNOT BE HANDLED HERE , ALSO DO NOT FORGET TO ADD THE CATCH WITH THE LOOP
    //SO THAT ANY OF THIS MATTERS
    public void setPassword(String password) throws InvalidPasswordException {
        if (password == null || password.isBlank() || password.length() < 8 ){
           throw new InvalidPasswordException(password);
        } else {
            this.password = password;
        }
    }

    public void setDateOfBirth(LocalDate dateOfBirth) throws InvalidDateOfBirthException {
        if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now()) && dateOfBirth.isBefore(LocalDate.of(1900, 1, 1))){
            throw new InvalidDateOfBirthException(dateOfBirth);
        } else {
            this.dateOfBirth = dateOfBirth;
        }
    }

    public void setUserType(String userType) throws Exception {
        if (userType == null) {
            throw new Exception("User type cannot be null");
        }
        switch (userType) {
            case "GUEST":
                this.userType = UserTypes.GUEST;
                break;
            case "ADMIN":
                this.userType = UserTypes.ADMIN;
                break;
            case "RECEPTIONIST":
                this.userType = UserTypes.RECEPTIONIST;
                break;
            default:
                throw new Exception(userType);
        }
    }


    //adding a constructor
    public User(String username, String password , LocalDate dateOfBirth) throws Exception{
        this.setUsername(username);
        this.setPassword(password);
        this.setDateOfBirth(dateOfBirth);
    }

    //adding the getters

    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public LocalDate getDateOfBirth() {return dateOfBirth;}
    public UserTypes getUserType() {return userType;}


    // this method returns a Guest object including all the guest info.
    //since in 2nd milestone when we implement this
    //we want the system to show the info of this specific guest
    public static User login(String username, String password) throws UserNotFoundException
    {
        User user = HotelDatabase.instance.searchUser(username, password);
        if(user == null)
            throw new UserNotFoundException(username);
        else
            return user;

    }

    public static ArrayList<Room> viewAvailableRooms()
    {
        ArrayList<Room> availableRooms = new ArrayList<Room>();
        for(int i = 0; i < HotelDatabase.instance.getRooms().size(); i++ )
        {
            Room room = HotelDatabase.instance.getRooms().get(i);
            if(room.isAvailable())
            {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

}
