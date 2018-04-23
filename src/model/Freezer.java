package model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Freezer extends RangedAttacker {
	private final int startHealth = 20;
	public Freezer(int x, int y) throws SlickException{
		super(x,y);
		setImg(new Image("res/Robots & Humans/freezer.png"));
		
		setHealth(startHealth);
		setDamage(3);
		setRange(400);
		setCost(200);
	}
}