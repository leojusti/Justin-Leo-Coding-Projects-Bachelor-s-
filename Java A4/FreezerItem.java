package A3;
/**
 *
 * @author justinleo
 */
public class FreezerItem extends PerishableItem{

    public FreezerItem(String item, float price, float weight) {
        super(item, price, weight);
    }

    public String toString(){
        return super.toString() + " [keep frozen]";
    }

}