/**
 * Created by justinleo on 2017-01-25.
 */
public class Customer {
    String name;
    CellPhone phone;
    PhonePlan phonePlan;
    float balance;
    private int callsMade;

    public PhonePlan getPlan(){
        return this.phonePlan;
    }

    public Customer(String n, CellPhone t, PhonePlan p){
        if (p != null){
            if (true == p.getPlanType()){
                //then payAsYouGo
                balance = 0.40f;
            }else{
                balance = 0f;
            }
            phonePlan = p;
        }
        name = n;
        phone = t;
        callsMade = 0;


    }

    public String toString(){

        return name + " with a " + phone.getModel() + " on a" + phonePlan.getPlanTypeAsString() + "with " + phonePlan.getMinutesRemaining() +
                "minutes and " + phonePlan.getDataRemaining() + "KB remaining ";

    }

    public void phone(Customer talkTo, int callLength){
        boolean isCallAllowed = true;
        if (this.phonePlan.getPlanType() || talkTo.phonePlan.getPlanType()) {
            if (this.phonePlan.getPlanType() && callLength > this.phonePlan.getMinutesRemaining()) {
                //System.out.println(" Call not allowed ");
                isCallAllowed = false;
            }


            if (talkTo.phonePlan.getPlanType() && callLength > talkTo.phonePlan.getMinutesRemaining()) {
                //System.out.println(" Call not allowed ");
                isCallAllowed = false;
            }
        }
        if (isCallAllowed) {
            this.phonePlan.setMinutesUsed(this.phonePlan.getMinutesUsed() + callLength);
            talkTo.phonePlan.setMinutesUsed(talkTo.phonePlan.getMinutesUsed() + callLength);

            this.callsMade++;
            talkTo.callsMade++;


        }
    }

    public void buyMinutes(int minsPurchased){
        if (this.phonePlan.getPlanType()){
            balance = balance + minsPurchased;
            this.phonePlan.setMinutesAllowed(this.phonePlan.getMinutesAllowed() + minsPurchased);
        }

    }

    public void accessInternet(int internetUsageKB){
        if(this.phonePlan.getPlanType()){
            if(this.phonePlan.getDataRemaining() >= internetUsageKB) {
                this.phonePlan.setDataUsed(this.phonePlan.getDataUsed() + internetUsageKB);
            }
            else{
                this.phonePlan.setDataUsed(this.phonePlan.getDataAllowed());
                this.phonePlan.setDataAllowed(0);
            }
        }else{
            this.phonePlan.setDataUsed(this.phonePlan.getDataUsed() + internetUsageKB);
        }

    }

    public void printMonthlyStatement(){
        System.out.println("Name:        " + "          " + name);
        System.out.println("Plan type:  " + "          " + phonePlan.getPlanTypeAsString());
        System.out.println("Minutes used:" + "          " + phonePlan.getMinutesUsed());
        if(this.phonePlan.getPlanType()){
            System.out.println("Minutes remaining:" + "     " + phonePlan.getMinutesRemaining());
        }
        System.out.println("Data used:   " + "          " + phonePlan.getDataUsed());
        if(this.phonePlan.getPlanType()){
            System.out.println("Data remaining:" + "        " + phonePlan.getDataRemaining());
        }
        System.out.println("Calls made:  " + "          " + this.callsMade);


        //this is only for monthly guys
        if(!this.phonePlan.getPlanType()){
            int baseMinutesPrice = this.phonePlan.getMinutesAllowed() == 100 ? 15 : 25;
            float baseDataPrice = (this.phonePlan.getDataAllowed()/1000000.00f) * 10;
            float overtimeMinutesPrice = 0;
            if(this.phonePlan.getMinutesUsed() - this.phonePlan.getMinutesAllowed() > 0){
                overtimeMinutesPrice = (this.phonePlan.getMinutesUsed() - this.phonePlan.getMinutesAllowed()) * 0.15f;
            }
            float overtimeDataPrice = 0.0f;
            if(this.phonePlan.getDataUsed() - this.phonePlan.getDataAllowed() > 0){
                overtimeDataPrice = (this.phonePlan.getDataUsed() - this.phonePlan.getDataAllowed()) * .00005f;
            }
            float monthlyCharges = baseMinutesPrice + baseDataPrice;
            float totalBeforeHST = baseMinutesPrice + baseDataPrice + overtimeMinutesPrice + overtimeDataPrice;
            float hst = totalBeforeHST * .13f;
            float totalAfterHSt = totalBeforeHST + hst;

            System.out.println(String.format( "Monthly Charges:       %.2f", monthlyCharges ));
            System.out.println(String.format("Voice Overtime charges:%.2f", overtimeMinutesPrice));
            System.out.println(String.format("Data Overtime charges: %.2f", overtimeDataPrice));
            System.out.println(String.format("HST:                   %.2f", hst));
            System.out.println(String.format("Total Due:             %.2f", totalAfterHSt));

        }
        else{
            //for pay as you go guys
            float monthlyCharges = this.phonePlan.getMinutesAllowed() * 0.4f;
            System.out.println(String.format( "Monthly Charges:       %.2f", monthlyCharges ));
            System.out.println(String.format("HST:                   %.2f", monthlyCharges*0.13f));
            System.out.println(String.format("Total Due:             %.2f", monthlyCharges*1.13f));
        }
        System.out.println("");

    }
}




