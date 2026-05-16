import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ManagementSystem{
    private HashMap<Integer, Doctor> doctors;
    private HashMap<Integer, Patient> patients;
    private HashMap<Integer, Exam> exams;
    private HashMap<Integer, Appointment> appointments;
    private int doctorCounter = 1;
    private int patientCounter = 1;
    private int examCounter = 1;
    private int appointmentCounter = 1;

    public ManagementSystem(){
        doctors = new HashMap<>();
        patients = new HashMap<>();
        exams = new HashMap<>();
        appointments = new HashMap<>();

    }

    // getNextID for all entities

    public int getNextDoctorID(){
        return doctorCounter++;
    }

    public int getNextPatientID(){
        return patientCounter++;
    }

    public int getNextExamID(){
        return examCounter++;
    }

    public int getNextAppointmentID(){
        return appointmentCounter++;
    }
    
    // Doctors methods

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
    public ArrayList<Appointment> getDoctorAppointments(int doctorId){
        ArrayList<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments.values()){
            Exam exam = exams.get(appointment.getExamId());
            if(exam != null && exam.getDoctorID() == doctorId) { // in order to connect the corresponding appointment from doctorID, we needed the examid  
                result.add(appointment);
            }
        }
        return result;
    }

    // connection between doctor and exam, one doctor has many exams
    public ArrayList<Exam> getExamsByDoctor(int doctorId){
        ArrayList<Exam> result = new ArrayList<>();
        for(Exam exam : exams.values()){
            if(exam.getDoctorID() == doctorId){
                result.add(exam);
            }
        }
        return result;
    }

    // Patients methods

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

    // Exams methods

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

    // get an exam
    public Exam getExam(int id){    // TODO: show exam - appointment in Menus
        return exams.get(id);
    }

    // get appointments by exam
    public ArrayList<Appointment> getAppointmentsByExam(int examId){
        ArrayList<Appointment> result = new ArrayList<>();
        for(Appointment appointment : appointments.values()){
            if(appointment.getExamId() == examId){
                result.add(appointment);
            }
        }
        return result;
    }

    // Appointment methods

    // validate appointment
    public String validateAppointment(int patientId, int examId, String stringDate){ // this method validates the users input before addAppointment
        if(!patients.containsKey(patientId)){ // check whether the patientId is registered
            return "INVALID_PATIENT";
        }
        // date validation for the format dd:MM:yyyy 
        LocalDate date;
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy"); // should we change the : to / ?
            date = LocalDate.parse(stringDate, formatter);
        } catch(Exception e){ // if the format is not the appropriate, an exception will be catched
            return "INVALID_DATE";
        }
        Exam exam = exams.get(examId);
        if(exam==null){ // check if exam exists
            return "INVALID_EXAM";
        }
        int count = (int) appointments.values()         // count() returns long, casted to int
                                    .stream()
                                    .filter(a -> a.getExamId() == examId && a.getExamDate().equals(date)) // filter per day from exam - appointment relationship
                                    .count();
        if(count >= exam.getMaxSlotsPerDay()){
            return "FULL_SLOTS";
        }
        
        return "PASS"; // passed all validates
    }

    // add appointment
    public boolean addAppointment(int patientId, int examId, boolean fastResults, String stringDate) {
        String result = validateAppointment(patientId, examId, stringDate);

        if(!result.equals("PASS")){
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        
        int id = getNextAppointmentID();
        Appointment appointment = new Appointment(id, patientId, examId, fastResults, date);
        appointments.put(id, appointment);
        
        return true;
    }

    // get all appointments
    public ArrayList<Appointment> getAllAppointments(){
        return new ArrayList<>(appointments.values());
    }

     // get an appointment
    public Appointment getAppointment(int id){ 
        return appointments.get(id);
    }

    // get appointments by patient

    public ArrayList<Appointment> getAppointmentsByPatient(int patientId){       // TODO: show all appointments for given patient in Menus
        ArrayList<Appointment> result = new ArrayList<>();
        for(Appointment appointment : appointments.values()){
            if(appointment.getPatientID() == patientId){
                result.add(appointment);
            }
        }
        return result;
    }

    // delete appointment

    public boolean deleteAppointment(int appointmentId){    // TODO: show updates in Menus
        if(!appointments.containsKey(appointmentId)){ // check whether the appointmentId exists in appointments
            return false;
        }
        appointments.remove(appointmentId); // remove from appointments
        return true;
    }

    // get appointments by date

    public ArrayList<Appointment> getAppointmentsByDate(String dateStr){
        LocalDate date;
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy"); // should we change the : to / ?
            date = LocalDate.parse(dateStr, formatter);
        } catch(Exception e){ 
            return new ArrayList<>(); // return an empty ArrayList
        }
        ArrayList<Appointment> result = new ArrayList<>();
        for(Appointment a : appointments.values()){
            if(a.getExamDate().equals(date)){
                result.add(a);
            }
        }
        return result;
    }

    // get appointment by category

    public ArrayList<Appointment> getAppointmentsByCategory(String categoryName){   // used for calculateRevenueByCategory
        ArrayList<Appointment> result = new ArrayList<>();
        for(Appointment appointment : appointments.values()){
            Exam exam = exams.get(appointment.getExamId());
            if(exam != null && exam.getCategoryName().equals(categoryName)){
                result.add(appointment);
            }
        }
        return result;
    }
    
    // Statistics

    // calculate appointment cost

    public double calculateAppointmentCost(Appointment appointment){ // this is an auxiliary method for other calculations
        Exam exam = exams.get(appointment.getExamId());
        if(exam ==  null){
            return 0.0;
        }
        return exam.getCost(appointment.getFastResults()); // get the exam cost whether the patient has fastResults or not
    }
    
    // calculate patient revenue
    
    public double calculateRevenueByPatient(int patientId){
        double total = 0.0;
        for(Appointment appointment : getAppointmentsByPatient(patientId)){
                total += calculateAppointmentCost(appointment);
        }
        return total;
    }

    // calculate exam revenue

    public double calculateRevenueByExam(int examId){
        double total = 0.0;
        for(Appointment appointment : getAppointmentsByExam(examId)){
            total += calculateAppointmentCost(appointment);
        }
        return total;
    }

    // calculate revenue per category

    public double calculateRevenueByCategory(String categoryName){
        double total = 0.0;
        for(Appointment appointment : getAppointmentsByCategory(categoryName)){
            total += calculateAppointmentCost(appointment);
        }
        return total;
    }

    // calculate total revenue

    public double calculateTotalRevenue(){  // this is the grand total
        double total = 0.0;
        for(Appointment appointment: appointments.values()){
            total += calculateAppointmentCost(appointment);
        }
        return total;
    }

    // sync method for FileManager
    public void syncCounters() {

    // doctors
    doctorCounter = doctors.keySet()
            .stream()
            .max(Integer::compare)
            .orElse(0) + 1;

    // patients
    patientCounter = patients.keySet()
            .stream()
            .max(Integer::compare)
            .orElse(0) + 1;

    // exams
    examCounter = exams.keySet()
            .stream()
            .max(Integer::compare)
            .orElse(0) + 1;

    // appointments
    appointmentCounter = appointments.keySet()
            .stream()
            .max(Integer::compare)
            .orElse(0) + 1;
    }
}