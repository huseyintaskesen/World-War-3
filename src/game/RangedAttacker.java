package game;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;



public abstract class RangedAttacker extends Human {
	private int range;
	private ArrayList<Bullet> bullets;
	private int reloadTime;
	
	
	public RangedAttacker(int x, int y) throws SlickException {
		super(x,y);
		bullets = new ArrayList<Bullet>();
	}
	
	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
	
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}


	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}
	
	public void attackToRobot(Robot casual) throws SlickException {
		bullets.add(new Bullet(getX()+50, getY()+25, getDamage()));
	}

	public int getReloadTime() {
		return reloadTime;
	}

	public void setReloadTime(int reloadTime) {
		this.reloadTime = reloadTime;
	}
}
