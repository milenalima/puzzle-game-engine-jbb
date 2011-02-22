package jbb.engine.pacman;

import javax.swing.ImageIcon;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.Position;
import jbb.engine.Tile;

public class PacMan extends Avatar{
	
	private boolean invulnerable;

	/**
	 * Constructor for Hero using specified Position.
	 * 
	 * @param image pictorial representation of the Hero to be used on a Tile
	 * @param hitPoints represents the starting health of the Hero
	 * @param lives represents the starting number of lives of the Hero
	 * @param board represents the board that is associated to this Hero
	 * @param position represents the position of the Hero on the Board
	 */
	public PacMan(ImageIcon image, int hitPoints, int lives, Board board, Position position) {
		super(image, hitPoints, lives, board, position);
		invulnerable = false;
	}
	
	/**
	 * Constructor for Hero using default Position (0,0).
	 * 
	 * @param image pictorial representation of the Hero to be used on a Tile
	 * @param hitPoints hitPoints represents the starting health of the Hero
	 * @param lives lives represents the starting number of lives of the Hero
	 * @param board board represents the board that is associated to this Hero
	 */
	public PacMan(ImageIcon image, int hitPoints, int lives, Board board) {
		this(image, hitPoints, lives, board, DEFAULT_POSITION);
	}
	
	public boolean getInvulnerable() {
		return invulnerable;
	}
	
	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}

	@Override
	protected boolean hasGoodie(Position position) {
		Tile tile = board.getTile(position);
		if (tile instanceof PacDot) {
			return true;
		}
		return false;
	}

}
