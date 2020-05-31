/**
 * Created by justinleo on 2017-01-25.
 */
public class PhonePlan {
    private int minutesAllowed;  //never decreases
    private int minutesUsed;
    private int dataAllowed; //never decreases
    private int dataUsed;
    private boolean planType; //true -> payAsYouGo


    public PhonePlan(int a, int d, boolean p) {
        minutesAllowed = a;
        dataAllowed = d;
        planType = p;
        minutesUsed = 0;
        dataUsed = 0;

    }

    public int getMinutesAllowed(){return minutesAllowed;}
    public int getMinutesUsed(){return minutesUsed;}
    public int getDataUsed(){return dataUsed;}
    public int getDataAllowed(){return dataAllowed;}
    public boolean getPlanType(){return planType;}

    public int getMinutesRemaining(){
        int x = minutesAllowed - minutesUsed;
        return x;
    }

    public int getDataRemaining(){
        int retVal =0;
        if(getPlanType()){
            if(dataAllowed - dataUsed < 0){
                retVal = 0;
            }
        }else{
            int x = dataAllowed - dataUsed;
            retVal = x ;
        }

        return retVal;

    }

    public String getPlanTypeAsString() {
       double x = getDataAllowed()/1000000.00;


        if (planType == true) {  //need == bool
            return (" Pay-as-you-go ");
        } else return (" Regular " + "(" + getMinutesAllowed() + " " + "minute," + " " + x+ "GB data) " + "Monthly plan ");

    }

    public String whichPlan(){
        if (planType == true) {
            return "minutes and";
        } else return "minutes remaining and";
    }

    public String toString(){
        return getPlanTypeAsString() + "Plan with " + "" + getMinutesRemaining() + " " + whichPlan() + " " + getDataRemaining() +"KB" + " remaing ";
    }


    public void setMinutesAllowed(int minutesAllowed) {
        this.minutesAllowed = minutesAllowed;
    }

    public void setMinutesUsed(int minutesUsed) {
        this.minutesUsed = minutesUsed;
    }

    public void setDataAllowed(int dataAllowed) {
        this.dataAllowed = dataAllowed;
    }

    public void setDataUsed(int dataUsed) {
        this.dataUsed = dataUsed;
    }

    public void setPlanType(boolean planType) {
        this.planType = planType;
    }
}



