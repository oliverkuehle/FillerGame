package controller;

import java.awt.Color;

import model.GameModel;
import view.GameView;
import view.FieldSizePrompt;

public class GameController {
	private GameModel model;
	private GameView view;

	public void startGame() {
		startGame(-1);
	}

	public void startGame(int previousWinner) {
		int[] fieldSize = (previousWinner == -1) ? new FieldSizePrompt().promptForFieldSize() : new FieldSizePrompt().promptForFieldSize(previousWinner);
		model = new GameModel(fieldSize);
		if (view != null) view.dispose();
		view = new GameView(fieldSize, this::onColorPressed, this::onColorsGenerated);
		view.getScorePanel().highlightTurn(model.getLeftColor(), model.getRightColor(), model.isLeftTurn());
		view.getButtonPanel().updateDisabledButtons(new Color[]{model.getLeftColor(), model.getRightColor()});
		view.showView();
	}

	/**
	 * Called after the tilePanel is generated and the player's initial colors are assigned.
	 */
	public void onColorsGenerated(Color[] colors) {
		model.setLeftColor(colors[0]);
		model.setRightColor(colors[1]);
	}

	/**
	 * Called when a color button is pressed.
	 */
	public void onColorPressed(Color color) {
		if (color.equals(model.getLeftColor()) || color.equals(model.getRightColor())) {
			return;
		}

		if (model.isLeftTurn()) {
			view.getTilePanel().eat(color, true);
			int newScore = view.getTilePanel().getScoreCount(model.isLeftTurn());
			model.setPointsLeft(newScore);
			model.setLeftColor(color);
			
		} else {
			view.getTilePanel().eat(color, false);
			int newScore = view.getTilePanel().getScoreCount(false);
			model.setPointsRight(newScore);
			model.setRightColor(color);
		}

		view.getScorePanel().updateScore(model.isLeftTurn() ? model.getPointsLeft() : model.getPointsRight(), model.isLeftTurn());
		view.getButtonPanel().updateDisabledButtons(new Color[]{model.getLeftColor(), model.getRightColor()});

		if (model.isGameOver()) {
			view.getScorePanel().highlightTurn(model.getLeftColor(), model.getRightColor(), model.isLeftTurn());		
			this.startGame(model.getWinner());
		} else {
			model.toggleTurn();
			view.getScorePanel().highlightTurn(model.getLeftColor(), model.getRightColor(), model.isLeftTurn());
		}
		
	}
}
