package jbb.engine.mouseland;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.Item;
import jbb.engine.Position;

/**
 * The Mouse Class is a subclass of Item and a Mouse Trap can be dropped by the MouseHero
 * 
 * @author Bruno Colantonio
 */
public class MouseTrap extends Item {
	
	private boolean is_set;			//false is MouseTrap already used
	
/**
 * Constructor for the MouseTrap using Position and Board as parameter
 * 
 * @param board represents the board that is associated to this MouseTrap
 * @param position represents the position of the MouseTrap on the Board
 */
	public MouseTrap(Position position, Board board) {
		super(position, board);
		is_set = true;
	}
	public MouseTrap(MouseTrap mouseTrap){
		this(new Position(mouseTrap.position.getRow(), mouseTrap.position.getCol()),mouseTrap.board);
	}
	
/**
 * The getTrapPosition method returns the Position of the MouseTrap	
 * 
 * @return the position of the MouseTrap
 */
	public Position getTrapPosition(){
		return this.position;
	}
/**
 * The set method returns true is the MouseTrap is set and false if it is already used.
 * 
 * @return true if the MouseTrap is set and false if it is already used
 */	
	public boolean set(){
		return is_set;
	}
/**
 * The deactivate method changes the image of a MouseTrap to a used MouseTrap and sets the 
 * MouseTrap to false meaning it is already used and cannot kill another mouse.	
 * 
 */
	public void deactivate(){
		is_set = false;
	}
	
	/**
	 * the toString method return the character "T" to show were the MouseTrap is on the board
	 * 
	 * @return string "T"
	 */	
	public String toString() {
		String str = "mouse_trap";
		if (!is_set) {
			str += "_deactivated";
		}
		return str;
	}

/**
 * If the parameter avatar is of instance Mouse and the the MouseTrap is set, remove a life
 * from the mouse and deactivate the MouseTrap.
 * 
 * else
 * If the parameter avatar is of instance MouseHero, increment the number of MouseTraps, meaning 
 * MouseHero will pick up the trap.
 * 
 * @param avatar is who the MouseTrap has collided with
 */	
	@Override
	public boolean pickedUp(Avatar avatar) {
		if ((avatar instanceof Mouse) && (is_set == true)) {
			avatar.removeLife();
			this.deactivate();
		}
		else if(avatar instanceof MouseHero){
			MouseHero mh = (MouseHero)avatar;
			mh.addMouseTraps();
			return true;
		}
		return false;
	}


}
