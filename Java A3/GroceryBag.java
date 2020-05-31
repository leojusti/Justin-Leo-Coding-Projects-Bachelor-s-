package A3;

/**
 * Created by justinleo on 2017-01-31.
 */
public class GroceryBag {

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

    public void addItem(GroceryItem groceryItem) {
        float weightWillBe = this.getWeight() + groceryItem.getWeight();
        if (weightWillBe <= MAX_WEIGHT) {
            items[this.getNumItems()] = groceryItem;
            numItems++;
            totalWeight += groceryItem.getWeight();
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

        String itemToBeRemovedName = item.getName();
        for (int i = 0; i < this.getNumItems(); i++) {
            GroceryItem currentItem = this.getItems()[i];

            if (currentItem.getName().equals(itemToBeRemovedName)) {

                if (i != this.getNumItems() - 1) //if i is not the last element
                    items[i] = items[this.getNumItems() - 1];

                items[this.getNumItems() - 1] = null;
                numItems--;
                break;
            }
        }
    }

    public GroceryItem heaviestItem() {
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

    public GroceryItem[] unpackPerishables() {

        int totalPerishables = 0;
        for (int i = 0; i < getNumItems(); i++) {
            if (this.getItems()[i].getPerishable()) {
                totalPerishables++;
            }
        }


        GroceryItem[] perishables = new GroceryItem[totalPerishables];
        int pCount = 0;
        for (int i = 0; i < getNumItems(); i++) {
            if (this.getItems()[i].getPerishable()) {
                perishables[pCount] = this.getItems()[i];
                pCount++;

            }
        }

        int npCount = this.getNumItems() - totalPerishables;
        int npIndex = 0;
        GroceryItem[] nonPerishables = new GroceryItem[npCount];
        for (int x = 0; x < this.getNumItems(); x++) {
            if (!this.getItems()[x].getPerishable()) {
                nonPerishables[npIndex] = this.getItems()[x];
                npIndex++;
            }
        }
        items = nonPerishables;
        numItems = npCount;

        return perishables;

    }
}