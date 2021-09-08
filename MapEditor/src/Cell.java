import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;


public class Cell {

    private Rectangle cellSquare;

    public Cell(int col, int row) {
        cellSquare = new Rectangle(MapEditor.PADDING + col * MapEditor.cellSize, MapEditor.PADDING + row * MapEditor.cellSize, MapEditor.cellSize, MapEditor.cellSize);
        cellSquare.draw();
    }

    public boolean isFilled() {
        return cellSquare.isFilled();
    }

    public void changeCell() {
        if (!isFilled()) {
            cellSquare.delete();
            cellSquare.fill();
        } else {
            cellSquare.delete();
            cellSquare.draw();
        }
    }
}
