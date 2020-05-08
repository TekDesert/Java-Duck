package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WaterLily {
	
	
	private int positionX;
	private int positionY;
	ImageView imageView;
	Image image;
	
	public WaterLily(int postionX, int postionY) {
		super();
		this.positionX = postionX;
		this.positionY = postionY;
		
	}
	
	public ImageView display() {

		image = new Image(getClass().getResourceAsStream("/images/lily1.png"));
	
		imageView = new ImageView(image);
		
		imageView.setX(this.positionX); 
	    imageView.setY(this.positionY); 
	    imageView.setFitHeight(60); 
	    imageView.setFitWidth(60); 
	    
	    return imageView;
	    
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

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	
	
}
