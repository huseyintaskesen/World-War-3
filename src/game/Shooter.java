/**
 * 
 */
package game;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @author ibrahim
 *
 */
public class Shooter {
	private final int startHealth=20;
	
	private Image shooter=new Image("res/shooter.png");
	
	private int damage;
	private int health;
	private int range;
	private int x;
	private int y;
	protected ArrayList<Bullet> bullets;
	
	
	public Shooter(int x,int y) throws SlickException {
		damage = 2;
		health= startHealth;
		range = 400;//TODO to be changed
		this.x=x;
		this.y=y;
		bullets = new ArrayList<Bullet>();
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

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void takeDamage(int damage) {
		health=health-damage;
	}
	public void attackToRobot(Casual robot) throws SlickException {
		bullets.add(new Bullet(getX()+50, getY()+25, damage));
	}
	public void draw() {
		shooter.draw(x, y, 50, 50);
	}
}
