package jbb.engine.pipes;

import javax.swing.ImageIcon;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.NPC;
import jbb.engine.Position;
import jbb.engine.Tile;

/**
 * The "enemy" NPC of PipeGame.
 * Water flows out of a starting pipe, 
 * trying to flow to an open Tile. Water 
 * hitting an open Tile is a leak, which 
 * ends the game.
 * @author Boris Ionine
 */
@SuppressWarnings("serial")
public class Water extends NPC{

	public static final int LIVES = 1;
	
	/**
	 * Constructor for Water.
	 * @param position
	 * @param board
	 */
	public Water(Position position, Board board) {
		super(new ImageIcon(), LIVES, position, board);
	}
	
	/**
	 * METHOD SHOULD NOT BE USED BY WATER.
	 * STUB.
	 * @param position
	 * @return position
	 * @Override
	 */
	public Position getNextPosition(Position position) throws IllegalArgumentException{
		return position;
	}

	/**
	 * Calculates the possible next Positions
	 * the Water will spread to and returns them in an array.
	 * @param position
	 * @return Position[] nextPosition
	 * @throws IllegalArgumentException
	 */
	public Position[] getNextPositions(Position position) throws IllegalArgumentException {
		//figure out the kind of Pipe/Tile the water's on right now:
		Tile tile = board.getItem(position);
		Position[] nextPositions = new Position[4];
		Tile[] adjTiles;
		if(tile instanceof Pipe){
			tile = (Pipe) board.getItem(position);
			adjTiles = tile.getAdjacentTiles(); 
			//check
			if(((Pipe) tile).isOpenLeft()){
				if(adjTiles[Tile.LEFT]!=null) 
					if(adjTiles[Tile.LEFT] instanceof Pipe)
					{
						Pipe p = (Pipe) adjTiles[Tile.LEFT];
						if(!p.isFilled())
							nextPositions[0] = adjTiles[Tile.LEFT].getPosition();
						else
							nextPositions[0] = null;	
					}
					else
						nextPositions[0] = adjTiles[Tile.LEFT].getPosition();
				else
					nextPositions[0] = null;
			}
			else
				nextPositions[0] = null;
			if(((Pipe) tile).isOpenBottom()){
				if(adjTiles[Tile.BOTTOM]!=null)
					if(adjTiles[Tile.BOTTOM] instanceof Pipe)
					{
						Pipe p = (Pipe) adjTiles[Tile.BOTTOM];
						if(!p.isFilled())
							nextPositions[1] = adjTiles[Tile.BOTTOM].getPosition();
						else
							nextPositions[1] = null;	
					}
					else
						nextPositions[1] = adjTiles[Tile.BOTTOM].getPosition();
				else
					nextPositions[1] = null;
			}
			else
				nextPositions[1] = null;
			if(((Pipe) tile).isOpenRight()){
				if(adjTiles[Tile.RIGHT]!=null)
					if(adjTiles[Tile.RIGHT] instanceof Pipe)
					{
						Pipe p = (Pipe) adjTiles[Tile.RIGHT];
						if(!p.isFilled())
							nextPositions[2] = adjTiles[Tile.RIGHT].getPosition();
						else
							nextPositions[2] = null;	
					}
					else
						nextPositions[2] = adjTiles[Tile.RIGHT].getPosition();
				else
					nextPositions[2] = null;
			}
			else
				nextPositions[2] = null;
			if(((Pipe) tile).isOpenTop()){
				if(adjTiles[Tile.TOP]!=null)
					if(adjTiles[Tile.TOP] instanceof Pipe)
					{
						Pipe p = (Pipe) adjTiles[Tile.TOP];
						if(!p.isFilled())
							nextPositions[3] = adjTiles[Tile.TOP].getPosition();
						else
							nextPositions[3] = null;	
					}
					else
						nextPositions[3] = adjTiles[Tile.TOP].getPosition();
				else
					nextPositions[3] = null;
			}
			else
				nextPositions[3] = null;
		}
		
		return nextPositions;
	}
	
	/**
	 * Method also not used by Water yet...may find a use for it later
	 * @return false
	 * @Override
	 */
	public boolean collidesWith(Avatar avatar) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * @return String 
	 */
	public String toString() {
		Pipe p = (Pipe) board.getItem(position);
		if(this.hasGoodie(position)){
			//Pipe p = (Pipe) board.getItem(position);
			return p.toString();
		}
		return "W";
	}
	
	/**
	 * Checks to see if the water is in a Pipe.
	 * @param position
	 * @return boolean
	 * @Override
	 */
	public boolean hasGoodie(Position position) {
		Tile tile = board.getItem(position);
		if (tile instanceof Pipe) {
			return true;
		}
		return false;
	}

}
