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
public abstract class Robot extends GameElement{
	protected int damage;
	protected int health;
	protected int curSpeed;
	protected Image img;
	protected boolean isRunning;
	
	
	public Robot(int x,int y) throws SlickException{
		super(x, y);
		isRunning=true;
	}
	
	public void takeDamage(int damage) {
		health=health-damage;
	}
	
	public void draw() {
		img.draw(getX(), getY(), 50, 50);
	}
	
	public int getDamage() {
		return damage;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getSpeed() {
		return curSpeed;
	}
	
	public void attackToHuman(AttackerHuman shooter) {
		shooter.takeDamage(damage);
	}
	public void updateLocation() {
		setX(getX()-curSpeed/2);
	}
	public void run() {
		isRunning=true;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	
	public void stop() {
		curSpeed=0;
		isRunning=false;
	}
}
