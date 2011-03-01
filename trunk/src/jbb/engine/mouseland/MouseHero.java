/**
 * The MouseHero Class is a subclass of Hero and of course if the Hero in the Mouseland Game
 * The user will be controlling the MouseHero when the game is running.
 * 
 * @author Bruno Colantonio
 */

package jbb.engine.mouseland;

import javax.swing.ImageIcon;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.Hero;
import jbb.engine.Position;
import jbb.engine.Tile;

public class MouseHero extends Hero{

	public static final int LIVES = 3;
	
	private int numMouseTraps = 1;
	private MouseTrap mouseTrap = null;
		
		
/**
 * Constructor for MouseHero using Position and Board as parameter.
 * 
 * @param board represents the board that is associated to this MouseHero
 * @param position represents the position of the MouseHero on the Board
 */
	public MouseHero(Position position, Board board) {
		super(new ImageIcon(), LIVES, position, board);
	}
	
/**
 * the getNumMouseTraps method returns the amount of Mouse Traps from the Mouse 
 * 
 * @return an integer of the number of Mouse Traps
 */
	public int getNumMouseTraps(){
		return numMouseTraps;
	}
	
/**
 * When called, the method setTrap will place a Mouse Trap at the position of the Mouse
 */	
	public void setTrap(){
		numMouseTraps-=1;
		if(numMouseTraps<0){
			System.out.print("you do not have any mouse traps left \n");
		}
		else 
			mouseTrap = new MouseTrap(this.position,this.board);
			this.board.placeItem(mouseTrap);
	}

/**
 * the getNextPosition method uses a position as the parameter and returns the next position
 * 
 * @param position represents the position of the MouseHero on the Board
 * 
 * @return the Position of the next position 
 */	
	public Position getNextPosition(Position position) {
		Position returnVal = super.getNextPosition(position);
		return returnVal;
	}

/**
 * the hasGoodie method always returns false because there are no items for the 
 * MouseHero to pick up.
 * 
 * @param position represents the position of the MouseHero on the Board
 * 
 * @return false because there are no items
 */
	public boolean hasGoodie(Position position) {
		return false;
	}
	
/**
 * the toString method return the character "m" to show were the MouseHero is on the board and if the MouseHero is
 * on the same Tile as a MouseTrap, it will then display a "M" instead.
 * 
 * @return a string "m" if normal case, but returns "M" if the MouseHero is on the same Tile as a MouseTrap
 */	
	public String toString() {
		if(numMouseTraps==0){
			if((this.position.getCol() == mouseTrap.getPosition().getCol()) && (this.position.getRow() == mouseTrap.getPosition().getRow())){
				return "M";
			}
		}
		return "m";
	}

/**
 * the collidesWith method uses the parameter avatar and checks to see if MouseHero an instance 
 * of Mouse and if it is, it will remove a life of the Mouse Hero
 * 
 * @param avatar is who the MouseHero has collided with
 * 
 * @return true if the MouseHero collided with a Mouse, otherwise it returns false.
 */
	@Override
	public boolean collidesWith(Avatar avatar) {
		if (avatar instanceof Mouse) {
			this.removeLife();
			return true;
		}
		return false;
	}

}
