package Exceptions;

public class AmenityDoesNotExistException extends Exception{
    private String amenity;

    public AmenityDoesNotExistException(String amenity){
        super("Invalid amenity name entered: " + amenity);
        this.amenity = amenity;
    }

    public String getAmenityName(){
        return amenity;
    }
}
