package jbb.engine.pipes;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.GameOver;
import jbb.engine.Item;
import jbb.engine.Position;
import jbb.engine.Tile;
import jbb.engine.Wall;

/**
 * 
 * @author Boris Ionine
 *
 */
public class PipeMap extends Board{
	public static final int WATER_START_TURN = 10;
	public static final int WIDTH = 7;
	public static final int HEIGHT = 7;
	public Position winningPosition = new Position(6,1);
	private int numTurns = 0;
	
	public PipeMap(){
		super(WIDTH, HEIGHT);
		movableTiles.add(new Plumber(new Position(1,1), this));
		movableTiles.add(new Water(new Position(2,1), this));
		syncItemMapAndField(movableTiles);
	}
	
	@Override
	protected void populateItemMap() {
		Tile.setBlankImage(new ImageIcon("img/black-tile.png"));
		itemMap[0][0] = new Wall(new Position(0,0),this);
		itemMap[0][1] = new Wall(new Position(0,1),this);
		itemMap[0][2] = new Wall(new Position(0,2),this);
		itemMap[0][3] = new Wall(new Position(0,3),this);
		itemMap[0][4] = new Wall(new Position(0,4),this);
		itemMap[0][5] = new Wall(new Position(0,5),this);
		itemMap[0][6] = new Wall(new Position(0,6),this);
		
		itemMap[1][0] = new Wall(new Position(1,0),this);
		itemMap[1][1] = new Tile(new Position(1,1),this);
		itemMap[1][2] = new Tile(new Position(1,2),this);
		itemMap[1][3] = new Tile(new Position(1,3),this);
		itemMap[1][4] = new Tile(new Position(1,4),this);
		itemMap[1][5] = new Tile(new Position(1,5),this);
		itemMap[1][6] = new Wall(new Position(1,6),this);
		
		itemMap[2][0] = new Wall(new Position(2,0),this);
		itemMap[2][1] = new Pipe(new Position(2,1),this, 'Q');
		itemMap[2][2] = new Tile(new Position(2,2),this);
		itemMap[2][3] = new Tile(new Position(2,3),this);
		itemMap[2][4] = new Tile(new Position(2,4),this);
		itemMap[2][5] = new Tile(new Position(2,5),this);
		itemMap[2][6] = new Wall(new Position(2,6),this);

		itemMap[3][0] = new Wall(new Position(3,0),this);
		itemMap[3][1] = new Tile(new Position(3,1),this);
		itemMap[3][2] = new Tile(new Position(3,2),this);
		itemMap[3][3] = new Tile(new Position(3,3),this);
		itemMap[3][4] = new Tile(new Position(3,4),this);
		itemMap[3][5] = new Tile(new Position(3,5),this);
		itemMap[3][6] = new Wall(new Position(3,6),this);
		
		itemMap[4][0] = new Wall(new Position(4,0),this);
		itemMap[4][1] = new Tile(new Position(4,1),this);
		itemMap[4][2] = new Tile(new Position(4,2),this);
		itemMap[4][3] = new Tile(new Position(4,3),this);
		itemMap[4][4] = new Tile(new Position(4,4),this);
		itemMap[4][5] = new Tile(new Position(4,5),this);
		itemMap[4][6] = new Wall(new Position(4,6),this);
		
		itemMap[5][0] = new Wall(new Position(5,0),this);
		itemMap[5][1] = new Tile(new Position(5,1),this);
		itemMap[5][2] = new Tile(new Position(5,2),this);
		itemMap[5][3] = new Tile(new Position(5,3),this);
		itemMap[5][4] = new Tile(new Position(5,4),this);
		itemMap[5][5] = new Tile(new Position(5,5),this);
		itemMap[5][6] = new Wall(new Position(5,6),this);
		
		itemMap[6][0] = new Wall(new Position(6,0),this);
		itemMap[6][1] = new Tile(new Position(6,1),this);
		itemMap[6][2] = new Wall(new Position(6,2),this);
		itemMap[6][3] = new Wall(new Position(6,3),this);
		itemMap[6][4] = new Wall(new Position(6,4),this);
		itemMap[6][5] = new Wall(new Position(6,5),this);
		itemMap[6][6] = new Wall(new Position(6,6),this);
	}
	
	@Override
	public void syncItemMapAndField(ArrayList<Avatar> movableTiles) {
		super.syncItemMapAndField(movableTiles);
		// the items should be seen rather than the water
		Position pos;
		for (int i = 1; i < movableTiles.size(); i++) {
			pos = movableTiles.get(i).getPosition();
			playingField[pos.getRow()][pos.getCol()] = itemMap[pos.getRow()][pos.getCol()];
		}
	}
	
	@Override
	public void playTurn(Position position) throws GameOver{
		numTurns++;
		//Hero is always the first element of the ArrayList
		Plumber plumber = (Plumber) movableTiles.get(0);
		
		Position movedPosition = plumber.getNextPosition(position);
		
		//check if moved to existing Pipe
		boolean itemPickedUp = plumber.hasGoodie(movedPosition); 
		if(itemPickedUp){
			((Item) itemMap[movedPosition.getRow()][movedPosition.getCol()]).pickedUp(plumber);
			System.out.println("you rotated the pipe at:" + movedPosition.getRow() + "," + movedPosition.getCol());
			plumber.setPosition(movedPosition);
			syncItemMapAndField(movableTiles);
		}
		else{
			plumber.setPosition(movedPosition);
			plumber.placePipe();
			syncItemMapAndField(movableTiles);
		}		
		
		if(numTurns > WATER_START_TURN){
			Water water;
			Position[] nextWaterPositions;
			//need the current number of movable Tiles to loop over
			int currentMovableTilesSize = movableTiles.size();
			//now to move the water...
			for(int i = 1; i < currentMovableTilesSize; i++){
				water = (Water) movableTiles.get(i); 
				int currentRow = water.getPosition().getRow();
				int currentCol = water.getPosition().getCol();
				//check if the water has hit a pipe
				if(water.hasGoodie(water.getPosition())){
					((Item) itemMap[currentRow][currentCol]).pickedUp(water);
					//pass current position since water movement does not depend on plumber
					nextWaterPositions = water.getNextPositions(water.getPosition());
					//water.setPosition(nextWaterPos);
					//loop over possible next positions for water 
					for(int j = 0; j < nextWaterPositions.length; j++){
						if(nextWaterPositions[j] != null){
							int nextRow = nextWaterPositions[j].getRow();
							int nextCol = nextWaterPositions[j].getCol();
							//get the tile at this position
							Tile tile = itemMap[nextRow][nextCol];
							//if the tile is a pipe try to fill it
							if(tile instanceof Pipe){
								if(((Pipe) tile).isOpenLeft()){
									//((Item) itemMap[nextRow][nextCol]).pickedUp(water);
									movableTiles.add(new Water(new Position(nextRow, nextCol),this));
								}
								else if(((Pipe) tile).isOpenBottom()){
									movableTiles.add(new Water(new Position(nextRow, nextCol),this));
								}
								else if(((Pipe) tile).isOpenRight()){
									movableTiles.add(new Water(new Position(nextRow, nextCol),this));
								}
								else if(((Pipe) tile).isOpenTop()){
									movableTiles.add(new Water(new Position(nextRow, nextCol),this));
								}
								//if the pipe cannot be filled (water trying to enter a blocked wall)
								//it counts as a leak and you lose
								else{
									throw new GameOver("YOU LOSE (YOU HAVE A LEAK)!");
								}
							}
							//if the tile is not a pipe the game ends with a win (winningTile) 
							//or a loss (any other non-pipe tile)
							else{
								if (checkWin()) {
									throw new GameOver("YOU WIN!");
								}
								throw new GameOver("YOU LOSE (YOU HAVE A LEAK)!");
							}
						}
					}
						
					
				}
				else
					throw new GameOver("YOU LOSE (YOU HAVE A LEAK)!");
			}
			
			syncItemMapAndField(movableTiles);
			if (checkWin()) {
				throw new GameOver("YOU WIN!");
			}
		}
	}

	@Override
	public void resetPlayingField() {
		// TODO Auto-generated method stub
	}

	@Override
	protected boolean checkWin() {
		Tile winningTile = playingField[winningPosition.getRow()][winningPosition.getCol()];
		if(winningTile instanceof Pipe) {
			return ((Pipe) winningTile).isFilled();
		} return false;
	}
	
	@Override
	public void restartGame() {
		numTurns = 0;
		this.width = WIDTH;
		this.height = HEIGHT;
		playingField = new Tile[width][height];	
		itemMap = new Tile[width][height];
		movableTiles = new ArrayList<Avatar>();
		populateItemMap();
		movableTiles.add(new Plumber(new Position(1,1), this));
		movableTiles.add(new Water(new Position(2,1), this));
		syncItemMapAndField(movableTiles);
	}
}

