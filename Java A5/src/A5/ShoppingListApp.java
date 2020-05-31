package A5;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.util.Observable;

/**
 * Created by justinleo on 2017-02-19.
 */
public class ShoppingListApp extends Application {

    public void start(Stage primaryStage) {

        Pane aPane = new Pane();

        /**
         * First column
         */

        // Label of the products list
        Label productListLabel = new Label("Products:");
        productListLabel.relocate(10, 10);
        productListLabel.setPrefSize(200, 35);

        // Create and position the "product" ListView with some products in it
        ListView<GroceryItem> productList = new ListView<>();
        productList.relocate(10, 45);
        productList.setPrefSize(200, 300);

        // Create and position the "Add" Button
        Button buyButton = new Button("Buy");
        buyButton.relocate(10, 355);
        buyButton.setPrefSize(200, 25);

        productList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                buyButton.setDisable(false);
            }
        });

        /**
         * Second column
         */

        // Label of the products list
        Label cartLabel = new Label("Shopping Cart:");
        cartLabel.relocate(220, 10);
        cartLabel.setPrefSize(200, 35);

        // Create and position the "cart" ListView with some items in it
        Shopper s1 = new Shopper();
        ListView<String> cartList = new ListView<>();
        String[] cartItems = {};

        cartList.relocate(220, 45);
        cartList.setPrefSize(200, 300);

        // Create and position the "Return" Button
        Button returnBtn = new Button("Return");
        returnBtn.relocate(220, 355);
        returnBtn.setPrefSize(200, 25);

        buyButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {

                ObservableList<String> localCartItems = cartList.getItems();
                GroceryItem product = productList.getSelectionModel().getSelectedItem();
                localCartItems.add(product.getName());
                s1.addItem(product);
//                cartList.setItems(s1.g);

            }
        });


        //when an item in the cast list is clicked the 'returnBtn' should be enabled
        cartList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {

//                System.out.println("Debug 1");
                if(cartList.getItems().size() > 0)
                    returnBtn.setDisable(false);
                else
                    returnBtn.setDisable(true);
            }
        });



        /**
         * Third column
         */

        // Label of the products list
        Label contentsLabel = new Label("Contents:");
        contentsLabel.relocate(430, 10);
        contentsLabel.setPrefSize(300, 35);

        // Create and position the "cart" ListView with some items in it
        ListView<String> contentsList = new ListView<>();
        String[] contents = {};

        contentsList.setItems(FXCollections.observableArrayList(contents));
        contentsList.relocate(430, 45);
        contentsList.setPrefSize(300, 300);

        // Create and position the "Return" Button
        Button checkoutBtn = new Button("Checkout");
        checkoutBtn.relocate(430, 355);
        checkoutBtn.setPrefSize(120, 25);

        returnBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {

                ObservableList<String> localCartItems = cartList.getItems();
                String selectedItem = cartList.getSelectionModel().getSelectedItem();
                localCartItems.remove(selectedItem);
                cartList.setItems(localCartItems);


                //updating S1 Shopper object to not contain the removed item.
                ObservableList<GroceryItem> itemsInCartObject = productList.getItems();
                for(GroceryItem item : itemsInCartObject){
                    if(selectedItem.equals(item.getName())){
                        s1.removeItem(item);
                    }
                }
//                System.out.println("S1 num items: "+ s1.getNumItems());
                if(cartList.getItems().size() == 0)
                    returnBtn.setDisable(true);

            }
        });


        cartList.getItems().addListener(new ListChangeListener() {
            public void onChanged(ListChangeListener.Change change) {
                ObservableList<String> localCartItems = cartList.getItems();

                if(localCartItems.isEmpty()){
                    checkoutBtn.setDisable(true);
                }else{
                    checkoutBtn.setDisable(false);
                }
            }
        });

        // Label of the totalPrice
        Label totalLabel = new Label("Total Price:");
        totalLabel.relocate(565, 355);
        totalLabel.setPrefSize(65, 25);
        totalLabel.setStyle("-fx-font: 10 Arial");

        //Text area
        TextField totalField = new TextField();
        totalField.relocate(630, 355);
        totalField.setPrefSize(100, 25);
        totalField.setDisable(true);

        checkoutBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {

                String btnText = checkoutBtn.getText();

                if(btnText == "Checkout"){

                    s1.packBags();

                    //updating the cart to contain packed items
                    ObservableList<String> itemsPacked = cartList.getItems();
                    itemsPacked.clear();

                    String temp = "";
                    for(int i=0; i< s1.getNumItems(); i++){
                        Carryable item = s1.getCart()[i];
                        temp = item.getDescription();
                        itemsPacked.add(temp);
                    }

                    //buy, return, products list should be disabled
                    buyButton.setDisable(true);
                    returnBtn.setDisable(true);

                    productList.setDisable(true);

                    //the Checkout button should have its text changed to "Restart Shopping"
                    checkoutBtn.setText("Restart Shopping");

                    //enabled regardless of whether or not anything is in the cart.
                    checkoutBtn.setDisable(false);

                    //receipt printed to the console
                    int itemsProcessed = 0;
                    String[] prod = new String[Shopper.MAX_CART_ITEMS];
                    Float[] totals = new Float[Shopper.MAX_CART_ITEMS];

                    for(int x=0; x<s1.getNumItems(); x++){

                        Carryable c = s1.getCart()[x];
                        if (c instanceof GroceryBag) {
                            GroceryBag cBag = (GroceryBag) c;
                            for (int i = 0; i < cBag.getNumItems(); i++) {
                                GroceryItem thisItem = cBag.getItems()[i];
                                prod[itemsProcessed] = thisItem.getDescription();
                                totals[itemsProcessed] = thisItem.getPrice();
                                itemsProcessed++;
                            }

                        }else{
                            prod[itemsProcessed] = c.getDescription();
                            totals[itemsProcessed] = c.getPrice();
                            itemsProcessed++;
                        }

                    }

                    float total = 0.0f;
                    for(int j=0; j<itemsProcessed; j++){
                        total = total+totals[j];
                    }

                    prod[itemsProcessed] = "TOTAL";
                    totals[itemsProcessed] = total;
                    itemsProcessed ++;

                    //total should also be shown
                    totalField.setText(String.valueOf(total));

                    for (int k = 0; k < itemsProcessed; k++) {
                        String left = prod[k];
                        float right = totals[k];
                        String value = String.format("%1$-35s %2$35s", left, right);
                        if(k==itemsProcessed-1){
                            String tempDash = "";
                            for(int f=0; f<value.length(); f++){
                                tempDash = tempDash+"-";
                            }
                            System.out.println(tempDash);
                        }
                        System.out.println(value);
                    }

                    //if a Grocery Bag is selected from the Shopping Cart list, its contents should appear in the Contents list
                    //and
                    //nothing if grocery item
                    cartList.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent mouseEvent) {

                            String selectedItem = cartList.getSelectionModel().getSelectedItem();
                            int indexOfSelectedItem = cartList.getSelectionModel().getSelectedIndex();

                            Carryable selectedCarryable = s1.getCart()[indexOfSelectedItem];

                            if (selectedCarryable instanceof GroceryBag) {
                                GroceryBag cBag = (GroceryBag) selectedCarryable;
                                ObservableList<String> contentsListString = contentsList.getItems();
                                contentsListString.clear();

                                for (int i = 0; i < cBag.getNumItems(); i++) {
                                    GroceryItem thisItem = cBag.getItems()[i];

                                    contentsListString.add(thisItem.toString());
                                }

                            }else{
                                contentsList.getItems().clear();
                            }

                        }
                    });

                }
                else{

                    contentsList.getItems().clear();
                    cartList.getItems().clear();
                    productList.setDisable(false);

                    //the application should reset with an empty shopping cart
                    for(Carryable c: s1.getCart()){
                        if(null != c){
                            s1.removeItem(c);
                        }
                    }

                    //a Total Cost of $0.00
                    totalField.setText("$0.00");
                    totalField.setAlignment(Pos.CENTER_RIGHT);

                    //an empty contents list.
                    contentsList.getItems().clear();

                    //checkout button should be renamed to checkout
                    checkoutBtn.setText("Checkout");

                    //when an item in the cast list is clicked the 'returnBtn' should be enabled
                    cartList.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent mouseEvent) {
                            if(cartList.getItems().size() > 0)
                                returnBtn.setDisable(false);
                            else
                                returnBtn.setDisable(true);
                        }
                    });

                }
            }

        });



        // Add all the components to the window
        aPane.getChildren().addAll(productListLabel, buyButton, productList, cartLabel, cartList, returnBtn, contentsLabel, contentsList, checkoutBtn, totalLabel, totalField );
        primaryStage.setTitle("Grocery Store application"); // Set title of window
        primaryStage.setScene(new Scene(aPane, 740,390)); // Set size of window
        primaryStage.setResizable(false); //making it non resizable

        //finally
        primaryStage.show();

        Button[] btnArray = {buyButton, returnBtn, checkoutBtn};
        update(btnArray, productList, totalField);

    }

    //2) initials
    public void update(Button[] btnArray, ListView<GroceryItem> productList, TextField totalField){

        //(1) with all buttons disabled,
        for(Button btn: btnArray){
            btn.setDisable(true);
        }

        //(2)product list populated with items
        GroceryItem[] products = {
                new FreezerItem("Smart-Ones Frozen Entrees", 1.99f, 0.311f),
                new GroceryItem("SnackPack Pudding", 0.99f, 0.396f),
                new FreezerItem("Breyers Chocolate Icecream",2.99f,2.27f),
                new GroceryItem("Nabob Coffee", 3.99f, 0.326f),
                new GroceryItem("Gold Seal Salmon", 1.99f, 0.213f),
                new GroceryItem("Ocean Spray Cranberry Cocktail",2.99f,2.26f),
                new GroceryItem("Heinz Beans Original", 0.79f, 0.477f),
                new RefrigeratorItem("Lean Ground Beef", 4.94f, 0.75f),
                new FreezerItem("5-Alive Frozen Juice",0.75f,0.426f),
                new GroceryItem("Coca-Cola 12-pack", 3.49f, 5.112f),
                new GroceryItem("Toilet Paper - 48 pack", 40.96f, 10.89f),
                new RefrigeratorItem("2L Sealtest Milk",2.99f,2.06f),
                new RefrigeratorItem("Extra-Large Eggs",1.79f,0.77f),
                new RefrigeratorItem("Yoplait Yogurt 6-pack",4.74f,1.02f),
                new FreezerItem("Mega-Sized Chocolate Icecream",67.93f,15.03f)};

        productList.setItems(FXCollections.observableArrayList(products));

        //(3) the Shopping Cart and Contents lists should be empty

        //(4) the Total Price should appear as $0.00
        totalField.setText("$0.00");
        totalField.setAlignment(Pos.CENTER_RIGHT);
    }

    public static void main(String[] args) {

        launch(args);
    }
}

