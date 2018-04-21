/**
 * 
 */
package model;

import org.newdawn.slick.SlickException;

/**
 * @author ibrahim
 *
 */
public abstract class Robot extends GameElement{
	private int damage;
	private int health;
	private int curSpeed;
	private boolean isRunning;
	private int attackTime;
	
	public Robot(int x,int y) throws SlickException{
		super(x, y);
		isRunning=true;
	}
	
	public void takeDamage(int damage) {
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
	
	public int getSpeed() {
		return curSpeed;
	}
	
	public void setSpeed(int speed) {
		this.curSpeed=speed;
	}
	
	public void attackToHuman(AttackerHuman shooter) {
		shooter.takeDamage(getDamage());
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

	/**
	 * @return the attackTime
	 */
	public int getAttackTime() {
		return attackTime;
	}

	/**
	 * @param attackTime the attackTime to set
	 */
	public void setAttackTime(int attackTime) {
		this.attackTime = attackTime;
	}
}
