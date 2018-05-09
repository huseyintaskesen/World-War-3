package model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class FastRobot extends RobotSide{
	private final int startHealth = 12;
	private final int speed = 7;
	
	
	public FastRobot(float x,float y) throws SlickException{
		super(x, y);
		setImg(new Image("res/Robots & Humans/basicRobot.png"));
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