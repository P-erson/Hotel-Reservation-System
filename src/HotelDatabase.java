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




}
