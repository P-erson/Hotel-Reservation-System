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


    //Setters


    //adding the setters which will also be used in the constructors since validation will be added in the setters
    //and validation is a must in the first initializing ot the object
    //all validation are in the setters


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


    //Getters

    //adding the getters

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getAddress() { return address; }
    public String getRoomPreferences() { return roomPreferences; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public double getBalance() { return balance; }
    public Gender getGender() { return gender; }


    
    //adding the constructor in case of a register ONLY

    public Guest(String username,String password,String address,String roomPreferences,LocalDate dateOfBirth,double balance,Gender gender){
        this.setUsername(username);
        this.setPassword(password);
        this.setAddress(address);
        this.setRoomPreferences(roomPreferences);
        this.setDateOfBirth(dateOfBirth);
        this.setBalance(balance);
        this.setGender(gender);

        DATABASE.addGuest(this);
    }








}
