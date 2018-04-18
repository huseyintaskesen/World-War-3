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
<<<<<<< HEAD
		setImg(new Image("res/Robots & Humans/basicRobot.png"));
		setDamage(2);
		setHealth(startHealth);
		setSpeed(speed);
=======
		img=new Image("res/Robots & Humans/basicRobot.png");
		damage = 3;
		health=startHealth;
		curSpeed=speed;
>>>>>>> d1759dba99317a36a3fe94269e9e61a94b03c120
	}

	public void run() {
		super.run();
		setSpeed(speed);
	}
	

	
}
