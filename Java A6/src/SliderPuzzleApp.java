import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sun.font.EAttribute;

public class SliderPuzzleApp extends Application {
    private SliderPuzzleGame    model;
    private SliderPuzzleView    view;

    private GamePiece           selectedPiece;
    private boolean             justGrabbed;
    private int                 lastX;
    private int                 lastY;

    public void start(Stage primaryStage) {
        model = new SliderPuzzleGame();
        view = new SliderPuzzleView(model);

        // Add event handlers to the inner game board buttons
        for (int w=1; w<=(GameBoard.WIDTH); w++) {
            for (int h=1; h<=(GameBoard.HEIGHT); h++) {
                view.getGridSection(w, h).setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        handleGridSectionSelection(mouseEvent);
                    }
                });
                view.getGridSection(w, h).setOnMouseDragged(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        handleGridSectionMove(mouseEvent);
                        view.update();
                    }
                });
            }
        }

        // Plug in the Start button and NeaxtBoard button event handlers
        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.startBoard();
                view.update();
            }
        });
        view.getNextBoardButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.moveToNextBoard();
                view.update();
            }
        });

        primaryStage.setTitle("Slide Puzzle Application");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, - 10+SliderPuzzleView.GRID_UNIT_SIZE*(GameBoard.WIDTH+2),45+SliderPuzzleView.GRID_UNIT_SIZE*(GameBoard.HEIGHT+2)));
        primaryStage.show();

        // Update the view upon startup
        view.update();
    }


    private void handleGridSectionSelection(MouseEvent mouseEvent) {
//        System.out.println("-----------------------------------------------");
//        System.out.println("Selection");
//        int currentGridX = (int)mouseEvent.getX();
//        int currentGridY = (int)mouseEvent.getY();
        
        int sceneX = (int) mouseEvent.getSceneX();
        int sceneY = (int) mouseEvent.getSceneY();
//        
//        System.out.println("Scene X: " + sceneX);
//        System.out.println("Scene Y: " + sceneY);
        
        int x = sceneX / SliderPuzzleView.GRID_UNIT_SIZE;
        int y = sceneY / SliderPuzzleView.GRID_UNIT_SIZE;
        
        x--;
        y--;
        
        lastX = x;
        lastY = y;
        
//        System.out.println("Last X: " + x);
//        System.out.println("Last Y: " + y);

        GameBoard currentBoard = model.getCurrentBoard();
        selectedPiece = currentBoard.pieceAt(x, y); //could be null as well

//        System.out.println("isPieceSelected: " + (null != selectedPiece));
//        System.out.println("-----------------------------------------------");

    }
    private void handleGridSectionMove(MouseEvent mouseEvent) {
//        System.out.println("-------------------MOVE BEGIN----------------------------");
        int currentGridX = (int)mouseEvent.getX();
        int currentGridY = (int)mouseEvent.getY();

        int lastGridX = (int)mouseEvent.getScreenX();
        int lastGridY = (int)mouseEvent.getScreenY();
        
        int sceneX = (int) mouseEvent.getSceneX();
        int sceneY = (int) mouseEvent.getSceneY();
        
        int newX = sceneX / SliderPuzzleView.GRID_UNIT_SIZE;
        int newY = sceneY / SliderPuzzleView.GRID_UNIT_SIZE;
        
        newX--;
        newY--;
        
//        System.out.println("NewX: " + newX);
//        System.out.println("NewY: " + newY);
        
//        System.out.println("NEW SCENE X:" +  sceneX + "||  NEW SCENE Y: "  + sceneY);
        
        int diffX = lastX - newX;
        int diffY = lastY - newY;
        
//        System.out.println("diff x :" + diffX );
//        System.out.println("diff Y :" + diffY );

        if(null != selectedPiece){
            if(Math.abs(diffX) > Math.abs(diffY)){
//                System.out.println("dragged horizontally");
                if(diffX < 0 ){
//                        System.out.println("Moved right horizontally");
                    if(selectedPiece.canMoveRightIn(model.getCurrentBoard())){
//                        System.out.println("can move right");
                        selectedPiece.moveRight();
                        this.model.makeAMove();

                    }
                }
                if(diffX > 0 ){
//                        System.out.println("Moved left horizontally");
                    if(selectedPiece.canMoveLeftIn(model.getCurrentBoard())){
//                        System.out.println("can move left");
                        selectedPiece.moveLeft();
                        this.model.makeAMove();
                    }
                    
                }
            }
            if(Math.abs(diffX) < Math.abs(diffY)){
//                System.out.println("dragged Vertically");
                if(diffY < 0){
                    //slided down
//                    System.out.println("Moved Down vertically");
                    if(selectedPiece.canMoveDownIn(model.getCurrentBoard())){
//                        System.out.println("can move down");
                        selectedPiece.moveDown();
                        this.model.makeAMove();
                    }
                }
                if(diffY>0){
                    //slided up
//                    System.out.println("Moved up vertically");
                    if(selectedPiece.canMoveUpIn(model.getCurrentBoard())){
//                        System.out.println("can move up");
                        selectedPiece.moveUp();
                        this.model.makeAMove();
                    }
                }

                

            }
            //check for completion
            model.getCurrentBoard().checkCompletion(selectedPiece);

            if(model.getCurrentBoard().isCompleted()){
                model.completeBoard();
//                System.out.println("Game completed");
            }
            
            lastX = selectedPiece.getTopLeftX();
            lastY = selectedPiece.getTopLeftY();

        }
        
//        System.out.println("------------------------MOVE END-----------------------");

    }

    public static void main(String[] args) { launch(args); }
}
