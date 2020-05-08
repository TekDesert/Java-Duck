package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Rock {
	private int positionX;
	private int positionY;
	
	ImageView imageView;
	Image image;
	
	public Rock(int postionX, int postionY) {
		super();
		this.positionX = postionX;
		this.positionY = postionY;
		
	}
	
	public ImageView display() {

		image = new Image(getClass().getResourceAsStream("/images/rock.png"));
		
	
		imageView = new ImageView(image);
		
		imageView.setX(this.positionX); 
	    imageView.setY(this.positionY); 
	    imageView.setFitHeight(60); 
	    imageView.setFitWidth(60); 
	    
	    return imageView;    
	}
	
	public int getPostionX() {
		return positionX;
	}
	public void setPostionX(int postionX) {
		this.positionX = postionX;
	}
	public int getPostionY() {
		return positionY;
	}
	public void setPostionY(int postionY) {
		this.positionY = postionY;
	}
}
