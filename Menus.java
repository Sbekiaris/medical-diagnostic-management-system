import java.util.List;
import java.util.Scanner;

public class Menus {
    private ManagementSystem system;
    private Scanner scanner;

    public Menus(ManagementSystem system){
        this.system=system;
        this.scanner= new Scanner(System.in);
    }

    // main menu implementation
    public void mainMenu(){
        int choice;
        do{
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Doctors");
            System.out.println("2. Patients");
            System.out.println("3. Exams");
            System.out.println("4. Appointments");
            System.out.println("5. Statistics");
            System.out.println("0. Exit");
            System.out.println("Choose an option, or press 0 to exit");
            choice = readInt();
            
            switch(choice){
                case 1:
                    doctorsMenu();
                    break;
                case 2:
                   patientsMenu();
                    break;
                case 3:
                    examsMenu();
                    break;
                case 4:
                    appointmentsMenu();
                    break;
                case 5:
                    // statisticsMenu();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Please select 1 - 5 or press 0 to exit");

            }
        } while(choice != 0);
    }

    // submenus implementation

    // Doctors Menu
    private void doctorsMenu(){
        int choice;
        do{
            System.out.println("\n=== DOCTORS MENU ===");
            System.out.println("1. Add a Doctor");
            System.out.println("2. Show all Doctors");
            System.out.println("3. Doctors Exams");
            System.out.println("4. Doctors Appointments");
            System.out.println("0. Return to Main Menu");
            System.out.println("Choose an option, or press 0 to go to Main Menu");
            choice = readInt();
            
            switch(choice){
                case 1:
                    System.out.println("Enter Doctor's name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter Doctor's phone");
                    String phone = scanner.nextLine();
                    System.out.println("Enter years of experience:");
                    int years = readInt();
                    String specialty = chooseSpecialty();
                    system.addDoctor(name, phone, specialty, years);
                    System.out.println("Doctor added successfully!");
                    break;
                case 2:
                    System.out.println("All Doctors:");
                    printAll(system.getAllDoctors());
                    break;
                case 3:{        // added block scope to prevent conflicts with doctorId and doctor variables
                    System.out.println("Doctors List:");
                    printAll(system.getAllDoctors());
                    System.out.println("Enter doctor ID");
                    int doctorId = readInt();
                    Doctor doctor = system.getDoctor(doctorId);
                    if(doctor == null){
                        System.out.println("Doctor not found");
                        break;
                    }
                    System.out.println("Selected Doctor:");
                    System.out.println(doctor);
                    System.out.println("Doctor's Examinations");
                    for(Exam exam : system.getExamsByDoctor(doctorId)){
                        System.out.println(exam);
                    }
                    break;
                }
                case 4:{
                    System.out.println("Doctors List:");
                    printAll(system.getAllDoctors());
                    System.out.println("Enter doctor ID");
                    int doctorId = readInt();
                    Doctor doctor = system.getDoctor(doctorId);
                    if(doctor == null){
                        System.out.println("Doctor not found");
                        break;
                    }
                    System.out.println("Selected Doctor:");
                    System.out.println(doctor);
                    System.out.println("Doctor's Appointments");
                    for(Appointment appointment : system.getDoctorAppointments(doctorId)){
                        System.out.println(appointment);
                }
                    break;
                    }
                case 0:
                    System.out.println("Exiting Doctors Menu...");
                    break;
                default:
                    System.out.println("Please select 1 - 4 or press 0 to go to the Main Menu");

            }
        } while(choice != 0);

    }

    // Patients Menu
    private void patientsMenu(){
        int choice;
        do{
            System.out.println("\n=== PATIENTS MENU ===");
            System.out.println("1. Add a Patient");
            System.out.println("2. Show all Patients");
            System.out.println("3. Patients Appointments");
            System.out.println("0. Return to Main Menu");
            System.out.println("Choose an option, or press 0 to go to Main Menu");
            choice = readInt();
            
            switch(choice){
                case 1:
                    System.out.println("Enter Patient's name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter Patient's phone: ");
                    String phone = scanner.nextLine();
                    System.out.println("Enter Patient's email: ");
                    String email = scanner.nextLine();
                    system.addPatient(name, phone, email);
                    System.out.println("Patient added successfully!");
                    break;
                case 2:
                    System.out.println("All Patients:");
                    printAll(system.getAllPatients());
                    break;
                case 3:
                    System.out.println("Patients List:");
                    printAll(system.getAllPatients());
                    System.out.println("Enter a Patient ID to get all available Appointments");
                    int patientId = readInt();
                    Patient patient = system.getPatient(patientId);
                    if(patient == null){
                        System.out.println("Patient not found");
                        break;
                    }
                    System.out.println("Selected Patient");
                    System.out.println(patient);
                    System.out.println("Patient's Appointments");
                    for(Appointment appointment : system.getAppointmentsByPatient(patientId)){
                        System.out.println(appointment);
                    }
                    break;
                case 0:
                    System.out.println("Exiting Patients Menu...");
                    break;
                default:
                    System.out.println("Please select 1 - 3 or press 0 to go to the Main Menu");

            }

        } while(choice !=0);
    }

    // Exams Menu
    private void examsMenu(){
        int choice;
        do{
            System.out.println("\n=== EXAMS MENU ===");
            System.out.println("1. Add an Exam");
            System.out.println("2. Show all Exams");
            System.out.println("3. Exam Appointments");
            System.out.println("0. Return to Main Menu");
            System.out.println("Choose an option, or press 0 to go to Main Menu");
            choice = readInt();

            switch(choice){
                case 1:
                    System.out.println("Doctors List:");
                    printAll(system.getAllDoctors());
                    System.out.println("Enter Doctor's ID"); 
                    int doctorId = readInt();
                    Doctor doctor = system.getDoctor(doctorId);
                    if(doctor == null){
                        System.out.println("Doctor not found");
                        break;
                    }
                    System.out.println("Enter exam name:");
                    String examName = scanner.nextLine();
                    String category = chooseExamCategory();
                    String examType = chooseExamType(category);
                    System.out.println("Enter max slots per day:");
                    int maxSlotsPerDay = readInt();
                    System.out.println("Enter cost:");
                    double cost = readDouble();
                    system.addExam(examName, category, maxSlotsPerDay, cost, doctorId, examType);
                    System.out.println("Exam added successfully");
                    break;
                case 2:
                    System.out.println("All Exams:");
                    printAll(system.getAllExams());
                    break;
                case 3:
                    System.out.println("Exams List:");
                    printAll(system.getAllExams());
                    System.out.println("Enter an Exam ID to get all Appointments");
                    int examId = readInt();
                    Exam exam = system.getExam(examId);
                    if(exam == null){
                        System.out.println("Exam not found");
                        break;
                    }
                    System.out.println("Selected Exam");
                    System.out.println(exam);
                    System.out.println("Exam's Appointments");
                    for(Appointment appointment : system.getAppointmentsByExam(examId)){
                        System.out.println(appointment);
                    }
                    break;
                case 0:
                    System.out.println("Exiting Exams Menu...");
                    break;
                default:
                    System.out.println("Please select 1 - 3 or press 0 to go to the Main Menu");

            }

        } while(choice !=0);
    }
    
    // Appointments menu
    private void appointmentsMenu(){
        int choice;
        do{
            System.out.println("\n=== APPOINTMENTS MENU ===");
            System.out.println("1. Add an Appointment");
            System.out.println("2. Show all Appointments");
            System.out.println("3. Show Patient's Appointments");
            System.out.println("4. Delete an Appointment");
            System.out.println("5. Show Day's Appointments");
            System.out.println("0. Return to Main Menu");
            System.out.println("Choose an option, or press 0 to go to Main Menu");
            choice = readInt();

            switch(choice){
                case 1:
                    System.out.println("Patients List:");
                    printAll(system.getAllPatients());
                    System.out.println("Enter Patient's ID");
                    int patientId = readInt();
                    System.out.println("Exams List:");
                    printAll(system.getAllExams());
                    System.out.println("Enter Exam's ID");
                    int examId = readInt();
                    System.out.println("Enter Appointment's date(DD:MM:YYYY)");
                    String date = scanner.nextLine();
                    boolean fastResults = chooseFastResults();
                    String result = system.validateAppointment(patientId, examId, date);

                    switch(result){
                        case "INVALID_PATIENT":
                            System.out.println("Patient not found");
                            return;
                        case "INVALID_EXAM":
                        System.out.println("Exam not found");
                        return;
                        case "INVALID_DATE":
                            System.out.println("Invalid date format (DD:MM:YYYY)");
                                return;
                        case "FULL_SLOTS":
                            System.out.println("No available slots for this exam on this date");
                            return;
                        case "PASS":
                            break;
                    }

                    boolean success = system.addAppointment(patientId, examId, fastResults, date);
                    if(success){
                        System.out.println("Appointment added successfully!");
                    } else{
                        System.out.println("Failed to add appointment");
                    }
                    break;
                case 2:
                    System.out.println("All Appointments");
                    printAll(system.getAllAppointments());
                    break;
            }

        } while(choice !=0);
    }
    
    // auxiliary menus
    private String chooseSpecialty() {
        int choice;
        do{
            System.out.println("Specialties");
            System.out.println("1. Cardiology");
            System.out.println("2. Radiology");
            System.out.println("3. Microbiology");
            System.out.println("4. Neurology");
            System.out.println("Select a specialty:");
            choice = readInt();
            
            switch (choice) {
                case 1:
                    return "Cardiology";
                case 2:
                    return "Radiology";
                case 3:
                    return "Microbiology";
                case 4:
                    return "Neurology";
                default:
                    System.out.println("Choose between 1 and 4 please");
            }
        } while(true);
    }

    private String chooseExamCategory(){
        int choice;
        do{
            System.out.println("Exam Categories");
            System.out.println("1. Imaging");
            System.out.println("2. Microbiological");
            System.out.println("3. Specialized");
            System.out.println("Select a category:");
            choice = readInt();
            
            switch(choice){
                case 1:
                    return "Imaging";
                case 2:
                    return "Microbiological";
                case 3:
                    return "Specialized";
                default:
                    System.out.println("Please select one of the three Exam Categories");
            }
        } while(true);
    }

    private String chooseExamType(String category){
        int choice;
        do{
            System.out.println("Exam Types");

            switch(category){
                case "Imaging":
                    System.out.println("1. MRI");
                    System.out.println("2. CT");
                    System.out.println("3. X-Ray");

                    choice = readInt();

                    switch(choice){
                        case 1: 
                            return "MRI";
                        case 2: 
                            return "CT";
                        case 3: 
                            return "X-Ray";
                        default:
                            System.out.println("Invalid choice");
                }
                break;

                case "Microbiological":
                    System.out.println("1. Blood");
                    System.out.println("2. Urine");
                    System.out.println("3. Swab");

                    choice = readInt();

                    switch(choice){
                        case 1: 
                            return "Blood";
                        case 2: 
                            return "Urine";
                        case 3: 
                            return "Swab";
                        default:
                            System.out.println("Invalid choice");
                }
                break;

                case "Specialized":
                    System.out.println("1. Holter");
                    System.out.println("2. EEG");
                    System.out.println("3. SPT");

                    choice = readInt();

                    switch(choice){
                        case 1: 
                            return "Holter";
                        case 2: 
                            return "EEG";
                        case 3: 
                            return "SPT";
                        default:
                            System.out.println("Invalid choice");
                }
                break;
            }

        } while(true);
    }

    private boolean chooseFastResults(){
        String choice;
        do{
            System.out.println("Does the patient wish fast results?(Yes/No)");
            choice = scanner.nextLine();
            switch(choice.toLowerCase()){
            case "yes":
            case "y":
                return true;
            case "no":
            case "n":
                return false;
            default:
                System.out.println("Invalid choice. Please type Yes or No");
            }
        } while(true);
    }

    private <T> void printAll(List<T> list){
        if(list.isEmpty()){
            System.out.println("No records found.");
            return;
        }
        for(T entity : list){
            System.out.println(entity);
        }

    }

    private int readInt() {
        while(true){
            String input = scanner.nextLine().trim();

            if(input.isEmpty()){
            System.out.println("Please enter a valid number or press 0 to quit.");
                continue;
            }

            try{
                return Integer.parseInt(input);
            } catch(NumberFormatException e){
            System.out.println("Invalid number. Try again.");
            }
        }
    }

    private double readDouble() {
        while(true){
            String input = scanner.nextLine().trim();

            if(input.isEmpty()){
            System.out.println("Please enter a valid number or press 0 to quit.");
                continue;
            }

            try{
                return Double.parseDouble(input);
            } catch(NumberFormatException e){
            System.out.println("Invalid number. Try again.");
            }
        }
    }

}
