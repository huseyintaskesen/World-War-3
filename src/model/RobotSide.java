/**
 * 
 */
package model;

import org.newdawn.slick.SlickException;

/**
 * @author ibrahim
 *
 */
public abstract class RobotSide extends GameElement{
	private int damage;
	private int health;
	private float curSpeed;
	private boolean isRunning;
	private int attackTime;
	
	public RobotSide(float x,float y) throws SlickException{
		super(x, y);
		isRunning=true;
	}
	
	public void takeDamage(int damage) {
		if(health-damage<=0)
			setToBeRemoved();
		else
			health=health-damage;
	}
	
	public int getDamage() {
		return damage;
	}

	/**
	 * @param damage the damage to set
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public float getSpeed() {
		return curSpeed;
	}
	
	public void setSpeed(float speed) {
		this.curSpeed=speed;
	}
	
	public void attackToHuman(HumanSide tempHuman) {
		tempHuman.takeDamage(getDamage());
	}
	public void updateLocation() {
		setX(getX()-curSpeed/10);
	}
	public void run() {
		isRunning=true;
		setSpeed(classSpeed());
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	
	public void stop() {
		curSpeed=0;
		isRunning=false;
	}

	
	public int getAttackTime() {
		return attackTime;
	}

	
	public void setAttackTime(int attackTime) {
		this.attackTime = attackTime;
	}

	public abstract float classSpeed();
	public void slow() {
		setSpeed(classSpeed()/2);
	}
}
