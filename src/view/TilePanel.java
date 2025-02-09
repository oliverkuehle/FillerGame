package view;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import java.awt.GridLayout;
import java.awt.Color;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.Random;

public class TilePanel extends JPanel {
	Consumer<Color[]> onColorsGeneratedCallback;
	private Tile[] tiles;
	private int rows;
	private int cols;
	private Color[] tileColors = {
		new Color(0xFFAAAC),
		new Color(0xB5E9B1),
		new Color(0xB6F0FF),
		new Color(0xFFB4F9),
		new Color(0xD3B6F0),
		new Color(0xFFF3A6)
	};

	public TilePanel(int[] fieldSize, Consumer<Color[]> onColorsGeneratedCallback) {
		this.onColorsGeneratedCallback = onColorsGeneratedCallback;
		this.rows = fieldSize[0];
		this.cols = fieldSize[1];
		setLayout(new  GridLayout(rows, cols));
		tiles = new Tile[rows * cols];
		createField();
	}

	public void createField() {
		Random random = new Random();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Tile tile = new Tile(j, i);
				tiles[i * cols + j] = tile;

				Tile leftNeighbor = j > 0 ? tiles[i * cols + (j - 1)] : null;
				Tile topNeighbor = i > 0 ? tiles[(i - 1) * cols + j] : null;

				if (topNeighbor != null) {
					tile.setNeighbor(0, topNeighbor);
					topNeighbor.setNeighbor(2, tile);
				}
				if (leftNeighbor != null) {
					tile.setNeighbor(3, leftNeighbor);
					leftNeighbor.setNeighbor(1, tile);
				}

				Set<Color> neighborColors = new HashSet<>();
				if (topNeighbor != null) neighborColors.add(topNeighbor.getColor());
				if (leftNeighbor != null) neighborColors.add(leftNeighbor.getColor());
				if (i == rows - 1 && j == 0) {
					neighborColors.add(tiles[cols - 1].getColor());
				}
				Color[] freeColors = Arrays.stream(tileColors).filter(color -> !neighborColors.contains(color)).toArray(Color[]::new);
				tile.setColor(freeColors[random.nextInt(freeColors.length)]);
				
				this.add(tile);
			}
		}

		Tile leftBottom = tiles[(rows - 1) * cols];
		Tile rightTop = tiles[cols - 1];
		leftBottom.setOwner(0);
		rightTop.setOwner(1);
		leftBottom.setBorder(new MatteBorder(2, 0, 0, 2, Color.DARK_GRAY));
		rightTop.setBorder(new MatteBorder(0, 2, 2, 0, Color.DARK_GRAY));
		this.onColorsGeneratedCallback.accept(new Color[]{leftBottom.getColor(), rightTop.getColor()});
	}

	public void ensureRendering() {
		Arrays.stream(tiles)
			.forEach(tile -> {
				tile.updateUI();
			});
	}

	public int getScoreCount(boolean isLeftTurn) {
		return (int) Arrays.stream(tiles).filter(tile -> tile.getOwner() == (isLeftTurn ? 0 : 1)).count();
	}

	public void eat(Color newColor, boolean isLeftTurn) {
		Arrays.stream(tiles)
			.filter(tile -> tile.getOwner() == (isLeftTurn ? 0 : 1))
			.forEach(tile -> {
				tile.setColor(newColor);
				for (Tile neighbor : tile.getNeighbors()) {
					if (neighbor != null && neighbor.getColor().equals(newColor) && neighbor.getOwner() == 2) {
						neighbor.setOwner(isLeftTurn ? 0 : 1);
						neighbor.setColor(newColor);
					}
				}
			});
		Arrays.stream(tiles)
			.filter(tile -> tile.getOwner() == (isLeftTurn ? 0 : 1))
			.forEach(tile -> {
				int top = (tile.getNeighbors()[0] != null && tile.getOwner() != tile.getNeighbors()[0].getOwner()) ? 2 : 0;
				int left = (tile.getNeighbors()[3] != null && tile.getOwner() != tile.getNeighbors()[3].getOwner()) ? 2 : 0;
				int bottom = (tile.getNeighbors()[2] != null && tile.getOwner() != tile.getNeighbors()[2].getOwner()) ? 2 : 0;
				int right = (tile.getNeighbors()[1] != null && tile.getOwner() != tile.getNeighbors()[1].getOwner()) ? 2 : 0;
				tile.setBorder(new MatteBorder(top, left, bottom, right, Color.DARK_GRAY));
			});
	}
}
