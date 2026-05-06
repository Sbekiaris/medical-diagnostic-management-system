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
    public ArrayList<Appointment> getDoctorAppointments(int doctorId){
        ArrayList<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments.values()){
            Exam e = exams.get(a.getExamId());
            if(e != null && e.getDoctorID() == doctorId) { // in order to connect the corresponding appointment from doctorID, we needed the examid  
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

    // get an exam
    public Exam getExam(int id){    // TODO: show exam - appointment in Menus
        return exams.get(id);
    }

    // get appointments by exam
    public ArrayList<Appointment> getAppointmentsByExam(int examId){
        ArrayList<Appointment> result = new ArrayList<>();
        for(Appointment a : appointments.values()){
            if(a.getExamId() == examId){
                result.add(a);
            }
        }
        return result;
    }

    // appointment methods

    // add appointment
    public boolean addAppointment(int patientId, int examId, boolean fastResults, String stringDate){ // return value to boolean for appointment validation in Menus
        if(!patients.containsKey(patientId)){ // check whether the patientId is registered
            return false;
        }
        // date validation for the format dd:MM:yyyy 
        LocalDate date;
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy"); // should we change the : to / ?
            date = LocalDate.parse(stringDate, formatter);
        } catch(Exception e){ // if the format is not the appropriate, an exception will be catched
            return false;
        }
        Exam exam = exams.get(examId);
        if(exam==null){ // check if exam exists
            return false;
        }
        int count = (int) appointments.values()         // count() returns long, casted to int
                                    .stream()
                                    .filter(a -> a.getExamId() == examId && a.getExamDate().equals(date)) // filter per day from exam - appointment relationship
                                    .count();
        if(count >= exam.getMaxSlotsPerDay()){
            return false;
        }
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

    // get appointment by client

    public ArrayList<Appointment> getAppointmentByClient(int patientId){       // TODO: show all appointments for given patient in Menus
        ArrayList<Appointment> result = new ArrayList<>();
        for(Appointment a : appointments.values()){
            if(a.getPatientID() == patientId){
                result.add(a);
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
    
    // statistics

    

}