import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ManagementSystem {
    private HashMap<Integer, Doctor> doctors;
    private HashMap<Integer, Patient> patients;
    private HashMap<Integer, Exam> exams;
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
    public Doctor getDoctor(int id){    // TODO: show doctor - exams in Menus
        return doctors.get(id);
    }

    // doctor appointment 
    public ArrayList<Appointment> getDoctorAppointment(int doctorId){
        ArrayList<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments.values()){
            Exam e = exams.get(a.getExamId());
            if(e != null && e.getDoctorID() == doctorId) { // in order to the the corresponding appointment from doctorID, we needed the examid  
                result.add(a);
            }
        }
        return result;
    }

    // connection between doctor and exam, one doctor has many exams
    public ArrayList<Exam> getExamsByDoctor(int doctorId){
        ArrayList<Exam> result = new ArrayList<>();
        for(Exam e : exams.values()){
            if(e.getDoctorID() == doctorId){
                result.add(e);
            }
        }
        return result;
    }

    // patients methods

    // add patient
    public void addPatient(String patientName, String patientPhone, String email){  
        int id = getNextPatientID();
        Patient patient = new Patient(id, patientName, patientPhone, email);
        patients.put(id, patient);
    }

    // get all patients
    public ArrayList<Patient> getAllPatients(){
        return new ArrayList<>(patients.values());
    }

     // get a patient
    public Patient getPatient(int id){
        return patients.get(id);    // TODO: show appointment for corresponding appointment in Menus
    }

    // exams methods

    // add exam
    public void addExam(String examName, String categoryName, int maxSlotsPerDay, double cost, int doctorID, String examType){
        int id = getNextExamID();
        Exam exam;
        if(categoryName.equals("Imaging")){
            exam = new ImagingExamination(id, examName, categoryName, maxSlotsPerDay, cost, doctorID, examType);
        } else if(categoryName.equals("Microbiological")){
            exam = new MicrobiologicalExamination(id, examName, categoryName, maxSlotsPerDay, cost, doctorID, examType);
        } else{
            exam = new SpecializedExamination(id, examName, categoryName, maxSlotsPerDay, cost, doctorID, examType);
        }
        exams.put(id, exam);
    }

    // get all exams sorted
    public ArrayList<Exam> getAllExams(){
        return exams.values()
                    .stream() // convert Collection to stream
                    .sorted(Comparator.comparing(Exam::getExamName, String.CASE_INSENSITIVE_ORDER)) // order by Exam Name
                    .collect(Collectors.toCollection(ArrayList::new)); // convert to a new ArrayList<>()
    }


    //Show Single exam
    public Exam getExam(int id){        //Shows exam if found by id
        return exams.get(id);
    }

    public ArrayList<Appointment> getAppointmentsByExam(int examId) {
    ArrayList<Appointment> result = new ArrayList<>();

    for (Appointment appointment : appointments.values()) {         //Returns list if appointment not deleted and exam id matches
        if (!appointment.isDeleted() && appointment.getExamId() == examId) { 
            result.add(appointment);
        }
    }

    return result;
}



    //appointment methods

    //add appointment
    public void addAppointment(int patientID, int examId, boolean fastResults, LocalDate examDate){
        int id = getNextAppointmentID();    
        int counter = 0; // Counts how many Appointments made in same date

        Exam exam = exams.get(examId);

        for (Appointment appointment : appointments.values()){
            if (appointment.getExamId() == examId && appointment.getExamDate().equals(examDate)){
                counter ++ ;
            }

        }

        if (counter>exam.getMaxSlotsPerDay()){
            System.out.println("No available slots for this exam on this date.");
                return ;
        }

        Appointment appointment = new Appointment(id, patientID, examId, fastResults, examDate);
            appointments.put(id, appointment);
    }

    //get all appointments
    public ArrayList<Appointment> getAllAppointments(){
        return new ArrayList<>(appointments.values());
    }

    //get one appointment
    public Appointment getAppointment(int id){
        return appointments.get(id);    
    }

    //get appointment by patient
    public ArrayList<Appointment> getAppointmentsByPatient(int patientID) {
        ArrayList<Appointment> result = new ArrayList<>();  //New list
        
        for (Appointment appointment : appointments.values()){
                if (appointment.getPatientID() == patientID){
                    result.add(appointment);
                }
        }

        return result; //Show the list
    }

    //remove appointment
    public boolean removeAppointment(int appointmentId){
        Appointment appointment = appointments.get(appointmentId);
        
        if (appointment == null || appointment.isDeleted()){
            return false;
        }

        appointment.setDeleted(true);
            return true;
    }
    
    //show all appointments
    public ArrayList<Appointment> getAppointmentByDate(LocalDate examDate){
        ArrayList<Appointment> result = new ArrayList<>();

        for (Appointment appointment : appointments.values()){
            if(!appointment.isDeleted() && appointment.getExamDate().equals(examDate)){
                result.add(appointment);
            }
        }

        return result; //Prints list on specific date 

    }


    //Statistics

    //Appointment cost
    public double calculateAppointmentsCost(Appointment appointment){
        Exam exam = exams.get(appointment.getExamId());

        if (exam == null || appointment.isDeleted()){
            return 0;
        }

        return exam.getCost(appointment.getFastResults());

    }

    //Income Per Patient
    public double calculateIncomePerPatient(int patientID){
        double total = 0;

        for (Appointment appointment : appointments.values()){
            if(!appointment.isDeleted() && appointment.getPatientID() == patientID){
                total = total + calculateAppointmentsCost(appointment);
            }
        }

        return total;
    }

    //Total Income Per Exam
    public double calculateTotalIncomeByExam(int examID) {
    double total = 0;

    for (Appointment appointment : appointments.values()) {
        if (!appointment.isDeleted() && appointment.getExamId() == examID) {
            total += calculateAppointmentsCost(appointment);
        }
    }

    return total;
    }

    //Appointments per Category
    public ArrayList<Appointment> getAppointmentsByCategory(String categoryName){
        ArrayList<Appointment> result = new ArrayList<>();

        for (Appointment appointment : appointments.values()){
            if (!appointment.isDeleted()){
                Exam exam = exams.get(appointment.getExamId());     //Finds category of exam
                
            

            if (exam != null && exam.getCategoryName().equals(categoryName)){       //Checks if matches categoryName
                result.add(appointment);
            }
            }
        }
        return result;
    
    }
    
    //Income per Category
    public double calculateTotalRevenueByCategory(String categoryName) {
        double total = 0;

        for (Appointment appointment : getAppointmentsByCategory(categoryName)) {
            total += calculateAppointmentsCost(appointment);
        }   

        return total;
    }

    //Total Income
    public double calculateTotalRevenue() {
        double total = 0;

        for (Appointment appointment : appointments.values()) {
            if (!appointment.isDeleted()) {
                total += calculateAppointmentsCost(appointment);
            }
        }
        return total;
    }


}




