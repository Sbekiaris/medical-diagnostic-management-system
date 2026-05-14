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
            choice = scanner.nextInt();
            switch(choice){
                case 1:
                    doctorsMenu();
                    break;
                case 2:
                   patientsMenu();
                    break;
                case 3:
                    // examsMenu();
                    break;
                case 4:
                    // appointmentsMenu();
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
            choice = scanner.nextInt();
            switch(choice){
                case 1:
                    scanner.nextLine(); // clear newline from previous nextInt 
                    System.out.println("Enter Doctor's name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter Doctor's phone");
                    String phone = scanner.nextLine();
                    System.out.println("Enter years of experience:");
                    int years = Integer.parseInt(scanner.nextLine());
                    String specialty = chooseSpecialty();
                    system.addDoctor(name, phone, specialty, years);
                    System.out.println("Doctor added successfully!");
                    break;
                case 2:
                    System.out.println("All Doctors");
                   printAllDoctors();
                    break;
                case 3:{        // added block scope to prevent conflicts with doctorId and doctor variables
                    System.out.println("Doctors List");
                    printAllDoctors();
                    System.out.println("Enter doctor ID");
                    int doctorId = scanner.nextInt();
                    scanner.nextLine(); // clear newline
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
                    System.out.println("Doctors List");
                    printAllDoctors();
                    System.out.println("Enter doctor ID");
                    int doctorId = scanner.nextInt();
                    scanner.nextLine(); // clear newline
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

            choice = scanner.nextInt();
            switch(choice){
                case 1:
                    scanner.nextLine(); // clear newline from previous nextInt 
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
                    System.out.println("All Patients");
                    printAllPatients();
                    break;
                case 3:
                    System.out.println("Patients List");
                    printAllPatients();
                    System.out.println("Enter a Patient ID to get all available Appointments");
                    int patientId = scanner.nextInt();
                    scanner.nextLine();  // clear newline
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

            String input = scanner.nextLine();

            try{
            choice = Integer.parseInt(input);
            } catch(Exception e){
                System.err.println("Please enter a number between 1-4");
                continue;
            }

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

    private void printAllDoctors(){
        for(Doctor doctor : system.getAllDoctors()){
            System.out.println(doctor);
        }
    }

    private void printAllPatients(){
        for(Patient patient : system.getAllPatients()){
            System.out.println(patient);
        }
    }

}
