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
	private final int startHealth=5;
	
	private Image casual=new Image("res/casualtemp.png");
	
	private int damage;
	private int health;
	private int speed;	
	private int x;
	private int y;
	
	
	public Casual(int x,int y) throws SlickException{
		damage = 2;
		health=startHealth;
		speed=2;
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
