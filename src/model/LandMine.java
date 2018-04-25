package model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class LandMine extends Passive {
	private final int startHealth = 40;
	private int bombTimer;
	private boolean bombReady;
	public LandMine(int x, int y) throws SlickException{
		super(x,y);
		setImg(new Image("res/Robots & Humans/mine.png"));
		setHealth(startHealth);
		setCost(50);
		bombTimer = 0;
	}
	
	public int getBombTimer() {
		return bombTimer;
	}
	public void setBombTimer(int mineTimer) {
		if(mineTimer<3000)
			this.bombTimer = mineTimer;
		else
			bombReady = true;
	}
	
	public boolean isBombReady() {
		return bombReady;
	}
	
	public void resetTimer() {
		bombReady=false;
		bombTimer=0;
	}
}