import java.util.*;

public class ManagementSystem {
    private HashMap<Integer, Doctor> doctors;
    private HashMap<Integer, Patient> patients;
    private HashMap<Integer, Exam> exams; // TODO: should it be HashMap or ArrayList?
    private HashMap<Integer, Appointment> appointments;
    private int doctorCounter = 1;
    private int patientCounter = 1;
    private int examCounter = 1;
    private int appointmentCounter = 1;

    public ManagementSystem() {
        doctors = new HashMap<>();
        patients = new HashMap<>();
        exams = new HashMap<>();
        appointments = new HashMap<>();

    }

    // getNextID for all entities

    public int getNextDoctorID() {
        return doctorCounter++;
    }

    public int getNextPatientID() {
        return patientCounter++;
    }

    public int getNextExamID() {
        return examCounter++;
    }

    public int getNextAppointmentID() {
        return appointmentCounter++;
    }
    
    // doctors methods

    // add doctor
    public void addDoctor(String name, String phone, String specialty, int years){  
        int id = getNextDoctorID();
        Doctor doctor = new Doctor(id, name, phone, specialty, years);
        doctors.put(id, doctor);
    }
    
    // get all doctors
    public ArrayList<Doctor> getAllDoctors(){
        return new ArrayList<>(doctors.values());
    }

    // get a doctor
    public Doctor getDoctor(int id){
        return doctors.get(id);
    }

    // doctor appointment 
    public ArrayList<Appointment> getDoctorAppointment(int doctorId){
        ArrayList<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments.values()) {
            Exam e = exams.get(a.getExamId());
            if (e != null && e.getDoctorID() == doctorId) {
                result.add(a);
            }
        }
        return result;
        xt
    }

}
