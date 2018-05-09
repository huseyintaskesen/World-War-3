package model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Obstacle extends Passive {
<<<<<<< HEAD
	private final int startHealth = 30;
=======
	private final int startHealth = 40;
	public final static int cost = 75;
>>>>>>> bacfcf9ebd143ed4905e5f284449ee26191dd496
	public Obstacle(int x, int y) throws SlickException{
		super(x,y);
		setImg(new Image("res/Robots & Humans/obstacle.png"));
		
		setHealth(startHealth);
	}
}
