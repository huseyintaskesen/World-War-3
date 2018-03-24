/**
 * 
 */
package game;

import java.util.ArrayList;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Music;

public class Play extends BasicGameState {

	public String mouse = "No input yet"; // To show mouse coordinates, for testing purposes

	int timePassed = 0; // time passed is calculated so that we can take actions at certain times
	int timeCount = 0; // another variable time, used for creating bullets with a specified delay

	Image view; // background image
	Image pause; // pause menu
	private Music music;

	// declare lists of game objects
	ArrayList<AttackerHuman> humans;
	ArrayList<Robot> robots;

	// temporary reference to these objects
	AttackerHuman shooter;
	Robot casual;

	private boolean pauseFlag = false; // to determine whether the game is in pause menu

	public Play(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// background and pause menu images
		view = new Image("res/playgame.png");
		pause = new Image("res/pause.png");

		// lists of game objects are initialized
		humans = new ArrayList<AttackerHuman>();
		robots = new ArrayList<Robot>();

		humans.add(new Shooter(100, 100));
		humans.add(new Shooter(100, 200));
		shooter = humans.get(0);

		robots.add(new Casual(600, 100));
		robots.add(new Casual(600, 200));
		casual = robots.get(0);

		// music
		music = new Music("res/Jamie and Selda.aif");
		music.loop();
		music.setVolume(0.6f);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		view.draw(0, 0); // background image drawn

		// temporary gray background
		g.setColor(Color.lightGray);
		g.fillRect(100, 100, 1180, 620);

		// game objects are drawn
		for (int i = 0; i < robots.size(); i++) {
			robots.get(i).draw();
		}

		for (int i = 0; i < humans.size(); i++) {
			humans.get(i).draw();
		}
		for (int i = 0; i < shooter.getBullets().size(); i++) {
			shooter.getBullets().get(i).draw();
		}

		// pause menu is drawn when flag is up
		if (pauseFlag)
			pause.draw(500, 200);

		// display mouse coordinates
		g.setColor(Color.white);
		g.fillRect(300, 300, 150, 30);
		g.setColor(Color.black);
		g.drawString(mouse, 300, 300);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// get mouse coordinates
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = 720 - Mouse.getY();
		// to display mouse coordinates
		mouse = "x : " + xpos + " y : " + ypos;

		// this if is taken when the pause menu is present
		// if statement will only take care of these buttons
		// All the update related to game will be in else part, i. e. when flag is down
		if (pauseFlag) {
			// Resume in pause menu
			if ((576 < xpos && xpos < 776) && (285 < ypos && ypos < 335)) {
				if (input.isMouseButtonDown(0)) {
					pauseFlag = false;
				}
			}

			// Main Menu in pause menu
			if ((576 < xpos && xpos < 776) && (365 < ypos && ypos < 415)) {
				if (input.isMouseButtonDown(0)) {
					pauseFlag = false;
					sbg.enterState(0);
				}
			}

			// Quit in pause menu
			if ((576 < xpos && xpos < 776) && (445 < ypos && ypos < 495)) {
				if (input.isMouseButtonDown(0)) {
					pauseFlag = false;
					gc.exit();
				}
			}

		} 
		else {// Pause flag is down, game is running
			
			// calculate time passed
			timePassed += delta;
			timeCount += delta;

			// reset the timer when 0.02 seconds has passed
			// update the map every 0.02 seconds(50 FPS)
			if (timePassed > 20) {
				// update the map
				casual.updateLocation();
				for (int i = 0; i < shooter.getBullets().size(); i++) {
					shooter.getBullets().get(i).updateLocation();
				}

				/////////////
				// collision detection logic
				/////////////
				// fire a bullet
				if ((shooter.getX() + shooter.getRange()) > casual.getX()
						&& (Math.abs(shooter.getY() - casual.getY()) < 20) && timeCount >= 1000) {
					shooter.attackToRobot(casual);
					timeCount = 0;

				}
				// damage human as robot
				if (((shooter.getX() + 60) > casual.getX()) && (Math.abs(shooter.getY() - casual.getY()) < 20)) {
					casual.stop();
					casual.attackToHuman(shooter);
					if (shooter.getHealth() <= 0) {
						humans.remove(shooter);
						// shooter=new Shooter(100, 200);
						casual.run();
					}
				}
				// damage robots as bullet
				for (int i = 0; i < shooter.getBullets().size(); i++) {
					if ((shooter.getBullets().get(i).getX() + 25 >= casual.getX())
							&& (Math.abs(shooter.getY() - casual.getY()) < 20)) {
						shooter.getBullets().get(i).damageRobot(casual, shooter);
						if (casual.getHealth() <= 0) {
							casual = new Casual(600, 100);
						}
					}
				}

				// reset the timer
				timePassed = 0;
			}

			// Pause button
			if ((1031 < xpos && xpos < 1095) && (15 < ypos && ypos < 79)) {
				if (input.isMouseButtonDown(0)) {
					pauseFlag = true;
				}
			}

			// Quit button
			if ((1203 < xpos && xpos < 1267) && (15 < ypos && ypos < 79)) {
				if (input.isMouseButtonDown(0)) {
					sbg.enterState(0);
				}
			}
		}
	}

	public int getID() {
		return 1;
	}
}
