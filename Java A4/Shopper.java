package A3;

/**
 * Shopper represents a person that buys grocery items
 */
public class Shopper {

    private static final int MAX_CART_ITEMS = 100;

    private Carryable[] cart = new Carryable[MAX_CART_ITEMS];
    private int numItems = 0;

    public Carryable[] getCart() {
        return cart;
    }

    public int getNumItems() {
        return numItems;
    }

    public Shopper(){}

    public String toString(){
        return "Shopper with shopping cart containing " + getNumItems() + " items.";
    }

    public void addItem(Carryable groceryItem){

        if (this.numItems < MAX_CART_ITEMS) {
            cart[this.getNumItems()] = groceryItem;
            numItems++;
        }

    }

    public void removeItem(Carryable item){

        String itemToBeRemovedName = item.getDescription();
        for(int i=0; i<this.getNumItems(); i++){
            GroceryItem currentItem = (GroceryItem)this.getCart()[i];

            if(currentItem.getName().equals(itemToBeRemovedName)){

                if(i != this.getNumItems()-1) //if i is not the last element
                    cart[i] = cart[this.getNumItems()-1];

                cart[this.getNumItems()-1] = null;
                numItems--;
                break;
            }
        }

    }

    public void packBags(){

        GroceryBag[] packedBags = new GroceryBag[MAX_CART_ITEMS];
        int numBagsPacked = 0;
        GroceryBag currentlyFillingBag = new GroceryBag();

        int rejectedItems = 0;


        while(this.getNumItems() != rejectedItems) {

            GroceryItem currentItemInCart = (GroceryItem)this.getCart()[rejectedItems];

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

        for(int newItem = 0; newItem < numBagsPacked; newItem++){
            this.addItem(finalPackedBags[newItem]);
        }

//        return finalPackedBags;
    }

    public void displayCartContents(){
        for(int i=0; i<numItems; i++){
            if(GroceryItem.class.isInstance(this.getCart()[i])){
                System.out.println(this.getCart()[i].getDescription());
            }else{
                GroceryBag bag = (GroceryBag)this.getCart()[i];
                System.out.println(bag.getDescription());
                for(int x=0; x<bag.getNumItems(); x++){
                    System.out.println("   " + bag.getItems()[x].toString());
                }
            }
        }
    }

    public PerishableItem[] removePerishables(){
        int perishablesCount = 0;
        PerishableItem[] perishables = new PerishableItem[MAX_CART_ITEMS];
        for(int i=0; i<numItems; i++){
            if(PerishableItem.class.isInstance(this.getCart()[i])){
                perishables[perishablesCount] = (PerishableItem)this.getCart()[i];
                this.removeItem(this.getCart()[i]);
                perishablesCount++;
            }else if(GroceryBag.class.isInstance(this.getCart()[i])){
                GroceryBag bag = (GroceryBag)this.getCart()[i];
                PerishableItem[] thisBagPerishables = bag.unpackPerishables();
                for(int x=0; x<thisBagPerishables.length; x++){
                    perishables[perishablesCount] = thisBagPerishables[x];
                    perishablesCount++;
                }
            }
        }
        return perishables;
    }

    /**
     * Return the total price of all FreezerItems
     * @return
     */
    public float computeFreezerItemCost(){
        float freezerTotal = 0.0f;
        for(int i=0; i<numItems; i++){
            if(FreezerItem.class.isInstance(this.getCart()[i])){
                freezerTotal += ((FreezerItem)this.getCart()[i]).getPrice();
            }
            else if(GroceryBag.class.isInstance(this.getCart()[i])){
                GroceryBag bag = (GroceryBag)this.getCart()[i];
                for(int x=0; x<bag.getNumItems(); x++){
                    if(FreezerItem.class.isInstance(bag.getItems()[x])){
                        freezerTotal += ((FreezerItem)(bag.getItems()[x])).getPrice();
                    }
                }
            }
        }
        return freezerTotal;
    }

    public float computeTotalCost(){
        float total = 0.0f;
        for(int i=0; i<numItems; i++){
            if(GroceryItem.class.isInstance(this.getCart()[i])){
                total += ((GroceryItem)this.getCart()[i]).getPrice();
            }
            else if(GroceryBag.class.isInstance(this.getCart()[i])){
                GroceryBag bag = (GroceryBag)this.getCart()[i];
                for(int x=0; x<bag.getNumItems(); x++){

                    total += ((GroceryItem)(bag.getItems()[x])).getPrice();
                }
            }
        }
        return total;
    }
}