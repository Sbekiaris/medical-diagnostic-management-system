import java.util.*;

public class ManagementSystem {
    private HashMap<Integer, Doctor> doctors;
    private HashMap<Integer, Patient> patients;
    private HashMap<Integer, Exam> exams; // TODO: should it be HashMap or ArrayList?
    private HashMap<Integer, Appointment> appointments;

    public ManagementSystem() {
        doctors = new HashMap<>();
        patients = new HashMap<>();
        exams = new HashMap<>();
        appointments = new HashMap<>();

    }
        // doctors methods

    public void addDoctor(Doctor doctor){
        doctors.put(doctor.getDoctorID(), doctor);
    }
    
    public ArrayList<Doctor> getAllDoctors(){
        return new ArrayList<>(doctors.values());
    }

        public Doctor getDoctor(int id){
        return doctors.get(id);
    }
}
