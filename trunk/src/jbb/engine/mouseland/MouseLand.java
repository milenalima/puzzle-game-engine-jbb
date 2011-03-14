/**
 * The MouseLand Class creates the playing field for the user to play on.
 * 
 * @author Bruno Colantonio
 */

package jbb.engine.mouseland;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.Position;
import jbb.engine.Tile;
import jbb.engine.Wall;

public class MouseLand extends Board{
	public static final int WIDTH = 15;
	public static final int HEIGHT = 15;
/**
 * the MouseLand constructor initialises a MouseHero and three Mouse instances on
 * the playing field 	
 */

	public MouseLand() {
		super(WIDTH, HEIGHT);
		movableTiles.add(new MouseHero(new Position(14,1), this));
		movableTiles.add(new Mouse(new Position(1,3), this));
		movableTiles.add(new Mouse(new Position(10,11), this));
		movableTiles.add(new Mouse(new Position(1,13), this));
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
		Tile.setBlankImage(new ImageIcon("img/white-tile.png"));
		//row 0
		for(int col =0;col<=14;col++){
			itemMap[0][col] = new Wall(new Position(0,col),this);
		}
		//row 1
		itemMap[1][0] = new Wall(new Position(1,0),this);
		itemMap[1][1] = new Tile(new Position(1,1),this);
		itemMap[1][10] = new Tile(new Position(1,10),this);
		itemMap[1][13] = new Tile(new Position(1,13),this);
		itemMap[1][14] = new Tile(new Position(1,14),this);

		for(int col =2;col<=4;col++){
			itemMap[1][col] = new Tile(new Position(1,col),this);
		}
		for(int col =5;col<=7;col++){
			itemMap[1][col] = new Wall(new Position(1,col),this);
		}
		for(int col =8;col<=13;col++){
			itemMap[1][col] = new Tile(new Position(1,col),this);
		}

		//row 2
		itemMap[2][0] = new Wall(new Position(2,0),this);
		itemMap[2][1] = new Tile(new Position(2,1),this);
		itemMap[2][7] = new Tile(new Position(2,7),this);
		itemMap[2][10] = new Tile(new Position(2,10),this);
		itemMap[2][9] = new Wall(new Position(2,9),this);
		itemMap[2][11] = new Wall(new Position(2,11),this);
		itemMap[2][12] = new Tile(new Position(2,12),this);
		itemMap[2][13] = new Tile(new Position(2,13),this);
		itemMap[2][14] = new Wall(new Position(2,14),this);

		for(int col =2;col<=3;col++){
			itemMap[2][col] = new Wall(new Position(2,col),this);
		}
		for(int col =4;col<=8;col++){
			itemMap[2][col] = new Tile(new Position(2,col),this);
		}


		//row 3
		itemMap[3][0] = new Wall(new Position(3,0),this);
		itemMap[3][1] = new Tile(new Position(3,1),this);
		itemMap[3][4] = new Wall(new Position(3,4),this);
		itemMap[3][5] = new Wall(new Position(3,5),this);
		itemMap[3][6] = new Wall(new Position(3,6),this);
		itemMap[3][7] = new Wall(new Position(3,7),this);
		itemMap[3][8] = new Tile(new Position(3,8),this);
		itemMap[3][9] = new Wall(new Position(3,9),this);
		itemMap[3][10] = new Wall(new Position(3,10),this);
		itemMap[3][13] = new Tile(new Position(3,13),this);
		itemMap[3][14] = new Wall(new Position(3,14),this);

		for(int col =2;col<=3;col++){
			itemMap[3][col] = new Tile(new Position(3,col),this);
		}

		for(int col =11;col<=12;col++){
			itemMap[3][col] = new Tile(new Position(3,col),this);
		}
		//row 4
		itemMap[4][0] = new Wall(new Position(4,0),this);
		itemMap[4][1] = new Wall(new Position(4,1),this);
		itemMap[4][2] = new Wall(new Position(4,2),this);
		itemMap[4][3] = new Tile(new Position(4,3),this);
		itemMap[4][4] = new Wall(new Position(4,4),this);

		itemMap[4][12] = new Wall(new Position(4,12),this);
		itemMap[4][13] = new Tile(new Position(4,13),this);
		itemMap[4][14] = new Wall(new Position(4,14),this);


		for(int col =5;col<=11;col++){
			itemMap[4][col] = new Tile(new Position(4,col),this);
		}
		//row 5
		itemMap[5][0] = new Wall(new Position(5,0),this);
		itemMap[5][1] = new Tile(new Position(5,1),this);
		itemMap[5][2] = new Wall(new Position(5,2),this);
		itemMap[5][3] = new Tile(new Position(5,3),this);
		itemMap[5][4] = new Wall(new Position(5,4),this);
		itemMap[5][5] = new Tile(new Position(5,5),this);
		itemMap[5][7] = new Tile(new Position(5,7),this);
		itemMap[5][10] = new Tile(new Position(5,10),this);
		itemMap[5][11] = new Wall(new Position(5,11),this);
		itemMap[5][14] = new Wall(new Position(5,14),this);

		for(int col =6;col<=9;col++){
			itemMap[5][col] = new Wall(new Position(5,col),this);
		}
		for(int col =12;col<=13;col++){
			itemMap[5][col] = new Tile(new Position(5,col),this);
		}

		//row 6
		itemMap[6][0] = new Wall(new Position(6,0),this);
		itemMap[6][7] = new Tile(new Position(6,7),this);
		itemMap[6][8] = new Wall(new Position(6,8),this);
		itemMap[6][9] = new Tile(new Position(6,9),this);
		itemMap[6][10] = new Tile(new Position(6,10),this);
		itemMap[6][11] = new Wall(new Position(6,11),this);
		itemMap[6][12] = new Tile(new Position(6,12),this);
		itemMap[6][13] = new Wall(new Position(6,13),this);
		itemMap[6][14] = new Wall(new Position(6,14),this);

		for(int col =1;col<=6;col++){
			itemMap[6][col] = new Tile(new Position(6,col),this);
		}
		//row 7
		itemMap[7][0] = new Wall(new Position(7,0),this);
		itemMap[7][1] = new Tile(new Position(7,1),this);
		itemMap[7][2] = new Wall(new Position(7,2),this);
		itemMap[7][3] = new Wall(new Position(7,3),this);
		itemMap[7][4] = new Wall(new Position(7,4),this);
		itemMap[7][5] = new Tile(new Position(7,5),this);
		itemMap[7][6] = new Wall(new Position(7,6),this);
		itemMap[7][13] = new Wall(new Position(7,13),this);
		itemMap[7][14] = new Wall(new Position(7,14),this);

		for(int col =7;col<=12;col++){
			itemMap[7][col] = new Tile(new Position(7,col),this);
		}

		//row 8
		itemMap[8][0] = new Wall(new Position(8,0),this);
		itemMap[8][6] = new Wall(new Position(8,6),this);
		itemMap[8][7] = new Tile(new Position(8,7),this);
		itemMap[8][8] = new Wall(new Position(8,8),this);
		itemMap[8][9] = new Tile(new Position(8,9),this);
		itemMap[8][14] = new Wall(new Position(8,14),this);
		for(int col =1;col<=5;col++){
			itemMap[8][col] = new Tile(new Position(8,col),this);
		}

		for(int col =10;col<=11;col++){
			itemMap[8][col] = new Wall(new Position(8,col),this);
		}
		for(int col =12;col<=13;col++){
			itemMap[8][col] = new Tile(new Position(8,col),this);
		}
		//row 9
		itemMap[9][0] = new Wall(new Position(9,0),this);
		itemMap[9][4] = new Tile(new Position(9,4),this);
		itemMap[9][10] = new Wall(new Position(9,10),this);
		itemMap[9][13] = new Tile(new Position(9,13),this);
		itemMap[9][14] = new Wall(new Position(9,14),this);
		for(int col =1;col<=3;col++){
			itemMap[9][col] = new Wall(new Position(9,col),this);
		}
		for(int col =5;col<=6;col++){
			itemMap[9][col] = new Wall(new Position(9,col),this);
		}
		for(int col =7;col<=9;col++){
			itemMap[9][col] = new Tile(new Position(9,col),this);
		}
		for(int col =11;col<=12;col++){
			itemMap[9][col] = new Wall(new Position(9,col),this);
		}
		//row 10
		itemMap[10][0] = new Wall(new Position(10,0),this);
		itemMap[10][8] = new Tile(new Position(10,8),this);
		itemMap[10][14] = new Wall(new Position(10,14),this);
		for(int col =1;col<=7;col++){
			itemMap[10][col] = new Tile(new Position(10,col),this);
		}

		for(int col =9;col<=10;col++){
			itemMap[10][col] = new Wall(new Position(10,col),this);
		}
		for(int col =11;col<=13;col++){
			itemMap[10][col] = new Tile(new Position(10,col),this);
		}

		
		//row 11
		itemMap[11][0] = new Wall(new Position(11,0),this);
		itemMap[11][1] = new Tile(new Position(11,1),this);
		itemMap[11][5] = new Tile(new Position(11,5),this);
		itemMap[11][8] = new Tile(new Position(11,8),this);
		itemMap[11][11] = new Tile(new Position(11,11),this);
		itemMap[11][12] = new Wall(new Position(11,12),this);
		itemMap[11][13] = new Tile(new Position(11,13),this);
		itemMap[11][14] = new Wall(new Position(11,14),this);
		for(int col =2;col<=4;col++){
			itemMap[11][col] = new Wall(new Position(11,col),this);
		}
		for(int col =6;col<=7;col++){
			itemMap[11][col] = new Wall(new Position(11,col),this);
		}
		for(int col =9;col<=10;col++){
			itemMap[11][col] = new Wall(new Position(11,col),this);
		}
		
		//row 12
		itemMap[12][0] = new Wall(new Position(12,0),this);
		itemMap[12][1] = new Tile(new Position(12,1),this);
		itemMap[12][7] = new Wall(new Position(12,7),this);
		itemMap[12][12] = new Wall(new Position(12,12),this);
		itemMap[12][13] = new Tile(new Position(12,13),this);
		itemMap[12][14] = new Wall(new Position(12,14),this);
		
		for(int col =2;col<=3;col++){
			itemMap[12][col] = new Wall(new Position(12,col),this);
		}
		for(int col =4;col<=6;col++){
			itemMap[12][col] = new Tile(new Position(12,col),this);
		}
		for(int col =8;col<=11;col++){
			itemMap[12][col] = new Tile(new Position(12,col),this);
		}

		
		//row 13
		itemMap[13][0] = new Wall(new Position(13,0),this);
		itemMap[13][5] = new Wall(new Position(13,5),this);
		itemMap[13][10] = new Wall(new Position(13,10),this);
		itemMap[13][14] = new Wall(new Position(13,14),this);
		
		for(int col =1;col<=4;col++){
			itemMap[13][col] = new Tile(new Position(13,col),this);
		}
		for(int col =6;col<=9;col++){
			itemMap[13][col] = new Tile(new Position(13,col),this);
		}
		for(int col =11;col<=13;col++){
			itemMap[13][col] = new Tile(new Position(13,col),this);
		}
		
		//row 14
		itemMap[14][0] = new Wall(new Position(14,0),this);
		itemMap[14][1] = new Tile(new Position(14,1),this);
		for(int col =2;col<=14;col++){
			itemMap[14][col] = new Wall(new Position(14,col),this);
		}
/*		itemMap[14][0] = new Wall(new Position(14,0),this);
		itemMap[14][1] = new Tile(new Position(14,1),this);
		itemMap[14][2] = new Wall(new Position(14,2),this);
		itemMap[14][3] = new Wall(new Position(14,3),this);
		itemMap[14][4] = new Wall(new Position(14,4),this);
		itemMap[14][5] = new Wall(new Position(14,5),this);
		itemMap[14][6] = new Wall(new Position(14,6),this);
		itemMap[14][7] = new Wall(new Position(14,7),this);
		itemMap[14][8] = new Wall(new Position(14,8),this);
		itemMap[14][9] = new Wall(new Position(14,9),this);
		itemMap[14][10] = new Wall(new Position(14,10),this);
		itemMap[14][11] = new Wall(new Position(14,11),this);
		itemMap[14][12] = new Wall(new Position(14,12),this);
		itemMap[14][13] = new Wall(new Position(14,13),this);
		itemMap[14][14] = new Wall(new Position(14,14),this);*/
	}

/**
 * The resetPlayingField method is called when a MouseHero and a Mouse collide.
 * The method repositions all the Avatars are there original positions.
 */
	@Override
	public void resetPlayingField() {
		movableTiles.get(0).setPosition(new Position(14,1));
		//Check to see if the mouse are still alive, if yes they are created again,
		//if they are dead they will remain dead.
		if(movableTiles.get(1).getLives() == 1)
			movableTiles.get(1).setPosition(new Position(1,1));
		if(movableTiles.get(2).getLives() == 1)
			movableTiles.get(2).setPosition(new Position(10,11));
		if(movableTiles.get(3).getLives() == 1)
			movableTiles.get(3).setPosition(new Position(1,8));
		//System.out.print("\nThe mouse caught you! \n\nRESTART!\n\n");
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
		Position endPos = new Position(1,14);
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
		populateItemMap();
		movableTiles.add(new MouseHero(new Position(14,1), this));
		movableTiles.add(new Mouse(new Position(1,1), this));
		movableTiles.add(new Mouse(new Position(10,11), this));
		movableTiles.add(new Mouse(new Position(1,8), this));
		syncItemMapAndField(movableTiles);
	}
}
