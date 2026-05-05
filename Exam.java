public abstract class Exam{
    private int examID;
    private String examName;
    private String examCategory;
    private int maxSlotsPerDay;
    private double cost;
    private int doctorID;

    public Exam(int examID, String examName, String examCategory, int maxSlotsPerDay, double cost, int doctorID){
        this.examID=examID;
        this.examName=examName;
        this.examCategory=examCategory;
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

    public String getExamCategory(){
        return examCategory;
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

    abstract double getCost(boolean fastResults);

}