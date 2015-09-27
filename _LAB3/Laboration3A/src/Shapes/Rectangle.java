package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Johan
 */
public class Rectangle extends FillableShape{
    private double width, height;

    public Rectangle(double x, double y, Color color, boolean filled, double width, double height) {
        super(x, y, color, filled);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void paint(GraphicsContext gc) {
        if(isFilled()){                         // Fill the whole rectangle or...
            gc.setFill(getColor());
            gc.fillRect(getX(), getY(), width, height);
        }
        else{                                   // ... Only draw the outline
            gc.setStroke(getColor());
            gc.strokeRect(getX(), getY(), width, height);
        }
    }
    
    @Override
    public void constrain(double boxX, double boxY, 
                          double boxWidth, double boxHeight) {
        double x = getX();
        double y = getY();
        double dx = getDx();
        double dy = getDy();
        
        if (x < boxX) {
            setVelocity(Math.abs(dx), dy);
        } else if ((x + width) > boxWidth) {
            setVelocity(-Math.abs(dx), dy);
        }
        if (y < boxY) {
            setVelocity(dx, Math.abs(dy));
        } else if ((y + height) > boxHeight) {
            setVelocity(dx, -Math.abs(dy));
        }
            
    }

    @Override
    public String toString() {
        String info;
        info = "Rectangle: X=" +getX() + ", Y=" + getY();
        info += ", DX=" + getDx() + ", DY=" + getDy() + ", W=" + width + ", H=" + height;
        info += ", Color=" + getColor();
        return info;
    }
    
    
}
