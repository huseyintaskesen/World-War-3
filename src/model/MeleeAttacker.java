package model;

import org.newdawn.slick.SlickException;

public abstract class MeleeAttacker extends HumanSide {
	
	
	
	public MeleeAttacker(int x, int y) throws SlickException {
		super(x,y);
	}
	
	
	public abstract void attackToRobot(RobotSide robot);

	
}
