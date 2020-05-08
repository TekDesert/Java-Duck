package application;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Game {
	
	private MediaPlayer mediaLost;
	private MediaPlayer mediaPlayer;
	private MediaPlayer mediaMain;
	private MediaPlayer mediaKing;
	private MediaPlayer mediaWin;
	
	public Game()  {
		try {
		String lost_gen = "resources/sound/loose.mp3";
		Media lost;
		lost = new Media(getClass().getResource("/sound/loose.mp3").toURI().toString());

		this.mediaLost = new MediaPlayer(lost);
		
		String bip = "resources/sound/quack.mp3";
		Media hit = new Media(getClass().getResource("/sound/quack.mp3").toURI().toString());
		this.mediaPlayer = new MediaPlayer(hit);
		
		String main_gen = "resources/sound/main.mp3";
		Media main = new Media(getClass().getResource("/sound/main.mp3").toURI().toString());
		this.mediaMain = new MediaPlayer(main);
		
		String king_load = "resources/sound/king_duck.mp3";
		Media king = new Media(getClass().getResource("/sound/king_duck.mp3").toURI().toString());
		this.mediaKing = new MediaPlayer(king);
		
		String win_load = "resources/sound/king_duck.mp3";
		Media win = new Media(getClass().getResource("/sound/win.mp3").toURI().toString());
		this.mediaWin = new MediaPlayer(win);
		
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Over(Stage primaryStage,MediaPlayer mediaLost) {
		
		Pane layout_end = new Pane();
		Scene scene = new Scene(layout_end, 1366, 768);
		
		Image Game_Over = new Image(getClass().getResourceAsStream("/images/game_over.jpg"));
		ImageView Game_Over_Display = new ImageView(Game_Over);
		 
		Game_Over_Display.setFitHeight(768); 
		Game_Over_Display.setFitWidth(1366);
		layout_end.getChildren().addAll(Game_Over_Display);
		
		mediaLost.seek(Duration.ZERO);
	    mediaLost.play();
	    
		
		Image imageOk = new Image(getClass().getResourceAsStream("/images/start.png"));
		
        Button button = new Button("Try Again",new ImageView(imageOk));
        button.setLayoutX(598);
        button.setLayoutY(150);
        button.setOnAction(e -> {
        	Game gameRestart = new Game();
        	gameRestart.Start(primaryStage);
  
        });
        layout_end.getChildren().addAll(button);
        button.setStyle(
                "-fx-background-color: white; "+
                "-fx-font-weight: bold;"+
                "-fx-font-size: 23px;"+
                "-fx-padding-left: 15px; "+
                "-fx-cursor: hand;"
                
        );
	      
		primaryStage.setScene(scene);
	    primaryStage.setResizable(false);
	    primaryStage.show();
		
	}
	
	public void win(Stage primaryStage) {
		Pane layout_win = new Pane();
        Scene scene_win = new Scene(layout_win, 1366, 768);
        
        Image Game_win = new Image(getClass().getResourceAsStream("/images/win.png"));
		ImageView Game_win_Display = new ImageView(Game_win);
		 
		Game_win_Display.setFitHeight(768); 
		Game_win_Display.setFitWidth(1366);
		
		
		Image imageOk = new Image(getClass().getResourceAsStream("/images/start.png"));
		
        Button button = new Button("Play again",new ImageView(imageOk));
        button.setLayoutX(1000);
        button.setLayoutY(140);
        button.setOnAction(e -> {
        	Game gameRestart = new Game();
        	gameRestart.Start(primaryStage);
  
        });
        button.setStyle(
                "-fx-background-color: white; "+
                "-fx-font-weight: bold;"+
                "-fx-font-size: 23px;"+
                "-fx-padding-left: 15px; "+
                "-fx-cursor: hand;"
                
        );
        layout_win.getChildren().addAll(Game_win_Display,button);
        
		mediaWin.seek(Duration.ZERO);
	    mediaWin.play();
        
        primaryStage.setScene(scene_win);
        primaryStage.setResizable(false);
        primaryStage.show();
	}
	
	public void Start(Stage primaryStage) {
		
		Pane layout_begin = new Pane();
        Scene scene_begin = new Scene(layout_begin, 1366, 768);
        Image Game_Begin = null;
        
        Game gameOver = new Game();
        

		Game_Begin = new Image(getClass().getResourceAsStream("/images/intro2.jpg"));
		System.out.println("Duck Image found !");
		
	
        ImageView Game_Begin_Display = new ImageView(Game_Begin);
        layout_begin.getChildren().addAll(Game_Begin_Display);
      //A button with the specified text caption and icon.
        Image imageOk = null;
	
		imageOk = new Image(getClass().getResourceAsStream("/images/start.png"));
		
        Button button = new Button("Start ",new ImageView(imageOk));
        button.setLayoutX(598);
        button.setLayoutY(460);
        button.setOnAction(e -> {
        	gameOver.main(primaryStage);
  
        });
        layout_begin.getChildren().addAll(button);
        button.setStyle(
                "-fx-background-color: white; "+
                "-fx-font-weight: bold;"+
                "-fx-font-size: 23px;"+
                "-fx-padding-left: 15px; "+
                "-fx-cursor: hand;"
                
        );
        
        primaryStage.setScene(scene_begin);
        primaryStage.setResizable(false);
        primaryStage.show();
		
	}
	
	public void main(Stage primaryStage) {
		primaryStage.setTitle("Duck Game");
		
		
		mediaMain.play();
		
		
		//Setting the image view 
		Pane layout = new Pane();
	    
		   
        Pond pond = new Pond(1099, 326);
	     
        List<Rock> list_rock = pond.generatePondRandomRock();
        
        pond.setList_rock(list_rock);
        
        List<WaterLily> list_lily = pond.generatePondRandomWaterLily();
        
        pond.setList_lily(list_lily);
        
        List<Duck> list_duck = pond.generatePondRandomDuck();
        
        pond.setList_duck(list_duck);
		

        for(int i =0; i<list_duck.size(); i++) {
            
        	list_duck.get(i).getLabel().setGraphic(list_duck.get(i).getImageView());


    	    layout.getChildren().addAll(list_duck.get(i).getImageView());
        	layout.getChildren().add(list_duck.get(i).getLabel());
            //layout.getChildren().add(list_duck.get(i).getLabel());
        }
        
	    
        layout.setId("pane");
        
        
        
        Image board_details = new Image(getClass().getResourceAsStream("/images/display.jpg"));
        ImageView board_details_display = new ImageView(board_details);
        board_details_display.setX(10);
        board_details_display.setY(10);
        
        Image duck_legend = new Image(getClass().getResourceAsStream("/images/duck_right_fat.png"));
        ImageView duck_legend1_display = new ImageView(duck_legend);
        duck_legend1_display.setX(20);
        duck_legend1_display.setY(70);
        duck_legend1_display.setFitHeight(40);
        duck_legend1_display.setFitWidth(40);
        
        ImageView duck_legend2_display = new ImageView(duck_legend);
        duck_legend2_display.setX(20);
        duck_legend2_display.setY(120);
        duck_legend2_display.setFitHeight(40);
        duck_legend2_display.setFitWidth(40);

        ImageView duck_legend3_display = new ImageView(duck_legend);
        duck_legend3_display.setX(20);
        duck_legend3_display.setY(170);
        duck_legend3_display.setFitHeight(40);
        duck_legend3_display.setFitWidth(40);
        
        Image lily_legend = new Image(getClass().getResourceAsStream("/images/lily1.png"));
        ImageView lily_legend1_display = new ImageView(lily_legend);
        lily_legend1_display.setX(150);
        lily_legend1_display.setY(80);
        lily_legend1_display.setFitHeight(30);
        lily_legend1_display.setFitWidth(30);

        ImageView lily_legend2_display = new ImageView(lily_legend);
        lily_legend2_display.setX(150);
        lily_legend2_display.setY(130);
        lily_legend2_display.setFitHeight(30);
        lily_legend2_display.setFitWidth(30);
        
        ImageView lily_legend3_display = new ImageView(lily_legend);
        lily_legend3_display.setX(150);
        lily_legend3_display.setY(180);
        lily_legend3_display.setFitHeight(30);
        lily_legend3_display.setFitWidth(30);
        
        
        ImageView lily_display = new ImageView(lily_legend);
        lily_display.setX(150);
        lily_display.setY(20);
        lily_display.setFitHeight(30);
        lily_display.setFitWidth(30);
        
        Text title = new Text();
		title.setText("3x     = Chief Duck");
		title.setX(120);
		title.setY(40);
		title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        
        
        Text text = new Text();
		text.setText("n°1: 0x");
		text.setX(65);
		text.setY(100);
		text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		Text text2 = new Text();
		text2.setText("n°2: 0x");
		text2.setX(65);
		text2.setY(150);
		text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		Text text3 = new Text();
		text3.setText("n°3: 0x");
		text3.setX(65);
		text3.setY(200);
		text3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
			    	

	    for (int i=0;i<5;i++) {
	    		layout.getChildren().addAll(list_rock.get(i).display(),list_lily.get(i).display());
	    		
	    }
	    
	    
	    	
	       
	    
		
        
        Scene scene = new Scene(layout, 1366, 768);
        

    
        Game gameOver = new Game();
        
        Image imageOk = new Image(getClass().getResourceAsStream("/images/start.png"));
		Button button = new Button("Reset Game",new ImageView(imageOk));
		button.setStyle(
                "-fx-background-color: rgb(6,120,242); "+    
                "-fx-font-weight: bold;"+
                "-fx-font-size: 15px;"+
                "-fx-padding-left: 15px; "+
                "-fx-cursor: hand;"+
                "-fx-text-fill: white"
                
        );
        button.setLayoutX(1150);
        button.setLayoutY(60);
        button.setOnAction(e -> {
        	list_duck.removeAll(list_duck);
        	list_lily.removeAll(list_lily);
        	list_rock.removeAll(list_rock);
        	mediaMain.stop();
        	
        	gameOver.main(primaryStage);
  
        });
        
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        

        layout.getChildren().addAll(board_details_display,title,text,text2,text3,lily_display,duck_legend1_display,duck_legend2_display,duck_legend3_display,lily_legend1_display,lily_legend2_display,lily_legend3_display,button);

	
  		
  		AnimationTimer timer = null;

			timer = new AnimationTimer() {
				float seconds = 0;
				Random rand = new Random(); 
				int direction = rand.nextInt(4);
				int changeDirection = -1;
				
				Image duck_right = new Image(getClass().getResourceAsStream("/images/duck_right_fat.png"));
			    Image duck_left = new Image(getClass().getResourceAsStream("/images/duck_left_fat.png"));
			    Image chiefDuck_left = new Image(getClass().getResourceAsStream("/images/chiefDuck_left_fat.png"));
			    Image chiefDuck_right = new Image(getClass().getResourceAsStream("/images/chiefDuck_right_fat.png"));
	
				
				@Override
				public void handle(long now) {
					
					
					
					for(int i=0; i<list_duck.size(); i++) {
						
						//text.setText("ee"+list_duck.get(0).getLily_eaten());

					if(list_duck.get(i).getChangeDirection() > -1) {
						
						//Right
						if(list_duck.get(i).getDirection() == 0) {
							//random.nextInt(max-min) + min;
							list_duck.get(i).setDirection(1);
							
						}
						//Left ok
						else if(list_duck.get(i).getDirection() == 1) {
							list_duck.get(i).setDirection(0);

						}
						//Up
						else if(list_duck.get(i).getDirection() == 2) {
							list_duck.get(i).setDirection(3);
						}
						//Down
						else if(list_duck.get(i).getDirection() == 3) {
							list_duck.get(i).setDirection(2);
						}
					}

					
					if (list_duck.get(i).getSeconds() > 1 ) {
						
						if (i == 0) {
							text.setText("n°1: " + list_duck.get(i).getLily_eaten() + "x         / Size is: " + list_duck.get(i).getSize());
						}
						else if( i == 1 ){
							text2.setText("n°2: " + list_duck.get(i).getLily_eaten() + "x  	     / Size is: " +list_duck.get(i).getSize());
						}
						else {
							text3.setText("n°3: " + list_duck.get(i).getLily_eaten() + "x  	     / Size is: " +list_duck.get(i).getSize());
						}
						
						if (!list_duck.get(i).isChief()) {//if duck isn't a chief
							if(list_duck.get(i).getSize()-1 <50) {
								list_duck.get(i).getImageView().setImage(null);
								list_duck.get(i).getLabel().setText("");
								
								if (i == 0) {
									text.setText("n°1: ");
									lily_legend1_display.setImage(null);
								}
								else if( i == 1 ){
									text2.setText("n°2: ");
									lily_legend2_display.setImage(null);
								}
								else {
									text3.setText("n°3: ");
									lily_legend3_display.setImage(null);
								}
								
								if (list_duck.size()-1==0) {// LOOSE SCENARIO (you don't have any ducks left)
									System.out.println("GAME OVER ---------------------------------------------------------------------------------");
									  
									   mediaMain.stop();
									  gameOver.Over(primaryStage, mediaLost);
									 
								      
								      list_duck.remove(i);
								      break;//we exit with a break to Display the end scene before the loop continues and displays an index out of range
								      
								}
								else {
									list_duck.remove(i);
								}
								
								
							}
							else {
								list_duck.get(i).getImageView().setFitWidth(list_duck.get(i).getSize()-1);
								list_duck.get(i).getImageView().setFitHeight(list_duck.get(i).getSize()-1);
								list_duck.get(i).setSize(list_duck.get(i).getSize()-1);
							}
							
						}
						else {// WIN SCENARIO
							if (list_duck.size()==1) {// If there is only one chief duck left, you win
								mediaMain.stop();
								gameOver.win(primaryStage);
								list_duck.removeAll(list_duck);
							}
							else if (list_duck.size()==2 && (list_duck.get(0).isChief() == true && list_duck.get(1).isChief() == true)) { // If there are two chief duck left, you win
								mediaMain.stop();
								gameOver.win(primaryStage);
								list_duck.removeAll(list_duck);
							}
							else if (list_duck.size()==3 && list_duck.get(0).isChief() == true && list_duck.get(1).isChief() == true && list_duck.get(2).isChief() == true) { // If all of your ducks are chief ducks, you win
								mediaMain.stop();
								gameOver.win(primaryStage);
								list_duck.removeAll(list_duck);
							}
						}
						
				
						list_duck.get(i).setDirection(rand.nextInt(4));
						
						list_duck.get(i).setSeconds(0);
					}
					
					//Add new water lily
					
					if(list_lily.size() < 5 && list_duck.get(i).isEating() == false) {
						
						WaterLily lilyNew = pond.addPondRandomWaterLily(list_duck);
				        
				        list_lily.add(lilyNew);
				       
				        
				        layout.getChildren().addAll(lilyNew.display());
				    	
					}
					
					

					list_duck.get(i).setSeconds(list_duck.get(i).getSeconds()+0.01f);
					
					changeDirection = list_duck.get(i).move(list_duck.get(i).getImageView(), list_lily, list_rock, list_duck, list_duck.get(i), mediaPlayer, layout, list_duck.get(i).getDirection(), duck_right, duck_left, chiefDuck_left, chiefDuck_right);
					
					list_duck.get(i).setChangeDirection(changeDirection);
					
				if (list_duck.get(i).getChangeDirection() == 4) {
						//-------------------------------------------------------
						if (list_duck.get(i).getLily_eaten()+1 > 2 && !list_duck.get(i).isChief()) {// if duck eats 3 lilies he becomes chief
							
							list_duck.get(i).setLily_eaten(list_duck.get(i).getLily_eaten()+1);//Adding a Lily to the total lily eaten
							list_duck.get(i).getImageView().setImage(new Image(getClass().getResourceAsStream("/images/chiefDuck_left_fat.png")));
							mediaKing.seek(Duration.ZERO);
							mediaKing.play();
							list_duck.get(i).setChief(true);
							if (i == 0) {
								duck_legend1_display.setImage(new Image(getClass().getResourceAsStream("/images/chiefDuck_right_fat.png")));
							}
							else if( i == 1 ){
								duck_legend2_display.setImage(new Image(getClass().getResourceAsStream("/images/chiefDuck_right_fat.png")));
							}
							else {
								duck_legend3_display.setImage(new Image(getClass().getResourceAsStream("/images/chiefDuck_right_fat.png")));
							}
						}
						else {
							list_duck.get(i).getImageView().setFitWidth(list_duck.get(i).getSize()+15);//Making the duck 15px bigger
							list_duck.get(i).getImageView().setFitHeight(list_duck.get(i).getSize()+15);//Making the duck 15px bigger
							list_duck.get(i).setSize(list_duck.get(i).getSize()+15);//Making the duck 15px bigger
							direction = rand.nextInt(4);
							
							list_duck.get(i).setLily_eaten(list_duck.get(i).getLily_eaten()+1);//Adding a Lily to the total lily eaten
						}

						list_duck.get(i).setChangeDirection(rand.nextInt(4));
					}
					
				
			
					}

				}
			};
		
		timer.start();
  		
        
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


		
	}
	
	
	public static int obstX(){
		Random rand = new Random();
		return rand.nextInt(1151-121)+121; //Generate random number between 121 and 1150
	}
	
	public static int obstY(){	
		Random rand = new Random();	
		return rand.nextInt(650-325)+325; //Generate random number between 325 and 650
	}
	
	/*
	public static boolean verif_secondary_collide(int Arg1X,int Arg1Y, int Arg2X,int Arg2Y) {
		
		
		if((Arg1X >= Arg2X && Arg1X >= Arg2X+25) || (Arg1X <= Arg2X && Arg1X+25 <= Arg2X)){ //Verify X collision
			
			if((Arg1Y >= Arg2Y && Arg1Y >= Arg2Y+25) || (Arg1Y <= Arg2Y && Arg1Y+25 <= Arg2Y)) { //Verify Y collision
				
				return true;
			}
			else {
				
				return false;
			}		
		}
		else {
			
			return false;
		}
	}
*/	
	public static boolean verif_secondary_collide(int Arg1X,int Arg1Y, int Arg2X,int Arg2Y, int Arg1Width, int Arg1Height, int Arg2Width, int Arg2Height) {
		
		Rectangle r = new Rectangle(Arg1X,Arg1Y,Arg1Width,Arg1Height);
	    Rectangle p = new Rectangle(Arg2X,Arg2Y,Arg2Width,Arg2Height);

	    // Assuming there is an intersect method, otherwise just handcompare the values
	    if (r.intersects(p))
	    {
	       return true;
	    }
	    else {
	    	return false;
	    }
	}

	public static boolean verif_list_collide(List<WaterLily> list_lily, List<Rock> list_rock, List<Duck> list_duck, int turn_eleX, int turn_eleY, int duck_size) {
	
		boolean collide_rock = false;
		boolean collide_lily = false;
		boolean collide_duck = false;
		

		for (int i=0;i<list_lily.size();i++) {
			
			collide_lily = verif_secondary_collide(list_lily.get(i).getPositionX(),list_lily.get(i).getPositionY(), turn_eleX, turn_eleY, 60, 60, duck_size, duck_size);
			
			//True donc collision
			if (collide_lily) {

				return collide_lily;
			}

		}
		
		for (int i=0;i<list_rock.size();i++) {
			collide_rock = verif_secondary_collide(list_rock.get(i).getPostionX(),list_rock.get(i).getPostionY(), turn_eleX,turn_eleY, 60, 60, duck_size, duck_size);
			
			//True donc collision
			if (collide_rock) {

				return collide_rock;
			}

		}

		for (int i=0;i<list_duck.size();i++) {
			collide_duck = verif_secondary_collide(list_duck.get(i).getPositionX(),list_duck.get(i).getPositionY(), turn_eleX,turn_eleY, list_duck.get(i).getSize(), list_duck.get(i).getSize(), duck_size, duck_size);
			
			//True donc collision
			if (collide_duck) {

				return collide_duck;
			}

		}
		
		return false;		
	}
	
	public static Object verif_object_list_collide(List<WaterLily> list_lily, List<Rock> list_rock, int turn_eleX, int turn_eleY, int DuckSize) {
		
		boolean collide_rock = false;
		boolean collide_lily = false;

		
		for (int i=0;i<list_lily.size();i++) {
			
			collide_lily = verif_secondary_collide(list_lily.get(i).getPositionX(),list_lily.get(i).getPositionY(), turn_eleX, turn_eleY, 60, 60, DuckSize, DuckSize);
			
			//True donc collision
			if (collide_lily) {

				return list_lily.get(i);
			}

		}
		
		for (int i=0;i<list_rock.size();i++) {
			collide_rock = verif_secondary_collide(list_rock.get(i).getPostionX(),list_rock.get(i).getPostionY(), turn_eleX,turn_eleY, 60, 60, DuckSize, DuckSize);
			
			//True donc collision
			if (collide_rock) {

				return list_rock.get(i);
			}

		}

		
		return null;		
	}
	
public static Boolean verif_duck_list_collide(List<Duck> list_duck, int turn_eleX, int turn_eleY, int DuckSize, Duck argDuck) {
		
		boolean collide_duck = false;
		
		List<Duck> compare_duck_list = new ArrayList<Duck>();
		
		compare_duck_list.addAll(list_duck);
		
		compare_duck_list.remove(argDuck);
		
		for (int i=0;i<compare_duck_list.size();i++) {
			
			collide_duck = verif_secondary_collide((int) compare_duck_list.get(i).getImageView().getX(), (int) compare_duck_list.get(i).getImageView().getY(), turn_eleX, turn_eleY, compare_duck_list.get(i).getSize(), compare_duck_list.get(i).getSize(), DuckSize, DuckSize);
			
			//False donc collision
			if (collide_duck) {

				return true;
			}

		}
		

		
		return false;		
	}

}
