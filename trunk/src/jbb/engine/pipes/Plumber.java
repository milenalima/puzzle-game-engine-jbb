package jbb.engine.pipes;

import javax.swing.ImageIcon;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.Hero;
import jbb.engine.Position;
import jbb.engine.Tile;

public class Plumber extends Hero{
	private char nextPipeType;
	public static final int LIVES = 3;
	public Plumber(Position position, Board board) {
		super(new ImageIcon(), LIVES, position, board);
		
		acquireNextPipeType();
	}
	
	//acquires the next pipe 
	public void acquireNextPipeType(){
		//TODO should randomly generate the type of the next Pipe
		nextPipeType = 'I';
	}
	
	public char getNextPipeType(){
		return nextPipeType;
	}
	
	public void setNextPipeType(char nextPipeType){
		this.nextPipeType = nextPipeType;
	}
	
	public void placePipe(){
		//place the next Pipe according to the nextPipeType stored
		this.board.placeItem(new Pipe(position, board, nextPipeType));
		getNextPipeType(); 
	}

	@Override
	public boolean collidesWith(Avatar avatar) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasGoodie(Position position) {
		// TODO Auto-generated method stub
		Tile tile = board.getTile(position);
		if (tile instanceof Pipe) {
			return true;
		}
		return false;
	}
	
	@Override
	public Position getNextPosition(Position position) throws IllegalArgumentException{
		if (position.getRow() < 0 || position.getCol() < 0
				|| position.getRow() >= board.getHeight() || position.getCol() >= board.getWidth())
			throw new IllegalArgumentException("Not a possible move");
		else if (!board.getTile(position).getAccessible()) 
			throw new IllegalArgumentException("Not a possible move");
		else
			return position;		
	}
	
	public String toString(){
		return "p";
	}
}
