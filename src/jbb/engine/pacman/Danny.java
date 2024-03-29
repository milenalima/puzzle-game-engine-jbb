package jbb.engine.pacman;

import java.util.Random;

import javax.swing.ImageIcon;

import jbb.engine.Board;
import jbb.engine.Position;

@SuppressWarnings("serial")
public class Danny extends Ghost {
	
	private Position destination = null;
	// save three previous positions
	private Position[] recentPos = {null, null, null};

	public Danny(Position position, Board board) {
		super(new ImageIcon("img/Ghost-D.png"),position, board);
	}
	//copy constructor
	public Danny(Danny danny){
		this(new Position(danny.position.getRow(), danny.position.getCol()), danny.board);
		this.setLives(danny.getLives());
	}
	
	@Override
	/**
	 * Danny randomly selects a position to move, and sticks to it until he
	 * can't move any closer, and then he tries a new position to move to.
	 * @author jon
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
