package jbb.engine.pacman;

import jbb.engine.Board;
import jbb.engine.Position;

public class Cam extends Ghost {

	public Cam(Position position, Board board) {
		super(position, board);
	}
	
	@Override
	/**
	 * basic NPC movement
	 */
	public Position getNextPosition(Position position) {
		return super.getNextPosition(position);
	}
	
	public String toString() {
		return "ghost_c";
	}

}
