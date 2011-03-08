/**
 * The Mouse Class is a subclass of NPC and are the enemy mouses that are trying to
 * catch the MouseHero.
 * 
 * @author Bruno Colantonio
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
 * Constructor for the Mouse using Position and Board as parameter
 * 
 * @param board represents the board that is associated to this Mouse
 * @param position represents the position of the Mouse on the Board
 */
	public Mouse(Position position, Board board) {
		super(new ImageIcon("img/redEyes-left.png"), LIVES, position, board);
	}

/**
 * the getNextPosition method uses a position as the parameter and returns the next position
 * 
 * @param position represents the position of the Mouse on the Board
 * 
 * @return the Position of the next position 
 */
	public Position getNextPosition(Position position) {
		Position returnVal = super.getNextPosition(position);
		return returnVal;
	}

	
/**
 * the hasGoodie method checks to see if Mouse walked over a Mouse Trap on the position given (which is the parameter)
 * 
 * @param position represents the position of the Mouse on the Board
 * 
 * @return true if the Mouse walked over a Mouse Trap
 */
	public boolean hasGoodie(Position position) {
		Tile tile = board.getItem(position);
		//create a tile at the position at the moment and compare it with a 
		//mouseTrap, if it is an instance of MouseTrap do the following..
		if (tile instanceof MouseTrap) {
			System.out.print("\nThe Mouse walk over Cheesy's Mouse Trap!!\n\n");
			return true;
		}
		return false;
	}

	
/**
 * the toString method return the character "e" to show were the Mouse is on the board
 * 
 * @return string "e"
 */
	public String toString() {
		return "e";
	}
	
/**
 * the collidesWith method uses the parameter avatar and checks to see if the Mouse is an instance 
 * of MouseHero and if it is, it will remove a life of the Mouse Hero
 * 
 * @param avatar is who the Mouse has collided with
 * 
 * @return true if the Mouse collided with a MouseHero, otherwise it returns false.
 */
	@Override
	public boolean collidesWith(Avatar avatar) {
		//If avatar is an instance of MouseHero ..
		if (avatar instanceof MouseHero) {
			//go to the removeLife method
			avatar.removeLife();
			return true;
		}
		return false;
	}

}
