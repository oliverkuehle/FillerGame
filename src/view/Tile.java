package view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

public class Tile extends JPanel {
	private int x;
	private int y;
	private Color color;
	private int owner = 2; // 0 == left, 1 == right, 2 == none.
	private Tile[] neighbors = new Tile[4];

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		setOpaque(true);
		setPreferredSize(new Dimension(60, 60));
		setMinimumSize(new Dimension(60, 60));
	}

	public void setColor(Color newColor) {
		this.color = newColor;
		setBackground(newColor);
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public void setNeighbor(int direction, Tile neighbor) {
		this.neighbors[direction] = neighbor;
	}

	public Color getColor() {
		return color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getOwner() {
		return owner;
	}

	public Tile[] getNeighbors() {
		return neighbors;
	}
}
