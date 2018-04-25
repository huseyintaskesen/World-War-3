package model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Miner extends Passive {
	private final int startHealth = 20;
	
	private int mineTimer;

	private boolean mineReady;
	
	public Miner(int x, int y) throws SlickException{
		super(x,y);
		setImg(new Image("res/Robots & Humans/miner.png"));
		
		setHealth(startHealth);
		setCost(50);
		mineReady = false;
	}
	
	public int getMineTimer() {
		return mineTimer;
	}
	public void setMineTimer(int mineTimer) {
		if(mineTimer<3000)
			this.mineTimer = mineTimer;
		else
			mineReady = true;
	}
	
	public boolean isMineReady() {
		return mineReady;
	}
	
	public void resetTimer() {
		mineReady=false;
		mineTimer=0;
	}
	
//	// Override the draw method
//	public void draw() {
//		if(!isMineReady())
//			super.draw();
//		else {
//			super.draw();
//			
//		}
//	}
}
