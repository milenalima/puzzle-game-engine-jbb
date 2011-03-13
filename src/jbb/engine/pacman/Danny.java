package jbb.engine.pacman;

import java.util.Random;

import javax.swing.ImageIcon;

import jbb.engine.Board;
import jbb.engine.Position;
import jbb.engine.Tile;

@SuppressWarnings("serial")
public class Danny extends Ghost {
	
	private Position destination = null;
	// save three previous positions
	private Position[] recentPos = {null, null, null};

	public Danny(Position position, Board board) {
		super(new ImageIcon("img/Ghost-D.png"),position, board);
	}
	
	@Override
	/**
	 * Danny randomly selects a position to move, and sticks to it until he
	 * can't move any closer, and then he tries a new position to move to.
	 */
	public Position getNextPosition(Position position) {
		if (destination == null) {
			// make a new destination
			Random r = new Random();
			int row = r.nextInt(board.getHeight());
			int col = r.nextInt(board.getWidth());
			destination = new Position(row,col);
		}
		recentPos[2] = recentPos[1];
		recentPos[1] = recentPos[0];
		recentPos[0] = super.getNextPosition(destination);
		// if the ghost doesn't move, or the ghost moves back to its old position,
		// clear destination
		if (recentPos[0].equals(recentPos[1]) || recentPos[0].equals(recentPos[2]))
			destination = null;
		return recentPos[0];
	}

}
