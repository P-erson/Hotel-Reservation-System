import java.time.LocalDate;

public abstract class Staff {
    private String username, password;
    private LocalDate dateOfBirth;
    private Role role;
    private int workingHours;

    public Staff(){}

    public Staff(String username, String password, LocalDate dateOfBirth, Role role, int workingHours){
        if (username == null || username.isBlank()){
            // throw empty username exception
        }

        this.username = username;

        if (password == null || password.isBlank()){
            // throw empty password exception
        }

        this.password = password;

        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.workingHours = workingHours;
    }

    public void viewGuests(HotelDatabase database){
        System.out.println(database.guests);
    }

    public void viewRooms(HotelDatabase database){
        System.out.println(database.rooms);
    }

    public void viewReservations(HotelDatabase database){
        System.out.println(database.reservations);
    }



    //Setters & Getters
    public void setUsername(String username){
        if (username == null || username.isBlank()){
            // throw empty username exception
        }

        this.username = username;
    }
    public void setPassword(String password){
        if (password == null || password.isBlank()){
            // throw empty password exception
        }

        this.password = password;
    }
    public void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public void setRole(Role role){
        this.role = role;
    }
    public void setWorkingHours(int workingHours){
        this.workingHours = workingHours;
    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }
    public Role getRole(){
        return role;
    }
    public int getWorkingHours(){
        return workingHours;
    }
}


enum Role {
    ADMIN,
    RECEPTIONIST
}