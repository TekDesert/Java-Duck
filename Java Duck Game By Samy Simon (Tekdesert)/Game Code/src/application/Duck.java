package application;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;




public class Duck {
	
	private int positionX;
	private int positionY;
	private String color;
	private int size;
	private Image duck;
	private ImageView imageView;
	private Image emotion_eat;
	private ImageView view_eat;
	private boolean isEating;
	private int lily_eaten;
	private boolean isChief;
	private boolean duckRockCollision;
	private int changeDirection;
	private int direction;
	private String name;
	private float seconds;
	private Label label;
	
	
	
	public Duck(int positionX, int positionY, String color, int size, String name) {
		super();
		this.positionX = positionX;
		this.positionY = positionY;
		this.color = color;
		this.size = size;
		this.isEating = false;
		this.name = name;
	}
	
	public ImageView display() {
		
		duck = new Image(getClass().getResourceAsStream("/images/duck_right_fat.png"));

	
		imageView = new ImageView(duck);
		
		imageView.setX(this.positionX); 
	    imageView.setY(this.positionY); 
	    imageView.setFitHeight(this.size); 
	    imageView.setFitWidth(this.size); 
	    
	    return imageView;
	}
	
	public ImageView eat(int duck_X, int duck_Y) {
		
	
		emotion_eat = new Image(getClass().getResourceAsStream("/images/eat_lily.png"));
		
		ImageView view_eat = new ImageView(emotion_eat); 
		
		view_eat.setX(duck_X+40); 
		view_eat.setY(duck_Y-80);
	     
		/*
		if (ArgRight) {
			view_eat.setX(duck_X+40); 
			view_eat.setY(duck_Y-80); 
		}
		else {
			view_eat.setX(duck_X+40); 
			view_eat.setY(duck_Y-80); 	
		}
		*/
		
		view_eat.setFitHeight(80); 
		view_eat.setFitWidth(80);
	    //Setting the position of the image 
		
	    
	    return view_eat;
	}
	
	public int move(ImageView duck_display, List<WaterLily> list_lily,List<Rock> list_rock, List<Duck> list_duck, Duck ArgDuck,MediaPlayer mediaPlayer,Pane layout, int direction, Image duck_right, Image duck_left, Image chiefDuck_left, Image chiefDuck_right) {
		


        Rock rock_duck = null; 
        WaterLily lily_duck = null;
 
  
		int size_duck_border = ArgDuck.getSize()-80;
	
		
		if(!ArgDuck.isEating) {

			
	    	//Right
			if (direction == 0) {
				if (duck_display.getX() + 4 < (1170 - size_duck_border)) {
					duck_display.setX(duck_display.getX() + 0.5);
					label.setTranslateX(duck_display.getX() + 0.5);
		
					if (!isChief) {
						duck_display.setImage(duck_right);
					}
					else {
						
						duck_display.setImage(chiefDuck_right);
				
					}
					
				}
				else {
					return direction;
				}
			}
			//Left
			else if (direction == 1) {
				if (duck_display.getX() - 4 > (100 - size_duck_border)) {
					duck_display.setX(duck_display.getX() - 0.5);
					label.setTranslateX(duck_display.getX() - 0.5);
					if (!isChief) {
						duck_display.setImage(duck_left);
					}
					else {
						duck_display.setImage(chiefDuck_left);
					
					}
					
				}
				else {
					return direction;
				}
			}
			//Up
			else if (direction == 2) {
				if (duck_display.getY() - 4 > (255 - size_duck_border)) {
					duck_display.setY(duck_display.getY() - 0.5);
					label.setTranslateY(duck_display.getY() - 0.5);
					
				}
				else {
					return direction;
				}
			}
			//Down
			else if (direction == 3) {
				if (duck_display.getY() - 4 < (635 - size_duck_border)) {
					duck_display.setY(duck_display.getY() + 0.5);
					label.setTranslateY(duck_display.getY() + 0.5);
			
				
				}
				else {
					return direction;
				}
			}
		}
		
  	  Object obj = Game.verif_object_list_collide(list_lily, list_rock, (int) duck_display.getX(), (int) duck_display.getY(), ArgDuck.getSize());
  	    
	    	if(obj != null) {
  	    	
	    		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	    		System.out.println("Collision detected !");
	    		System.out.println(ArgDuck.isDuckRockCollision());
	    		
  	    	
			if (obj instanceof Rock) {
				
				rock_duck = (Rock) obj;
				System.out.println("Its a rock : " + rock_duck.getPostionX() + "," + rock_duck.getPostionY());
	
				System.out.println("Duck direction : " + direction);
				 
				 return direction;
				 
				/*
				Thread thread = new Thread(() -> {
		            try {
		            	System.out.println("/////////////////////////////////////////");
		            	System.out.println("Duck Waiting");
		                Thread.sleep(2000);
		            } catch (InterruptedException exc) {
		                throw new Error("Unexpected interruption", exc);
		            }
		            
		            Platform.runLater(new Runnable() {
		                @Override public void run() {
		                	System.out.println("ROCK AFTER WAIT !!!!!!!!!!");
				           ArgDuck.duckRockCollision = false;
		                }
		            });
				}); 
		            
		       
		        thread.start();*/
				
				
			}
			if (obj instanceof WaterLily) {
				
				System.out.println("Duck is eating !");
				ArgDuck.isEating = true;
				lily_duck = (WaterLily) obj;
				
				ImageView emote_eat = ArgDuck.eat((int) duck_display.getX(),(int) duck_display.getY());
				
				
				System.out.println("Its a lily : " + lily_duck.getPositionX() + "," + lily_duck.getPositionY());
				System.out.println("Its " + ArgDuck.getName());
				System.out.println("Duck position : " + duck_display.getX() + "," + duck_display.getY());
				
				
				
				mediaPlayer.seek(Duration.ZERO);
				mediaPlayer.play();
				layout.getChildren().addAll(emote_eat);
				
				Platform.runLater(new Runnable() {
	                @Override public void run() {
	                	try {
			            	System.out.println("/////////////////////////////////////////");
			            	System.out.println(ArgDuck.getName() + " Waiting");
			                Thread.sleep(2000);
			            } catch (InterruptedException exc) {
			                throw new Error("Unexpected interruption", exc);
			            }
			            
	                	System.out.println("LILY AFTER WAIT !!!!!!!!!!");
			            ((WaterLily) obj).getImageView().setImage(null);
						list_lily.remove(obj);
						emote_eat.setImage(null);
						ArgDuck.isEating = false;
						
	                }
	            });
	
				return 4;
				/*
				Thread thread = new Thread(() -> {
		            try {
		            	System.out.println("/////////////////////////////////////////");
		            	System.out.println("Duck Waiting");
		                Thread.sleep(2000);
		            } catch (InterruptedException exc) {
		                throw new Error("Unexpected interruption", exc);
		            }
		            
		            Platform.runLater(new Runnable() {
		                @Override public void run() {
		                	System.out.println("LILY AFTER WAIT !!!!!!!!!!");
				            ((WaterLily) obj).getImageView().setImage(null);
							list_lily.remove(obj);
							emote_eat.setImage(null);
							ArgDuck.isEating = false;
		                }
		            });
				}); 
		            
		       
		        thread.start();*/
		        
		        
		      
			
	
      			
			}
			


  	    	
  	    	
  	    }
			
	  	  Boolean duck_collision = Game.verif_duck_list_collide(list_duck, (int) duck_display.getX(), (int) duck_display.getY(), ArgDuck.getSize(), ArgDuck);
	  	  
	  	  if(duck_collision) {
	  			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	      		System.out.println("Collision detected with a Duck !");
	      		System.out.println("Duck direction : " + direction);
	      		return direction;
	  	  }
			
	    return -1;	
	}
	
	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Image getDuck() {
		return duck;
	}
	public void setDuck(Image duck) {
		this.duck = duck;
	}

	public boolean isEating() {
		return isEating;
	}

	public void setEating(boolean isEating) {
		this.isEating = isEating;
	}

	public boolean isDuckRockCollision() {
		return duckRockCollision;
	}

	public void setDuckRockCollision(boolean duckRockCollision) {
		this.duckRockCollision = duckRockCollision;
	}

	public int getChangeDirection() {
		return changeDirection;
	}

	public void setChangeDirection(int changeDirection) {
		this.changeDirection = changeDirection;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public float getSeconds() {
		return seconds;
	}

	public void setSeconds(float seconds) {
		this.seconds = seconds;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean isChief() {
		return isChief;
	}

	public void setChief(boolean isChief) {
		this.isChief = isChief;
	}

	public int getLily_eaten() {
		return lily_eaten;
	}

	public void setLily_eaten(int lily_eaten) {
		this.lily_eaten = lily_eaten;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}
	

}
