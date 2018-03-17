/**
 * 
 */
package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * @author ibrahim
 *
 */
public class UIManager extends StateBasedGame{

	public static final String gamename = "Humans vs. Robots";
	public static final int menu = 0;
	public static final int play = 1;
	public static final int settings = 2;
	public static final int credits = 3;
	public static final int gameover = 4; // TODO gameover screen
	
	public UIManager(String gamename) {
		super(gamename);
		this.addState(new MainMenu(menu));
		this.addState(new Play(play));
		this.addState(new Settings(settings));
		this.addState(new Credits(credits));
	}

	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(settings).init(gc, this);
		this.getState(credits).init(gc, this);
		this.enterState(menu);
		
	}
	
	public static void main(String[] args) {
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new UIManager(gamename));
			appgc.setDisplayMode(800, 600, false);
			appgc.start();
		}catch(SlickException e) {
			e.printStackTrace();
		}
	}

	

}
