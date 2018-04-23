package model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Swordsman extends MeleeAttacker {
	private final int startHealth = 20;
	public Swordsman(int x, int y) throws SlickException{
		super(x,y);
		setImg(new Image("res/Robots & Humans/swordsman4.png"));
		
		setDamage(1);
		setHealth(startHealth);
		setCost(100);
	}
}
