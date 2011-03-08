package jbb.engine.pipes;

import jbb.engine.BoardView;
import jbb.engine.pacman.PacGameView;
import jbb.engine.pacman.PacWorld;

public class PipeGameView extends BoardView {

	public PipeGameView(PipeMap board) {
		super(board);
	}
	
	public static void main(String[] args) {
		PipeMap pm = new PipeMap();
		@SuppressWarnings("unused")
		PipeGameView game = new PipeGameView(pm);
	}

}
