package com.example.damier;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.InputStream;

import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;

public class HelloApplication extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    
    private GridPane gp_commandes;
    private GridPane gp_game;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        gp_commandes = new GridPane();
        gp_commandes.setHgap(15);
        gp_commandes.setVgap(15);
        gp_game = new GridPane();

        Text unTexte = new Text("Bienvenue");
        gp_commandes.add(unTexte, 0, 0);

        Button bt_debut = new Button("Commencer");
        bt_debut.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                loadGameUI();
            }
        });

        gp_commandes.add(bt_debut,1,0);
        gp_commandes.add(gp_game,0,1, 8, 1);

        Scene scene = new Scene(gp_commandes, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    private void loadGameUI() {
        gp_game.getChildren().clear();

        Class<?> clazz = this.getClass();

        InputStream input = clazz.getResourceAsStream("/com/example/damier/mario.png");

        Image image = new Image(input);

        // Ajout des cases noires et blanches au damier
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Rectangle rect = new Rectangle((WIDTH-100) / 8, (HEIGHT-100) / 8);
                if ((row + col) % 2 == 0) {
                    rect.setFill(Color.BLACK);
                } else {
                    rect.setFill(Color.WHITE);
                }
                gp_game.add(rect, col, row);
            }
        }

        // Ajout des coordonnées aux cases
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Text text = new Text("(" + row + ", " + col + ")");
                gp_game.add(text, col, row);
            }
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight((WIDTH-100)/8);
        imageView.setFitWidth((HEIGHT-100)/8);

        gp_game.add(imageView, 0, 0);

    }
}
