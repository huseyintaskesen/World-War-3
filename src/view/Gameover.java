/**
 * 
 */
package view;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controller.GameManager;


public class Gameover extends BasicGameState {

	Image view;
	private Sound gameOverSound;
	public String mouse= "No input yet";
	
	private GameManager gameManager;
	private TrueTypeFont myFont;
	
	public Gameover(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		view = new Image("res/gameover.png");
		gameOverSound = new Sound("res/game-over-sound.wav");
		gameManager = GameManager.getInstance();
		
		myFont = new TrueTypeFont(new Font("Pixeled Regular", Font.BOLD, 30), true);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
//		gameManager = GameManager.getInstance();
		gameOverSound.play(1,0.3f);
	}
	
	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.BasicGameState#leave(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		super.leave(container, game);
		gameManager.resetMap();
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		view.draw(0, 0);
		
//		g.fillOval(75, 100, 100, 100);
//		g.drawString("Play", 80, 70);
//		g.setColor(Color.white);
//		g.fillRect(300, 300, 150, 30);
//		g.setColor(Color.black);
//		g.drawString(mouse, 300, 300);
		//gameOverSound.play(1,0.3f);

		g.setFont(myFont);
		g.drawString("" + gameManager.getScore() / 1000, 666, 160);
		g.drawString(gameManager.getName() , 560, 125);
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
	}
	

	public int getID() {
		// TODO Auto-generated method stub
		return 4;
	}

}
