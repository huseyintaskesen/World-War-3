/**
 * 
 */
package view;

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
	private boolean mousePressed;
	private boolean mousePressed2;
	
	public Settings(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		view = new Image("res/settings.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		view.draw(0, 0);
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
		int ypos = 720 - Mouse.getY();
		mouse = "x : "+xpos+" y : "+ypos;

		if ((16 < xpos && xpos < 244) && (23 < ypos && ypos < 71)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(0);
			}
		}
		if ((550 < xpos && xpos < 814) && (245 < ypos && ypos < 295)) {
			if (input.isMouseButtonDown(0) && !mousePressed) {
				mousePressed = true;
				gc.setMusicOn(!gc.isMusicOn());
			}
			if (!input.isMouseButtonDown(0) && mousePressed) {
				mousePressed = false;
				gc.setMusicOn(gc.isMusicOn());
			}
		}
		if((550 < xpos && xpos < 814)&&(160 < ypos && ypos < 220)){
			if(input.isMouseButtonDown(0)&& !mousePressed2){
				mousePressed2 = true;
				gc.setSoundOn(!gc.isSoundOn());
			}
			if (!input.isMouseButtonDown(0) && mousePressed2) {
				mousePressed2 = false;
				gc.setSoundOn(gc.isSoundOn());
			}
		}
		
		if ((550 < xpos && xpos < 814) && (325 < ypos && ypos < 375)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(5);
			}
		}
	}
	public int getID() {
		return 2;
	}

}
