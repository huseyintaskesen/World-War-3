/**
 * 
 */
package game;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

/**
 * @author ibrahim
 *
 */
public interface AggressiveHuman  {
	//ArrayList<Bullet> bullets=new ArrayList<Bullet>();
	//int range;
	public void attackToRobot(Casual robot) throws SlickException;
	public void draw();
	
	
	public int getX();
	public int getY();
	/**
	 * @return
	 */
	public int getRange();
	public void takeDamage(int damage);
	/**
	 * @return
	 */
	public int getHealth();
	public ArrayList<Bullet> getBullets();
}
