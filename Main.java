public class Main{
    public static void main(String[] args) {

        ManagementSystem system = new ManagementSystem();
        FileManager fm = new FileManager(system);
        Menus menus = new Menus(system);

        // check if files exist when executing the system
        if(fm.filesExist()){
            System.out.println("Files found. Loading data...");
            fm.loadAll();
        } else{
            System.out.println("No files found. Creating default data.");
            loadDefaultData(system);
            fm.saveAll();

        }
        // execute Menus
        menus.mainMenu();

        // save all data
        fm.saveAll();

        // close input stream
        menus.closeScanner();


    }

    // default data method

    private static void loadDefaultData(ManagementSystem system){

    // Doctors
    system.addDoctor("Dr. Smith", "111", "Cardiology", 10);
    system.addDoctor("Dr. Jones", "222", "Radiology", 8);
    system.addDoctor("Dr. Brown", "333", "Microbiology", 3);

    // Patients
    system.addPatient("John Doe", "6901", "john@mail.com");
    system.addPatient("Alice Green", "6902", "alice@mail.com");
    system.addPatient("Bob White", "6903", "bob@mail.com");

    // Imaging Exam
    system.addExam("Brain MRI", "Imaging", 5, 120.0, 2, "MRI");
    system.addExam("Chest CT", "Imaging", 8, 80.0, 2, "CT");
    system.addExam("Hand X-Ray", "Imaging", 10, 30.0, 2, "X-Ray");

    // Microbiological Exam
    system.addExam("PCR Test", "Microbiological", 20, 20.0, 3, "Blood");
    system.addExam("Urine Analysis", "Microbiological", 15, 15.0, 3, "Urine");
    system.addExam("COVID Test", "Microbiological", 10, 25.0, 3, "Swab");

    // Specialized Exam
    system.addExam("Holter Monitoring", "Specialized", 5, 50.0, 1, "Cardiology");
    system.addExam("EEG Recording", "Specialized", 10, 20.0, 1, "Neurology");
    system.addExam("Spirometry Test", "Specialized", 8, 40.0, 1, "Pulmonology");

    // Appointments
    system.addAppointment(1, 1, true,  "12:06:2026");
    system.addAppointment(2, 4, false, "13:06:2026");
    system.addAppointment(3, 7, true,  "14:06:2026");
    }
}