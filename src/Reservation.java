import java.time.LocalDate;
public class Reservation{
    public enum ReservationStatus {PENDING, CONFIRMED, CANCELLED, COMPLETED};
    private Guest guest;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private ReservationStatus status = ReservationStatus.PENDING;
    public Reservation (){
                this.status = ReservationStatus.PENDING;
    }
    public Reservation (Guest guest, Room room, LocalDate checkIn, LocalDate checkOut){
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
        this.status = ReservationStatus.CONFIRMED;
    }
    public void cancelReservation() {
        this.status = ReservationStatus.CANCELLED;
    }
    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
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
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
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

}
