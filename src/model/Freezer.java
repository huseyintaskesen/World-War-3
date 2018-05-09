package model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Freezer extends RangedAttacker {
<<<<<<< HEAD
	private final int startHealth = 7;
=======
	private final int startHealth = 20;
	public final static int cost = 200;
>>>>>>> bacfcf9ebd143ed4905e5f284449ee26191dd496
	public Freezer(int x, int y) throws SlickException{
		super(x,y);
		setImg(new Image("res/Robots & Humans/freezer.png"));
		
		setHealth(startHealth);
		setDamage(1);
		setRange(400);
	}
}