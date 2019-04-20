/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author John
 */
public class CircleCircleMain extends Application {
    
      /**
     * Stage is the window or the main form
     *      - Scene is the container of all content
     *          - Root node container of all elements like button, text etc
     * 
     * @param windowApplication 
     */
    @Override
    public void start(Stage windowApplication) throws Exception {
        
        windowApplication.setTitle("Collisions Detection");
        windowApplication.setResizable(false);

        //Without using FXML
        //StackPane rootNode = new StackPane();
        //windowApplication.setScene(new Scene(rootNode, 900, 500));
        
        //Using FXML
        Parent rootNode = FXMLLoader.load(getClass().getResource("/fxml_files/circle.circle.fxml"));
        //Parent rootNode = FXMLLoader.load(getClass().getResource("/fxml_files/box.box.fxml"));
        windowApplication.setScene(new Scene(rootNode));
        windowApplication.show();       
    }
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
}
