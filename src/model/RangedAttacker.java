/**
 * 
 */
package model;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * @author ibrahim
 *
 */
public abstract class RangedAttacker extends HumanSide {
	
	private ArrayList<Bullet> bullets;
	private int range;
	private int reloadTime;
	public Sound shotSound;

	/**
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public RangedAttacker(int x, int y) throws SlickException {
		super(x, y);
		bullets = new ArrayList<Bullet>();
		shotSound = new Sound("res/fire-shot.wav");

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
	
	public void attackToRobot(RobotSide casual) throws SlickException {
		if(this instanceof Shooter)
			bullets.add(new NormalBullet(getX()+50, getY()+25, getDamage()));
		else if(this instanceof Freezer)
			bullets.add(new FreezerBullet(getX()+50, getY()+25, getDamage()));
//		shotSound.play(0.3f,0.3f);
//		shotSound.play(0.5f,0.1f);
//		shotSound.play(0.3f,0.1f);
		shotSound.play(1.0f,0.1f);
	}

	public int getReloadTime() {
		return reloadTime;
	}

	public void setReloadTime(int reloadTime) {
		this.reloadTime = reloadTime;
	}
	
	
}
