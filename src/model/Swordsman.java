package model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Swordsman extends MeleeAttacker {
<<<<<<< HEAD
	private final int startHealth = 7;
	
=======
	private final int startHealth = 20;
	public final static int cost = 100;
>>>>>>> bacfcf9ebd143ed4905e5f284449ee26191dd496
	private int chargeTime;
	
	public Swordsman(int x, int y) throws SlickException{
		super(x,y);
		setImg(new Image("res/Robots & Humans/swordsman4.png"));
		
		setDamage(2);
		setHealth(startHealth);
	}
	
	public int getChargeTime() {
		return chargeTime;
	}
	public void setChargeTime(int chargeTime) {
		this.chargeTime = chargeTime;
	}

	/* (non-Javadoc)
	 * @see model.MeleeAttacker#attackToRobot(model.RobotSide)
	 */
	@Override
	public void attackToRobot(RobotSide robot) {
		robot.takeDamage(getDamage());
	}
}
