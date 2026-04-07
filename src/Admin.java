import java.time.LocalDate;
import java.util.ArrayList;


public class Admin extends Staff{
    public Admin(String username, String password, LocalDate dateOfBirth, int workingHours){
        super(username, password, dateOfBirth, Role.ADMIN, workingHours);
    }

    //Room CRUDs
    // public void createRoom(HotelDatabase database, int roomNumber, ){
    //     database.rooms.add(new Room(roomNumber, ))
    // }


    //Amenity CRUDs
    public void createAmenity(HotelDatabase database, String amenity){
        database.amenities.add(amenity);
    }

    public ArrayList<Amenity> readAmenities(HotelDatabase database){
        return database.amenities;
    }

    public void updateAmenity(HotelDatabase database, String originalAmenity, String newAmenity){
        int originalAmenityIndex = database.amenities.indexOf(originalAmenity);
        database.amenities.set(originalAmenityIndex, newAmenity);
    }

    public void removeAmenity(HotelDatabase database, String amenity){
        int amenityIndex = database.amenities.indexOf(amenity);
        database.amenities.remove(amenityIndex);
    }


    //Room Type CRUDs
    public void createRoomType(HotelDatabase database, String roomType){
        database.roomTypes.add(roomType);
    }

    public ArrayList<RoomType> readRoomTypes(HotelDatabase database){
        return database.roomTypes;
    }

    public void updateRoomType(HotelDatabase database, String originalRoomType, String newRoomType){
        int originalRoomTypesIndex = database.roomTypes.indexOf(originalRoomType);
        database.roomTypes.set(originalRoomTypesIndex, newRoomType);
    }

    public void removeRoomType(HotelDatabase database, String roomType){
        int roomTypeIndex = database.roomTypes.indexOf(roomType);
        database.roomTypes.remove(roomTypeIndex);
    }
}
