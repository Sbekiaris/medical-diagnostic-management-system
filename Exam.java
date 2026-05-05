public abstract class Exam{
    private int examID;
    private String examName;
    private String categoryName;
    private int maxSlotsPerDay;
    private double cost;
    private int doctorID;

    public Exam(int examID, String examName, String categoryName, int maxSlotsPerDay, double cost, int doctorID){
        this.examID=examID;
        this.examName=examName;
        this.categoryName=categoryName;
        this.maxSlotsPerDay=maxSlotsPerDay;
        this.cost=cost;
        this.doctorID=doctorID;
    }
    
    public int getExamID(){
        return examID;
    }

    public String getExamName(){
        return examName;
    }

    public String getCategoryName(){
        return categoryName;
    }

    public int getMaxSlotsPerDay(){
        return maxSlotsPerDay;
    }
    
    public double getCostValue(){
        return cost;
    }

    public int getDoctorID(){
        return doctorID;
    }

    public abstract double getCost(boolean fastResults); // takes a boolean fastResults parameter to determine whether a patient wants fastResults

}