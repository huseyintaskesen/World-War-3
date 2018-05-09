package model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class FastRobot extends RobotSide{
<<<<<<< HEAD
	private final int startHealth = 12;
	private final int speed = 7;
=======
	private final int startHealth=3;
	private final int speed = 12;
>>>>>>> bacfcf9ebd143ed4905e5f284449ee26191dd496
	
	
	public FastRobot(float x,float y) throws SlickException{
		super(x, y);
		setImg(new Image("res/Robots & Humans/fastRobot.png"));
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