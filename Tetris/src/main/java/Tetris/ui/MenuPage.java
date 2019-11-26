
package Tetris.ui;

import Tetris.ui.TetrisGame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MenuPage extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Luodaan aloitussivun nappulat
        Label label = new Label("TETRIS - MENU");
        Button newGameButton = new Button("Uusi peli");
        Button instructionsButton = new Button("Ohjeet");
        Button exitGameButton = new Button("Lopeta");
        
        // Sijoitetaan nappulat aloitussivulle
        GridPane menuGrid = new GridPane();
        menuGrid.setPrefSize(600, 800);
        menuGrid.add(label, 0, 0);
        menuGrid.add(newGameButton, 0, 1);
        menuGrid.add(instructionsButton, 0, 2);
        menuGrid.add(exitGameButton, 0, 3);
        menuGrid.setAlignment(Pos.CENTER);
        menuGrid.setVgap(10);
        menuGrid.setHgap(10);
        menuGrid.setPadding(new Insets(20, 20, 20, 20));
        
        Scene menuScene = new Scene(menuGrid);
        stage.setTitle("TETRIS");
        
        newGameButton.setOnAction((ActionEvent event) -> {
            TetrisGame.start(stage);
        });
        
        exitGameButton.setOnAction((event) -> {
            System.exit(0);
        });
        
        stage.setScene(menuScene);
        stage.show();
    }
    
}
