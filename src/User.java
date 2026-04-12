import java.time.LocalDate;

public class User {
    String username, password;
    LocalDate dateOfBirth;

    //validation of username
    // if username is left empty or in other words null
    // first checking if the username was left empty by checking if the variable is still null from the default initiliaztion
    // then checking if the user actually entered blank information using the isBlack method which is already implemented in the string clas
    // if the above is true , it will set the username automatically to unnamed

    public void setUsername(String username) {
        if (username == null || username.isBlank()){
            // will create a custom made exception and throw it here
        } else {
            this.username = username;
        }
    }

    //validation of password
    // if password is left empty or in other words null or less than 8 characters
    // first checking if the password was left empty by checking if the variable is still null from the default initiliaztion
    // then checking if the user actually entered blank information using the isBlack method which is already implemented in the string clas
    // if the above is true , it will set the password automatically set to 8 zeros until get changed later using a setter

    public void setPassword(String password) {
        if (password == null || password.isBlank() || password.length() < 8 ){
            // will create a custom made exception and throw it here
        } else {
            this.password = password;
        }
    }

    //Validating that the date of birth entered is not in the future and not dead otherwise date of birth is automatically set to todays
    //date and to change it later use a setter

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth.isBefore(LocalDate.now()) && dateOfBirth.isAfter(LocalDate.of(1900, 1, 1))){
            this.dateOfBirth = dateOfBirth;
        } else {
            // will create a custom made exception and throw it here
        }
    }

    static HotelDatabase DATABASE = new HotelDatabase();
}
