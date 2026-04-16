import java.time.LocalDate;
import java.util.ArrayList;
public class HotelDatabase {
    // room types
    public RoomType ROOM_TYPES = new RoomType();

    // amenties
    private ArrayList<Amenity> amenities = new ArrayList<Amenity>();

    public ArrayList<Amenity> getAmenities() { return amenities; }
    public void addAmenity(Amenity amenity) { amenities.add(amenity); }
    public void removeAmenity(Amenity amenity) { amenities.remove(amenity);}
    public void setAmenity(int index, Amenity amenity) { amenities.set(index, amenity); }

    
    // guests
    private ArrayList<Guest> guests = new ArrayList<Guest>();

    public ArrayList<Guest> getGuests() { return guests; }
    public void addGuest(Guest guest) { guests.add(guest); }
    public void removeGuest(Guest guest) { guests.remove(guest); }
    public void setGuest(int index, Guest guest) { guests.set(index, guest); }
    public Guest searchGuest(String username, String password)
    {
        for(int i = 0; i <= guests.size() - 1; i++ )
        {
            if(guests.get(i).getUsername().equals(username) && guests.get(i).getPassword().equals(password) )
            {
                return guests.get(i);
            }
        }
        return null;
    }


    // rooms
    private ArrayList<Room> rooms = new ArrayList<Room>();

    public ArrayList<Room> getRooms() { return rooms; }
    public void addRoom(Room room) { rooms.add(room); }
    public void removeRoom(Room room) { rooms.remove(room); }
    public void setRoom(int index, Room room) { rooms.set(index, room); }

    
    // reservations
    private ArrayList<Reservation> reservations = new ArrayList<Reservation>();

    public ArrayList<Reservation> getReservations() { return reservations; }
    public void addReservation(Reservation reservation) { reservations.add(reservation); }
    public void removeReservation(Reservation reservation) { reservations.remove(reservation); }
    public void setReservation(int index, Reservation reservation) { reservations.set(index, reservation); }
    
    
    // invoice
    private ArrayList<Invoice> invoices = new ArrayList<Invoice>();

    public ArrayList<Invoice> getInvoices() { return invoices; }
    public void addInvoices(Invoice invoice) { invoices.add(invoice); }
    public void removeInvoice(Invoice invoice) { invoices.remove(invoice); }
    public void setInvoice(int index, Invoice invoice) { invoices.set(index, invoice); }


    //admins
    private ArrayList<Admin> admins = new ArrayList<Admin>();

    public ArrayList<Admin> getAdmins() {return admins;}
    public void addAdmins(Admin admin) { admins.add(admin); }
    public void removeAdmins(Admin admin) { admins.remove(admin); }
    public void setAdmin(int index, Admin admin) { admins.set(index, admin); }


    //receptionist
    private ArrayList<Receptionist> receptionists = new ArrayList<Receptionist>();

    public ArrayList<Receptionist> getReceptionists() {return receptionists;}
    public void addReceptionist(Receptionist receptionist) { receptionists.add(receptionist); }
    public void removeReceptionist(Receptionist receptionist) { receptionists.remove(receptionist); }
    public void setReceptionist(int index, Receptionist receptionist) { receptionists.set(index, receptionist); }






    public HotelDatabase() throws Exception
    {
        Amenity wifi = new Amenity("Wifi",1111,"a wireless networking technology using radio waves" , 20 );
        Amenity tv = new Amenity("TV",1112,"a device with a screen for receiving television signals.",100);
        Amenity gym = new Amenity("GYM",1113,"an indoor venue for exercise and sports",60);

        addAmenity(wifi);
        addAmenity(tv);
        addAmenity(gym);

        Guest g1 = new Guest("Ali Sadek","as173_g1", LocalDate.of(2007, 01, 28), "Cairo","Add extra pillows and blankets",100, Guest.Gender.MALE);
        Guest g2 = new Guest("Khaled Sorour","ks548_g2", LocalDate.of(2007, 07, 1),"Menofiya","Use quiet AC units",500, Guest.Gender.MALE);
        Guest g3 =new Guest("Mahmoud Fayed","mf784_g3",LocalDate.of(2009, 01, 04),"Giza","Ensure a light switch is next to the bed",200, Guest.Gender.MALE);

        addGuest(g1);
        addGuest(g2);
        addGuest(g3);

        Room r1 = new Room(101,"Single");
        Room r2 = new Room(102,"Double");
        Room r3 = new Room(103 , "Single ");

        addRoom(r1);
        addRoom(r2);
        addRoom(r3);

        Reservation res1 = new Reservation(g1,r1,LocalDate.of(2026,06,12),LocalDate.of(2026,06,15));
        Reservation res2 = new Reservation(g2,r2,LocalDate.of(2026,07,02),LocalDate.of(2026,07,07));
        Reservation res3 = new Reservation();

        addReservation(res1);
        addReservation(res2);
        addReservation(res3);

    }
}
