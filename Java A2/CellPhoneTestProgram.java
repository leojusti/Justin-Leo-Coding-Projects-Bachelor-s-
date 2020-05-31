/**
 * Created by justinleo on 2017-01-25.
 */
public class CellPhoneTestProgram {
    public static void main(String args[]){
        CellPhone iPhone,galaxy,priv;
        iPhone = new CellPhone("iPhone6Plus", "Apple", 12, 915.0f );
        galaxy = new CellPhone("Galaxy S7", "Samsung", 18, 900.00f);
        priv = new CellPhone("PRIV", "Blackberry", 24, 890.00f);




        System.out.println("Here is the Apple phone information: ");
        System.out.println(iPhone.getModel());
        System.out.println(iPhone.getManufacturer());
        System.out.println(iPhone.getMonthsWarranty());
        System.out.println(iPhone.getPrice());

        System.out.println("\nHere is the Samsung phone information: ");
        System.out.println(galaxy.getModel());
        System.out.println(galaxy.getManufacturer());
        System.out.println(galaxy.getMonthsWarranty());
        System.out.println(galaxy.getPrice());


        System.out.println("\nHere is the Blackberry phone information: ");
        System.out.println(priv.getModel());
        System.out.println(priv.getManufacturer());
        System.out.println(priv.getMonthsWarranty());
        System.out.println(priv.getPrice());

        //update iphone properties
        iPhone.setModel("iPhoneSE");
        iPhone.setPrice(590.0f);

        System.out.println("\nHere is the Apple phone's new information: ");
        System.out.println(iPhone.getModel());
        System.out.println(iPhone.getManufacturer());
        System.out.println(iPhone.getMonthsWarranty());
        System.out.println(iPhone.getPrice());


        System.out.println("\nThe total cost of all phones is $" + (iPhone.getPrice() + galaxy.getPrice() + priv.getPrice()));

        if (iPhone.getMonthsWarranty() > galaxy.getMonthsWarranty() &&
                iPhone.getMonthsWarranty() > priv.getMonthsWarranty()) {
            System.out.println("The Apple phone has the longest warranty");
        }
        else if (galaxy.getMonthsWarranty() > iPhone.getMonthsWarranty() &&
                galaxy.getMonthsWarranty() > priv.getMonthsWarranty())
            System.out.println("\nThe Galaxy phone has the longest warranty");
        else
            System.out.println("\nThe Blackberry has the longest warranty");

    }
}
