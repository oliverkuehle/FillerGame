package view;

import java.awt.BorderLayout;
import java.awt.Color;

import java.util.function.Consumer;

import javax.swing.JFrame;

public class GameView extends JFrame {
	private ScorePanel scorePanel;
	private TilePanel tilePanel;
	private ButtonPanel buttonPanel;

	public GameView(int[] fieldSize, Consumer<Color> onColorPressedCallback, Consumer<Color[]> onColorsGeneratedCallback) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setVisible(true);

		scorePanel = new ScorePanel();
		tilePanel = new TilePanel(fieldSize, onColorsGeneratedCallback);
		buttonPanel = new ButtonPanel(onColorPressedCallback);

		add(scorePanel, BorderLayout.NORTH);
		add(tilePanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	public void showView() {
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.tilePanel.ensureRendering();
		new javax.swing.Timer(200, _ -> this.tilePanel.ensureRendering()).start();
		new javax.swing.Timer(1000, _ -> this.tilePanel.ensureRendering()).start();
		new javax.swing.Timer(3000, _ -> this.tilePanel.ensureRendering()).start();
	}

	public ScorePanel getScorePanel() {
		return scorePanel;
	}

	public TilePanel getTilePanel() {
		return tilePanel;
	}

	public ButtonPanel getButtonPanel() {
		return buttonPanel;
	}
}
