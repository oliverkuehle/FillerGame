package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionEvent;

import java.util.function.Consumer;

public class ButtonPanel extends JPanel {
	private JButton[] buttons = new JButton[6];
	private Color[] tileColors = {
		new Color(0xFFAAAC),
		new Color(0xB5E9B1),
		new Color(0xB6F0FF),
		new Color(0xFFB4F9),
		new Color(0xD3B6F0),
		new Color(0xFFF3A6)
	};

	public ButtonPanel(Consumer<Color> onColorPressedCallback) {
		setBackground(Color.DARK_GRAY);
		setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		for (int i = 0; i < buttons.length; i++) {
			JButton button = new JButton(String.valueOf(i + 1));
			button.setForeground(Color.GRAY);
			button.setBackground(tileColors[i]);
			button.setOpaque(true);
			button.setBorder(javax.swing.BorderFactory.createLineBorder(Color.GRAY, 1));
			button.setPreferredSize(new Dimension(50, 50));
			button.addActionListener(_ -> onColorPressedCallback.accept(button.getBackground()));
			buttons[i] = button;
			add(button);
		}
		// Add global key bindings to handle number key presses.
		for (int i = 0; i < buttons.length; i++) {
			int index = i; // final index for lambda
			getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(String.valueOf(i + 1)), "color" + i);
			getActionMap().put("color" + i, new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (buttons[index].isEnabled()) {
						onColorPressedCallback.accept(buttons[index].getBackground());
					}
				}
			});
		}
	}

	public void updateDisabledButtons(Color[] disabledColors) {
		for (int i = 0; i < buttons.length; i++) {
			JButton button = buttons[i];
			if (tileColors[i].equals(disabledColors[0]) || tileColors[i].equals(disabledColors[1])) {
				button.setBorder(javax.swing.BorderFactory.createLineBorder(Color.DARK_GRAY, 7));
				button.setBackground(tileColors[i].darker().darker());
				button.setText("");
				button.setEnabled(false);
			} else {
				button.setBorder(javax.swing.BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
				button.setBackground(tileColors[i]);
				button.setText(String.valueOf(i + 1));
				button.setEnabled(true);
			}
		}
	}
}
