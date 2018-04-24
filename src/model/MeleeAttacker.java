package model;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

public abstract class MeleeAttacker extends HumanSide {
	
	public MeleeAttacker(int x, int y) throws SlickException {
		super(x,y);
	}
	
	
	public void attackToRobot(RobotSide casual) throws SlickException {
	}

	
}
