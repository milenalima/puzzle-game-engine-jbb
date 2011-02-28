package jbb.engine.pacman;

import javax.swing.ImageIcon;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.Hero;
import jbb.engine.Position;
import jbb.engine.Tile;

public class PacMan extends Hero {
	
	public static final int INVULN_LEN = 10; // invulnerability timer lasts 10 turns
	public static final int LIVES = 3;
	
	private int timer;
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
	public PacMan(Position position, Board board) {
		super(new ImageIcon(), LIVES, position, board);
		invulnerable = false;
		timer = 0;
	}
	
	public boolean getInvulnerable() {
		return invulnerable;
	}
	
	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
		timer = INVULN_LEN;
	}
	
	public Position moveTo(Position position) {
		Position returnVal = super.getNextPosition(position);
		if (timer > 0 && --timer == 0) {
			setInvulnerable(false);
		}
		return returnVal;
	}

	public boolean hasGoodie(Position position) {
		Tile tile = board.getTile(position);
		if (tile instanceof PacDot) {
			PacDot pd = (PacDot) tile;
			pd.pickedUp(this);
			return true;
		}
		return false;
	}
	
	public String toString() {
		if (invulnerable) {
			return "C";
		}
		return "c";
	}

	@Override
	public void collidesWith(Avatar avatar) {
		if (avatar instanceof Ghost) {
			this.removeLife();
		}
	}

}
