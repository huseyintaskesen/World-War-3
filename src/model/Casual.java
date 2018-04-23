/**
 * 
 */
package model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @author ibrahim
 *
 */
public class Casual extends RobotSide{
	private final int startHealth=5;
	private final int speed = 3;
	
	
	public Casual(int x,int y) throws SlickException{
		super(x, y);
		setImg(new Image("res/Robots & Humans/basicRobot.png"));
		setDamage(2);
		setHealth(startHealth);
		setSpeed(speed);
	}

	public void run() {
		super.run();
		setSpeed(speed);
	}
	

	
}
