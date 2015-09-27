package gui;
import javafx.scene.paint.Color;

/**
 * A representation of a world containing a set of moving shapes. NB! The worlds
 * y-axis points downward.
 *
 * @author Anders Lindström, anderslm@kth.se 2015-09-16
 */
public class World {

    private double width, height; // this worlds width and height

    private final Shape[] shapes; // an array of references to the shapes
    public static final double time = 1_000_000.0;

    /**
     * Creates a new world, containing a pad and a set of balls. NB! The worlds
     * y-axis points downward.
     *
     * @param width the width of this world
     * @param height the height of this worl
     */
    
    public World(double width, double height) {
        this.width = width;
        this.height = height;

        shapes = new Shape[4]; // an array of references (change to non-zero size)
        // Create the actual Shape objects (sub types)
        // ....
        shapes[0] = new Line(100.0, 100.0, Color.AQUAMARINE, 20.0, 20.0);
        shapes[0].setVelocity(50, 200);
        shapes[1] = new Circle(0.0, 250.0, Color.BLUE, false, 50);
        shapes[1].setVelocity(300, 300);
        shapes[2] = new Rect(150.0, 150.0, Color.CHOCOLATE, true, 30, 30);
        shapes[2].setVelocity(100, 150);        
        shapes[3] = new Circle(300.0, 300.0, Color.RED, true, 50);
        shapes[3].setVelocity(300.0, 300.0);

        
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
     * @param elapsedTimeNs the elpsed time in nanoseconds
     */
    public void move(long elapsedTimeNs) {
        // alterantive loop: for(Shape s : shapes) { ...
        for (int i = 0; i < shapes.length; i++) { 
            shapes[i].move(elapsedTimeNs);
            shapes[i].constrain(0, 0, width, height);
        }
        
        System.out.println(width + ", " + height);
    }
    public void fill(long elapsedTimeNs){
        System.out.println(elapsedTimeNs/time);
        //Sätter circle fylld eller ofylld innom en period
            for(int i=0; i<shapes.length; i++){
                if(shapes[i] instanceof Circle){
                    if(elapsedTimeNs/time > 30){
                        ((Circle)shapes[i]).toggle();
                    }
                    
                }
            }
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
