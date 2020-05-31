package A5;

/**
 *
 * @author justinleo
 */
public class RefrigeratorItem extends PerishableItem{

    public RefrigeratorItem(String item, float price, float weight) {
        super(item, price, weight);
    }

    public String toString(){
        return super.toString() + " [keep refrigerated]";
    }

}
