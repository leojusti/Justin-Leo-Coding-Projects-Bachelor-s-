/**
 * Created by justinleo on 2017-01-25.
 */
public class CellPhone {
    private String  model;
    private String  manufacturer;
    private int     monthsWarranty;
    private float   price;

    public CellPhone(String m, String n, int w, float p){
        model = m;
        manufacturer = n;
        monthsWarranty = w;
        price = p;

    }

    public String getModel(){return model;}
    public String getManufacturer(){return manufacturer;}
    public int getMonthsWarranty(){return monthsWarranty;}
    public float getPrice(){return price;}

    public void setModel(String model) {
            this.model = model;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setMonthsWarranty(int monthsWarranty) {
        this.monthsWarranty = monthsWarranty;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
