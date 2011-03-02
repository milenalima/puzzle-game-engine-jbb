package jbb.engine.pipes;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.Item;
import jbb.engine.Position;

public class Pipe extends Item{
	private int numOpenings;
	private char pipeType; //one of the Q(stopper and starting faucet), I, L, T, X pipe types
	private boolean openLeft = false;
	private boolean openBottom = false;
	private boolean openRight = false;
	private boolean openTop = false;
	private boolean filled = false;
	
	public Pipe(Position position, Board board, char pipeType) {
		super(position, board);
		//this.numOpenings = numOpenings;
		this.pipeType = pipeType;
		
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
			case 'X':
				this.numOpenings = 4;
				openLeft = true;
				openRight = true;
				openBottom = true;
				openTop = true;
				break;
		}
	}

	@Override
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
	
	private void fillUp() {
		// TODO further use?
		filled = true;
	}

	//rotates the Pipe counter-clockwise
	public void rotate(){
		boolean bTemp = openBottom;
		openBottom = openLeft;
		boolean rTemp = openRight;
		openRight = bTemp;
		boolean tTemp = openTop;
		openTop = rTemp;
		openLeft = tTemp;		
	}
	
	public boolean connectToAdjacent(){
		//should eventually look for adjacent pipes and make "connections" where possible
		return false;
	}
	
	public int getNumOpenings(){
		return numOpenings;
	}
	
	public char getPipeType(){
		return pipeType;
	}
	
	public boolean isOpenLeft() {
		return openLeft;
	}
	
	public boolean isOpenBottom() {
		return openBottom;
	}

	public boolean isOpenRight() {
		return openRight;
	}

	public boolean isOpenTop() {
		return openTop;
	}
	
	public String toString(){
		if(filled)
			return "" + pipeType + "w";
		return "" + pipeType;
	}
}
