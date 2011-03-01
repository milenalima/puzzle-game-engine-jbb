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
 * Constructor for Hero using specified Position.
 * 
 * @param image pictorial representation of the Hero to be used on a Tile
 * @param hitPoints represents the starting health of the Hero
 * @param lives represents the starting number of lives of the Hero
 * @param board represents the board that is associated to this Hero
 * @param position represents the position of the Hero on the Board
 */
public MouseHero(Position position, Board board) {
	super(new ImageIcon(), LIVES, position, board);
}
	
	public int getNumMouseTraps(){
		return numMouseTraps;
	}

	
	public void setTrap(){
		numMouseTraps-=1;
		if(numMouseTraps<0){
			System.out.print("you do not have any mouse traps left \n");
		}
		else 
			mouseTrap = new MouseTrap(this.position,this.board);
			this.board.placeItem(mouseTrap);
	}
	
	public Position getNextPosition(Position position) {
		Position returnVal = super.getNextPosition(position);
		return returnVal;
	}

	public boolean hasGoodie(Position position) {
		Tile tile = board.getTile(position);
		if (tile instanceof Mouse) {
			Mouse m = new Mouse(position, board);
			m.collidesWith(this);
			return true;
		}
		return false;
	}
	
	public String toString() {
		if(numMouseTraps==0){
			if((this.position.getCol() == mouseTrap.getPosition().getCol()) && (this.position.getRow() == mouseTrap.getPosition().getRow())){
				return "M";
			}
		}
		return "m";
	}
	
	@Override
	public boolean collidesWith(Avatar avatar) {
		if (avatar instanceof Mouse) {
			this.removeLife();
			return true;
		}
		return false;
	}

}
