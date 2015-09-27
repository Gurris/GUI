package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Johan
 */
public class Line extends Shape {
    private double x2, y2;

    public Line(double x, double y, double x2, double y2, Color color) {
        super(x, y, color);
        this.x2 = x2;
        this.y2 = y2;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

     public void setX2(double x2) {
        this.x2 = x2;
    }
     
    public void setY2(double y2) {
        this.y2 = y2;
    }

    @Override
    public void paint(GraphicsContext gc) {
        gc.setStroke(getColor());
        gc.strokeLine(getX(), getY(), x2, y2);
    }
    
    @Override
    public void constrain(double boxX, double boxY, 
                          double boxWidth, double boxHeight) {
        
        double x = getX();
        double y = getY();
        double dx = getDx();
        double dy = getDy();
         
         
        if (x < boxX || x2 < boxX) {
            setVelocity(Math.abs(dx), dy);
        } 
        else if (x > boxWidth || x2 > boxWidth) {
            setVelocity(-Math.abs(dx), dy);
        }
        
        if (y < boxY || y2 < boxY) {
            setVelocity(dx, Math.abs(dy));
        } 
        else if (y > boxHeight || y2 > boxHeight) {
            setVelocity(dx, -Math.abs(dy));
        }
    }

    @Override
    public void move(long elapsedTimeNs) {
        super.move(elapsedTimeNs);
        x2 += getDx() * elapsedTimeNs / BILLION;
        y2 += getDy() * elapsedTimeNs / BILLION;
    }
    
    @Override
    public String toString() {
        String info;
        info = "Line: X1=" + getX() + ", Y1=" + getY();
        info += ", X2=" + x2 + ", Y2=" + y2 + ", DX=" + getDx();
        info += ", DY=" + getDy() + ", Color=" + getColor();
        return info;
    }
    
    
}
