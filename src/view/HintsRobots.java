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


public class HintsRobots extends BasicGameState {
	
	Image view;
	public String mouse= "No input yet";
	
	public HintsRobots(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		view = new Image("res/Hints screens/Hints-CasualRobot.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		view.draw(0, 0);
		
		g.drawRect(700, 30, 70, 30);
		g.drawString("Back", 710, 35);
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
		
		if ((700 < xpos && xpos < 770) && (30 < ypos && ypos < 60)){
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(2);
			}
		}
		
		if ((122 < xpos && xpos < 180) && (102 < ypos && ypos < 130)){
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(5);
			}
		}
		
		if((126 < xpos && xpos < 246) && (447 < ypos && ypos < 595)){
			if (input.isMouseButtonDown(0)) {
				view = new Image("res/Hints screens/Hints-CasualRobot.png");
			}
		}
		else if((276 < xpos && xpos < 396) && (447 < ypos && ypos < 595)){
			if (input.isMouseButtonDown(0)) {
				view = new Image("res/Hints screens/Hints-FastRobot.png");
			}
		}
		else if((426 < xpos && xpos < 546) && (447 < ypos && ypos < 595)){
			if (input.isMouseButtonDown(0)) {
				view = new Image("res/Hints screens/Hints-TankRobot.png");
			}
		}
	}
	public int getID() {
		return 6;
	}

}