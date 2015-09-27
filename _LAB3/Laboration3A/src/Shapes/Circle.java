package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Johan
 */
public class Circle extends FillableShape{
    private double diameter;
    
    public Circle(double x, double y, Color color, boolean filled, double diameter) {
        super(x, y, color, filled);
        this.diameter = diameter;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }
    
    @Override
    public void paint(GraphicsContext gc) {
        if(isFilled()){                         // Fill the whole circle or...
            gc.setFill(getColor());
            gc.fillOval(getX(), getY(), diameter, diameter);
        }
        else{                                   // ... Only draw the outline
            gc.setStroke(getColor());
            gc.strokeOval(getX(), getY(), diameter, diameter);
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
        } else if ((x + diameter) > boxWidth) {
            setVelocity(-Math.abs(dx), dy);
        }
        if (y < boxY) {
            setVelocity(dx, Math.abs(dy));
        } else if ((y + diameter) > boxHeight) {
            setVelocity(dx, -Math.abs(dy));
        }
    }
    
    @Override
    public String toString() {
        String info;
        info = "Circle: X=" +getX() + ", Y=" + getY();
        info += ", DX=" + getDx() + ", DY=" + getDy() + ", Diameter=" + diameter;
        info += ", Color=" + getColor();
        return info;
    }
}
