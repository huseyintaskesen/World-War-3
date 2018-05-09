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
public class CasualRobot extends RobotSide{
	private final int startHealth = 18;
	private final float speed = 5;
	
	public CasualRobot(float x,float y) throws SlickException{
		super(x, y);
		setImg(new Image("res/Robots & Humans/basicRobot.png"));
		setDamage(2);
		setHealth(startHealth);
		setSpeed(speed);
	}

	public float classSpeed() {
		return speed;
	}	
}
