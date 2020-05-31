
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.ColumnConstraintsBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import sun.security.pkcs11.P11TlsKeyMaterialGenerator;

//view
public class FloorBuilderView extends GridPane {

    private static final int BUTTON_PADDING = 0;

    private GridPane floorGrid;
    private Button[][] gridSections; //only for reference, but not rendered
    private Button buildindOverviewButton, roomColorButton;
    private TextField floorSummaryField;
    private Label floorLayoutLabel, selectEditLabel, floorSummaryLabel;
    private VBox toggleOptions;
    private ToggleGroup tg;
    private FloorPlan fp;

    private Building model;

    public FloorBuilderView(Building b) {

        model = b;
        fp = model.getFloorPlan(0);

        int rowCols = fp.size();
        gridSections = new Button[rowCols][rowCols];
        floorGrid = new GridPane();

        floorGrid.setPadding(new Insets(BUTTON_PADDING));
        floorGrid.setHgap(BUTTON_PADDING);
        floorGrid.setVgap(BUTTON_PADDING);

        for (int r = 0; r < rowCols; r++) {
            for (int c = 0; c < rowCols; c++) {
//                int number = rowCols * r + c;
                Button button = new Button();
                button.setUserData(new Position(r, c));

                if (fp.wallAt(r, c)) {
                    button.setStyle("-fx-base: #000000;");
                }
                if (b.hasExitAt(r, c)) {
                    button.setStyle("-fx-base: #FF0000;");

                }

                floorGrid.add(button, c, r);
                gridSections[r][c] = button;
            }
        }

        floorLayoutLabel = new Label("Floor Layout");
        selectEditLabel = new Label("Select /Edit");

        ToggleButton wallsT = new RadioButton("Walls");
        wallsT.setUserData("Walls");
        ToggleButton exitsT = new RadioButton("Exits");
        exitsT.setUserData("Exits");
        ToggleButton roomTilesT = new RadioButton("Room Tiles");
        roomTilesT.setUserData("Room Tiles");
        ToggleButton selectRoomT = new RadioButton("Select Room");
        selectRoomT.setUserData("Select Room");

        tg = new ToggleGroup();
        toggleOptions = new VBox(wallsT, exitsT, roomTilesT, selectRoomT);
        wallsT.setToggleGroup(tg);
        exitsT.setToggleGroup(tg);
        roomTilesT.setToggleGroup(tg);
        selectRoomT.setToggleGroup(tg);

        buildindOverviewButton = new Button("Building Overview");

        floorSummaryLabel = new Label("FLOOR SUMMARY");

        floorSummaryField = new TextField();
        floorSummaryField.setText(fp.getName() + " with " + fp.getNumberOfRooms() + " rooms.");

        roomColorButton = new Button();
        roomColorButton.setStyle("-fx-base: ORANGE;");

//        ColumnConstraints halfConstraint = ColumnConstraintsBuilder.create().percentWidth(20).build();
//        this.getColumnConstraints().addAll(halfConstraint, halfConstraint);
        //adding to the view
        this.add(floorLayoutLabel, 0, 0);
        this.add(floorGrid, 0, 1);
//        this.add(gridSections, 0, 1);
        this.add(floorSummaryLabel, 0, 2);
        this.add(floorSummaryField, 0, 3);

        this.add(selectEditLabel, 1, 0);
        this.add(toggleOptions, 1, 1);
        this.add(buildindOverviewButton, 1, 2);

        this.add(roomColorButton, 2, 1);

    }

    public Button getGridSection(int r, int c) {
        return gridSections[r][c];
    }

//    public GridPane getFloorGrid() {
//        return floorGrid;
//    }
    public Button getBuildindOverviewButton() {
        return buildindOverviewButton;
    }

    public Button getRoomColorButton() {
        return roomColorButton;
    }

    public ToggleGroup getToggleGroup() {
        return tg;
    }

    public TextField getFloorSummaryField() {
        return floorSummaryField;
    }

    public void update() {
        
//        System.out.println("Update called");
        int rowCols = model.getFloorPlan(0).size();
        
        for (int r = 0; r < rowCols; r++) {
            for (int c = 0; c < rowCols; c++) {
//                int number = rowCols * r + c;
                Button button = gridSections[r][c];
//                button.setUserData(new Position(r, c));

                if (model.hasExitAt(r, c)) {
                    button.setStyle("-fx-base: #FF0000;");
                }
                else if(fp.wallAt(r, c)) {
                    button.setStyle("-fx-base: #000000;");
                }else{
                    button.setStyle("-fx-base: #FFFFFF;");
                }

//                floorGrid.add(button, c, r);
//                gridSections[r][c] = button;
            }
        }

    }

    public class Position {

        private int _row;
        private int _col;

        public Position(int row, int col) {
            this._row = row;
            this._col = col;
        }
        
        public int getRow(){
            return _row;
        }
        
        public int getCol(){
            return _col;
        }
    }

}
