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
public class Casual {
	private final int startHealth=500;
	
	private Image casual=new Image("res/casualtemp.png");
	
	private int damage;
	private int health;
	private int speed;	
	private int x;
	private int y;
	
	
	public Casual(int x,int y) throws SlickException{
		damage = 2;
		health=startHealth;
		speed=4;
		this.x=x;
		this.y=y;
	}

	public int getDamage() {
		return damage;
	}

	public int getHealth() {
		return health;
	}

	public int getSpeed() {
		return speed;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
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
	public void attackToHuman(Shooter human) {
		human.takeDamage(damage);
	}
	public void updateLocation() {
		x=x-speed/2;
	}
	public void draw() {
		casual.draw(x, y, 50, 50);
	}
}
