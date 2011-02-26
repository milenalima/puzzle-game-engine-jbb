package jbb.engine.pacman;

import javax.swing.ImageIcon;

import jbb.engine.Board;
import jbb.engine.NPC;
import jbb.engine.Position;

public class Ghost extends NPC{

	public static final int HIT_POINTS = 1;
	public static final int LIVES = 1;
	
	public Ghost(Position position, Board board) {
		super(new ImageIcon(), HIT_POINTS, LIVES, position, board);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected boolean hasGoodie(Position position) {
		return false; // ghost cannot pick up goodies
	}
	
	public String toString() {
		return "G";
	}

}
