package model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TankRobot extends RobotSide{
	private final int startHealth=7;
	private final int speed = 2;
	
	
	public TankRobot(int x,int y) throws SlickException{
		super(x, y);
		setImg(new Image(""));
		setDamage(3);
		setHealth(startHealth);
		setSpeed(speed);
	}

	public void run() {
		super.run();
		setSpeed(speed);
	}
	

	
}
