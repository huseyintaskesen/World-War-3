/**
 * 
 */
package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class MainMenu extends BasicGameState {

	Image menu;
	public String mouse= "No input yet";
	
	public MainMenu(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		menu = new Image("res/main menu.png");
	
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("Humans vs. Robots", 100, 50);
		menu.draw(0, 0);
//		playNow.draw(100,100);
//		quit.draw(100,200);
		
//		g.fillOval(75, 100, 100, 100);
//		g.drawString("Play", 80, 70);
		g.setColor(Color.white);
		g.fillRect(300, 300, 150, 30);
		g.setColor(Color.black);
		g.drawString(mouse, 300, 300);
		g.setColor(Color.white);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = 600 - Mouse.getY();
		mouse = "x : "+xpos+" y : "+ypos;
		if ((567 < xpos && xpos < 721) && (253 < ypos && ypos < 293)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(1);
			}
		}
		else if ((413 < xpos && xpos < 537) && (402 < ypos && ypos < 430)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(2);
			}
		}
		else if ((413 < xpos && xpos < 537) && (442 < ypos && ypos < 472)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(3);
			}
		}
		else if ((413 < xpos && xpos < 537) && (487 < ypos && ypos < 513)) {
			if (input.isMouseButtonDown(0)) {
				gc.exit();
			}
		}
	}

	public int getID() {
		return 0;
	}

}
