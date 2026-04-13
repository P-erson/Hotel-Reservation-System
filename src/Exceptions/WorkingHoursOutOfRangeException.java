package Exceptions;

public class WorkingHoursOutOfRangeException extends Exception{
    private int workingHours;

    public WorkingHoursOutOfRangeException(int workingHours){
        super("Out of range shift entered: " + workingHours);
        this.workingHours = workingHours;
    }

    public int getWorkingHours() { return workingHours; }
}
