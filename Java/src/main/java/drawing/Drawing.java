package drawing;

import drawing.shapes.Line;
import drawing.shapes.Shape;
import drawing.writing.JPEGWriter;
import drawing.writing.PNGWriter;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Refactor Task 3: (Mis-)Shaped
 *
 * @author Zishen Wen (F22), Deyuan Chen (S22)
 */

// Draw method has nearly idential code for PNG and JPEG
// If you want to asdd support for a new format you need to copy and paste to repeat
// Violates dont repeat yourself
// i would create a method to return the appropriate writer based on the format, 
// and then have one loop to draw the shapes, so that we dont repeat code for each format

// Second problem is Drawing calls shape.toLines to get Line Array and gives it back to shape.draw
// Shape already knows its own lines, so it shouldnt need to be given them again, its a method ON Shape
// To solve, i would have shape.draw just call toLines itself, and not have Drawing do it, 
// so that we dont have to pass the lines around and risk them getting out of sync with the shape
// Shape manages its own internal representation - information expert principle
public class Drawing {

    private List<Shape> shapes;

    public Drawing(List<Shape> shapes) {
        this.shapes = shapes;
    }

    /**
     * Draw shapes to a file with given file format.
     *
     * @param format   file format
     * @param filename file name
     */
    public void draw(String format, String filename) {
        // TODO: Do you notice any issues here?
        // Adding new format means compy and paste
        if (format.equals("jpeg")) {
            try (Writer writer = new JPEGWriter(filename + ".jpeg")) {
                for (Shape shape : this.shapes) {
                    // TODO: What is the issue of the behavior here?
                    // Getting lines from shape, then giving them back
                    Line[] lines = shape.toLines();
                    shape.draw(writer, lines);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (format.equals("png")) {
            try (Writer writer = new PNGWriter(filename + ".png")) { // repeated here again
                for (Shape shape : this.shapes) {
                    Line[] lines = shape.toLines(); // same problem here
                    shape.draw(writer, lines);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

