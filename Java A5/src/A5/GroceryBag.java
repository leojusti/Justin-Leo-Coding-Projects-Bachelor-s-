package A5;


public class GroceryBag implements Carryable{

    public static final double MAX_WEIGHT = 5;

    private static final int MAX_ITEMS = 25;

    private GroceryItem[] items = new GroceryItem[MAX_ITEMS];
    private int numItems;
    private float totalWeight = 0.0f;

    public GroceryItem[] getItems() {
        return items;
    }

    public int getNumItems() {
        return numItems;
    }

    public void setNumItems(int count) {
        this.numItems = count;
    }

    public float getWeight() {
        return totalWeight;
    }

    /**
     * No arg cons
     */
    public GroceryBag() {
    }

    public String toString() {
        if (getNumItems() > 0)
            return "A " + this.getWeight() + " grocey bag with " + getNumItems() + " items.";
        else
            return ("An empty grocery bag");
    }

    public boolean canHold(GroceryItem g) {
        return (((totalWeight + g.getWeight()) <= MAX_WEIGHT) && (numItems <= MAX_ITEMS));
    }

    public void addItem(GroceryItem g) {
        if (canHold(g)) {
            items[numItems++] = g;
            totalWeight += g.getWeight();
        }

    }

    public void removeItem(GroceryItem item) {

//        GroceryItem[] resultingItems = new GroceryItem[MAX_ITEMS];
//        int resultingItemCount = 0;
//        String itemToBeRemovedName = item.getName();
//        boolean removed = false;
//
//        for(int i=0; i<this.getNumItems(); i++){
//            GroceryItem tempItem = getItems()[i];
//            if(!tempItem.getName().equals(itemToBeRemovedName) && !removed){
//                resultingItems[resultingItemCount] = tempItem;
//                resultingItemCount++;
//                removed = true;
//            }
//        }
//
//        items = resultingItems;
//        numItems = resultingItemCount;

//        String itemToBeRemovedName = item.getName();
//        for (int i = 0; i < this.getNumItems(); i++) {
//            GroceryItem currentItem = this.getItems()[i];
//
//            if (currentItem.getName().equals(itemToBeRemovedName)) {
//
//                if (i != this.getNumItems() - 1) //if i is not the last element
//                    items[i] = items[this.getNumItems() - 1];
//
//                items[this.getNumItems() - 1] = null;
//                numItems--;
//                totalWeight = totalWeight-item.getWeight();
//                break;
//            }
//        }

        //Prof version
        for (int i = 0; i < numItems; i++) {
            if (items[i] == item) {
                totalWeight -= items[i].getWeight();
                items[i] = items[numItems - 1];
                numItems -= 1;
                return;
            }
        }
    }

    public GroceryItem heaviestItem() {
        if (numItems == 0)
            return null;

        GroceryItem heaviestItem = null;
        float heaviestItemWeight = 0.0f;

        for (int i = 0; i < this.getNumItems(); i++) {
            GroceryItem currentItem = this.getItems()[i];
            if (currentItem.getWeight() > heaviestItemWeight) {
                heaviestItem = currentItem;
                heaviestItemWeight = currentItem.getWeight();
            }
        }

        return heaviestItem;
    }

    public boolean has(GroceryItem item) {

        for (int i = 0; i < this.getNumItems(); i++) {
            GroceryItem currentItem = getItems()[i];
            if (currentItem.getName().equals(item.getName())) {
                return true;
            }
        }
        return false;
    }

    public PerishableItem[] unpackPerishables() {

        int perishableCount = 0;
        for (int i=0; i<numItems; i++) {
            if (items[i] instanceof PerishableItem)
                perishableCount++;
        }
        PerishableItem[] perishables = new PerishableItem[perishableCount];
        perishableCount = 0;
        for (int i=0; i<numItems; i++) {
            if (items[i] instanceof PerishableItem) {
                perishables[perishableCount++] = (PerishableItem)items[i];
                removeItem(items[i]);
                i--;
            }
        }
        return perishables;
    }

    @Override
    public String getContents() {

        String content = "";
        for (int i = 0; i < getNumItems(); i++) {
            GroceryItem thisItem = this.getItems()[i];
            content = content + "   " + thisItem.toString() + "\n";
        }

        return content;

    }

    @Override
    public String getDescription() {
        return "GROCERY BAG (" + this.getWeight() + " kg)";
    }

    @Override
    public float getPrice() {

        float price = 0.0f;
        for (int i = 0; i < getNumItems(); i++) {
            GroceryItem thisItem = this.getItems()[i];
            price += thisItem.getPrice();
        }
        return price;
    }
}