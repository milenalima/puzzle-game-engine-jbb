/**
 * The MouseLand Class creates the playing field for the user to play on.
 * 
 * @author Bruno Colantonio
 */

package jbb.engine.mouseland;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.Position;
import jbb.engine.Tile;
import jbb.engine.Wall;

public class MouseLand extends Board{
	private static final int MAP_ONE = 1;
	private static final int MAP_TWO = 2;
	private static final int MAP_THREE = 3;	
	public static final int WIDTH = 15;
	public static final int HEIGHT = 15;
	private static int map = 1;
	
/**
 * the MouseLand constructor initialises a MouseHero and three Mouse instances on
 * the playing field 	
 */

	public MouseLand(InputStream[] is) {
		super(WIDTH, HEIGHT, is);
		syncItemMapAndField(movableTiles);
	}

/**
 * the populateItemMap method fills all the times with a blank tile or a wall,
 * if the tile is blank then a Mouse or MouseHero can be made and if able to move 
 * on those tiles.	
 * 
 */
	@Override
	protected void populateItemMap() {
		if(map == MAP_ONE){
			movableTiles.add(new MouseHero(new Position(14,7), this));
			movableTiles.add(new Mouse(new Position(1,1), this));
			movableTiles.add(new Mouse(new Position(7,7), this));
			movableTiles.add(new Mouse(new Position(1,13), this));
			movableTiles.add(new Mouse(new Position(13,13), this));
			}
		else if(map == MAP_TWO){
			movableTiles.add(new MouseHero(new Position(14,1), this));
			movableTiles.add(new Mouse(new Position(1,3), this));
			movableTiles.add(new Mouse(new Position(10,11), this));
			movableTiles.add(new Mouse(new Position(1,13), this));
			}
		else if(map == MAP_THREE){
			movableTiles.add(new MouseHero(new Position(1,0), this));
			movableTiles.add(new Mouse(new Position(13,1), this));
			movableTiles.add(new Mouse(new Position(7,7), this));
			movableTiles.add(new Mouse(new Position(1,13), this));
			}
				
		
		Scanner s;
		try {
			is[map-1].reset();
		} catch (IOException e) {
			// shouldn't happen
		}
		s = new Scanner(is[map-1]);
		s.useDelimiter("\n");
		char [] tiles;
		int count = 0;
		while(s.hasNext()){
			String str = s.next();
			tiles = str.toCharArray();
			// for(int row = 0; row<15; row++){
				for(int col =0; col<tiles.length-1; col++){
					if(tiles[col] == ('W')){
						itemMap[count][col] =new Wall(new Position(count,col),this);
					}
					else if(tiles[col] == ('w')){
						itemMap[count][col] =new Wall(new Position(count,col),this);
					}
					else if(tiles[col] == (' ')){
						itemMap[count][col] =new Tile(new Position(count,col),this);
					}
					else if(tiles[col] == ('f')){
						itemMap[count][col] =new Tile(new Position(count,col),this);
					}
					else {
						itemMap[count][col] =new Wall(new Position(count,col),this);
					}
					
				}
				count++;
			}

		
	}

/**
 * The resetPlayingField method is called when a MouseHero and a Mouse collide.
 * The method repositions all the Avatars are there original positions.
 */
	@Override
	public void resetPlayingField() {
		if(map==MAP_ONE){
			movableTiles.get(0).setPosition(new Position(14,7));
			//Check to see if the mouse are still alive, if yes they are created again,
			//if they are dead they will remain dead.
			if(movableTiles.get(1).getLives() == 1)
				movableTiles.get(1).setPosition(new Position(1,1));
			if(movableTiles.get(2).getLives() == 1)
				movableTiles.get(2).setPosition(new Position(7,7));
			if(movableTiles.get(3).getLives() == 1)
				movableTiles.get(3).setPosition(new Position(1,13));
			if(movableTiles.get(4).getLives() == 1)
				movableTiles.get(4).setPosition(new Position(13,13));
		}
		else if(map==MAP_TWO){
			movableTiles.get(0).setPosition(new Position(14,1));
			//Check to see if the mouse are still alive, if yes they are created again,
			//if they are dead they will remain dead.
			if(movableTiles.get(1).getLives() == 1)
				movableTiles.get(1).setPosition(new Position(1,1));
			if(movableTiles.get(2).getLives() == 1)
				movableTiles.get(2).setPosition(new Position(10,11));
			if(movableTiles.get(3).getLives() == 1)
				movableTiles.get(3).setPosition(new Position(1,8));
		}
		else if(map==MAP_THREE){
			movableTiles.get(0).setPosition(new Position(1,0));
			//Check to see if the mouse are still alive, if yes they are created again,
			//if they are dead they will remain dead.
			if(movableTiles.get(1).getLives() == 1)
				movableTiles.get(1).setPosition(new Position(1,13));
			if(movableTiles.get(2).getLives() == 1)
				movableTiles.get(2).setPosition(new Position(7,7));
			if(movableTiles.get(3).getLives() == 1)
				movableTiles.get(3).setPosition(new Position(1,13));
		}
	}

	
/**
 * The checkWin method returns true if the game is finished, the game is finished when the
 * MouseHero gets to the finish line, which is located at tile position [1][9].
 * 
 * @return true if the use has won the game, otherwise false.
 */
	@Override
	protected boolean checkWin() {
		//Create the End Tile and if the MouseHero has the same row and column 
		//then her wins.
		Position endPos = new Position(0,0);
		if(map==MAP_ONE){
			endPos = new Position(0,7);
		}
		if(map==MAP_TWO){
			endPos = new Position(1,14);
		}
		if(map==MAP_THREE){
			endPos = new Position(13,14);
		}

		Position heroPos = this.getHero().getPosition();
		int heroCol = heroPos.getCol();
		int heroRow = heroPos.getRow();
		int endRow = endPos.getRow();
		int endCol = endPos.getCol();
		
		if ((heroCol == endCol) && (heroRow == endRow)){
			return true;
		}
		return false;
	}

	@Override
	public void restartGame() {
		this.width = WIDTH;
		this.height = HEIGHT;
		playingField = new Tile[width][height];	
		itemMap = new Tile[width][height];
		movableTiles = new ArrayList<Avatar>();
		map=MAP_ONE;
		populateItemMap();
		syncItemMapAndField(movableTiles);
	}
	
	public void nextLevel() {
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
		else if(map == MAP_THREE){
			return true;
			}
		return false;
		
	}
}
