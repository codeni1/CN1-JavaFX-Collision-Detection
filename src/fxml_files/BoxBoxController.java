/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml_files;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author John
 */
public class BoxBoxController implements Initializable {

    @FXML private Canvas canvas;
    @FXML private Label staticTopXYLabel, staticBottomXYLabel, movingTopXYLabel, movingBottomXYLabel;
    @FXML private Label c1, c2;
    @FXML private Label c3, c4;
    @FXML private Label c5, c6;
    @FXML private Label c7, c8;
    
    @FXML private Label collide, notcollide;
    
    int staticBoxXAxis = 300, staticBoxYAxis = 270, staticBoxWidth = 150, staticBoxHeight = 150;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.createRec(gc, Color.BLACK, this.staticBoxXAxis, this.staticBoxYAxis, 150, 150);
        
        canvas.onMouseMovedProperty().set((event) -> {
            
           double mouseBoxXAxis = event.getX(), mouseBoxYAxis = event.getY();
           int mouseBoxWidth = 50, mouseBoxHeight = 90;
            
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            this.createRec(gc, Color.BLUE, mouseBoxXAxis, mouseBoxYAxis, mouseBoxWidth, mouseBoxHeight);
            this.createRec(gc, Color.BLACK, this.staticBoxXAxis, this.staticBoxYAxis, this.staticBoxWidth, this.staticBoxHeight);
            
            staticTopXYLabel.setMinWidth(200);
            staticTopXYLabel.setText("(Y: "+staticBoxYAxis+", X: "+staticBoxXAxis+")");
            
            staticBottomXYLabel.setMinWidth(200);
            staticBottomXYLabel.setText("(Y: "+(staticBoxYAxis + staticBoxHeight)+", X: "+(staticBoxXAxis + staticBoxWidth)+")");

            movingTopXYLabel.setMinWidth(200);
            movingTopXYLabel.setText("(Y: "+mouseBoxYAxis+", X: "+mouseBoxXAxis+")");
            
            movingBottomXYLabel.setMinWidth(200);
            movingBottomXYLabel.setText("(Y: "+(mouseBoxYAxis + mouseBoxHeight)+", X: "+(mouseBoxXAxis + mouseBoxWidth)+")");
           
            c1.setText(""+(this.staticBoxXAxis + staticBoxWidth)+"");
            c2.setText(""+(mouseBoxXAxis)+"");
            
            c3.setText(""+(this.staticBoxXAxis)+"");
            c4.setText(""+(mouseBoxXAxis + mouseBoxWidth)+"");
            
            c5.setText(""+(this.staticBoxYAxis + this.staticBoxHeight)+"");
            c6.setText(""+(mouseBoxYAxis)+"");
            
            c7.setText(""+(this.staticBoxYAxis)+"");
            c8.setText(""+(mouseBoxYAxis + mouseBoxHeight)+"");
            
            //if collide
            if (
                   //check if static box x axis plus its width is greater than equal to the moving or mouse box x axis
                   (this.staticBoxXAxis + staticBoxWidth) >= mouseBoxXAxis && 

                   //check if static box x axis is less than equal to the moving or mouse box x axis plus its width
                   this.staticBoxXAxis <= (mouseBoxXAxis + mouseBoxWidth)  &&
                   
                   //check if static box y axis plus its width is greater than equal to moving or mouse box y axis
                   (this.staticBoxYAxis + this.staticBoxHeight) >= mouseBoxYAxis && 
                    
                   //check if static box y axies is less than to moving or mouse box y axis plus its height 
                   this.staticBoxYAxis <= (mouseBoxYAxis + mouseBoxHeight)
               ) {
                
                //change color collision
                this.createRec(gc, Color.RED, this.staticBoxXAxis, this.staticBoxYAxis, staticBoxWidth, staticBoxHeight);
                this.createRec(gc, Color.BLUE, mouseBoxXAxis, mouseBoxYAxis, mouseBoxWidth, mouseBoxHeight);
               
                collide.setStyle("-fx-font-weight: bold;");
                notcollide.setStyle("-fx-font-weight: normal;");
           
            } else {
                notcollide.setStyle("-fx-font-weight: bold;");
                collide.setStyle("-fx-font-weight: normal;");
            }
        });
                
    }    
    
    /**
     * method to create the rectangle or square
     * 
     * @param gc
     * @param c
     * @param x
     * @param y
     * @param w
     * @param h 
     */
    public void createRec(GraphicsContext gc, Color c, double x, double y, double w, double h) {
        gc.beginPath();
        gc.setFill(c);
        gc.rect(x, y, w, h);
        gc.fill();
    }
}
