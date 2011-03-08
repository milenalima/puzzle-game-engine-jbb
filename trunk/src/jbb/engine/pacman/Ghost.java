package jbb.engine.pacman;

import javax.swing.ImageIcon;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.NPC;
import jbb.engine.Position;

/**
 * The Ghost currently moves towards the position using the NPCs
 * movement method. Later on, there will be three different ghost
 * types, described in the USER GUIDE.
 * @author Jonathan Gravel
 */
@SuppressWarnings("serial")
public class Ghost extends NPC{

	public static final int LIVES = 1; // only one life
	
	/**
	 * @param position
	 * @param board
	 */
	public Ghost(Position position, Board board) {
		super(new ImageIcon("img/Ghost-D.png"), LIVES, position, board);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return false; the ghost cannot pick anything up
	 */
	@Override
	public boolean hasGoodie(Position position) {
		return false; // ghost cannot pick up goodies
	}
	
	/**
	 * @return String
	 */
	@Override
	public String toString() {
		return "G";
	}

	/**
	 * @return true if Ghost kills PacMan
	 */
	@Override
	public boolean collidesWith(Avatar avatar) {
		if (avatar instanceof PacMan) {
			return avatar.collidesWith(this);
		}
		return false;
	}

}
