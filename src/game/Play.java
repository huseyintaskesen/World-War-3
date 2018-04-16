/**
 * 
 */
package game;

import java.util.ArrayList;
import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState {

	TrueTypeFont myFont;//

	public String mouse = "No input yet"; // To show mouse coordinates, for testing purposes

	int timePassed = 0; // time passed is calculated so that we can take actions at certain times
	int timeCount = 0; // another variable time, used for creating bullets with a specified delay
	int score = 0;

	Image view; // background image
	Image pause; // pause menu
	Image land;
	private Music music;

	// declare lists of game objects
//	ArrayList<AttackerHuman> humans;
//	ArrayList<Robot> robots;
	
	
	GameManager gameManager;


	private boolean pauseFlag = false; // to determine whether the game is in pause menu

	public Play(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		myFont = new TrueTypeFont(new Font("Pixeled Regular", Font.PLAIN, 30), true);

		// background and pause menu images
		view = new Image("res/playgame.png");
		pause = new Image("res/pause.png");

//		// lists of game objects are initialized
//		humans = new ArrayList<AttackerHuman>();
//		robots = new ArrayList<Robot>();
		
		gameManager = GameManager.getInstance();

		land = new Image("res/Land.png");

		gameManager.resetMap();

		// music
		music = new Music("res/soundtrack.aiff");
		music.loop();
		music.setVolume(0.0f);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		view.draw(0, 0); // background image drawn

		// temporary gray background
		g.setColor(Color.lightGray);
		g.fillRect(100, 100, 1180, 620);

		// land.draw(100,100,1180,620);

		// game objects are drawn
		gameManager.draw();

		// pause menu is drawn when flag is up
		if (pauseFlag)
			pause.draw(500, 200);

		// display mouse coordinates
		g.setColor(Color.white);
		g.fillRect(300, 300, 150, 30);
		g.setColor(Color.black);
		g.drawString(mouse, 300, 300);

		g.setFont(myFont);
		g.drawString("250", 380, 30);
		g.drawString("Score: " + score / 1000, 600, 30);
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
					gameManager.resetMap();
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

		} else {// Pause flag is down, game is running

			
			// calculate time passed
			timePassed += delta;
//			for (int i = 0; i < humans.size(); i++) {
//				humans.get(i).setReloadTime(humans.get(i).getReloadTime()+delta);
//			}
			//timeCount += delta;
			score += delta;

			// reset the timer when 0.02 seconds has passed
			// update the map every 0.02 seconds(50 FPS)
			if (timePassed > 20) {

				
				gameManager.gameUpdate(timePassed);
				// add human or robot simply by clicking the corresponding mouse button
				// added for first iteration demo, testing purposes
				if ((100 < xpos && xpos < 1280) && (100 < ypos && ypos < 720)) {
					if (input.isMousePressed(1)) {
						gameManager.addAttackerHuman(new Shooter((xpos - xpos % 100), (ypos - ypos % 100)));
						//humans.add(new Shooter((xpos - xpos % 100), (ypos - ypos % 100)));
					} else if (input.isMousePressed(2)) {
						gameManager.addRobot(new Casual((xpos - xpos % 100), (ypos - ypos % 100)));
						//robots.add(new Casual((xpos - xpos % 100), (ypos - ypos % 100)));
					}
				}

				// update the map
//				for (int i = 0; i < robots.size(); i++) {
//					robots.get(i).updateLocation();
//				}
//				for (int i = 0; i < humans.size(); i++) {
//					for (int j = 0; j < humans.get(i).getBullets().size(); j++) {
//						humans.get(i).getBullets().get(j).updateLocation();
//					}
//				}

				/////////////
				// collision detection logic
				/////////////

				if(!gameManager.handleCollisions())
					gameover(sbg);
				
				
				
//				for (int i = 0; i < humans.size(); i++) {
//
//					for (int j = 0; j < robots.size(); j++) {
//						AttackerHuman tempHuman = humans.get(i);
//						Robot tempRobot = robots.get(j);
//
//						// gameover when one robot reaches basement
//						if (tempRobot.getX() <= 100) {
//							gameover(sbg);
//							break;
//						}
//						// fire a bullet
//						if ((tempHuman.getX() + tempHuman.getRange()) > tempRobot.getX()
//								&& (Math.abs(tempHuman.getY() - tempRobot.getY()) < 20) 
//								&& tempHuman.getReloadTime() >= 1000) {
//							tempHuman.attackToRobot(tempRobot);
//							tempHuman.setReloadTime(0);
//						}
//
//						// damage human as robot
//						if (((tempHuman.getX() + 60) > tempRobot.getX()) && tempHuman.getY() == tempRobot.getY()
//								&& tempHuman.getX() - 10 <= tempRobot.getX()) {
//
//							if (tempHuman.getHealth() > 0) {
//								tempRobot.stop();
//								tempRobot.attackToHuman(tempHuman);
//							}
//							if (tempHuman.getHealth() <= 0 && !tempRobot.isRunning()) {
//								tempHuman.setToBeRemoved();
//								tempRobot.run();
//								j = -1;
//							}
//						}
//
//						// damage robot as bullet
//						for (int k = 0; k < tempHuman.getBullets().size(); k++) {
//							if ((tempHuman.getBullets().get(k).getX() + 25 >= tempRobot.getX())
//									&& tempHuman.getY() == tempRobot.getY()
//									&& tempHuman.getBullets().get(k).getX() - 10 <= tempRobot.getX()) {
//								tempHuman.getBullets().get(k).damageRobot(tempRobot, tempHuman);
//								if (tempRobot.getHealth() <= 0) {
//									//robots.remove(tempRobot);
//									tempRobot.setToBeRemoved();
//								}
//							}
//						}
//
//					}
//				}

				////////////////////////////
				////// handle removals
				////////////////////////////
				
				gameManager.handleRemovals();
//				for (int i = 0; i < humans.size(); i++) {
//					AttackerHuman tempHuman = humans.get(i); 
//					if (tempHuman.isToBeRemoved())
//						humans.remove(i);
////					for (int k = 0; k < tempHuman.getBullets().size(); k++) {
////						
////					}
//				}
//				for (int i = 0; i < robots.size(); i++) {
//					if (robots.get(i).isToBeRemoved())
//						robots.remove(i);
//				}
				

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
					gameManager.resetMap();
					sbg.enterState(0);
				}
			}
		}
	}

	/**
	 * @throws SlickException
	 * 
	 */
	private void gameover(StateBasedGame sbg) throws SlickException {
		gameManager.resetMap();
		sbg.enterState(4);
	}

	/**
	 * @throws SlickException
	 * 
	 */
//	private void resetMap() throws SlickException {
//		score = 0;
//		humans.clear();
//		humans.add(new Shooter(100, 100));
//		robots.clear();
//		robots.add(new Casual(600, 100));
//	}

	public int getID() {
		return 1;
	}
}
