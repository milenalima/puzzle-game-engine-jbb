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

@SuppressWarnings("serial")
public class MouseHero extends Hero{

	public static final int LIVES = 3;
	
	private int numMouseTraps = 1;
	private MouseTrap mouseTrap = null;
	protected static final ImageIcon mhRightImage = new ImageIcon("img/cheesy-right.png");
	protected static final ImageIcon mhUpImage = new ImageIcon("img/cheesy-up.png");
	protected static final ImageIcon mhdownImage = new ImageIcon("img/cheesy-down.png");
	protected static final ImageIcon mhLeftImage = new ImageIcon("img/cheesy-left.png");
		
		
/**
 * Constructor for MouseHero using Position and Board as parameter.
 * 
 * @param board represents the board that is associated to this MouseHero
 * @param position represents the position of the MouseHero on the Board
 */
	public MouseHero(Position position, Board board) {
		super(mhUpImage, LIVES, position, board);
	}
	//copy constructor
	public MouseHero(MouseHero mouseHero){
		this(new Position(mouseHero.position.getRow(), mouseHero.position.getCol()), mouseHero.board);
		this.mouseTrap = mouseHero.mouseTrap;
		this.numMouseTraps = mouseHero.numMouseTraps;
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
 * It adds one to the number of Mouse Traps
 * 
 * @return an integer of the number of Mouse Traps
 */
	public int addMouseTraps(){
		numMouseTraps++;
		return numMouseTraps;
	}
	
/**
 * When called, the method setTrap will place a Mouse Trap at the position of the Mouse
 */	
	public void setTrap(){
		//when MouseHero runs out of MouseTrap it will print out the following
		if(numMouseTraps<=0){
			//System.out.print("you do not have any mouse traps left \n");
		}
		else {
			numMouseTraps--;
			mouseTrap = new MouseTrap(this.position,this.board);
			board.placeItem(mouseTrap);
		}
	}

/**
 * the getNextPosition method uses a position as the parameter and returns the next position
 * It also checks to see what direction the MouseHero is going and will setImage() according
 * to the direction.
 * 
 * @param position represents the position of the MouseHero on the Board
 * 
 * @return the Position of the next position 
 */	
	public Position getNextPosition(Position position) {
		Position returnVal = super.getNextPosition(position);
			if (returnVal.isSouthOf(this.position)) {
				setImage(mhdownImage);
			}
			if (returnVal.isNorthOf(this.position)) {
				setImage(mhUpImage);
			}
			if (returnVal.isEastOf(this.position)) {
				setImage(mhRightImage);
			}
			if (returnVal.isWestOf(this.position)) {
				setImage(mhLeftImage);
			}
	 		
		return returnVal;
		
	}

/**
 * the hasGoodie method true if the tile MouseHero is currently on is of instance 
 * of Mouse Trap, else false
 * 
 * @param position represents the position of the MouseHero on the Board
 * 
 * @return true if the tile MouseHero is currently on is of instance of Mouse Trap, else false
 */
	public boolean hasGoodie(Position position) {
		Tile tile = board.getItem(position);
		//create a tile at the position at the moment and compare it with a 
		//mouseTrap, if it is an instance of MouseTrap do the following..
		if (tile instanceof MouseTrap) {
			//System.out.print("\nThe Mouse walk over Cheesy's Mouse Trap!!\n\n");
			return true;
			}
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
			//if the position of the MouseHero is the same as MouseTrap return "M"
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
		//If avatar is an instance of Mouse ..
		if (avatar instanceof Mouse) {
			//go to the removeLife method
			this.removeLife();
			return true;
		}
		return false;
	}

}
