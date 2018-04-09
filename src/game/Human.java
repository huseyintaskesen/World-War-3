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
	private Image img;
	
	
	public void setDamage(int damage) {
		this.damage = damage;
	}


	public void setImg(Image img) {
		this.img = img;
	}


	public Human(int x,int y) throws SlickException{
		super(x, y);
		
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
	
	

}
