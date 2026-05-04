public class SpecializedExamination extends Exam {
    String examSpecialty;
    final double costIncreaseRate = 0.3;

    public SpecializedExamination(int examID, String examName, String examCategory, int maxSlotsPerDay, int cost, int doctorID, String examSpecialty){
        super(examID, examName, examCategory, maxSlotsPerDay, cost, doctorID);
        this.examSpecialty=examSpecialty;
    }

    // getters

    public String getExamSpecialty(){
        return examSpecialty;
    }

    // setters

    public void setExamSpecialty(String examSpecialty){
        this.examSpecialty=examSpecialty;
    }

1234555
    public double getCost(boolean fastResults){
        double examCost = this.getCostValue();
        if(fastResults){
            return examCost + (examCost*costIncreaseRate);
        }
        else{
            return examCost;
        }
    }
    
}
