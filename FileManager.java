import java.io.*;
import java.time.format.DateTimeFormatter;

public class FileManager{

    private ManagementSystem system;

    public FileManager(ManagementSystem system){
        this.system = system;
    }

    // doctors save
    public void saveDoctors(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("doctors.txt"))){

            for (Doctor dοctor : system.getAllDoctors()) {
                bw.write(dοctor.getDoctorID() + "," +
                         dοctor.getDoctorName() + "," +
                         dοctor.getDoctorPhone() + "," +
                         dοctor.getSpecialty() + "," +
                         dοctor.getYears());
                bw.newLine();
            }

        } catch (IOException e){
            System.out.println("Error saving doctors");
        }
    }

    // doctors load
    public void loadDoctors(){
        try(BufferedReader br = new BufferedReader(new FileReader("doctors.txt"))){

            String line;
            while((line = br.readLine()) != null){
                String[] p = line.split(",");
                String name = p[1];
                String phone = p[2];
                String specialty = p[3];
                int years = Integer.parseInt(p[4]);

                system.addDoctor(name, phone, specialty, years);
            }

        } catch (IOException e) {
            System.out.println("Doctors file not found. Initializing empty data.");
        }
    }

    // patients save
    public void savePatients(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("patients.txt"))){

            for (Patient patient : system.getAllPatients()) {
                bw.write(patient.getPatientID() + "," +
                         patient.getPatientName() + "," +
                         patient.getPatientPhone() + "," +
                         patient.getEmail());
                bw.newLine();
            }

        } catch(IOException e){
            System.out.println("Error saving patients");
        }
    }

    // patients load
    public void loadPatients(){
        try(BufferedReader br = new BufferedReader(new FileReader("patients.txt"))){

            String line;
            while((line = br.readLine()) != null){
                String[] p = line.split(",");
                String name = p[1];
                String phone = p[2];
                String email = p[3];

                system.addPatient(name, phone, email);
            }

        } catch(IOException e){
            System.out.println("Patients file not found.");
        }
    }

    // exams save
    public void saveExams(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("exams.txt"))){

            for(Exam exam : system.getAllExams()){

                String type;

                if(exam instanceof ImagingExamination){
                    type = ((ImagingExamination) exam).getMachineType();
                } 
                else if(exam instanceof MicrobiologicalExamination){
                    type = ((MicrobiologicalExamination) exam).getSampleType();
                } 
                else {
                    type = ((SpecializedExamination) exam).getExamSpecialty();
                }

                bw.write(exam.getExamID() + "," +
                         exam.getExamName() + "," +
                         exam.getCategoryName() + "," +
                         exam.getMaxSlotsPerDay() + "," +
                         exam.getCostValue() + "," +
                         exam.getDoctorID() + "," +
                         type);

                bw.newLine();
            }

        } catch(IOException e){
            System.out.println("Error saving exams");
        }
    }

    // exams load
    public void loadExams(){
        try(BufferedReader br = new BufferedReader(new FileReader("exams.txt"))){

            String line;
            while((line = br.readLine()) != null){
                String[] p = line.split(",");
                String name = p[1];
                String category = p[2];
                int maxSlots = Integer.parseInt(p[3]);
                double cost = Double.parseDouble(p[4]);
                int doctorId = Integer.parseInt(p[5]);
                String type = p[6];

                system.addExam(name, category, maxSlots, cost, doctorId, type);
            }

        } catch(IOException e){
            System.out.println("Exams file not found.");
        }
    }

    // appointments save
    public void saveAppointments(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("appointments.txt"))){

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");

            for(Appointment appointment : system.getAllAppointments()){

                bw.write(appointment.getAppointmentId() + "," +
                         appointment.getPatientID() + "," +
                         appointment.getExamId() + "," +
                         appointment.getFastResults() + "," +
                         appointment.getExamDate().format(formatter));

                bw.newLine();
            }

        } catch(IOException e){
            System.out.println("Error saving appointments");
        }
    }

    // appointments load
    public void loadAppointments(){
        try(BufferedReader br = new BufferedReader(new FileReader("appointments.txt"))){

            String line;
            while((line = br.readLine()) != null){
                String[] p = line.split(",");
                int patientId = Integer.parseInt(p[1]);
                int examId = Integer.parseInt(p[2]);
                boolean fast = Boolean.parseBoolean(p[3]);

                system.addAppointment(patientId, examId, fast, p[4]);
            }


        } catch(IOException e){
            System.out.println("Appointments file not found.");
        }
    }

   
    // all methods

    public void saveAll(){
        saveDoctors();
        savePatients();
        saveExams();
        saveAppointments();
    }

    public void loadAll(){
        loadDoctors();
        loadPatients();
        loadExams();
        loadAppointments();
        system.syncCounters();
    }
}