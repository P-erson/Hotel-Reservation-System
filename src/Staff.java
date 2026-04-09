import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Staff {
    private String username, password;
    private LocalDate dateOfBirth;
    private Role role;
    private int workingHours;

    public Staff() {}

    public Staff(String username, String password, LocalDate dateOfBirth, Role role, int workingHours){
        this.setUsername(username);
        this.setPassword(password);
        this.setDateOfBirth(dateOfBirth);
        this.setRole(role);
        this.setWorkingHours(workingHours);
    }

    static HotelDatabase DATABASE = new HotelDatabase();

    public ArrayList<Guest> viewGuests(HotelDatabase database){ return database.getGuests(); }
    public ArrayList<Room> viewRooms(HotelDatabase database){ return database.getRooms(); }
    public ArrayList<Reservation> viewReservations(HotelDatabase database){ return database.getReservations(); }



    //Setters
    public void setUsername(String username){
        if (username == null || username.isBlank()){
            // throw empty username exception
        } else {
            this.username = username;
        }
    }
    public void setPassword(String password){
        if (password == null || password.isBlank()){
            // throw empty password exception
        } else {
            this.password = password;
        }
    }
    public void setDateOfBirth(LocalDate dateOfBirth){
        if (dateOfBirth.isAfter(LocalDate.of(1952, 2, 6))){
            this.dateOfBirth = dateOfBirth;
        } else {
            // throw invalid date exception
        }
    }
    public void setRole(Role role){
        if (role == Role.ADMIN || role == Role.RECEPTIONIST){
            this.role = role;
        } else {
            // throw invalid role exception
        }
    }
    public void setWorkingHours(int workingHours){
        if (workingHours <= 12){
            this.workingHours = workingHours;
        } else {
            // throw maximum shift exceeded exception
        }
        
    }

    //Getters
    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
    public LocalDate getDateOfBirth(){ return dateOfBirth; }
    public Role getRole(){ return role; }
    public int getWorkingHours(){ return workingHours; }
}


enum Role {
    ADMIN,
    RECEPTIONIST
}