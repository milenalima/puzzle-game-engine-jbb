package jbb.engine.pacman;

import javax.swing.ImageIcon;

import jbb.engine.Board;
import jbb.engine.Position;

@SuppressWarnings("serial")
public class Cam extends Ghost {

	public Cam(Position position, Board board) {
		super(new ImageIcon("img/Ghost-C.png"),position, board);
	}
	
	@Override
	public Position getNextPosition(Position position) {
		return super.getNextPosition(position);
	}

}
