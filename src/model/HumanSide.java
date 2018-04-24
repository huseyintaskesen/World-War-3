/**
 * 
 */
package model;


import org.newdawn.slick.SlickException;

/**
 * @author ibrahim
 *
 */
public abstract class HumanSide extends GameElement{
	private int damage;
	private int health;
	private static int cost;
	
	public HumanSide(float x,float y) throws SlickException{
		super(x, y);
		
	}
	
	public static int getCost() {
		return cost;
	}


	public void setCost(int cost) {
		HumanSide.cost = cost;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}


	
	
	
	public void takeDamage(int damage) {
		if(health<=0)
			setToBeRemoved();
		else
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
