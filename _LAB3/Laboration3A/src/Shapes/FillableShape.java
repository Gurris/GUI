package Shapes;

import javafx.scene.paint.Color;

/**
 *
 * @author Johan
 */
abstract public class FillableShape extends Shape{
    private boolean filled;
    
    protected FillableShape(double x, double y, Color color, boolean filled) {
        super(x, y, color);
        this.filled = filled;
    }
    
    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
