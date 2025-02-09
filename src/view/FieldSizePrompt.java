package view;

import javax.swing.*;

import java.awt.*;

public class FieldSizePrompt {
	Icon icon;

	public FieldSizePrompt() {
		try {
			icon = new ImageIcon(getClass().getResource("/resources/FillerIcon.png"));
		} catch(Exception e) {
			System.err.println("Couldn't find file: /resources/FillerIcon.png");
			icon = UIManager.getIcon("OptionPane.informationIcon"); // Fallback icon
		}
	}

	public int[] promptForFieldSize() {
		JPanel panel = new JPanel(new GridLayout(2, 2));
		panel.add(new JLabel("Rows:"));
		JSpinner rowsSpinner = new JSpinner(new SpinnerNumberModel(7, 2, 100, 1));
		panel.add(rowsSpinner);
		panel.add(new JLabel("Cols:"));
		JSpinner colsSpinner = new JSpinner(new SpinnerNumberModel(8, 2, 100, 1));
		panel.add(colsSpinner);

		int result = JOptionPane.showOptionDialog(null, panel, "Enter field size", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon, new String[]{"Start game"}, "Start game");
		if (result == JOptionPane.OK_OPTION) {
			int rows = (int) rowsSpinner.getValue();
			int cols = (int) colsSpinner.getValue();
			return new int[] { rows, cols };
		}
		System.exit(0);
		return null;
	}

	public int[] promptForFieldSize(int previousWinner) {
    String message = new String[]{ "Left side won!", "Right side won!", "It's a tie!" }[previousWinner];
    Object[] options = { "Play again" };
    JOptionPane.showOptionDialog(null, message, "Game result", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, icon, options, options[0]);
    return promptForFieldSize();
	}
}
