/**
 * 
 */
package game;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;


/**
 * @author ibrahim
 *
 */
public class GameManager {

	private static GameManager singleton = new GameManager();

	// declare lists of game objects
	private static ArrayList<AttackerHuman> humans;
	private static ArrayList<Robot> robots;
	MapManager mapManager;
	int score = 0;

	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private GameManager() {
		mapManager = MapManager.getInstance();

		
		// lists of game objects are initialized
		humans = new ArrayList<AttackerHuman>();
		robots = new ArrayList<Robot>();
	}

	/* Static 'instance' method */
	public static GameManager getInstance() {
		return singleton;
	}

	
	public void gameUpdate(int delta) {
		// update reload time
		for (int i = 0; i < humans.size(); i++) {
			humans.get(i).setReloadTime(humans.get(i).getReloadTime()+delta);
		}
		for (int i = 0; i < robots.size(); i++) {
			robots.get(i).setAttackTime(robots.get(i).getAttackTime()+delta);
		}
		
		//update the map
		for (int i = 0; i < robots.size(); i++) {
			robots.get(i).updateLocation();
		}
		for (int i = 0; i < humans.size(); i++) {
			for (int j = 0; j < humans.get(i).getBullets().size(); j++) {
				humans.get(i).getBullets().get(j).updateLocation();
			}
		}
	}
	public boolean handleCollisions() throws SlickException {
		for (int i = 0; i < humans.size(); i++) {

			for (int j = 0; j < robots.size(); j++) {
				AttackerHuman tempHuman = humans.get(i);
				Robot tempRobot = robots.get(j);

				// gameover when one robot reaches basement
				if (tempRobot.getX() <= 100) {

					return false;
				}
				// fire a bullet
				if ((tempHuman.getX() + tempHuman.getRange()) > tempRobot.getX()
						&& (Math.abs(tempHuman.getY() - tempRobot.getY()) < 20) 
						&& tempHuman.getReloadTime() >= 1000) {
					tempHuman.attackToRobot(tempRobot);
					tempHuman.setReloadTime(0);


				}

				// damage human as robot
				if (((tempHuman.getX() + 60) > tempRobot.getX()) 
						&& tempHuman.getY() == tempRobot.getY()
						&& tempHuman.getX() - 10 <= tempRobot.getX()
						&& tempRobot.getAttackTime()>=1000) {

					if (tempHuman.getHealth() > 0) {
						tempRobot.stop();
						tempRobot.attackToHuman(tempHuman);
					}
					if (tempHuman.getHealth() <= 0 && !tempRobot.isRunning()) {
						tempHuman.setToBeRemoved();
						tempRobot.run();
						j = -1;
					}
					
					tempRobot.setAttackTime(0);
				}

				// damage robot as bullet
				for (int k = 0; k < tempHuman.getBullets().size(); k++) {
					if ((tempHuman.getBullets().get(k).getX() + 25 >= tempRobot.getX())
							&& tempHuman.getY() == tempRobot.getY()
							&& tempHuman.getBullets().get(k).getX() - 10 <= tempRobot.getX()) {
						tempHuman.getBullets().get(k).damageRobot(tempRobot, tempHuman);
						if (tempRobot.getHealth() <= 0) {
							// robots.remove(tempRobot);
							tempRobot.setToBeRemoved();
						}
					}
				}

			}
		}
		return true;
	}

	/**
	 * @param shooter
	 */
	public void addAttackerHuman(AttackerHuman attackerHuman) {
		humans.add(attackerHuman);
	}

	/**
	 * @param casual
	 */
	public void addRobot(Robot robot) {	
		robots.add(robot);
	}

	/**
	 * 
	 */
	public void handleRemovals() {
		for (int i = 0; i < humans.size(); i++) {
			AttackerHuman tempHuman = humans.get(i); 
			if (tempHuman.isToBeRemoved()) {
				humans.remove(i);
				i--;
			}
//			for (int k = 0; k < tempHuman.getBullets().size(); k++) {
//				
//			}
		}
		for (int i = 0; i < robots.size(); i++) {
			if (robots.get(i).isToBeRemoved()) {
				robots.remove(i);
				i--;
			}
		}
		
	}
	
	public void resetMap() throws SlickException {
		score = 0;
		humans.clear();
		humans.add(new Shooter(100, 100));
		robots.clear();
		robots.add(new Casual(600, 100));
	}
	
	// to draw game objects
	public void draw() {
		mapManager.drawAttackerHumans(humans);
		mapManager.drawRobots(robots);
	}
}
