import java.util.ArrayList;
public class HotelDatabase {
    // amenties
    private ArrayList<Amenity> amenities = new ArrayList<Amenity>();

    public ArrayList<Amenity> getAmenities() { return amenities; }
    public void addAmenity(Amenity amenity) { amenities.add(amenity); }
    public void removeAmenity(Amenity amenity) { amenities.remove(amenity);}

    
    // guests
    private ArrayList<Guest> guests = new ArrayList<Guest>();

    public ArrayList<Guest> getGuests() { return guests; }
    public void addGuest(Guest guest) { guests.add(guest); }
    public void removeGuest(Guest guest) { guests.remove(guest); }


    // rooms
    private ArrayList<Room> rooms = new ArrayList<Room>();

    public ArrayList<Room> getRooms() { return rooms; }
    public void addRoom(Room room) { rooms.add(room); }
    public void removeRoom(Room room) { rooms.remove(room); }
    
    
    // reservations
    private ArrayList<Reservation> reservations = new ArrayList<Reservation>();

    public ArrayList<Reservation> getReservations() { return reservations; }
    public void addReservation(Reservation reservation) { reservations.add(reservation); }
    public void removeReservation(Reservation reservation) { reservations.remove(reservation); }
    
    
    // invoice
    private ArrayList<Invoice> invoices = new ArrayList<Invoice>();

    public ArrayList<Invoice> getInvoices() { return invoices; }
    public void addInvoices(Invoice invoice) { invoices.add(invoice); }
    public void removeInvoice(Invoice invoice) { invoices.remove(invoice); }
}
