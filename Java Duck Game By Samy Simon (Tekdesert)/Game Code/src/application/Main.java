package application;
	
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

//Code By Samy Simon 291227
public class Main extends Application {
	

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		Game game = new Game();
		game.Start(primaryStage);
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
