public class Patient{
    private int patientID;
    private String patientName;
    private String patientPhone;
    private String email;

    public Patient(int patientID, String patientName, String patientPhone, String email){
        this.patientID=patientID;
        this.patientName=patientName;
        this.patientPhone=patientPhone;
        this.email=email;
    }

    // getters

    public int getPatientID(){
        return patientID;
    }
    
    public String getPatientName(){
        return patientName;
    }

    public String getPatientPhone(){
        return patientPhone;
    }

    public String getEmail(){
        return email;
    }

    // setters

    public void setPatientID(int patientID){
        this.patientID=patientID;
    }

    public void setPatientName(String patientName){
        this.patientName=patientName;
    }

    public void setPatientPhone(String patientPhone){
        this.patientPhone=patientPhone;
    }

    public void setEmail(String email){
        this.email=email;
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientID +
            ", Name: " + patientName +
            ", Phone: " + patientPhone +
            ", Email: " + email;
    }

    
}
