import java.time.LocalDate;
public class Reservation{
    public enum ReservationStatus {PENDING, CONFIRMED, CANCELLED, COMPLETED};
    private Guest guest;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private ReservationStatus status = ReservationStatus.PENDING;
    private Invoice invoice;
    public Reservation (){
                this.status = ReservationStatus.PENDING;
    }
    public Reservation (Guest guest, Room room, LocalDate checkIn, LocalDate checkOut){
        setGuest(guest);
        setRoom(room);
        setCheckInDate(checkIn);
        setCheckOutDate(checkOut); 
        this.status = ReservationStatus.CONFIRMED;
        room.setAvailable(false);
    }
    public void cancelReservation() {
        if (this.status == ReservationStatus.COMPLETED) {
            throw new IllegalStateException("Cannot cancel a completed reservation.");
        }
        if (this.status == ReservationStatus.CANCELLED) {
            throw new IllegalStateException("Reservation is already cancelled.");
        }
        this.status = ReservationStatus.CANCELLED;
        room.setAvailable(true);
    }
    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null.");
        }
        this.status = status;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        
    if (checkOutDate == null || checkOutDate.isBefore(checkInDate) || checkOutDate.isEqual(checkInDate)) {
        System.out.println("Check-out date must be after check-in date.");
        return;
    }
        this.checkOutDate = checkOutDate;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        if (guest == null) {
            throw new IllegalArgumentException("Guest cannot be null.");
        }
        this.guest = guest;
    }


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }
        this.room = room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        if (checkInDate == null || checkInDate.isBefore(LocalDate.now())) {
        System.out.println("Check-in date must be today or in the future.");
        return;
    }
        this.checkInDate = checkInDate;
    }
    public Invoice getInvoice() {
        return invoice;
    }

}
