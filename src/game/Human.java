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
public abstract class Human extends GameElement{
//	private int x;
//	private int y;
	protected int damage;
	protected int health;
	protected Image img;
	protected boolean toBeRemoved;
	
	
	public Human(int x,int y) throws SlickException{
		super(x, y);
		toBeRemoved=false;
	}
	
	
	public void takeDamage(int damage) {
		health=health-damage;
	}
	
	public void draw() {
		img.draw(x, y, 50, 50);
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
	
	public boolean isToBeRemoved() {
		return toBeRemoved;
	}
	public void setToBeRemoved() {
		toBeRemoved=true;
	}

}
