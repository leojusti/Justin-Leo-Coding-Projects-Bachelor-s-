import javafx.scene.paint.Color;

public class VerticalGamePiece extends GamePiece {
    public VerticalGamePiece(int h, Color c, int x, int y) {
        super(1, h, c, x, y);
    }

    public boolean canMoveDownIn(GameBoard b) {
        // REPLACE THE CODE BELOW WITH YOUR OWN CODE
        if(this.getTopLeftY()+this.getHeight() < GameBoard.HEIGHT && null == b.pieceAt(this.getTopLeftX(), this.getTopLeftY()+this.getHeight())){
            return true;
        }

        return false;
    }
    public boolean canMoveUpIn(GameBoard b) {
        // REPLACE THE CODE BELOW WITH YOUR OWN CODE
        if(this.getTopLeftY()-1 >= 0 && null == b.pieceAt(this.getTopLeftX(), this.getTopLeftY()-1)){
            return true;
        }
        return false;
    }
}