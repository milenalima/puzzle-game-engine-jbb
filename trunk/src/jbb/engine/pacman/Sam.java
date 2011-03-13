package jbb.engine.pacman;

import javax.swing.ImageIcon;

import jbb.engine.Board;
import jbb.engine.Position;

@SuppressWarnings("serial")
public class Sam extends Ghost {
	
	public Position lastPosition = null;

	public Sam(Position position, Board board) {
		super(new ImageIcon("img/Ghost-S.png"),position, board);
	}
	
	@Override
	public Position getNextPosition(Position position) {
		if (lastPosition != null) {
			
		}
		return super.getNextPosition(position);
	}

}
