import javafx.scene.paint.Color;

public class HorizontalGamePiece extends GamePiece {

    public HorizontalGamePiece(int w, Color c, int x, int y) {

        super(w, 1, c, x, y);

    }
    public boolean canMoveLeftIn(GameBoard b) {
        // REPLACE THE CODE BELOW WITH YOUR OWN CODE
        if(this.getTopLeftX()-1 >= 0 && null == b.pieceAt(this.getTopLeftX()-1, this.getTopLeftY())){
            return true;
        }
        return false;
    }
    public boolean canMoveRightIn(GameBoard b) {
        // REPLACE THE CODE BELOW WITH YOUR OWN CODE
        if(this.getTopLeftX()+this.getWidth() < GameBoard.WIDTH && null == b.pieceAt(this.getTopLeftX()+this.getWidth(), this.getTopLeftY())){
            return true;
        }
        return false;
    }
}