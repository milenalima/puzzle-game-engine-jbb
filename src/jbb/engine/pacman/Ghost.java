package jbb.engine.pacman;

import javax.swing.ImageIcon;

import jbb.engine.Board;
import jbb.engine.NPC;
import jbb.engine.Position;

public class Ghost extends NPC{

	public Ghost(ImageIcon image, int hitPoints, int lives, Position position,
			Board board) {
		super(image, hitPoints, lives, position, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean hasGoodie(Position position) {
		// TODO Auto-generated method stub
		return false;
	}

}
