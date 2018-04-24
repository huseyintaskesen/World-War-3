/**
 * 
 */
package controller;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import model.RangedAttacker;
import model.Casual;
import model.Freezer;
import model.HumanSide;
import model.LandMine;
import model.MeleeAttacker;
import model.Miner;
import model.Obstacle;
import model.Passive;
import model.RobotSide;
import model.Shooter;
import model.Swordsman;
import model.User;
import view.MapManager;

/**
 * @author ibrahim
 *
 */
public class GameManager {

	private static GameManager singleton = new GameManager();
	private User user;

	// declare lists of game objects
	// private static ArrayList<RangedAttacker> humans;
	private static ArrayList<HumanSide> humans;
	private static ArrayList<RobotSide> robots;
	MapManager mapManager;
	int score = 0;

	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private GameManager() {
		mapManager = MapManager.getInstance();

		// lists of game objects are initialized
		// humans = new ArrayList<RangedAttacker>();
		humans = new ArrayList<HumanSide>();
		robots = new ArrayList<RobotSide>();
	}

	/* Static 'instance' method */
	public static GameManager getInstance() {
		return singleton;
	}

	public void defineUser(User u) {
		user = u;
	}

	public void gameUpdate(int delta) {
		// update reload time
		for (int i = 0; i < humans.size(); i++) {
			if (humans.get(i) instanceof RangedAttacker) {
				RangedAttacker tempHuman = (RangedAttacker) humans.get(i);
				tempHuman.setReloadTime(tempHuman.getReloadTime() + delta);

				// update bullet location
				for (int j = 0; j < tempHuman.getBullets().size(); j++) {
					tempHuman.getBullets().get(j).updateLocation();
				}
			}
		}
		for (int i = 0; i < robots.size(); i++) {
			robots.get(i).setAttackTime(robots.get(i).getAttackTime() + delta);
		}

		// update the map
		for (int i = 0; i < robots.size(); i++) {
			robots.get(i).updateLocation();
		}
		// for (int i = 0; i < humans.size(); i++) {
		// for (int j = 0; j < humans.get(i).getBullets().size(); j++) {
		// humans.get(i).getBullets().get(j).updateLocation();
		// }
		// }
	}

	public boolean handleCollisions() throws SlickException {
		for (int i = 0; i < humans.size(); i++) {
			HumanSide tempHuman = humans.get(i);
			RangedAttacker rangedAttacker = null;
			Passive passive = null;
			MeleeAttacker meleeAttacker = null;
			if (tempHuman instanceof RangedAttacker)
				rangedAttacker = (RangedAttacker) tempHuman;
			if (tempHuman instanceof MeleeAttacker)
				meleeAttacker = (MeleeAttacker) tempHuman;
			if (tempHuman instanceof Passive)
				passive = (Passive) tempHuman;

			for (int j = 0; j < robots.size(); j++) {
				RobotSide tempRobot = robots.get(j);

				// gameover when one robot reaches basement
				if (tempRobot.getX() <= 100) {

					return false;
				}

				if (rangedAttacker != null) {

					// fire a bullet
					if ((rangedAttacker.getX() + rangedAttacker.getRange()) > tempRobot.getX()
							&& (Math.abs(rangedAttacker.getY() - tempRobot.getY()) < 20)
							&& rangedAttacker.getReloadTime() >= 1000) {
						rangedAttacker.attackToRobot(tempRobot);
						rangedAttacker.setReloadTime(0);

					}

					// damage robot as bullet
					for (int k = 0; k < rangedAttacker.getBullets().size(); k++) {
						if ((rangedAttacker.getBullets().get(k).getX() + 25 >= tempRobot.getX())
								&& rangedAttacker.getY() == tempRobot.getY()
								&& rangedAttacker.getBullets().get(k).getX() - 10 <= tempRobot.getX()) {
							rangedAttacker.getBullets().get(k).damageRobot(tempRobot, rangedAttacker);
							// if (tempRobot.getHealth() <= 0) {
							// // robots.remove(tempRobot);
							// tempRobot.setToBeRemoved();
							// }

						}

					}
				}

				handleRobotRemovals();
				// damage human as robot
				if (((tempHuman.getX() + 60) > tempRobot.getX()) && tempHuman.getY() == tempRobot.getY()
						&& tempHuman.getX() - 10 <= tempRobot.getX() && tempRobot.getAttackTime() >= 1000) {

					tempRobot.stop();
					tempRobot.attackToHuman(tempHuman);

					tempRobot.setAttackTime(0);
				}

				handleHumanRemovals();
			}
		}
		return true;
	}

	public void handleHumanRemovals() {
		for (int i = 0; i < humans.size(); i++) {
			HumanSide tempHuman = humans.get(i);
			if (tempHuman.isToBeRemoved()) {
				for (int j = 0; j < robots.size(); j++) {
					RobotSide tempRobot = robots.get(j);

					// make the stopped robots move
					if (((tempHuman.getX() + 60) > tempRobot.getX()) && tempHuman.getY() == tempRobot.getY()
							&& tempHuman.getX() - 10 <= tempRobot.getX()) {
						tempRobot.run();
					}
				}

				// delete marked humans
				humans.remove(i);
				i--;
			}

		}

	}

	public void handleRobotRemovals() {
		for (int i = 0; i < robots.size(); i++) {
			RobotSide tempRobot = robots.get(i);

			// delete marked robots
			if (tempRobot.isToBeRemoved()) {
				robots.remove(i);
				i--;
			}
		}
	}

	public void addHuman(int humanCode, int xpos, int ypos) throws SlickException {
		if (checkBalance(humanCode)) {
			int cost=0;
			switch (humanCode) {
			case 1:
				humans.add(new Miner((xpos - xpos % 100), (ypos - ypos % 100)));
				cost=Miner.getCost();
				break;
			case 2:
				humans.add(new Swordsman((xpos - xpos % 100), (ypos - ypos % 100)));
				cost = Swordsman.getCost();
				break;
			case 3:
				humans.add(new Freezer((xpos - xpos % 100), (ypos - ypos % 100)));
				cost = Freezer.getCost();
				break;
			case 4:
				humans.add(new Shooter((xpos - xpos % 100), (ypos - ypos % 100)));
				cost = Shooter.getCost();
				break;
			case 5:
				humans.add(new Obstacle((xpos - xpos % 100), (ypos - ypos % 100)));
				cost = Obstacle.getCost();
				break;
			case 6:
				humans.add(new LandMine((xpos - xpos % 100), (ypos - ypos % 100)));
				cost = LandMine.getCost();
				break;

			default:
				break;
			}
			updateBalance(-cost);
		}
	}

	public void updateBalance(int change) {
		user.setBalance(user.getBalance() + change);
	}

	/**
	 * @param casual
	 */
	public void addRobot(RobotSide robot) {
		robots.add(robot);
	}

	/**
	 * 
	 */

	public boolean checkBalance(int humanCode) {
		if (user.getBalance() < 150) {
			System.out.println("not enough diamonds!");
			return false;
		} else
			return true;
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
		mapManager.drawHumans(humans);
		mapManager.drawRobots(robots);
	}

}
