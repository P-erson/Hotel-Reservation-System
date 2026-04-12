import java.time.LocalDate;
import java.util.ArrayList;


public class Admin extends Staff{
    public Admin(String username, String password, LocalDate dateOfBirth, int workingHours){
        super(username, password, dateOfBirth, Role.ADMIN, workingHours);

        DATABASE.addAdmins(this);
    }



    
    //Room CRUDs
    public void createRoom(int roomNumber, String roomType, ArrayList<Amenity> amenities){
        DATABASE.addRoom(new Room(roomNumber, roomType, amenities));
    }

    public ArrayList<Room> viewRooms() { return DATABASE.getRooms(); }

    public void updateRoom(int roomNumber, String roomtype, ArrayList<Amenity> amenities){
        int roomNumberIndex = -1;

        for (int i = 0; i < DATABASE.getRooms().size(); i++){
            Room room = DATABASE.getRooms().get(i);

            if (room.getRoomNumber() == roomNumber){
                roomNumberIndex = i;
                break;
            }
        }

        if (roomNumberIndex == -1){
            // throw non-existant room exception
        } else {
            DATABASE.setRoom(roomNumberIndex, new Room(roomNumber, roomtype, amenities));
        }
    }

    public void removeRoom(int roomNumber){
        int roomIndex = -1;

        for (int i = 0; i < DATABASE.getRooms().size(); i++){
            if (DATABASE.getRooms().get(i).getRoomNumber() == roomNumber) {
                roomIndex = i;
                break;
            }
        }

        if (roomIndex == -1) {
            // throw non-existant room exception
        } else {
            Room roomObject = DATABASE.getRooms().get(roomIndex);
            DATABASE.removeRoom(roomObject);
        }
    }




    //Amenity CRUDs
    public void createAmenity(String name, int id, String description, double price){
        DATABASE.addAmenity(new Amenity(name, id, description, price));
    }

    public ArrayList<Amenity> viewAmenities(){ return DATABASE.getAmenities(); }

    public void updateAmenity(String originalAmenityName, String name, int id, String description, double price){
        int originalAmenityIndex = -1;

        for (int i = 0; i < DATABASE.getAmenities().size(); i++){
            if (DATABASE.getAmenities().get(i).getName().equalsIgnoreCase(originalAmenityName)){
                originalAmenityIndex = i;
                break;
            }
        }

        if (originalAmenityIndex == -1){
            // throw non-existant amenity exception
        } else {
            DATABASE.setAmenity(originalAmenityIndex, new Amenity(name, id, description, price));
        }
    }

    public void removeAmenity(String amenity){
        int amenityIndex = -1;

        for (int i = 0; i < DATABASE.getAmenities().size(); i++){
            if (DATABASE.getAmenities().get(i).getName().equalsIgnoreCase(amenity)) {
                amenityIndex = i;
                break;
            }
        }

        if (amenityIndex == -1) {
            // throw non-existant amenity exception
        } else {
            Amenity amenityObject = DATABASE.getAmenities().get(amenityIndex);
            DATABASE.removeAmenity(amenityObject);
        }
    }




    //Room Type CRUDs
    public void createRoomType(String roomType, Double roomPrice){ DATABASE.ROOM_TYPES.addType(roomType, roomPrice); }
    public ArrayList<String> viewRoomTypes(){ return DATABASE.ROOM_TYPES.getTypes(); }

    public void updateRoomType(String originalRoomType, String newRoomType, Double originalRoomPrice, Double newRoomPrice){
        DATABASE.ROOM_TYPES.update(originalRoomType, newRoomType, originalRoomPrice, newRoomPrice);
    }

    public void removeRoomType(String roomType){ DATABASE.ROOM_TYPES.removeType(roomType); }
}
