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
<<<<<<< HEAD:src/model/CasualRobot.java
public class CasualRobot extends RobotSide{
	private final int startHealth = 18;
	private final float speed = 5;
=======
public class Casual extends RobotSide{
	private final int startHealth=5;
	private final float speed = 8;
>>>>>>> bacfcf9ebd143ed4905e5f284449ee26191dd496:src/model/Casual.java
	
	
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
