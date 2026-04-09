import java.time.LocalDate;
import java.util.ArrayList;


public class Admin extends Staff{
    public Admin(String username, String password, LocalDate dateOfBirth, int workingHours){
        super(username, password, dateOfBirth, Role.ADMIN, workingHours);
    }

    //Room CRUDs
    public void createRoom(int roomNumber, RoomType roomType, ArrayList<Amenity> amenities){
        DATABASE.addRoom(new Room(roomNumber, roomType, amenities));
    }

    public ArrayList<Room> viewRooms() { return DATABASE.getRooms(); }

    // public void updateRoom(int roomNumber, RoomType newRoomType, ArrayList<Amenity> newRoomAmenities) {
    //     int roomNumberIndex = DATABASE.getRooms().indexOf(roomNumber);
    //     Room originalRoomIndex = DATABASE.getRooms().get(roomNumberIndex);
    //     DATABASE.updateRooms(originalRoomIndex, )
    // }


    //Amenity CRUDs
    public void createAmenity(String name, int id, String description, double price){
        DATABASE.addAmenity(new Amenity(name, id, description, price));
    }

    public ArrayList<Amenity> viewAmenities(){ return DATABASE.getAmenities(); }

    // public void updateAmenity(String originalAmenity, String name, int id, String description, double price){
    //     int originalAmenityIndex = DATABASE.getAmenities().indexOf(originalAmenity);
    //     DATABASE.amenities.set(originalAmenityIndex, new Amenity(name, id, description, price));
    // }

    public void removeAmenity(String amenity){
        int amenityIndex = -1;

        for (int i = 0; i < DATABASE.getAmenities().size(); i++){
            if (DATABASE.getAmenities().get(i).getName().equalsIgnoreCase(amenity)) {
                amenityIndex = i;
                break;
            }
        }

        if (amenityIndex != -1) {
            Amenity amenityObject = DATABASE.getAmenities().get(amenityIndex);
            DATABASE.removeAmenity(amenityObject);
        } else {
            // throw non-existant amenity exceeption
        }
    }


    //Room Type CRUDs
    public void createRoomType(String roomType){ ROOM_TYPES.addType(roomType); }
    public ArrayList<String> viewRoomTypes(){ return ROOM_TYPES.getTypes(); }

    public void updateRoomType(String originalRoomType, String newRoomType){
        ROOM_TYPES.updateType(originalRoomType, newRoomType);
    }

    public void removeRoomType(String roomType){ ROOM_TYPES.removeType(roomType); }
}
