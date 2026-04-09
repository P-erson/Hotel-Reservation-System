import java.time.LocalDate;

public class Guest extends User{

    //initializing the required attributes

    private String address, roomPreferences;
    private double balance;
    private Gender gender;


    //initializing the gender enum required
    public enum Gender{
        MALE,FEMALE;
    }

    //adding the setters which will also be used in the constructors since validation will be added in the setters
    //and validation is a must in the first initializing ot the object
    //all validation are in the setters

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

    public void setAddress(String address) {
        if (address == null || address.isBlank()){
            this.address = "no current address";
        } else {
            this.address = address;
        }
    }

    public void setRoomPreferences(String roomPreferences) {
        if (roomPreferences == null || roomPreferences.isBlank()){
            this.roomPreferences = "no current room preference";
        } else {
            this.roomPreferences = roomPreferences;
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

    //validation for non-negative balance
    //if the balance entered is negative, it will be automatically be a 0 balance

    public void setBalance(double balance) {
        if( balance >= 0) {
            this.balance = balance;
        }
        else {
            // will create a custom made exception and throw it here
        }
    }

    //here the validation is special since enum will actually only accept the choices or left blank
    //so thats why i only added another check to see if it is null

    public void setGender(Gender gender) {
        if(gender == Gender.MALE || gender == Gender.FEMALE){
            this.gender = gender;
        }
        else{
            if(gender == null) {
                // will create a custom made exception and throw it here
            }
        }
    }


    //adding the getters

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getRoomPreferences() {
        return roomPreferences;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public double getBalance() {
        return balance;
    }

    public Gender getGender() {
        return gender;
    }


    //adding the constructor in case of a register ONLY

    public Guest(String username,String password,String address,String roomPreferences,LocalDate dateOfBirth,double balance,Gender gender){
        this.setUsername(username);
        this.setPassword(password);
        this.setAddress(address);
        this.setRoomPreferences(roomPreferences);
        this.setDateOfBirth(dateOfBirth);
        this.setBalance(balance);
        this.setGender(gender);

    }








}
