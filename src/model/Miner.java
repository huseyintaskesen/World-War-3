package model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Miner extends Passive {
	private final int startHealth = 20;
	public Miner(int x, int y) throws SlickException{
		super(x,y);
		setImg(new Image("res/Robots & Humans/miner.png"));
		
		setHealth(startHealth);
		setCost(50);
	}
}
