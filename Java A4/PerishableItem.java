/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A3;

/**
 *
 * Created by justinleo
 */
public abstract class PerishableItem extends GroceryItem{

    public PerishableItem(String item, float price, float weight){
        super(item, price, weight);
    }

    public String toString(){
        return super.toString() + " (perishable)";
    }

}
