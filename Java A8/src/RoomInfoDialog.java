import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.GridPane.setMargin;
import javafx.util.Callback;
import javafx.util.Pair;

/*
 */
public class RoomInfoDialog {

    private Dialog<Pair<String, String>> dialog;
    private Button colorButton;

    public RoomInfoDialog(Building model, FloorBuilderView view, int currentColor, Room room, int currentFloor) {
        dialog = new Dialog<>();
        dialog.setTitle("Room Details");
        dialog.setHeaderText("Details of the room!");

        // Set the button types.
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField occupant = new TextField();
        occupant.setPromptText("person who occupies the room");
        occupant.setMinWidth(200);
        occupant.setPrefWidth(100);
        TextField position = new TextField();
        position.setPromptText("job/title of this person");

        TextField number = new TextField();
        number.setPromptText("the room number");
        TextField floor = new TextField();
        floor.setPromptText("floor info");
        floor.setDisable(true);
        TextField size = new TextField();
        size.setPromptText("size of room");
        size.setDisable(true);

        grid.add(new Label("Occupant:"), 0, 0);
        grid.add(occupant, 1, 0);
        grid.add(new Label("Position:"), 0, 1);
        grid.add(position, 1, 1);
        grid.add(new Label("Number:"), 0, 2);
        grid.add(number, 1, 2);
        grid.add(new Label("Floor:"), 0, 3);
        grid.add(floor, 1, 3);
        grid.add(new Label("Size:"), 0, 4);
        grid.add(size, 1, 4);

        colorButton = new Button();
        grid.add(colorButton, 2, 3, 1, 1);
        colorButton.setMinHeight(30);
        colorButton.setMinWidth(100);
        colorButton.setPrefWidth(100);
        colorButton.setStyle("-fx-base: WHITE;");
        colorButton.setFocusTraversable(false);
        colorButton.setStyle("-fx-base: " + FloorBuilderView.ROOM_COLORS[currentColor] + ";");
        setMargin(colorButton, new Insets(0, 10, 0, 0));

        occupant.setText(room.getOccupant());
        position.setText(room.getPosition());
        number.setText(room.getNumber());
        floor.setText((model.getFloorPlan(currentFloor)).getName());
        size.setText((model.getFloorPlan(currentFloor)).size() + "");

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(new Callback<ButtonType, Pair<String, String>>() {
            @Override
            public Pair<String, String> call(ButtonType dialogButton) {
                if (dialogButton == ButtonType.OK) {
                    if (occupant.getText() != null && !occupant.getText().isEmpty()) {
                        room.setOccupant(occupant.getText());
                    }
                    if (position.getText() != null && !position.getText().isEmpty()) {
                        room.setPosition(position.getText());
                    }
                    if (number.getText() != null && !number.getText().isEmpty()) {
                        room.setNumber(number.getText());
                    }
                }
                return null;
            }
        });
    }

    public Dialog<Pair<String, String>> getRoomInfoDialog() {
        return dialog;
    }

    public void setRoomInfoDialog(Dialog<Pair<String, String>> roomInfoDialog) {
        this.dialog = roomInfoDialog;
    }

}
