/**
 * 
 */
package view;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import controller.GameManager;
import model.User;

import java.awt.Font;
import org.newdawn.slick.gui.TextField;

public class MainMenu extends BasicGameState {

	Image menu;
	public String mouse = "No input yet";
	private TextField textField;
	private String userName;
	private Music music;
	
	private GameManager gameManager;
	private User user;
	private int timePassed;
	private boolean cursorFlag;

	public MainMenu(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		menu = new Image("res/main menu.png");
		

		// music
		music = new Music("res/soundtrack.aiff");
		music.loop();
		music.setVolume(0.5f);
		
		
		gameManager = GameManager.getInstance();
		user = new User("Dummy");
		gameManager.defineUser(user);

		textField = new TextField(gc, new TrueTypeFont (new Font("Pixeled Regular", Font.BOLD, 24), true), 932, 257, 195, 30);
		textField.setFocus(true);
		textField.setBorderColor(Color.transparent);
		textField.setBackgroundColor(new Color(0.9f, 0.9f, 0.9f, 1f));
		textField.setTextColor(Color.black);
		textField.setCursorVisible(true);
		textField.setMaxLength(10);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("Humans vs. Robots", 100, 50);
		menu.draw(0, 0);
		textField.setFocus(true);
		textField.render(gc, g);

		// playNow.draw(100,100);
		// quit.draw(100,200);

		// g.fillOval(75, 100, 100, 100);
		// g.drawString("Play", 80, 70);
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
		mouse = "x : " + xpos + " y : " + ypos;

		textField.setCursorVisible(cursorFlag);
		timePassed += delta;
		if(timePassed>300) {
			cursorFlag = !cursorFlag;
			timePassed=0;
		}
		if ((907 < xpos && xpos < 1154) && (300 < ypos && ypos < 353)) {
			if (input.isMouseButtonDown(0)) {
				userName = textField.getText();
				if(!userName.equals(""))
					user = new User(userName);
				gameManager.defineUser(user);
				sbg.enterState(1);
			}
		} else if ((661 < xpos && xpos < 858) && (480 < ypos && ypos < 520)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(2);
			}
		} else if ((661 < xpos && xpos < 858) && (530 < ypos && ypos < 568)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(3);
			}
		} else if ((661 < xpos && xpos < 858) && (583 < ypos && ypos < 620)) {
			if (input.isMouseButtonDown(0)) {
				gc.exit();
			}
		}
		

	}

	public int getID() {
		return 0;
	}


}
