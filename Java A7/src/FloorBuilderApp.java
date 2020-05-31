
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

//controller
public class FloorBuilderApp extends Application {

    private Building model;
    private FloorPlan fp;
    private FloorBuilderView view;
    private String selectedToggle = "";

    public static final String[] ROOM_COLORS
            = {"ORANGE", "YELLOW", "LIGHTGREEN", "DARKGREEN",
                "LIGHTBLUE", "BLUE", "CYAN", "DARKCYAN",
                "PINK", "DARKRED", "PURPLE", "GRAY"};

    @Override
    public void start(Stage primaryStage) throws Exception {
        model = Building.example();
        view = new FloorBuilderView(model);
        fp = model.getFloorPlan(0);

        //EVENT HANDLERS
        view.getRoomColorButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
//                model.getFloorPlan(0).
            }
        });

        view.getToggleGroup().selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) {
                selectedToggle = view.getToggleGroup().getSelectedToggle().getUserData().toString();
                System.out.println("SelectedToggle: " + selectedToggle);
            }

        });

        int rowCols = model.getFloorPlan(0).size();
        for (int r = 0; r < rowCols; r++) {
            for (int c = 0; c < rowCols; c++) {
//                boolean isWall, isRoom, isExit;
                
                Button thisButton = view.getGridSection(r, c);
                thisButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        
                        FloorBuilderView.Position btnPos = (FloorBuilderView.Position)(( Node ) mouseEvent.getSource()).getUserData();
                        int row = btnPos.getRow();
                        int col = btnPos.getCol();
                        
//                        System.out.println("row: " + row);
//                        System.out.println("col: " + col);
                        
                        
                        switch(selectedToggle){
                            case "Walls":
                                
                                //altering the model
                                if(fp.wallAt(row,col)){
                                    fp.setWallAt(row, col, false);
                                }else{
                                    fp.setWallAt(row, col, true);
                                    
                                    //tile is part of room
                                    Room partRoom = fp.roomAt(row, col);
                                    if(null != partRoom){
                                        partRoom.removeTile(row, col);
                                    }
                                }
                                
                                break;
                            case "Exits":
                                
                                if(model.hasExitAt(row, col)){
                                    model.removeExit(row, col);
                                }else{
                                    
                                    model.addExit(row, col);
                                    
                                    //tile is part of room
                                    Room partRoom = fp.roomAt(row, col);
                                    if(null != partRoom){
                                        partRoom.removeTile(row, col);
                                    }
                                }
                                
                                 break;
                            case "Room Tiles":
                                
                                Room partRoom = fp.roomAt(row, col);
                                if(null != partRoom){
                                    partRoom.removeTile(row, col);
                                }else{
                                    fp.addRoomAt(row, col);
                                }
                                break;
                        }
                        
                        view.update();

                    }
                });
            }
        }
        primaryStage.setTitle("Floor plan builder");
        primaryStage.setResizable(true);
//        primaryStage.setScene(new Scene(view, - 10+SliderPuzzleView.GRID_UNIT_SIZE*(GameBoard.WIDTH+2),45+SliderPuzzleView.GRID_UNIT_SIZE*(GameBoard.HEIGHT+2)));
        primaryStage.setScene(new Scene(view, 600, 800));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
