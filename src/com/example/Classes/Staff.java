package com.example.Classes;

import com.example.Exceptions.InvalidRole;
import com.example.Exceptions.WorkingHoursOutOfRangeException;

import java.time.LocalDate;
import java.util.ArrayList;



public abstract class Staff extends User{
    private Role role;
    private int workingHours;

    // public Staff() {}

    public Staff(String username, String password, LocalDate dateOfBirth, Role role, int workingHours) throws Exception{
        super(username, password, dateOfBirth);
        this.setRole(role);
        this.setWorkingHours(workingHours);
    }


    public ArrayList<Guest> viewGuests(HotelDatabase database){ return database.getGuests(); }
    public ArrayList<Room> viewRooms(HotelDatabase database){ return database.getRooms(); }
    public ArrayList<Reservation> viewReservations(HotelDatabase database){ return database.getReservations(); }



    //Setters
    public void setRole(Role role) throws InvalidRole {
        if (role == Role.ADMIN || role == Role.RECEPTIONIST){
            this.role = role;
        } else {
            throw new InvalidRole(role);
        }
    }
    public void setWorkingHours(int workingHours) throws WorkingHoursOutOfRangeException {
        if (workingHours <= 12 && workingHours > 0){
            this.workingHours = workingHours;
        } else {
            throw new WorkingHoursOutOfRangeException(workingHours);
        }
        
    }


    public void setUserType(String userType) throws Exception{
        super.setUserType(userType);
    }

    //Getters
    public Role getRole(){ return role; }
    public int getWorkingHours(){ return workingHours; }
}


enum Role {
    ADMIN,
    RECEPTIONIST
}