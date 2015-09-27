/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


/**
 *
 * @author Gustaf
 */
public class Line extends Shape{
 
    private double x, y;
    private double dx, dy;
    private Color color;
    
    double x2;
    double y2;
    
    public Line(Double x, double y, Color color, double x2, double y2){
        super(x, y, color);
        this.x2 = x2;
        this.y2 = y2;
        
    }
    
    public double getX2(){
        return x2;
    }
    
    public double getY2(){
        return y2;
    }
    
    @Override
    public void paint(GraphicsContext gc){
        gc.setStroke(getColor());
        gc.setLineWidth(5);
        gc.strokeLine(getX(), getY(), x2, y2);
    }
    
    @Override
    public void move(long elapsedTimeNs) {
        dx = getDx();
        dy = getDy();
        x = getX();
        y = getY();
        
        x += dx * elapsedTimeNs / BILLION;
        y += dy * elapsedTimeNs / BILLION;
        this.x2 += dx * elapsedTimeNs / BILLION;
        this.y2 += dy * elapsedTimeNs / BILLION;
        setX(x);
        setY(y);
    }
    
    @Override
    protected void constrain(
            double boxX, double boxY, 
            double boxWidth, double boxHeight) {
        // If outside the box - calculate new dx and dy
            dx = getDx();
            dy = getDy();
        
        if (getX() < boxX) {
            dx = Math.abs(getDx());
            setVelocity(dx, dy);
        } else if (getX() > boxWidth) {
            dx = -Math.abs(getDx());
            setVelocity(dx, dy);
        }
        if (getY() < boxY) {
            dy = Math.abs(getDy());
            setVelocity(dx, dy);
        } else if (getY() > boxHeight) {
            dy = -Math.abs(getDy());
            setVelocity(dx, dy);
        }
        //fuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuult! Fix it to the limit
        
        if (x2 < boxX) {
            dx = Math.abs(getDx());
            setVelocity(dx, dy);
        } else if (x2 > boxWidth) {
            dx = -Math.abs(getDx());
            setVelocity(dx, dy);
        }
        if (y2 < boxY) {
            dy = Math.abs(getDy());
            setVelocity(dx, dy);
        } else if (y2 > boxHeight) {
            dy = -Math.abs(getDy());
            setVelocity(dx, dy);
        }
        
        
    }
    
}
