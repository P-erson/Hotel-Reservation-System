package Exceptions;
import java.time.LocalDate;

public class InvalidDateOfBirthException extends Exception {
    private LocalDate DOB;
    public InvalidDateOfBirthException(LocalDate DOB){
        super("Invalid Date of birth, please try again");
        this.DOB = DOB;
    }

    public LocalDate getDateOfBirth() {
        return DOB;
    }
}
