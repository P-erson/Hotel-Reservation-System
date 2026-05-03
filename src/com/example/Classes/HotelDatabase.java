package com.example.Classes;

import java.time.LocalDate;
import java.util.ArrayList;
public class HotelDatabase {
    // room types
    public RoomType ROOM_TYPES = new RoomType();

    // amenties
    private final ArrayList<Amenity> amenities = new ArrayList<Amenity>();

    public ArrayList<Amenity> getAmenities() { return amenities; }
    public void addAmenity(Amenity amenity) { amenities.add(amenity); }
    public void removeAmenity(Amenity amenity) { amenities.remove(amenity);}
    public void setAmenity(int index, Amenity amenity) { amenities.set(index, amenity); }

    
    // guests
    private final ArrayList<Guest> guests = new ArrayList<Guest>();

    public ArrayList<Guest> getGuests() { return guests; }
    public void addGuest(Guest guest) {
        guests.add(guest);
        users.add(guest);
    }
    public void removeGuest(Guest guest) { guests.remove(guest); }
    public void setGuest(int index, Guest guest) { guests.set(index, guest); }



    // rooms
    private final ArrayList<Room> rooms = new ArrayList<Room>();

    public ArrayList<Room> getRooms() { return rooms; }
    public void addRoom(Room room) { rooms.add(room); }
    public void removeRoom(Room room) { rooms.remove(room); }
    public void setRoom(int index, Room room) { rooms.set(index, room); }

    
    // reservations
    private final ArrayList<Reservation> reservations = new ArrayList<Reservation>();

    public ArrayList<Reservation> getReservations() { return reservations; }
    public void addReservation(Reservation reservation) { reservations.add(reservation); }
    public void removeReservation(Reservation reservation) { reservations.remove(reservation); }
    public void setReservation(int index, Reservation reservation) { reservations.set(index, reservation); }
    
    
    // invoice
    private final ArrayList<Invoice> invoices = new ArrayList<Invoice>();

    public ArrayList<Invoice> getInvoices() { return invoices; }
    public void addInvoices(Invoice invoice) { invoices.add(invoice); }
    public void removeInvoice(Invoice invoice) { invoices.remove(invoice); }
    public void setInvoice(int index, Invoice invoice) { invoices.set(index, invoice); }


    //admins
    private final ArrayList<Admin> admins = new ArrayList<Admin>();

    public ArrayList<Admin> getAdmins() {return admins;}
    public void addAdmins(Admin admin) {
        admins.add(admin);
        users.add(admin);
    }
    public void removeAdmins(Admin admin) { admins.remove(admin); }
    public void setAdmin(int index, Admin admin) { admins.set(index, admin); }


    //receptionist
    private final ArrayList<Receptionist> receptionists = new ArrayList<Receptionist>();

    public ArrayList<Receptionist> getReceptionists() {return receptionists;}
    public void addReceptionist(Receptionist receptionist) {
        receptionists.add(receptionist);
        users.add(receptionist);
    }
    public void removeReceptionist(Receptionist receptionist) { receptionists.remove(receptionist); }
    public void setReceptionist(int index, Receptionist receptionist) { receptionists.set(index, receptionist); }


    // user
    private final ArrayList<User> users = new ArrayList<User>();
    public User searchUser(String username, String password)
    {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    public static HotelDatabase instance;

    static {
        try {
            instance = new HotelDatabase();
        } catch (Exception e) {
            System.err.println("Failed to initialize HotelDatabase:");
            e.printStackTrace();
        }
    }

    public HotelDatabase() throws Exception { 
        HotelDatabase.instance = this;
        this.populateData(); 
    }

    public void populateData() throws Exception{
        Amenity wifi = new Amenity("Wifi",1111,"a wireless networking technology using radio waves" , 20 );
        Amenity tv = new Amenity("TV",1112,"a device with a screen for receiving television signals.",100);
        Amenity gym = new Amenity("GYM",1113,"an indoor venue for exercise and sports",60);


        this.addAmenity(wifi);
        this.addAmenity(tv);
        this.addAmenity(gym);


        Guest g1 = new Guest("Ali Sadek","as173_g1", LocalDate.of(2007, 01, 28), "Cairo","Add extra pillows and blankets",100, Guest.Gender.MALE);
        Guest g2 = new Guest("Khaled Sorour","ks548_g2", LocalDate.of(2007, 07, 1),"Menofiya","Use quiet AC units",500, Guest.Gender.MALE);
        Guest g3 =new Guest("Mahmoud Fayed","mf784_g3",LocalDate.of(2009, 01, 04),"Giza","Ensure a light switch is next to the bed",200, Guest.Gender.MALE);


        this.addGuest(g1);
        this.addGuest(g2);
        this.addGuest(g3);


        ArrayList<Amenity> room101Amenities = new ArrayList<>();
        room101Amenities.add(wifi);
        room101Amenities.add(tv);
        Room r1 = new Room(101, "Single", room101Amenities, 100.00);


        ArrayList<Amenity> room102Amenities = new ArrayList<>();
        room102Amenities.add(wifi);
        room102Amenities.add(tv);
        room102Amenities.add(gym);
        Room r2 = new Room(102, "Double", room102Amenities, 200.00);


        ArrayList<Amenity> room103Amenities = new ArrayList<>();
        room103Amenities.add(wifi);
        Room r3 = new Room(103, "Single", room103Amenities, 100.00);


        this.addRoom(r1);
        this.addRoom(r2);
        this.addRoom(r3);


        Reservation res1 = new Reservation(g1,r1,LocalDate.of(2026,06,12),LocalDate.of(2026,06,15));
        Reservation res2 = new Reservation(g2,r2,LocalDate.of(2026,07,02),LocalDate.of(2026,07,07));



        this.addReservation(res1);
        this.addReservation(res2);


        Admin a1 = new Admin("Mark Eham","me854_a1",LocalDate.of(1998,02,27),8);
        Admin a2 = new Admin("Mohamed Khaled ","mk963_a2",LocalDate.of(1994,05,14),7);
        Admin a3 = new Admin(".",".",LocalDate.of(1974,8,06),6);


        this.addAdmins(a1);
        this.addAdmins(a2);
        this.addAdmins(a3);




        Invoice i1 = new Invoice(20,LocalDate.of(2026,06,16));
        Invoice i2 = new Invoice(10,LocalDate.of(2026,07,18));
        Invoice i3 = new Invoice(30,LocalDate.of(2026,07,20));


        this.addInvoices(i1);
        this.addInvoices(i2);
        this.addInvoices(i3);




        Receptionist receptionist1 = new Receptionist("Fares el Assab","fa911_rece1",LocalDate.of(2001,5,13),8);
        Receptionist receptionist2 = new Receptionist("hossam mowafi","hm912_rece2",LocalDate.of(2000,9,30),7);
        Receptionist receptionist3 = new Receptionist("Mohamed Magdy","mm913_rece3",LocalDate.of(1999,7,21),6);


        this.addReceptionist(receptionist1);
        this.addReceptionist(receptionist2);
        this.addReceptionist(receptionist3);
    }



}


