package jbb.engine.pacman;

import javax.swing.ImageIcon;

import jbb.engine.Board;
import jbb.engine.Hero;
import jbb.engine.Position;
import jbb.engine.Tile;

public class PacMan extends Hero {
	
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
	public PacMan(int hitPoints, int lives, Board board, Position position) {
		super(new ImageIcon(), hitPoints, lives, board, position);
		invulnerable = false;
	}
	
	public boolean getInvulnerable() {
		return invulnerable;
	}
	
	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}

	protected boolean hasGoodie(Position position) {
		Tile tile = board.getTile(position);
		if (tile instanceof PacDot) {
			PacDot pd = (PacDot) tile;
			pd.pickedUp(this);
			return true;
		}
		return false;
	}

}
