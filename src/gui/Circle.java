/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Gustaf
 */

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends FillableShape{
    
    private double diameter;
    private double dx, dy;
    
    public Circle(double x, double y, Color color, 
            boolean filled, double diameter){
        
        super(x, y, color, filled);
        this.diameter = diameter;
    }
    
    
    public double getDiameter(){
        return diameter;
    }
    
    @Override
    public void paint(GraphicsContext gc){
        
        gc.setFill(getColor());
        gc.setLineWidth(5);
        
        if(getFilled() == true){
            gc.fillOval(getX(), getY(), diameter, diameter);
        }
        else{
            gc.setStroke(getColor());
            gc.strokeOval(getX(), getY(), diameter, diameter);
        }
        
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
        } else if (getX() > boxWidth-diameter) {
            dx = -Math.abs(getDx());
            setVelocity(dx, dy);
        }
        if (getY() < boxY) {
            dy = Math.abs(getDy());
            setVelocity(dx, dy);
        } else if (getY() > boxHeight-diameter) {
            dy = -Math.abs(getDy());
            setVelocity(dx, dy);
        }
        
    }
    
}
