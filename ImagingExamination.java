public class ImagingExamination extends Exam {
    String machineType;
    final double costIncreaseRate = 0.1;

    public ImagingExamination(int examID, String examName, String examCategory, int maxSlotsPerDay, int cost, int doctorID, String machineType){
        super(examID, examName, examCategory, maxSlotsPerDay, cost, doctorID);
        this.machineType=machineType;
    }

    // getters

    public String getMachineType(){
        return machineType;
    }

    // setters

    public void setMachineType(String machineType){
        this.machineType=machineType;
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
    
}
