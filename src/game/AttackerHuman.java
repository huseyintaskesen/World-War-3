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
public abstract class AttackerHuman extends Human {
	
	protected ArrayList<Bullet> bullets;
	protected int range;
	/**
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public AttackerHuman(int x, int y) throws SlickException {
		super(x, y);
		bullets = new ArrayList<Bullet>();
	}
	
	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
	
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}


	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}
	
	public void attackToRobot(Robot casual) throws SlickException {
		bullets.add(new Bullet(getX()+50, getY()+25, damage));
	}

}
