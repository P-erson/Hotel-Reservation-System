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

    //added a custom exception incase of a negative balance
    //IMPORTANT NOTE: to be handled in the main method when asking the user to enter a balance by adding
    //a while loop for example, cause throwing and catching the exception in the same method is useless so it must be
    //done in the main
    public void setBalance(double balance) throws InvalidBalanceException {
        if (balance >= 0)
            this.balance = balance;
        else {
            throw new InvalidBalanceException(balance);
        }
    }


    //here the validation is special since enum will actually only accept the choices or left blank
    //so thats why i only added another check to see if it is null

    public void setGender(Gender gender) throws NullPointerException  {
        if(gender == Gender.MALE || gender == Gender.FEMALE){
            this.gender = gender;
        }
        else{
                throw new NullPointerException();

        }
    }


    //Getters

    //adding the getters

    public String getAddress() { return address; }
    public String getRoomPreferences() { return roomPreferences; }
    public double getBalance() { return balance; }
    public Gender getGender() { return gender; }


    
    //adding the constructor in case of a register ONLY

    public Guest(String username, String password, LocalDate dateOfBirth,String address,String roomPreferences, double balance,Gender gender) throws Exception{
        super(username, password, dateOfBirth);
        this.setAddress(address);
        this.setRoomPreferences(roomPreferences);
        this.setBalance(balance);
        this.setGender(gender);

        DATABASE.addGuest(this);
    }






}
