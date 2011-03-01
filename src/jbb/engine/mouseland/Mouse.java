/**
 * I'm a Mouse. MouseTraps kill me. I kill MouseHero
 */

package jbb.engine.mouseland;

import javax.swing.ImageIcon;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.GameOver;
import jbb.engine.NPC;
import jbb.engine.Position;
import jbb.engine.Tile;

public class Mouse extends NPC{
	public static final int LIVES = 1;
	
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
}
	
	
	public Position getNextPosition(Position position) {
		Position returnVal = super.getNextPosition(position);
		return returnVal;
	}

	public boolean hasGoodie(Position position) {
		Tile tile = board.getItem(position);
		if (tile instanceof MouseTrap) {
			System.out.print("\n\n\n WOOOOO!!\n\n\n\n");
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "e";
	}

	@Override
	public boolean collidesWith(Avatar avatar) {
		if (avatar instanceof MouseHero) {
			avatar.removeLife();
			return true;
		}
		return false;
	}

}
