/*
 * @author Code ni Juan
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml_files;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author John
 */
public class CircleCircleController implements Initializable {
    
    @FXML private Canvas canvas;
    @FXML private Label lblCollide;
    @FXML private Label lblDistance;
    @FXML private Label lblSCircle1, lblMCircle2;
    
    int x = 290, y = 180, mainCircleRadius = 50;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //graphics context
         GraphicsContext gc = canvas.getGraphicsContext2D();
         
         //create oval at the beginning
         createCircle(this.x, this.y, this.mainCircleRadius, gc, Color.RED);
         
        //when you mouse your mouse on the top of the node
        //our node here is the canvas
        canvas.onMouseMovedProperty().set((event) -> {
            
            //clear the canvas full width and height
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
           
            //the mouser circle radius
            int mouseCircleRadius = 30;
            
           //draw the center cirle every mouse move
           createCircle(this.x, this.y, this.mainCircleRadius, gc, Color.RED);
           
           //Get the distance between two circles
            double distance = getDistance(this.x, this.y, event.getX(), event.getY());
            lblDistance.setText("Distance: " + distance);
            lblSCircle1.setText("Static circle 1 radius: " + this.mainCircleRadius);
            lblMCircle2.setText("Moving circle 2 radius: " + mouseCircleRadius);
            
            String collide = "false";
            
            //if the distance is less to the circle 1 radius + the circle 2 radius 
            //then there is a collision bettwen the two objects
            if (distance < (this.mainCircleRadius + mouseCircleRadius)) {
               collide = "true";
               createCircle(this.x, this.y, this.mainCircleRadius, gc, Color.BLACK);
            } 
            
            lblCollide.setText("Collide: " + collide);
          
            createCircle(event.getX(), event.getY(), mouseCircleRadius, gc, Color.BLUE);
        });

    }    

    /**
     * Create a circle base on the given parameters
     * 
     * @param x
     * @param y
     * @param radius
     * @param c 
     * @param gc
     */
    public void createCircle(double x, double y, double radius, GraphicsContext gc, Color c) {
        gc.beginPath();
        gc.setFill(c);
        
        double startAngleDegress = 0;
        double lengthDegress = 360;
        
        gc.arc(x, y, radius, radius, startAngleDegress, lengthDegress);
        gc.fill();
    }
    
    /**
     * method to get the distance between two circles
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return 
     */
    public double getDistance(double x1, double y1, double x2, double y2) {
        double xDistance = x2 - x1;
        double yDistance = y2 - y1;
        return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }
}
