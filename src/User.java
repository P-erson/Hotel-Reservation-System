import java.time.LocalDate;

import Exceptions.InvalidDateOfBirthException;
import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;

public class User {
    private String username, password;
    private LocalDate dateOfBirth;

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
    public void setPassword(String password) throws InvalidPasswordException{
        if (password == null || password.isBlank() || password.length() < 8 ){
           throw new InvalidPasswordException(password);
        } else {
            this.password = password;
        }
    }

    //Validating that the date of birth entered is not in the future and not dead otherwise date of birth is automatically set to todays
    //date and to change it later use a setter


    //added a custom-made exception to be handled in the main incase of an invalid dob
    // TO BE HANDLED IN THE MAIN USING A LOOP MOST PROBABLY SINCE CANNOT BE HANDLED HERE , ALSO DO NOT FORGET TO ADD THE CATCH WITH THE LOOP
    //SO THAT ANY OF THIS MATTERS
    public void setDateOfBirth(LocalDate dateOfBirth) throws InvalidDateOfBirthException {
        if (dateOfBirth.isBefore(LocalDate.now()) && dateOfBirth.isAfter(LocalDate.of(1900, 1, 1))){
            this.dateOfBirth = dateOfBirth;
        } else {
            throw new InvalidDateOfBirthException(dateOfBirth);
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


    static HotelDatabase DATABASE;

    static {
        try {
            DATABASE = new HotelDatabase();
        } catch(Exception e) {
            System.out.println("Exception caused in Database: " + e);
        }
    }
}
