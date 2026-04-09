import java.util.ArrayList;
public class HotelDatabase {
// amenties
   private ArrayList<Amenity> amenities = new ArrayList<Amenity>();

    public ArrayList<Amenity> getAmenities()
    {
        return this.amenities;
    }

    public void addAmenity(Amenity amenity)
    {
        this.amenities.add(amenity);
    }
    public void removeAmenity(Amenity amenity)
    {
        this.amenities.remove(amenity);
    }
    // guests
    private ArrayList<Guest> guests = new ArrayList<Guest>();

    public ArrayList<Guest> getGuests()
    {
        return this.guests;
    }

    public void addGuest(Guest guest)
    {
        this.guests.add(guest);
    }
    public void removeGuest(Guest guest)
    {
        this.guests.remove(guest);
    }
//rooms
private ArrayList<Room> rooms = new ArrayList<Room>();

    public ArrayList<Room> getRooms()
    {
        return this.rooms;
    }

    public void addRoom(Room room)
    {
        this.rooms.add(room);
    }
    public void removeRoom(Room room)
    {
        this.rooms.remove(room);
    }
    // reservations
    private ArrayList<Reservation> reservations = new ArrayList<Reservation>();

    public ArrayList<Reservation> getReservations()
    {
        return this.reservations;
    }

    public void addReservation(Reservation reservation)
    {
        this.reservations.add(reservation);
    }
    public void removeReservation(Reservation reservation)
    {
        this.reservations.remove(reservation);
    }
    // invoice
    private ArrayList<Invoice> invoices = new ArrayList<Invoice>();

    public ArrayList<Invoice> getInvoices()
    {
        return this.invoices;
    }

    public void addInvoices(Invoice invoice)
    {
        this.invoices.add(invoice);
    }
    public void removeInvoice(Invoice invoice)
    {
        this.invoices.remove(invoice);
    }
}
