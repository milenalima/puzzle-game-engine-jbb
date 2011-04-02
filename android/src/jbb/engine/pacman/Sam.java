package jbb.engine.pacman;

import jbb.engine.Board;
import jbb.engine.Position;

public class Sam extends Ghost {
	
	public Position lastPosition = null;

	public Sam(Position position, Board board) {
		super(position, board);
	}
	
	@Override
	/**
	 * this ghost remembers a previous move of PacMan and tries to go where
	 * pacman will be, rather than where he was. If PacMan becomes invulnerable,
	 * this ghost runs away.
	 */
	public Position getNextPosition(Position position) {
		if (!((PacMan)(board.getHero())).isInvulnerable()) {
			if (lastPosition != null) {
				if (position.isNorthOf(lastPosition)) {
					lastPosition = position;
					return getNextPosition(new Position(position.getRow()-1,position.getCol()));
				} else if (position.isSouthOf(lastPosition)) {
					lastPosition = position;
					return getNextPosition(new Position(position.getRow()+1,position.getCol()));
				} else if (position.isEastOf(lastPosition)) {
					lastPosition = position;
					return getNextPosition(new Position(position.getRow(),position.getCol()+1));
				} else if (position.isWestOf(lastPosition)) {
					lastPosition = position;
					return getNextPosition(new Position(position.getRow(),position.getCol()-1));
				}
			} return super.getNextPosition(position);
		} else { // PacMan is invulnerable, so run away!
			// run to the opposite side of the board
			int runCol = Math.abs(board.getWidth()-position.getCol());
			int runRow = Math.abs(board.getHeight()-position.getRow());
			Position runPosition = new Position(runRow,runCol);
			return super.getNextPosition(runPosition);
		}
		// otherwise just try to move towards given position
	}
	
	public String toString() {
		return "ghost_s";
	}

}
