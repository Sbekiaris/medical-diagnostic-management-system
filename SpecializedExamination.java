public class SpecializedExamination extends Exam{
    private String examSpecialty;
    final double costIncreaseRate = 0.3;

    public SpecializedExamination(int examID, String examName, String examCategory, int maxSlotsPerDay, double cost, int doctorID, String examSpecialty){
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

    @Override
    public double getCost(boolean fastResults){
        double examCost = this.getCostValue();
        if(fastResults){
            return examCost + (examCost*costIncreaseRate);
        }
        else{
            return examCost;
        }
    }

    @Override
    public String toString() {
        return super.toString() +
            ", Exam Specialty: " + examSpecialty;
    }
    
}
