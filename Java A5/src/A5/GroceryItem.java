package A5;

/**
 * Created by justinleo on 2017-01-31.
 */
public class GroceryItem implements Carryable {

    private String name;
    private float price;
    private float weight;
//    private boolean perishable;

    public String getName(){return name;}
    public float getPrice(){return price;}
    public float getWeight(){return weight;}
//    public boolean getPerishable(){
//        return perishable;
//    }


    public GroceryItem(){

    }

    public GroceryItem(String item, float price, float weight){
        this.name = item;
        this.price = price;
        this.weight = weight;
//        this.perishable = false;

    }

//    public GroceryItem(String item, float price, float weight, boolean f){
//        this.name = item;
//        this.price = price;
//        this.weight = weight;
////        this.perishable = f;
//
//    }

    public String toString(){
        return this.getName() + " weighing " + getWeight() + "kg with price $" + getPrice();
    }

    @Override
    public String getContents() {
        return "";
    }

    @Override
    public String getDescription() {
        return this.getName();
    }

}