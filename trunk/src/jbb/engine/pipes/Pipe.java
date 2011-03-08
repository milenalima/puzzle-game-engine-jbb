package jbb.engine.pipes;

import javax.swing.ImageIcon;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.Item;
import jbb.engine.Position;

/**
 * An Item that the Plumber drops and Water flows through.
 * @author Boris Ionine
 */
public class Pipe extends Item{
	private int numOpenings;
	private char pipeType; //one of the Q(stopper and starting faucet), I, L, T, X pipe types
	private boolean openLeft = false;
	private boolean openBottom = false;
	private boolean openRight = false;
	private boolean openTop = false;
	private boolean filled = false;
	
	/**
	 * Constructor for Pipe.
	 * Sets the number of openings and which sides of the pipe are open
	 * based on the "type" of Pipe, represented by a character.
	 * @param position
	 * @param board
	 * @param pipeType
	 */
	public Pipe(Position position, Board board, char pipeType) {
		super(position, board);
		//this.numOpenings = numOpenings;
		this.pipeType = pipeType;
		this.setImage(new ImageIcon("pipe-"+pipeType+"-1.png"));
		//switch statement to determine number of openings and which 
		//sides are open, based on the pipeType
		switch (pipeType){
			case 'Q':
				this.numOpenings = 1;
				openBottom = true;
				break;
			case 'I': 
				this.numOpenings = 2;
				openLeft = true;
				openRight = true;
				break;
			case 'L':
				this.numOpenings = 2;
				openLeft = true;
				openBottom = true;
				break;
			case 'T':
				this.numOpenings = 3;
				openLeft = true;
				openRight = true;
				openBottom = true;
				break;
			case '+':
				this.numOpenings = 4;
				openLeft = true;
				openRight = true;
				openBottom = true;
				openTop = true;
				break;
		}
	}

	
	/**
	 * Override of pickedUp in Item.
	 * Checks to see if Water or a Plumber picked up the Pipe and reacts
	 * accordingly (calls correspoding method).
	 * @param picker
	 * @Override
	 */
	public void pickedUp(Avatar picker) {
		// TODO Auto-generated method stub
		Avatar p;
		//determine if the Plumber or the Water has picked up the pipe
		try{
			p = (Plumber) picker;
		}
		catch(ClassCastException e){
			p = (Water) picker; 
		}
		//use internal methods to handle either case
		if(p instanceof Water)
		{
			this.fillUp();
		}
		else{
			this.rotate();
		}				
	}
	
	/**
	 * Sets boolean filled to true.
	 */
	private void fillUp() {
		// TODO further use?
		filled = true;
	}

	/**
	 * Rotates the Pipe counter-clockwise.
	 * Called when Plumber lands on a Pipe.
	 */
	public void rotate(){
		boolean bTemp = openBottom;
		openBottom = openLeft;
		boolean rTemp = openRight;
		openRight = bTemp;
		boolean tTemp = openTop;
		openTop = rTemp;
		openLeft = tTemp;		
	}
	
	/**
	 * Will be used in the GUI implementation to link adjacent pipe 
	 * openings graphically.
	 * @return boolean
	 */
	public boolean connectToAdjacent(){
		//should eventually (GUI) look for adjacent pipes and make "connections" where possible
		return false;
	}
	
	/**
	 * @return numOpenings
	 */
	public int getNumOpenings(){
		return numOpenings;
	}
	
	/**
	 * @return pipeType
	 */
	public char getPipeType(){
		return pipeType;
	}
	
	/**
	 * @return openLeft
	 */
	public boolean isOpenLeft() {
		return openLeft;
	}
	
	/**
	 * @return openBottom
	 */
	public boolean isOpenBottom() {
		return openBottom;
	}

	/**
	 * @return openRight
	 */
	public boolean isOpenRight() {
		return openRight;
	}

	/**
	 * @return openTop
	 */
	public boolean isOpenTop() {
		return openTop;
	}
	
	/**
	 * toString method.
	 * Prints the type of the Pipe, with a small
	 * w if it is filled with Water.
	 */
	public String toString(){
		if(filled)
			return "" + pipeType + "w";
		return "" + pipeType;
	}
}
