package model;

import java.awt.Color;

public class GameModel {
	private int pointsLeft;
	private int pointsRight;
	private Color leftColor;
	private Color rightColor;
	private boolean isLeftTurn;
	private int[] fieldSize;

	public GameModel(int[] fieldSize) {
		this.fieldSize = fieldSize;
		pointsLeft = 1;
		pointsRight = 1;
		isLeftTurn = Math.random() < 0.5;
	}

	public int[] getFieldSize() {
		return fieldSize;
	}

	public boolean isLeftTurn() {
		return isLeftTurn;
	}

	public void toggleTurn() {
		isLeftTurn = !isLeftTurn;
	}

	public int getPointsLeft() {
		return pointsLeft;
	}

	public int getPointsRight() {
		return pointsRight;
	}

	public void setPointsLeft(int points) {
		pointsLeft = points;
	}

	public void setPointsRight(int points) {
		pointsRight = points;
	}

	public Color getLeftColor() {
		return leftColor;
	}

	public Color getRightColor() {
		return rightColor;
	}

	public void setLeftColor(Color color) {
		leftColor = color;
	}

	public void setRightColor(Color color) {
		rightColor = color;
	}

	public boolean isGameOver() {
		return pointsRight + pointsLeft >= fieldSize[0]*fieldSize[1];
	}

	public int getWinner() {
		if (pointsLeft > pointsRight) {
			return 0;
		} else if (pointsRight > pointsLeft) {
			return 1;
		} else {
			return 2;
		}
	}
}
