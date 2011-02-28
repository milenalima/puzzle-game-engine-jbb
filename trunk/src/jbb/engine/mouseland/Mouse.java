package jbb.engine.mouseland;

import javax.swing.ImageIcon;

import jbb.engine.Board;
import jbb.engine.NPC;
import jbb.engine.Position;
import jbb.engine.Tile;

public class Mouse extends NPC{

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
public Mouse(Position position, Board board) {
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
	
	public boolean moveTo(Position position) {
		boolean returnVal = super.moveTo(position);
		if (--timer == 0) {
			setInvulnerable(false);
		}
		return returnVal;
	}

	protected boolean hasGoodie(Position position) {
		Tile tile = board.getTile(position);
		if (tile instanceof MouseTrap) {
			this.removeLife();
			return true;
		}
		return false;
	}
	
	public String toString() {
		if (invulnerable) {
			return "E";
		}
		return "e";
	}

}
