package A3;

/**
 * Shopper represents a person that buys grocery items
 */
public class Shopper {

    private static final int MAX_CART_ITEMS = 100;

    private GroceryItem[] cart = new GroceryItem[MAX_CART_ITEMS];
    private int numItems = 0;

    public GroceryItem[] getCart() {
        return cart;
    }

    public int getNumItems() {
        return numItems;
    }

    public Shopper(){}

    public String toString(){
        return "Shopper with shopping cart containing " + getNumItems() + " items.";
    }

    public void addItem(GroceryItem groceryItem){

        if (this.numItems < MAX_CART_ITEMS) {
            cart[this.getNumItems()] = groceryItem;
            numItems++;
        }

    }

    public void removeItem(GroceryItem item){

        String itemToBeRemovedName = item.getName();
        for(int i=0; i<this.getNumItems(); i++){
            GroceryItem currentItem = this.getCart()[i];

            if(currentItem.getName().equals(itemToBeRemovedName)){

                if(i != this.getNumItems()-1) //if i is not the last element
                    cart[i] = cart[this.getNumItems()-1];

                cart[this.getNumItems()-1] = null;
                numItems--;
                break;
            }
        }

    }

    public GroceryBag[] packBags(){
        GroceryBag[] packedBags = new GroceryBag[MAX_CART_ITEMS];
        int numBagsPacked = 0;
        GroceryBag currentlyFillingBag = new GroceryBag();

        int rejectedItems = 0;


        while(this.getNumItems() != rejectedItems) {

            GroceryItem currentItemInCart = this.getCart()[rejectedItems];

            if (currentItemInCart.getWeight() <= GroceryBag.MAX_WEIGHT) {

                if (currentlyFillingBag.getWeight() + currentItemInCart.getWeight() <= GroceryBag.MAX_WEIGHT) {
                    currentlyFillingBag.addItem(currentItemInCart); //add to the bag
                    this.removeItem(currentItemInCart); //remove from the cart

                } else {
                    packedBags[numBagsPacked] = currentlyFillingBag;
                    numBagsPacked++;
                     currentlyFillingBag = new GroceryBag();
                    currentlyFillingBag.addItem(currentItemInCart); //add to the bag
                    this.removeItem(currentItemInCart); //remove from the cart

                }
            }else{
                rejectedItems++;
            }

        }
        packedBags[numBagsPacked] = currentlyFillingBag;
        numBagsPacked++;

        GroceryBag[] finalPackedBags = new GroceryBag[numBagsPacked];
        for(int x=0; x<numBagsPacked; x++){
            finalPackedBags[x] = packedBags[x];
        }

        return finalPackedBags;
    }
}

