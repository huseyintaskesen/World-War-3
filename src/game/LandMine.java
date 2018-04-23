package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class LandMine extends Passive {
	private final int startHealth = 40;
	public LandMine(int x, int y) throws SlickException{
		super(x,y);
		setImg(new Image("res/Robots & Humans/mine.png"));
		setHealth(startHealth);
	}
}