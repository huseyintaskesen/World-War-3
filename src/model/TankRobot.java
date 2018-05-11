package model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TankRobot extends RobotSide{
	
	private final int startHealth=24;
	private final int speed = 3;	
	
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
