import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.GridPane.setMargin;
import javafx.util.Pair;

/*
 */
public class BuildingOverviewDialog {

    private Dialog<Pair<String, String>> dialog;
    private Button directoryListingButton;

    public BuildingOverviewDialog(Building model, int currentFloor) {
        dialog = new Dialog<>();
        dialog.setTitle("Building Overview");
        dialog.setHeaderText("Overview of the building!");

        // Set the button types.
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField numFloors = new TextField();
        TextField numExits = new TextField();
        TextField numRooms = new TextField();
        TextField totalSize = new TextField();

        grid.add(new Label("Num Floors:"), 0, 0);
        grid.add(numFloors, 1, 0);
        grid.add(new Label("Num Exits:"), 0, 1);
        grid.add(numExits, 1, 1);
        grid.add(new Label("Num Rooms:"), 0, 2);
        grid.add(numRooms, 1, 2);
        grid.add(new Label("Total Size:"), 0, 3);
        grid.add(totalSize, 1, 3);

        numFloors.setText(model.getNumFloors()+"");
        numExits.setText(model.getNumExits()+"");
        numRooms.setText(((FloorPlan)model.getFloorPlan(currentFloor)).getNumberOfRooms()+"");
        totalSize.setText(((FloorPlan)model.getFloorPlan(currentFloor)).size()+"");

        directoryListingButton = new Button();
        directoryListingButton.setText("Directory Listing");
        grid.add(directoryListingButton, 1, 4, 1, 2);
        directoryListingButton.setMinHeight(30);
        directoryListingButton.setMinWidth(200);
        directoryListingButton.setPrefWidth(200);
        directoryListingButton.setStyle("-fx-base: WHITE;");
        setMargin(directoryListingButton, new Insets(0, 10, 0, 0));

        dialog.getDialogPane().setContent(grid);
    }

    public Dialog<Pair<String, String>> getDialog() {
        return dialog;
    }

}
