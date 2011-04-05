package jbb.engine.pipes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.Item;
import jbb.engine.Position;
import jbb.engine.Tile;
import jbb.engine.Wall;


/**
 * Board class for the Pipes game. 
 * Manages the pieces when a turn is played.
 * @author Boris Ionine
 */
public class PipeMap extends Board{
	private static final int MAP_ONE = 1;
	private static final int MAP_TWO = 2;
	private static final int MAP_THREE = 3;	
	public static final int WATER_START_TURN = 60;
	public static final int WIDTH = 15;
	public static final int HEIGHT = 15;
	public Position winningPosition;
	private int turnsUntilWater = WATER_START_TURN;
	private int numTurns = 0;
	private boolean runWater = false;
	private static int map=1;
	private static String mapName = "txt/pipeMap1.txt";

	
	public PipeMap(){
		super(WIDTH, HEIGHT);
		syncItemMapAndField(movableTiles);
	}
	
	@Override
	protected void populateItemMap() {
		Tile.setBlankImage(new ImageIcon("img/black-tile.png"));

		if(map == MAP_ONE){
			mapName = "txt/pipeMap1.txt";
			movableTiles.add(new Plumber(new Position(1,1), this));
			movableTiles.add(new Water(new Position(1,1), this));
			winningPosition = new Position(13,14);
		}
		else if(map == MAP_TWO){
			mapName = "txt/pipeMap2.txt";
			movableTiles.add(new Plumber(new Position(1,1), this));
			movableTiles.add(new Water(new Position(1,1), this));
			winningPosition = new Position(9,14);
		}
		else if(map == MAP_THREE){
			mapName = "txt/pipeMap3.txt";
			movableTiles.add(new Plumber(new Position(2,1), this));
			movableTiles.add(new Water(new Position(2,1), this));
			winningPosition = new Position(13,14);

		}

	Scanner s;
	try {
		s = new Scanner(new File(mapName));
		s.useDelimiter("\n");
		char [] tiles;
		int count = 0;
		while(s.hasNext()){
			String str = s.next();
			tiles = str.toCharArray();
		//	for(int row = 0; row<15; row++){
				for(int col =0; col<tiles.length; col++){
					if(tiles[col] == ('W')){
						itemMap[count][col] =new Wall(new Position(count,col),this);
					}
					else if(tiles[col] == ('w')){
						itemMap[count][col] =new Wall(new Position(count,col),this);
					}
					else if(tiles[col] == (' ')){
						itemMap[count][col] =new Tile(new Position(count,col),this);
					}
					else if(tiles[col] == ('q')){
						itemMap[count][col] = new Pipe(new Position(count,col),this, 'Q');
					}
					else if(str.equals(null)){
						itemMap[count][col] =new Tile(new Position(count,col),this);
					}
					
				}
				count++;
			}
			
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	
	@Override
	public void syncItemMapAndField(ArrayList<Avatar> movableTiles) {
		super.syncItemMapAndField(movableTiles);
		// the items should be seen rather than the water
		Position pos;
		for (int i = 0; i < movableTiles.size(); i++) {
			pos = movableTiles.get(i).getPosition();
			playingField[pos.getRow()][pos.getCol()] = itemMap[pos.getRow()][pos.getCol()];
		}
		setChanged();
		notifyObservers("update");
	}
	
	/**
	 * Overridden playTurn method for Pipes game. 
	 * Needed to be overridden completely largely due to the
	 * multiplying nature of the Water flowing through
	 * the Pipes.
	 * @throws GameOver
	 */
	@Override
	public void playTurn(Position position) {
		//stuff for undo------------------------------------------------
		//save the previous state for undo purposes:
		/*prevItemMaps.addLast(itemMap);
		ArrayList<Avatar> oldMovableTiles = new ArrayList<Avatar>(); 
		for(int t = 0; t < movableTiles.size(); t++){
			if(movableTiles.get(t) instanceof Plumber){
				oldMovableTiles.add(new Plumber((Plumber)movableTiles.get(t))); 
			}
			else if(movableTiles.get(t) instanceof Water){
				oldMovableTiles.add(new Water((Water)movableTiles.get(t))); 
			}
		}
		prevMovableTiles.addLast(oldMovableTiles);*/
		updateUndoLists();
		//------------------------------------------------end undo stuff
		
		numTurns++;
		turnsUntilWater--;
		//Hero is always the first element of the ArrayList
		Plumber plumber = (Plumber) movableTiles.get(0);
		
		Position movedPosition = plumber.getNextPosition(position);
		
		//check if moved to existing Pipe
		boolean itemPickedUp = plumber.hasGoodie(movedPosition); 
		if(!runWater)
		{
			if(itemPickedUp){ //player has clicked on a pipe
				((Item) itemMap[movedPosition.getRow()][movedPosition.getCol()]).pickedUp(plumber);
				//System.out.println("you rotated the pipe at:" + movedPosition.getRow() + "," + movedPosition.getCol());
				plumber.setPosition(movedPosition);
				syncItemMapAndField(movableTiles);
				if(turnsUntilWater > 0)
					turnsUntilWater++;
				return;			
			}
			else{ //plumber is placing a pipe
				plumber.setPosition(movedPosition);
				plumber.placePipe();
				plumber.acquireNextPipeType();
				syncItemMapAndField(movableTiles);
			}		
		}
		
		if(turnsUntilWater <= 0){
			Water water;
			Position[] nextWaterPositions;
			//need the current number of movable Tiles to loop over
			//int currentMovableTilesSize = movableTiles.size();
			//now to move the water...
			for(int i = 1; i < movableTiles.size(); i++){
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
					
					//boolean true to begin with, set to false within for loop if it's false	
					boolean allnull = true;
					
					for(int j = 0; j < nextWaterPositions.length; j++){
						if(nextWaterPositions[j] != null){
							allnull = false;
							int nextRow = nextWaterPositions[j].getRow();
							int nextCol = nextWaterPositions[j].getCol();
							//get the tile at this position
							Tile tile = itemMap[nextRow][nextCol];
							//if the tile is a pipe try to fill it
							if(tile instanceof Pipe){
								//for each possible opening, also check that the water is
								//coming from the appropriate place.
								if(!((Pipe) tile).isFilled()){
									if((((Pipe) tile).isOpenLeft()) &&
											(currentRow == nextRow) && (currentCol == nextCol - 1)){
										movableTiles.add(new Water(new Position(nextRow, nextCol),this));
										((Pipe) tile).fillUp();
									}
									else if((((Pipe) tile).isOpenBottom()) && 
											(currentRow == nextRow + 1) && (currentCol == nextCol)){
										movableTiles.add(new Water(new Position(nextRow, nextCol),this));
										((Pipe) tile).fillUp();
									}
									else if((((Pipe) tile).isOpenRight()) &&
											(currentRow == nextRow) && (currentCol == nextCol + 1)){
										movableTiles.add(new Water(new Position(nextRow, nextCol),this));
										((Pipe) tile).fillUp();
									}
									else if((((Pipe) tile).isOpenTop()) &&
											(currentRow == nextRow - 1) && (currentCol == nextCol)){
										movableTiles.add(new Water(new Position(nextRow, nextCol),this));
										((Pipe) tile).fillUp();
									}
									//if the pipe cannot be filled (water trying to enter a blocked wall)
									//it counts as a leak and you lose
									else{
										syncItemMapAndField(movableTiles);
										setChanged();
										notifyObservers("You have a leak: you lose");
										runWater = false;
										return;
									}
								}
								else{
									//syncItemMapAndField(movableTiles);
									runWater = false;
									return;
								}
								//syncItemMapAndField(movableTiles);
							}
							//if the tile is not a pipe the game ends with a win (winningTile) 
							//or a loss (any other non-pipe tile)
							else{
								movableTiles.add(new Water(new Position(nextRow, nextCol),this));

							}
						}
					}
					if(allnull){
						movableTiles.remove(water);
						//if(i < movableTiles.size()){
							//i++;
						//}
						if(movableTiles.size()<=1){
							runWater = false;
							setChanged();
							notifyObservers("You have created an infinite loop: you lose");
							return;
						}
						allnull = false;
						
					}
						
					
				}
				else{
					if (checkWin()) {
						syncItemMapAndField(movableTiles);
						if(lastLevel()){
							setChanged();
							notifyObservers("Congratulations: You win!");
						}
						nextLevel();
						runWater = false;
						return;
					}
					syncItemMapAndField(movableTiles);
					setChanged();
					notifyObservers("You have a leak: you lose");
					runWater = false;
					return;
				}
			}
			
			syncItemMapAndField(movableTiles);
			if (checkWin()) {
				syncItemMapAndField(movableTiles);
				if(lastLevel()){
					setChanged();
					notifyObservers("Congratulations: You win!");
				}
				nextLevel();
				runWater = false;
				return;
			}
		}
	}
	
	/**
	 * Returns the number of turns left until
	 * water starts to flow.
	 * @return turnsUntilWater
	 */
	public int getTurnsUntilWater(){
		return turnsUntilWater; 
	}
	
	public void runWaterPressed() {
		runWater = true;
		turnsUntilWater = 0;
		while(runWater){
			this.playTurn(new Position(1,1));
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
		runWater = false;
		turnsUntilWater = WATER_START_TURN;
		this.width = WIDTH;
		this.height = HEIGHT;
		playingField = new Tile[height][width];	
		itemMap = new Tile[height][width];
		movableTiles = new ArrayList<Avatar>();
		map=1;
		populateItemMap();

		syncItemMapAndField(movableTiles);
	}

	public void nextLevel() {
		numTurns = 0;
		runWater = false;
		turnsUntilWater = WATER_START_TURN;
		this.width = WIDTH;
		this.height = HEIGHT;
		playingField = new Tile[width][height];	
		itemMap = new Tile[width][height];
		movableTiles = new ArrayList<Avatar>();
		map++;
		populateItemMap();
		syncItemMapAndField(movableTiles);
	}
	
	protected boolean lastLevel(){
		if(map == MAP_ONE){
			return false;
		}
		else if(map == MAP_TWO){
			return false;
			}
		else if(map == MAP_THREE ){
			return true;
			}
		return false;
		
	}
}

