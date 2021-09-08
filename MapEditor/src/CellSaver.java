import com.sun.corba.se.impl.orbutil.ObjectWriter;
import sun.jvm.hotspot.utilities.ObjectReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class CellSaver implements Iterable<Cell> {

    private BufferedWriter bWriter;
    private BufferedReader bReader;
    private Cell[][] cells;
    private Cell currentCell;
    private byte[][] buffer;


    public CellSaver(Cell[][] cells) {
        this.cells = cells;

        try {
            bWriter = new BufferedWriter(new FileWriter("/Users/codecadet/Desktop/shellmurai-sean/MapEditor/src/savefile.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*public void writeCells() throws IOException {

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                currentCell = cells[i][j];
                bWriter.write(currentCell);
            }
        }
    }*/


    @Override
    public Iterator<Cell> iterator() {
        return null;
    }
}
