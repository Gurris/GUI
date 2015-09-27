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

public class Rect extends FillableShape{
    
    private double width;
    private double height;
    private double dx, dy;
    
    public Rect(double x, double y, Color color, 
            boolean filled, double width, double height){
        
        super(x, y, color, filled);
        this.width = height;
        this.height = height;
    }
    
    
    public double getWidth(){
        return width;
    }
    
    public double getHeight(){
        return height;
    }
    
    @Override
    public void paint(GraphicsContext gc){
        
        gc.setFill(getColor());
        gc.setLineWidth(5);

        if(getFilled() == true){
            gc.fillRect(getX(), getY(), width, height);
        }
        else{
            gc.setStroke(getColor());
            gc.strokeRect(getX(), getY(), width, height);
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
        } else if (getX() > boxWidth-width) {
            dx = -Math.abs(getDx());
            setVelocity(dx, dy);
        }
        if (getY() < boxY) {
            dy = Math.abs(getDy());
            setVelocity(dx, dy);
        } else if (getY() > boxHeight-height) {
            dy = -Math.abs(getDy());
            setVelocity(dx, dy);
        }
        
    }
    
}
