
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FloorBuilderApp extends Application {

    private FloorBuilderView view;
    private Building model;
    private int currentFloor;    // Index of the floor being displayed
    private int currentColor;    // Index of the current room color

    public void start(Stage primaryStage) {

        model = Building.example();
        currentFloor = 0;
        currentColor = 0;

        VBox aPane = new VBox();
        view = new FloorBuilderView(model);
        view.setPrefWidth(Integer.MAX_VALUE);
        view.setPrefHeight(Integer.MAX_VALUE);

        MenuBar menuBar = new MenuBar();
        Menu selectFloorMenu = new Menu("Select Floor");
        FloorPlan fp;
        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        for (int i = 0; i < model.getNumFloors(); i++) {
            fp = model.getFloorPlan(i);
            MenuItem item = new MenuItem(fp.getName());
            item.setUserData(i);
            if ("Basement".equals(fp.getName())) {
                selectFloorMenu.getItems().add(separatorMenuItem);
            }
            selectFloorMenu.getItems().add(item);
            item.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    int selectedFloor = (int) ((MenuItem) event.getSource()).getUserData();
                    System.out.println(selectedFloor);
                    currentFloor = selectedFloor;
                    System.out.println("xxx3: " + currentFloor);
                    view.update(currentFloor, currentColor);
                }
            });
        }
        menuBar.getMenus().addAll(selectFloorMenu);

        aPane.getChildren().addAll(menuBar, view);
        primaryStage.setTitle("Floor Plan Builder");
        primaryStage.setScene(new Scene(aPane, 370, 340));
        primaryStage.show();

        // Plug in the floor panel event handlers:
        for (int r = 0; r < model.getFloorPlan(0).size(); r++) {
            for (int c = 0; c < model.getFloorPlan(0).size(); c++) {
                view.getFloorTileButton(r, c).setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        handleTileSelection(event);
                    }
                });
            }
        }

        // Plug in the radioButton event handlers
        view.getEditWallsButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.update(currentFloor, currentColor);
            }
        });
        view.getSelectExitsButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.update(currentFloor, currentColor);
            }
        });
        view.getEditRoomsButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.update(currentFloor, currentColor);
            }
        });
        view.getDefineRoomsButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                view.update(currentFloor, currentColor);
            }
        });

        // Plug in the office color button
        view.getRoomColorButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                currentColor = (currentColor + 1) % view.ROOM_COLORS.length;
                view.update(currentFloor, currentColor);
            }
        });

        view.getBuildingOverviewButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                BuildingOverviewDialog buildingOverviewDialog = new BuildingOverviewDialog(model, currentFloor);
                buildingOverviewDialog.getDialog().showAndWait();
            }
        });

        view.update(currentFloor, currentColor);
    }

    // Handle a Floor Tile Selection
    private void handleTileSelection(ActionEvent e) {
        // Determine which row and column was selected
        int r = 0, c = 0;
        OUTER:
        for (r = 0; r < model.getFloorPlan(0).size(); r++) {
            for (c = 0; c < model.getFloorPlan(0).size(); c++) {
                if (e.getSource() == view.getFloorTileButton(r, c)) {
                    break OUTER;
                }
            }
        }

        // Check if we are in edit wall mode, then toggle the wall
        if (view.getEditWallsButton().isSelected()) {
            model.getFloorPlan(currentFloor).setWallAt(r, c, !model.getFloorPlan(currentFloor).wallAt(r, c));
            // Remove this tile from the room if it is on one, because it is now a wall
            Room room = model.getFloorPlan(currentFloor).roomAt(r, c);
            if (room != null) {
                room.removeTile(r, c);
            }
        } // Otherwise check if we are in edit exits mode
        else if (view.getSelectExitsButton().isSelected()) {
            if (model.exitAt(r, c) != null) {
                model.removeExit(r, c);
            } else {
                model.addExit(r, c);
                // Remove this tile from the room if it is on one, because it is now an exit
                Room off = model.getFloorPlan(currentFloor).roomAt(r, c);
                if (off != null) {
                    off.removeTile(r, c);
                }
            }
        } // Otherwise check if we are selecting a room tile
        else if (view.getEditRoomsButton().isSelected()) {
            if (!model.getFloorPlan(currentFloor).wallAt(r, c) && !model.hasExitAt(r, c)) {
                Room room = model.getFloorPlan(currentFloor).roomAt(r, c);
                if (room != null) {
                    room.removeTile(r, c);
                    if (room.getNumberOfTiles() == 0) {
                        model.getFloorPlan(currentFloor).removeRoom(room);
                    }
                } else {
                    room = model.getFloorPlan(currentFloor).roomWithColor(currentColor);
                    if (room == null) {
                        room = model.getFloorPlan(currentFloor).addRoomAt(r, c);
                        room.setColorIndex(currentColor);
                    } else {
                        room.addTile(r, c);
                    }
                }
            }
        } // Otherwise do nothing
        else if (view.getDefineRoomsButton().isSelected()) {
            System.out.println("Select room selected mode");
            System.out.println(r);
            System.out.println(c);
            Room room = model.getFloorPlan(currentFloor).roomAt(r, c);
            if (room == null) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Invalid selection");
                alert.setContentText("You must select a tile that belongs to a room");
                alert.showAndWait();
            } else {
                RoomInfoDialog roomInfoDialog = new RoomInfoDialog(model, view, currentColor, room, currentFloor);
                roomInfoDialog.getRoomInfoDialog().showAndWait();

            }

        } else {
        }

        view.update(currentFloor, currentColor);
    }

    public static void main(String[] args) {
        launch(args);
    }
}