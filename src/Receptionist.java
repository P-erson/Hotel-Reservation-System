import java.time.LocalDate;

public class Receptionist extends Staff {
    public Receptionist(String username, String password, LocalDate dateOfBirth, int workingHours) throws Exception{
        super(username, password, dateOfBirth, Role.RECEPTIONIST, workingHours);

        DATABASE.addReceptionist(this);
    }

}
