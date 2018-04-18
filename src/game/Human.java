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
	private int damage;
	private int health;
	
	
	
	public void setDamage(int damage) {
		this.damage = damage;
	}


	public Human(int x,int y) throws SlickException{
		super(x, y);
		
	}
	
	
	public void takeDamage(int damage) {
		health=health-damage;
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
	
	

}
