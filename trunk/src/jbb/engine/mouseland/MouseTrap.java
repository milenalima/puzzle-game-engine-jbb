package jbb.engine.mouseland;

import javax.swing.ImageIcon;

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

/**
 * Constructor for the MouseTrap using Position and Board as parameter
 * 
 * @param board represents the board that is associated to this MouseTrap
 * @param position represents the position of the MouseTrap on the Board
 */
	public MouseTrap(Position position, Board board) {
		super(position, board);
		setImage(new ImageIcon("img/mouse-trap.png"));
	}

/**
 * The getTrapPosition returns the Position of the MouseTrap	
 * 
 * @return the position of the MouseTrap
 */
	public Position getTrapPosition(){
		return this.position;
	}

	/**
	 * the toString method return the character "T" to show were the MouseTrap is on the board
	 * 
	 * @return string "T"
	 */
	public String toString() {
			return "T";
	}

/**
 * the pickedUp method uses the parameter avatar and checks to see if the MouseTrap is an instance 
 * of Mouse and if it is, it will remove a life of the Mouse
 * 
 * @param avatar is who the MouseTrap has collided with
 */	
	@Override
	public void pickedUp(Avatar avatar) {
		if (avatar instanceof Mouse) {
			avatar.removeLife();
		}
	}


}
