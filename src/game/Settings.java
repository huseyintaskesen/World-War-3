/**
 * 
 */
package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Settings extends BasicGameState {
	Image view;
	public String mouse= "No input yet";
	private Image soundButton;
	private Image musicButton;
	private boolean mousePressed;
	
	public Settings(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		view = new Image("res/settings.png");
		soundButton = new Image("res/soundbutton.png");
		musicButton = new Image("res/musicbutton.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		view.draw(0, 0);
		soundButton.draw(100,375);
		musicButton.draw(490,410);
//		g.fillOval(75, 100, 100, 100);
//		g.drawString("Play", 80, 70);
		g.setColor(Color.white);
		g.fillRect(300, 300, 150, 30);
		g.setColor(Color.black);
		g.drawString(mouse, 300, 300);
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = 600 - Mouse.getY();
		mouse = "x : "+xpos+" y : "+ypos;
		if ((10 < xpos && xpos < 400) && (25 < ypos && ypos < 95)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(0);
			}
		}
		if ((490 < xpos && xpos < 640) && (419 < ypos && ypos < 553)) {
			if (input.isMouseButtonDown(0) && !mousePressed) {
				mousePressed = true;
				gc.setMusicOn(!gc.isMusicOn());
			}
			if (!input.isMouseButtonDown(0) && mousePressed) {
				mousePressed = false;
				gc.setMusicOn(gc.isMusicOn());
			}
		}
		
		if ((155 < xpos && xpos < 605) && (267 < ypos && ypos < 336)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(5);
			}
		}
	}
	public int getID() {
		return 2;
	}

}
