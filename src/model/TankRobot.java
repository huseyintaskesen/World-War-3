package model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TankRobot extends RobotSide{
<<<<<<< HEAD
	private final int startHealth=24;
	private final int speed = 3;
=======
	private final int startHealth=7;
	private final int speed = 5;
>>>>>>> bacfcf9ebd143ed4905e5f284449ee26191dd496
	
	
	public TankRobot(float x,float y) throws SlickException{
		super(x, y);
		setImg(new Image("res/Robots & Humans/tankRobot.png"));
		setDamage(3);
		setHealth(startHealth);
		setSpeed(speed);
	}

	public void run() {
		super.run();
		setSpeed(speed);
	}

	public float classSpeed() {
		return speed;
	}
	

	
}
