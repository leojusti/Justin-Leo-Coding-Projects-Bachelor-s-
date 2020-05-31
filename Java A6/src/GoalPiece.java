import javafx.scene.paint.Color;

public class GoalPiece extends HorizontalGamePiece {
    public GoalPiece(int x, int y) {
        super(2, Color.RED, x, y);
    }
    public boolean canMoveRightIn(GameBoard b) {
        // REPLACE THE CODE BELOW WITH YOUR OWN CODE
        if(null == b.pieceAt(this.getTopLeftX()+this.getWidth(), this.getTopLeftY())){
            return true;
        }
        return false;
    }
}