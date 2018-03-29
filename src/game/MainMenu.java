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

	private String userName;


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
		int ypos = 720 - Mouse.getY();
		mouse = "x : "+xpos+" y : "+ypos;


		if ((907 < xpos && xpos < 1154) && (300 < ypos && ypos < 353)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(1);
			}
		}
		else if ((661 < xpos && xpos < 858) && (480 < ypos && ypos < 520)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(2);
			}
		}
		else if ((661 < xpos && xpos < 858) && (530 < ypos && ypos < 568)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(3);
			}
		}
		else if ((661 < xpos && xpos < 858) && (583 < ypos && ypos < 620)) {
			if (input.isMouseButtonDown(0)) {
				gc.exit();
			}
		}

	}

	public int getID() {
		return 0;
	}



}
