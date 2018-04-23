package game;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

public abstract class MeleeAttacker extends Human {
	private int range;
	
	
	public MeleeAttacker(int x, int y) throws SlickException {
		super(x,y);
	}
	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
	
	public void attackToRobot(Robot casual) throws SlickException {
	}

	
}
