package model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Obstacle extends Passive {
	private final int startHealth = 30;
	public Obstacle(int x, int y) throws SlickException{
		super(x,y);
		setImg(new Image("res/Robots & Humans/obstacle.png"));
		
		setHealth(startHealth);
		setCost(75);
	}
}
