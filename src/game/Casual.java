/**
 * 
 */
package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @author ibrahim
 *
 */
public class Casual extends Robot{
	private final int startHealth=5;
	private final int speed = 2;
	
	
	public Casual(int x,int y) throws SlickException{
		super(x, y);
		img=new Image("res/Robots & Humans/basicRobot.png");
		damage = 3;
		health=startHealth;
		curSpeed=speed;
	}

	public void run() {
		curSpeed = speed;
	}
	

	
}
