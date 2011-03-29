package jbb.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This is the controller for the BoardView. When a Tile is clicked, this calls
 * actionPerformed(...).
 */
class BoardController implements ActionListener {

	private Board board;

	/**
	 * 
	 * @param board board to use as reference.
	 */
	public BoardController(Board board) {
		this.board = board;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Tile tile = (Tile) e.getSource();
		try {
			// when a button is clicked, call playTurn on the clicked position
			board.playTurn(tile.getPosition());
		} catch (IllegalArgumentException ex) {
			// do nothing to avoid annoying popups.
		}
	}

}