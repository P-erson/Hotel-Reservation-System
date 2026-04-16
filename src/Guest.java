import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import Exceptions.GuestNotFoundException;
import Exceptions.InvalidBalanceException;
import Exceptions.InvalidPaymentException;
import Exceptions.RoomNotAvailableException;

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


    // this method returns a Guest object including all the guest info.
    //since in 2nd milestone when we implement this
    //we want the system to show the info of this specific guest
    public Guest login(String username, String password) throws GuestNotFoundException
    {
        if(DATABASE.searchGuest(username, password) == null)
            throw new GuestNotFoundException(username);
        else
            return DATABASE.searchGuest(username, password);

    }

    public ArrayList<Room> viewAvailableRooms()
    {
        ArrayList<Room> availableRooms = new ArrayList<Room>();
        for(int i = 0; i< DATABASE.getRooms().size(); i++ )
        {
            if(DATABASE.getRooms().get(i).isAvailable())
            {
              availableRooms.add(DATABASE.getRooms().get(i) );
            }
        }
        return availableRooms;
    }

    public Reservation makeReservation(Room room, LocalDate checkIn, LocalDate checkOut) throws RoomNotAvailableException
    {
        if(room.isAvailable())
        {
            Reservation reservation = new Reservation(this, room, checkIn, checkOut);
            DATABASE.addReservation(reservation);
            return reservation;
        }
        else
           throw new RoomNotAvailableException(room.getRoomNumber());
    }

    public ArrayList<Reservation> viewReservations()
    {
        ArrayList<Reservation> viewReservationsArray = new ArrayList<Reservation>();
        for(int i = 0; i< DATABASE.getReservations().size(); i++ )
        {
            if(DATABASE.getReservations().get(i).getGuest().getUsername().equals(this.getUsername()) )
            {
                viewReservationsArray.add(DATABASE.getReservations().get(i));
            }
        }
        return viewReservationsArray;
    }

    public void cancelReservation(int roomNumber){
        ArrayList<Reservation> viewReservationsArray = DATABASE.getReservations();
        for(int i = 0; i< viewReservationsArray.size(); i++ )
        {
            if(viewReservationsArray.get(i).getGuest().getUsername().equals(this.getUsername()) &&
                    viewReservationsArray.get(i).getRoom().getRoomNumber() == roomNumber)
            {
                viewReservationsArray.get(i).cancelReservation();
                return;
            }
        }
    }

    public Invoice checkout(int roomNumber, Invoice.PaymentMethod paymentMethod) throws InvalidPaymentException, InvalidBalanceException {
        ArrayList<Reservation> viewReservationsArray = DATABASE.getReservations();
        Reservation reservation = new Reservation();
        for(int i = 0; i< viewReservationsArray.size(); i++ )
        {
            if(viewReservationsArray.get(i).getGuest().getUsername().equals(this.getUsername()) &&
                    viewReservationsArray.get(i).getRoom().getRoomNumber() == roomNumber)
            {
                reservation = viewReservationsArray.get(i);
            }
        }

        double total = ChronoUnit.DAYS.between(reservation.getCheckInDate(), reservation.getCheckOutDate()) *
                reservation.getRoom().getPricePerNight();

        if(this.getBalance() < total){
            throw new InvalidPaymentException(total, this.getBalance());
        }

        Invoice invoice = new Invoice(total, LocalDate.now());
        invoice.addPayment(paymentMethod);

        this.setBalance(this.getBalance() - total);
        reservation.setStatus(Reservation.ReservationStatus.COMPLETED);
        reservation.getRoom().setAvailable(true);
        DATABASE.addInvoices(invoice);
        return invoice;


    }









}
