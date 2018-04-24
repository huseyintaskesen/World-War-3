/**
 * 
 */
package view;

import java.util.ArrayList;

import model.HumanSide;
import model.RangedAttacker;
import model.RobotSide;

/**
 * @author ibrahim
 *
 */
public class MapManager {

	private static MapManager singleton = new MapManager();

	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private MapManager() {

	}

	/* Static 'instance' method */
	public static MapManager getInstance() {
		return singleton;
	}

	public void drawAttackerHumans(ArrayList<RangedAttacker> humans) {

		for (int i = 0; i < humans.size(); i++) {
			humans.get(i).draw();
			for (int j = 0; j < humans.get(i).getBullets().size(); j++) {
				humans.get(i).getBullets().get(j).draw();
			}
		}
	}

	/**
	 * @param robots
	 */
	public void drawRobots(ArrayList<RobotSide> robots) {
		for (int i = 0; i < robots.size(); i++) {
			robots.get(i).draw();
		}
	}

	/**
	 * @param humans
	 */
	public void drawHumans(ArrayList<HumanSide> humans) {
		for (int i = 0; i < humans.size(); i++) {
			HumanSide tempHuman = humans.get(i);
			tempHuman.draw();

			if (tempHuman instanceof RangedAttacker) {
				RangedAttacker rangedAttacker = (RangedAttacker) tempHuman;
				for (int j = 0; j < rangedAttacker.getBullets().size(); j++) {
					rangedAttacker.getBullets().get(j).draw();
				}
			}
		}

	}

}
