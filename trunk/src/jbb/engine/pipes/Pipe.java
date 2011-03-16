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
@SuppressWarnings("serial")
public class Pipe extends Item{
	
	private static final int degrees0 = 1;
	private static final int degrees270 = 4;
	
	private int numOpenings;
	private char pipeType; //one of the Q(stopper and starting faucet), I, L, T, X pipe types
	private int rotation = degrees0;
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
		this.setImage(new ImageIcon("img/pipe-"+pipeType+"-1.png"));
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
				openRight = true;
				openTop = true;
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
	 * accordingly (calls corresponding method).
	 * @param picker
	 */
	@Override
	public boolean pickedUp(Avatar picker) {
		//determine if the Plumber or the Water has picked up the pipe
		if (picker instanceof Plumber) {
			if(!filled)//don't allow rotation of filled pipes
				this.rotate();
		} else if (picker instanceof Water) {
			this.fillUp();
		}
		return false;
	}
	
	/**
	 * Sets boolean filled to true. 
	 * Changes the Pipe's image to one with water.
	 */
	private void fillUp() {
		filled = true;
		this.setImage(new ImageIcon("img/pipe-"+pipeType+"-water-"+rotation+".png"));
	}

	/**
	 * Rotates the Pipe counter-clockwise.
	 * Changes the image to the appropriate, rotated one.
	 * Called when Plumber lands on a Pipe.
	 */
	public void rotate(){
		
		boolean temp = openLeft;
	  	openLeft = openTop;
	  	openTop = openRight;
	  	openRight = openBottom;
	  	openBottom = temp;
	 
		if(++rotation > degrees270)
			rotation = degrees0;
		
		this.setImage(new ImageIcon("img/pipe-"+pipeType+"-"+rotation+".png"));
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
	 * @return filled
	 */
	public boolean isFilled() {
		return filled;
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
