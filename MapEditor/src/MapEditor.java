import com.sun.corba.se.impl.orbutil.ObjectWriter;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.io.*;

public class MapEditor implements KeyboardHandler {

    private Pointer pointer;
    private Cell[][] cells;

    private Keyboard keyboard;
    private KeyboardEvent upPressed;
    private KeyboardEvent spacePressed;
    private KeyboardEvent downPressed;
    private KeyboardEvent leftPressed;
    private KeyboardEvent rightPressed;
    private KeyboardEvent spaceReleased;
    private boolean isSpacePressed;

    private int width;
    private int height;
    private int cols;
    private int rows;
    public static int cellSize;
    public static final int PADDING = 10;


    public MapEditor(int cols, int rows, int cellSize) {
        this.cellSize = cellSize;
        this.cols = cols;
        this.rows = rows;
        width = cols * cellSize;
        height = rows * cellSize;

        Rectangle field = new Rectangle(PADDING, PADDING, width, height);
        field.draw();
        createGrid();

        pointer = new Pointer(new Position(0, 0));
        keyboardInit();
    }


    public void createGrid() {
        cells = new Cell[cols][rows];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }


    public void keyboardInit() {
        keyboard = new Keyboard(this);

        downPressed = new KeyboardEvent();
        downPressed.setKey(KeyboardEvent.KEY_DOWN);
        downPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        upPressed = new KeyboardEvent();
        upPressed.setKey(KeyboardEvent.KEY_UP);
        upPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        spacePressed = new KeyboardEvent();
        spacePressed.setKey(KeyboardEvent.KEY_SPACE);
        spacePressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        rightPressed = new KeyboardEvent();
        rightPressed.setKey(KeyboardEvent.KEY_RIGHT);
        rightPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        leftPressed = new KeyboardEvent();
        leftPressed.setKey(KeyboardEvent.KEY_LEFT);
        leftPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(upPressed);
        keyboard.addEventListener(downPressed);
        keyboard.addEventListener(rightPressed);
        keyboard.addEventListener(leftPressed);
        keyboard.addEventListener(spacePressed);

        spaceReleased = new KeyboardEvent();
        spaceReleased.setKey(KeyboardEvent.KEY_SPACE);
        spaceReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        keyboard.addEventListener(spaceReleased);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_DOWN:
                if (pointer.getPointerPosition().getRow() * cellSize < height - cellSize) {
                    pointer.translatePointer(pointer.getPointerPosition().getCol(), pointer.getPointerPosition().getRow() + 1);
                }
                if (isSpacePressed) {
                    cells[pointer.getPointerPosition().getCol()][pointer.getPointerPosition().getRow()].changeCell();
                }
                break;

            case KeyboardEvent.KEY_UP:
                if (pointer.getPointerPosition().getRow() >= 1) {
                    pointer.translatePointer(pointer.getPointerPosition().getCol(), pointer.getPointerPosition().getRow() - 1);
                }
                if (isSpacePressed) {
                    cells[pointer.getPointerPosition().getCol()][pointer.getPointerPosition().getRow()].changeCell();
                }
                break;

            case KeyboardEvent.KEY_RIGHT:
                if (pointer.getPointerPosition().getCol() * cellSize < width - cellSize) {
                    pointer.translatePointer(pointer.getPointerPosition().getCol() + 1, pointer.getPointerPosition().getRow());
                }
                if (isSpacePressed) {
                    cells[pointer.getPointerPosition().getCol()][pointer.getPointerPosition().getRow()].changeCell();
                }
                break;

            case KeyboardEvent.KEY_LEFT:
                if (pointer.getPointerPosition().getCol() >= 1) {
                    pointer.translatePointer(pointer.getPointerPosition().getCol() - 1, pointer.getPointerPosition().getRow());
                }
                if (isSpacePressed) {
                    cells[pointer.getPointerPosition().getCol()][pointer.getPointerPosition().getRow()].changeCell();
                }
                break;

            case KeyboardEvent.KEY_SPACE:
                isSpacePressed = true;
                cells[pointer.getPointerPosition().getCol()][pointer.getPointerPosition().getRow()].changeCell();
                break;

            /*case KeyboardEvent.KEY_S:

                break;*/

            default:
                break;
        }
    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_SPACE:
                isSpacePressed = false;
                break;

            default:
                break;
        }
    }
}