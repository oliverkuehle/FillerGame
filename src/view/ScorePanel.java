package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class ScorePanel extends JPanel {
	private JLabel leftScore;
	private JLabel rightScore;
	private JLabel title;

	public ScorePanel() {
		leftScore = new JLabel("1");
		rightScore = new JLabel("1");
		title = new JLabel("Filler");

		leftScore.setFont(new Font("Courier New", Font.BOLD, 30));
		leftScore.setOpaque(true);
		leftScore.setHorizontalAlignment(JLabel.CENTER);
		leftScore.setPreferredSize(new Dimension(50, 50)); // Updated to square
		leftScore.setMaximumSize(new Dimension(50, 50)); // Force square height
		leftScore.setForeground(Color.DARK_GRAY);

		rightScore.setFont(new Font("Courier New", Font.BOLD, 30));
		rightScore.setOpaque(true);
		rightScore.setHorizontalAlignment(JLabel.CENTER);
		rightScore.setPreferredSize(new Dimension(50, 50)); // Updated to square
		rightScore.setMaximumSize(new Dimension(50, 50)); // Force square height
		rightScore.setForeground(Color.DARK_GRAY);

		title.setFont(new Font("Copperplate", Font.BOLD, 50));
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(JLabel.CENTER);

		setBackground(Color.DARK_GRAY);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(leftScore);
		add(Box.createHorizontalGlue());
		add(title);
		add(Box.createHorizontalGlue());
		add(rightScore);
		setBorder(new EmptyBorder(7, 7, 7, 7));
	}

	public void updateScore(int newScore, boolean isLeftTurn) {
		(isLeftTurn ? leftScore : rightScore).setText(String.valueOf(newScore));
	}

	public void highlightTurn(Color leftColor, Color rightColor, boolean isLeftTurn) {
		JLabel isPlaying = isLeftTurn ? leftScore : rightScore;
		JLabel isWaiting = !isLeftTurn ? leftScore : rightScore;
		isPlaying.setBackground(isLeftTurn ? leftColor : rightColor);
		isPlaying.setForeground(Color.DARK_GRAY);
		isWaiting.setBackground(null);
		isWaiting.setForeground(Color.WHITE);
	}
}
