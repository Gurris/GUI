package App;

import Shapes.*;
import javafx.scene.paint.Color;

/**
 * A representation of a world containing a set of moving shapes. NB! The worlds
 * y-axis points downward.
 *
 * @author Anders LindstrÃ¶m, anderslm@kth.se 2015-09-16
 */
public class World {

    private double width, height; // this worlds width and height

    private final Shape[] shapes; // an array of references to the shapes
    private int tickCounter;      // To help doing things in longer intervals
    /**
     * Creates a new world, containing a pad and a set of balls. NB! The worlds
     * y-axis points downward.
     *
     * @param width the width of this world
     * @param height the height of this world
     */
    public World(double width, double height) {
        this.width = width;
        this.height = height;
        this.tickCounter = 0;
        
        shapes = new Shape[7]; // an array of references (change to non-zero size)
        shapes[0] = new Line(0, 0, 0, 100, Color.BLACK);
        shapes[0].setVelocity(100.0, 100.0);
        shapes[1] = new Line(0, 0, 100, 100, Color.AQUAMARINE);
        shapes[1].setVelocity(100.0, 100.0);
        shapes[2] = new Line(0, 100, 100, 100, Color.CADETBLUE);
        shapes[2].setVelocity(100.0, 100.0);
        shapes[3] = new Circle(200, 200, Color.GREENYELLOW, false, 100);
        shapes[3].setVelocity(100.0, 100.0);
        shapes[4] = new Circle(500, 500, Color.GREEN, true, 70);
        shapes[4].setVelocity(-100.0, -50.0);
        shapes[5] = new Rectangle(100, 100, Color.NAVY, true, 70, 70);
        shapes[5].setVelocity(100.0, -10.0);
        shapes[6] = new Rectangle(400, 50, Color.HOTPINK, false, 70, 140);
        shapes[6].setVelocity(-200.0, 50.0);
    }

    /**
     * Sets the new dimensions, in pixels, for this world. The method could be
     * used for example when the canvas is reshaped.
     *
     * @param newWidth
     * @param newHeight
     */
    public void setDimensions(double newWidth, double newHeight) {
        this.width = newWidth;
        this.height = newHeight;
    }

    /**
     * Move the world one step, based on the time elapsed since last move.
     *
     * @param elapsedTimeNs the elapsed time in nanoseconds
     */
    public void move(long elapsedTimeNs) {
        for (Shape shape : shapes) {
            shape.move(elapsedTimeNs);
            shape.constrain(0, 0, width, height);
        }
    }
    
    /**
     * Inverts the fills on all FillableShapes if enough ticks have passed
     * since last time.
     */
    public void invertFills() {    
        if(tickCounter >= 50){
            for (Shape shape : shapes) {
                if(shape instanceof FillableShape){
                    boolean isFilled = ((FillableShape)shape).isFilled();
                    ((FillableShape)shape).setFilled(!isFilled);
                }
            }
            tickCounter = 0;
        }
        else
            tickCounter++; 
    }
    
    /**
     * Returns a copy of the list of ball references.
     * Due to the implementation of clone, a shallow copy is returned.
     *
     * @return a copy of the list of balls
     */
    public Shape[] getShapes() {
        return (Shape[]) shapes.clone();
    }
}
