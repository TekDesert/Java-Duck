package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import application.Game;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Pond {
	int width;
	int height;
	List<WaterLily> list_lily;
	List<Rock> list_rock;
	List<Duck> list_duck;
	
	
	public Pond(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		list_rock = new ArrayList<Rock>();
		list_lily = new ArrayList<WaterLily>();
		list_duck = new ArrayList<Duck>();
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public List<WaterLily> getList_lily() {
		return list_lily;
	}
	public void setList_lily(List<WaterLily> list_lily) {
		this.list_lily = list_lily;
	}
	public List<Rock> getList_Rock() {
		return list_rock;
	}
	public void setList_Rock(List<Rock> list_rock) {
		this.list_rock = list_rock;
	}
	
	public List<Rock> getList_rock() {
		return list_rock;
	}

	public void setList_rock(List<Rock> list_rock) {
		this.list_rock = list_rock;
	}

	public List<Duck> getList_duck() {
		return list_duck;
	}

	public void setList_duck(List<Duck> list_duck) {
		this.list_duck = list_duck;
	}


public List<Duck> generatePondRandomDuck(){
	
    int duckX;
    int duckY;
	int count = 1;
	boolean add_lily_final = false;
    List<Duck> list_duck = new ArrayList<Duck>();

   	do {

   			duckX = Game.obstX();
   			duckY = Game.obstY();
		    

			if (list_duck.size()<5) { //else we will check if the NEW rock will collide with a previously added rock or lily
			    		
				add_lily_final = Game.verif_list_collide(list_lily,  list_rock, list_duck, duckX, duckY, 80);
			    		
			    if(!add_lily_final) {
			    	String duck_name = "Duck " + count;
	    			Duck new_duck = new Duck(duckX,duckY,"yellow",80, duck_name);
					ImageView duck_display = new_duck.display();
					Label label = new Label(duck_name);
					label.setTranslateX(duckX);
					label.setTranslateY(duckY);
					new_duck.setLabel(label);
					new_duck.setImageView(duck_display);
					list_duck.add(new_duck);
					
				    count += 1;
			    }

			}   	

			    
		} while (list_duck.size() < 3); //We will do this until we have our 5 lilies and rocks
		    
		
		return list_duck;

		
	}
	
	public List<Rock> generatePondRandomRock(){
		
	    int rockX;
	    int rockY;
	    int count = 0;
	    boolean add_rock_final = false;
	    
    
    	do {

		    rockX = Game.obstX();
		    rockY = Game.obstY();
		    
		    
			if (list_rock.size()==0){ //there are no previous lily or rocks we directly add them
			    	
					list_rock.add(new Rock(rockX,rockY));
			    
				}
			    	

			if ((list_rock.size()>0)&& (list_rock.size()<5)) { //else we will check if the NEW rock will collide with a previously added rock or lily
			    		
				    	add_rock_final = Game.verif_list_collide(list_lily,  list_rock, list_duck, rockX,rockY, 60);
			    		
			    		if(!add_rock_final) {

			    			list_rock.add(new Rock(rockX,rockY));
			    		}

				    }   	

			    count += 1;
			    
		    }
		    while (list_rock.size() !=5); //We will do this until we have our 5 lilies and rocks
		    
		
		return list_rock;
		
	}
	public List<WaterLily> generatePondRandomWaterLily() {
		
	    int lilyX;
	    int lilyY;
	    int count = 0;
	    boolean add_lily_final = false;
	    
    
    	do {

		    lilyX = Game.obstX();
		    lilyY = Game.obstY();
		    

			if (list_lily.size()<5) { //else we will check if the NEW rock will collide with a previously added rock or lily
			    		
				add_lily_final = Game.verif_list_collide(list_lily,  list_rock, list_duck, lilyX,lilyY, 60);
			    		
			    if(!add_lily_final) {
			    			list_lily.add(new WaterLily(lilyX,lilyY));
			    		}

				    }   	

			    count += 1;
			    
		    }
		    while (list_lily.size() !=5); //We will do this until we have our 5 lilies and rocks
		    
		
		return list_lily;
			   
	}

	public WaterLily addPondRandomWaterLily(List<Duck> list_duck) {
		
	    int lilyX;
	    int lilyY;
	    int count = 0;
	    boolean add_lily_final = false;
	    
    
    	do {

		    lilyX = Game.obstX();
		    lilyY = Game.obstY();
		    
		    add_lily_final = Game.verif_list_collide(list_lily,  list_rock, list_duck, lilyX,lilyY, 60);
			     	

		    count += 1;

			
    	}while (add_lily_final); //We will do this until we have our 5 lilies and rocks
		
		 WaterLily lily = new WaterLily(lilyX,lilyY);
		
		return lily;
			   
	}

	
}
