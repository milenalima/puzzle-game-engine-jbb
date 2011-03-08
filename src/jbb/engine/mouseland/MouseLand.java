/**
 * The MouseLand Class creates the playing field for the user to play on.
 * 
 * @author Bruno Colantonio
 */

package jbb.engine.mouseland;

import jbb.engine.Board;
import jbb.engine.Position;
import jbb.engine.Tile;
import jbb.engine.Wall;

public class MouseLand extends Board{
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
/**
 * the MouseLand constructor initialises a MouseHero and three Mouse instances on
 * the playing field 	
 */

	public MouseLand() {
		super(WIDTH, HEIGHT);
		Tile.setBlankColor(Tile.BLACK);
		movableTiles.add(new MouseHero(new Position(9,1), this));
		movableTiles.add(new Mouse(new Position(1,1), this));
		movableTiles.add(new Mouse(new Position(8,8), this));
		movableTiles.add(new Mouse(new Position(1,8), this));
		syncItemMapAndField(movableTiles);
	}

/**
 * the populateItemMap method fills all the times with a blank tile or a wall,
 * if the tile is blank then a Mouse or MouseHero can be made and if able to move 
 * on those tiles.	
 * 
 * Initial Playing Field
 * 
 * X X X X X X X X X X
 * X e			   e	
 * X   X X	   X X   X	
 * X   X X     X X   X
 * X                 X  
 * X   X X     X X   X
 * X   X X     X X   X 
 * X   X       X X   X
 * X			   e X 
 * X m X X X X X X X X
 * 
 */
	@Override
	protected void populateItemMap() {
		itemMap[0][0] = new Wall(new Position(0,0),this);
		itemMap[0][1] = new Wall(new Position(0,1),this);
		itemMap[0][2] = new Wall(new Position(0,2),this);
		itemMap[0][3] = new Wall(new Position(0,3),this);
		itemMap[0][4] = new Wall(new Position(0,4),this);
		itemMap[0][5] = new Wall(new Position(0,5),this);
		itemMap[0][6] = new Wall(new Position(0,6),this);
		itemMap[0][7] = new Wall(new Position(0,7),this);
		itemMap[0][8] = new Wall(new Position(0,8),this);
		itemMap[0][9] = new Wall(new Position(0,9),this);
		
		itemMap[1][0] = new Wall(new Position(1,0),this);
		itemMap[1][1] = new Tile(new Position(1,1),this);
		itemMap[1][2] = new Tile(new Position(1,2),this);
		itemMap[1][3] = new Tile(new Position(1,3),this);
		itemMap[1][4] = new Tile(new Position(1,4),this);
		itemMap[1][5] = new Tile(new Position(1,5),this);
		itemMap[1][6] = new Tile(new Position(1,6),this);
		itemMap[1][7] = new Tile(new Position(1,7),this);
		itemMap[1][8] = new Tile(new Position(1,8),this);
		itemMap[1][9] = new Tile(new Position(1,9),this);
		
		itemMap[2][0] = new Wall(new Position(2,0),this);
		itemMap[2][1] = new Tile(new Position(2,1),this);
		itemMap[2][2] = new Wall(new Position(2,2),this);
		itemMap[2][3] = new Wall(new Position(2,3),this);
		itemMap[2][4] = new Tile(new Position(2,4),this);
		itemMap[2][5] = new Tile(new Position(2,5),this);
		itemMap[2][6] = new Wall(new Position(2,6),this);
		itemMap[2][7] = new Wall(new Position(2,7),this);
		itemMap[2][8] = new Tile(new Position(2,8),this);
		itemMap[2][9] = new Wall(new Position(2,9),this);
		
		itemMap[3][0] = new Wall(new Position(3,0),this);
		itemMap[3][1] = new Tile(new Position(3,1),this);
		itemMap[3][2] = new Wall(new Position(3,2),this);
		itemMap[3][3] = new Wall(new Position(3,3),this);
		itemMap[3][4] = new Tile(new Position(3,4),this);
		itemMap[3][5] = new Tile(new Position(3,5),this);
		itemMap[3][6] = new Wall(new Position(3,6),this);
		itemMap[3][7] = new Wall(new Position(3,7),this);
		itemMap[3][8] = new Tile(new Position(3,8),this);
		itemMap[3][9] = new Wall(new Position(3,9),this);
		
		itemMap[4][0] = new Wall(new Position(4,0),this);
		itemMap[4][1] = new Tile(new Position(4,1),this);
		itemMap[4][2] = new Tile(new Position(4,2),this);
		itemMap[4][3] = new Tile(new Position(4,3),this);
		itemMap[4][4] = new Tile(new Position(4,4),this);
		itemMap[4][5] = new Tile(new Position(4,5),this);
		itemMap[4][6] = new Tile(new Position(4,6),this);
		itemMap[4][7] = new Tile(new Position(4,7),this);
		itemMap[4][8] = new Tile(new Position(4,8),this);
		itemMap[4][9] = new Wall(new Position(4,9),this);
	
		itemMap[5][0] = new Wall(new Position(5,0),this);
		itemMap[5][1] = new Tile(new Position(5,1),this);
		itemMap[5][2] = new Wall(new Position(5,2),this);
		itemMap[5][3] = new Wall(new Position(5,3),this);
		itemMap[5][4] = new Tile(new Position(5,4),this);
		itemMap[5][5] = new Tile(new Position(5,5),this);
		itemMap[5][6] = new Wall(new Position(5,6),this);
		itemMap[5][7] = new Wall(new Position(5,7),this);
		itemMap[5][8] = new Tile(new Position(5,8),this);
		itemMap[5][9] = new Wall(new Position(5,9),this);
		
		itemMap[6][0] = new Wall(new Position(6,0),this);
		itemMap[6][1] = new Tile(new Position(6,1),this);
		itemMap[6][2] = new Wall(new Position(6,2),this);
		itemMap[6][3] = new Wall(new Position(6,3),this);
		itemMap[6][4] = new Tile(new Position(6,4),this);
		itemMap[6][5] = new Tile(new Position(6,5),this);
		itemMap[6][6] = new Wall(new Position(6,6),this);
		itemMap[6][7] = new Wall(new Position(6,7),this);
		itemMap[6][8] = new Tile(new Position(6,8),this);
		itemMap[6][9] = new Wall(new Position(6,9),this);
		
		itemMap[7][0] = new Wall(new Position(7,0),this);
		itemMap[7][1] = new Tile(new Position(7,1),this);
		itemMap[7][2] = new Wall(new Position(7,2),this);
		itemMap[7][3] = new Tile(new Position(7,3),this);
		itemMap[7][4] = new Tile(new Position(7,4),this);
		itemMap[7][5] = new Tile(new Position(7,5),this);
		itemMap[7][6] = new Wall(new Position(7,6),this);
		itemMap[7][7] = new Wall(new Position(7,7),this);
		itemMap[7][8] = new Tile(new Position(7,8),this);
		itemMap[7][9] = new Wall(new Position(7,9),this);
		
		itemMap[8][0] = new Wall(new Position(8,0),this);
		itemMap[8][1] = new Tile(new Position(8,1),this);
		itemMap[8][2] = new Tile(new Position(8,2),this);
		itemMap[8][3] = new Tile(new Position(8,3),this);
		itemMap[8][4] = new Tile(new Position(8,4),this);
		itemMap[8][5] = new Tile(new Position(8,5),this);
		itemMap[8][6] = new Tile(new Position(8,6),this);
		itemMap[8][7] = new Tile(new Position(8,7),this);
		itemMap[8][8] = new Tile(new Position(8,8),this);
		itemMap[8][9] = new Wall(new Position(8,9),this);
	
		itemMap[9][0] = new Wall(new Position(9,0),this);
		itemMap[9][1] = new Tile(new Position(9,1),this);
		itemMap[9][2] = new Wall(new Position(9,2),this);
		itemMap[9][3] = new Wall(new Position(9,3),this);
		itemMap[9][4] = new Wall(new Position(9,4),this);
		itemMap[9][5] = new Wall(new Position(9,5),this);
		itemMap[9][6] = new Wall(new Position(9,6),this);
		itemMap[9][7] = new Wall(new Position(9,7),this);
		itemMap[9][8] = new Wall(new Position(9,8),this);
		itemMap[9][9] = new Wall(new Position(9,9),this);
		
	}

/**
 * The resetPlayingField method is called when a MouseHero and a Mouse collide.
 * The method repositions all the Avatars are there original positions.
 */
	@Override
	public void resetPlayingField() {
		movableTiles.get(0).setPosition(new Position(9,1));
		//Check to see if the mouse are still alive, if yes they are created again,
		//if they are dead they will remain dead.
		if(movableTiles.get(1).getLives() == 1)
			movableTiles.get(1).setPosition(new Position(1,1));
		if(movableTiles.get(2).getLives() == 1)
			movableTiles.get(2).setPosition(new Position(8,8));
		if(movableTiles.get(3).getLives() == 1)
			movableTiles.get(3).setPosition(new Position(1,8));
		System.out.print("\nThe mouse caught you! \n\nRESTART!\n\n");
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
		Position endPos = new Position(1,9);
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
}
