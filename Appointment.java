import java.time.LocalDate;

public class Appointment{
    private int appointmentId;
    private int patientID;
    private int examId;
    private boolean fastResults;
    private LocalDate examDate;

    public Appointment(int appointmentId, int patientID, int examId, boolean fastResults, LocalDate examDate){
        this.appointmentId=appointmentId;
        this.patientID=patientID;
        this.examId=examId;
        this.fastResults=fastResults;
        this.examDate=examDate;
    }

    // setters

    public int getAppointmentId(){
        return appointmentId;
    }

     public int getPatientID(){
        return patientID;
    }

     public int getExamId(){
        return examId;
    }

    public boolean getFastResults(){
        return fastResults;
    }

    public LocalDate getExamDate(){
        return examDate;
    }

    // setters

    public void setAppointmentId(int appointmentId){
        this.appointmentId=appointmentId;
    }

    public void setPatientID(int patientID){
        this.patientID=patientID;
    }

    public void setExamId(int examId){
        this.examId=examId;
    }

    public void setFastResults(boolean fastResults){
        this.fastResults=fastResults;
    }

    public void setExamDate(LocalDate examDate){
        this.examDate=examDate;
    }
    
}
