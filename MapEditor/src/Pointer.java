import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Pointer {

    private Position pointerPosition;
    private Rectangle pointerCell;
    private Color color = Color.PINK;

    public Pointer(Position position) {
        this.pointerPosition = position;
        pointerInit();
    }

    public void pointerInit() {
        pointerCell = new Rectangle(MapEditor.PADDING + pointerPosition.getCol() * MapEditor.cellSize, MapEditor.PADDING + pointerPosition.getRow() * MapEditor.cellSize, MapEditor.cellSize, MapEditor.cellSize);
        pointerCell.setColor(color);
        pointerCell.fill();
    }

    public Position getPointerPosition() {
        return pointerPosition;
    }

    public void translatePointer(int col, int row) {
        pointerCell.translate((col - pointerPosition.getCol()) * MapEditor.cellSize, (row - pointerPosition.getRow()) * MapEditor.cellSize);
        pointerPosition.setPosition(col, row);
    }
}
