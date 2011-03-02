package jbb.engine.pipes;

import javax.swing.ImageIcon;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.NPC;
import jbb.engine.Position;
import jbb.engine.Tile;

public class Water extends NPC{

	public static final int LIVES = 1;
	
	public Water(Position position, Board board) {
		super(new ImageIcon(), LIVES, position, board);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Position getNextPosition(Position position) throws IllegalArgumentException{
		return position;
	}

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
					nextPositions[0] = adjTiles[Tile.LEFT].getPosition();
				else
					nextPositions[0] = null;
			}
			else
				nextPositions[0] = null;
			if(((Pipe) tile).isOpenBottom()){
				if(adjTiles[Tile.BOTTOM]!=null)
					nextPositions[1] = adjTiles[Tile.BOTTOM].getPosition();
				else
					nextPositions[1] = null;
			}
			else
				nextPositions[1] = null;
			if(((Pipe) tile).isOpenRight()){
				if(adjTiles[Tile.RIGHT]!=null)
					nextPositions[2] = adjTiles[Tile.RIGHT].getPosition();
				else
					nextPositions[2] = null;
			}
			else
				nextPositions[2] = null;
			if(((Pipe) tile).isOpenTop()){
				if(adjTiles[Tile.TOP]!=null)
					nextPositions[3] = adjTiles[Tile.TOP].getPosition();
				else
					nextPositions[3] = null;
			}
			else
				nextPositions[3] = null;
		}
		
		return nextPositions;
	}
	
	@Override
	public boolean collidesWith(Avatar avatar) {
		// TODO Auto-generated method stub
		return false;
	}

	public String toString() {
		Pipe p = (Pipe) board.getItem(position);
		if(this.hasGoodie(position)){
			//Pipe p = (Pipe) board.getItem(position);
			return p.toString();
		}
		return "W";
	}
	@Override
	public boolean hasGoodie(Position position) {
		Tile tile = board.getItem(position);
		if (tile instanceof Pipe) {
			return true;
		}
		return false;
	}

}
