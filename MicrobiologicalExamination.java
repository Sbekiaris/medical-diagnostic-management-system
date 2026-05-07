public class MicrobiologicalExamination extends Exam{
    private String sampleType;
    final double costIncreaseRate = 0.2;

    public MicrobiologicalExamination(int examID, String examName, String examCategory, int maxSlotsPerDay, double cost, int doctorID, String sampleType){
        super(examID, examName, examCategory, maxSlotsPerDay, cost, doctorID);
        this.sampleType=sampleType;
    }

    // getters

    public String getSampleType(){
        return sampleType;
    }

    // setters

    public void setSampleType(String sampleType){
        this.sampleType=sampleType;
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
            ", Sample Type: " + sampleType;
    }
    
}
