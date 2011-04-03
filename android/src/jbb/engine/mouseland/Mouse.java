/**
 * The Mouse Class is a subclass of NPC and are the enemy mouses that are trying to
 * catch the MouseHero.
 * 
 * @author Bruno Colantonio
 */

package jbb.engine.mouseland;

import java.util.Random;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.NPC;
import jbb.engine.Position;
import jbb.engine.Tile;

public class Mouse extends NPC{
	public static final int LIVES = 1;
	private Position[] recentPos = {null, null, null};
	private String direction;
	
/**
 * Constructor for the Mouse using Position and Board as parameter
 * 
 * @param board represents the board that is associated to this Mouse
 * @param position represents the position of the Mouse on the Board
 */
	public Mouse(Position position, Board board) {
		super(LIVES, position, board);
		direction = "right";
	}

/**
 * the getNextPosition method uses a position as the parameter and returns the next position,
 * It keeps in memory the previous positions and checks to see if the mouse is stuck in a 
 * corner, (or if the mouse is going back and forth, from one position to another), and will 
 * create a random new position so it leaves the area.
 * 
 * @param position represents the position of the Mouse on the Board
 * 
 * @return the Position of the next position 
 */
	public Position getNextPosition(Position position) {
		Position returnVal;
		
		recentPos[2] = recentPos[1];
		recentPos[1] = recentPos[0];
		recentPos[0] = super.getNextPosition(position);		
		if (recentPos[0].equals(recentPos[1]) || recentPos[0].equals(recentPos[2])){
			Random r = new Random();
			int row = r.nextInt(board.getHeight());
			int col = r.nextInt(board.getWidth());
			returnVal = super.getNextPosition(new Position(row,col));
		}
		else{
			returnVal = super.getNextPosition(position);
		}
		
		
		if (returnVal.isSouthOf(this.position)) {
			direction = "down";
		}
		if (returnVal.isNorthOf(this.position)) {
			direction = "up";
		}
		if (returnVal.isEastOf(this.position)) {
			direction = "right";
		}
		if (returnVal.isWestOf(this.position)) {
			direction = "left";
		}
		
		return returnVal;
		
	}
	
/**
 * the hasGoodie method checks to see if Mouse walked over a Mouse Trap on the 
 * position given (which is the parameter), if yes, it checks to see if the MouseTrap is
 * set or used and if used returns false and if set returns true.
 * 
 * @param position represents the position of the Mouse on the Board
 * 
 * @return true if the Mouse walked over a Mouse Trap and the MouseTrap is set
 */
	public boolean hasGoodie(Position position) {
		Tile tile = board.getItem(position);
		//create a tile at the position at the moment and compare it with a 
		//mouseTrap, if it is an instance of MouseTrap do the following..
		if (tile instanceof MouseTrap) {
			//System.out.print("\nThe Mouse walk over Cheesy's Mouse Trap!!\n\n");
			MouseTrap mt = (MouseTrap) tile;
			if(mt.set()){
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}

	
/**
 * 
 * @return image name
 */
	public String toString() {
		return "red_eyes_"+direction;
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
