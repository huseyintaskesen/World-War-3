package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TankRobot extends Robot{
	private final int startHealth=7;
	private final int speed = 2;
	
	
	public TankRobot(int x,int y) throws SlickException{
		super(x, y);
		img=new Image("");
		damage = 3;
		health=startHealth;
		curSpeed=speed;
	}

	public void run() {
		super.run();
		curSpeed = speed;
	}
	

	
}
