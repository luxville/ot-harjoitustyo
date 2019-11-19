
package tetris.window;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MenuPage extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label("TETRIS - MENU");
        
        StackPane menuPane = new StackPane();
        menuPane.setPrefSize(300, 480);
        menuPane.getChildren().add(label);
        menuPane.setAlignment(Pos.CENTER);
        
        Scene menuScene = new Scene(menuPane);
        
        stage.setScene(menuScene);
        stage.show();
    }
    
}
