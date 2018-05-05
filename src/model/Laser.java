/**
 * 
 */
package model;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @author ibrahim
 *
 */
public class Laser extends GameElement {

	/**
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public Laser(float x, float y) throws SlickException {
		super(x, y);

		setImg(new Image("res/Robots & Humans/laser.png"));
	}

	/**
	 * @param robots 
	 * 
	 */
	public void damageRobots(ArrayList<RobotSide> robots) {
		
		for (int i = 0; i < robots.size(); i++) {
			RobotSide tempRobot = robots.get(i);
			if(tempRobot.getY() == this.getY()) {
				
				tempRobot.takeDamage(9999);
			}
		}
		
	}

}
