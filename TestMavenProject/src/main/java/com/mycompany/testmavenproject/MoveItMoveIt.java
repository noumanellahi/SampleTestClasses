package com.mycompany.testmavenproject;

import java.awt.Robot;
import java.util.Random;

public class MoveItMoveIt {
	public static final int FIVE_SECONDS = 5000;
	public static final int MAX_Y = 400;
	public static final int MAX_X = 400;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			Robot robot = new Robot();
			Random random = new Random();
			while (true) {
				robot.mouseMove(random.nextInt(MAX_X), random.nextInt(MAX_Y));
				Thread.sleep(FIVE_SECONDS);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
