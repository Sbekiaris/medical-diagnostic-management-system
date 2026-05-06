public class Doctor {
    private int doctorID;
    private String doctorName;
    private String doctorPhone;
    private String specialty;
    private int years;

    public Doctor(int doctorID, String doctorName, String doctorPhone, String specialty, int years){
        this.doctorID=doctorID;
        this.doctorName=doctorName;
        this.doctorPhone=doctorPhone;
        this.specialty=specialty;
        this.years=years;
    }

    // getters

    public int getDoctorID(){
        return doctorID;
    }
    
    public String getDoctorName(){
        return doctorName;
    }

    public String getDoctorPhone(){
        return doctorPhone;
    }

    public String getSpecialty(){
        return specialty;
    }

    public int getYears(){
        return years;
    }

    // setters

    public void setDoctorID(int doctorID){
        this.doctorID=doctorID;
    }

    public void setDoctorName(String doctorName){
        this.doctorName=doctorName;
    }

    public void setDoctorPhone(String doctorPhone){
        this.doctorPhone=doctorPhone;
    }

    public void setSpecialty(String specialty){
        this.specialty=specialty;
    }

    public void setYears(int years){
        this.years=years;
    }

    @Override
    public String toString() {
        return "Doctor ID: " + doctorID +
            ", Name: " + doctorName +
            ", Phone: " + doctorPhone +
            ", Specialty: " + specialty +
            ", Years: " + years;
    }

}
