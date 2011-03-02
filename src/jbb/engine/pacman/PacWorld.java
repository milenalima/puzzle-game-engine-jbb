package jbb.engine.pacman;

import jbb.engine.*;

public class PacWorld extends Board{
	public static final int WIDTH = 13;
	public static final int HEIGHT = 13;
	
	public static final Position DEFAULT_PACMAN_POS = new Position(6,1);
	public static final Position DEFAULT_GHOST1_POS = new Position(3,6);
	public static final Position DEFAULT_GHOST2_POS = new Position(5,7);
	public static final Position DEFAULT_GHOST3_POS = new Position(9,3);
	
	/**
	 * Generate a PacWorld with 1 PacMan and 3 Ghosts
	 */
	public PacWorld() {
		super(WIDTH, HEIGHT);
		// Hero is always at the start of the list;
		movableTiles.add(new PacMan(DEFAULT_PACMAN_POS,this));
		movableTiles.add(new Ghost(DEFAULT_GHOST1_POS,this));
		movableTiles.add(new Ghost(DEFAULT_GHOST2_POS,this));
		movableTiles.add(new Ghost(DEFAULT_GHOST3_POS,this));
		// you have to call the following to initialise playing field
		syncItemMapAndField(movableTiles);
	}
	
	/**
	 * Sets up the Item map as such:
	 * 
	 * X X X X X X X X X X X X X
	 * X . . . . . . . . . . . X
	 * X . X X X X . X X X X . X
	 * X . X . . . G . . . X . X
	 * X . X . X X X X X . X . X
	 * X . X . X . . G X . X . X
	 * X c . . . . X . . . . . X
	 * X . X . X . . . X . X . X
	 * X . X . X X X X X . X . X
	 * X . X G . . . . . . X . X	  
	 * X . X X X X . X X X X . X
	 * X . . . . . . . . . . . X
	 * X X X X X X X X X X X X X
	 * 
	 * and the ghosts are standing on PacDots
	 */
	@Override
	protected void populateItemMap()
	{
		// place PacDots everywhere in the middle
		for (int row = 1; row < 12; row++) {
			for (int col = 1; col < 12; col++) {
				if (row == DEFAULT_PACMAN_POS.getRow() && col == DEFAULT_PACMAN_POS.getCol()) {
					itemMap[row][col] = new Tile(new Position(row,col),this);
				} else if ((row == 1 && col == 1) || (row == 1 && col == 11) ||
					(row == 11 && col == 11) || (row == 11 && col == 1)) {
					// place powerPellet here
					itemMap[row][col] = new PacDot(new Position(row,col), this, true);
				} else {
					itemMap[row][col] = new PacDot(new Position(row,col), this, false);
				}
			}
		}
		
		// first row is just wall
		Tile[] currentRow = itemMap[0];
		for (int i = 0; i < 13; i++) {
			currentRow[i] = new Wall(new Position(0,i), this);
		}
		
		// first col is just wall
		for (int row = 1; row < 13; row++) {
			itemMap[row][0] = new Wall(new Position(row,0), this);
		}
		
		// 13th col is just wall
		for (int row = 1; row < 13; row++) {
			itemMap[row][12] = new Wall(new Position(row,12), this);
		}
		
		// 2nd row is blank tiles only
		
		// 3rd row
		currentRow = itemMap[2];
		for (int col = 2; col < 11; col++) {
			if (col == 6) continue;
			currentRow[col] = new Wall(new Position(2,col), this);
		}
		
		// 4th row
		itemMap[3][2] = new Wall(new Position(3,2), this);
		itemMap[3][10] = new Wall(new Position(3,10), this);
		
		// 5th row
		currentRow = itemMap[4];
		for (int col = 2; col < 11; col++) {
			if (col == 1 || col == 3 || col == 9 || col == 11) continue;
			currentRow[col] = new Wall(new Position(4,col), this);
		}
		
		// 6th row
		itemMap[5][2] = new Wall(new Position(5,2), this);
		itemMap[5][4] = new Wall(new Position(5,4), this);
		itemMap[5][8] = new Wall(new Position(5,8), this);
		itemMap[5][10] = new Wall(new Position(5,10), this);
		
		// 7th row
		itemMap[6][6] = new Wall(new Position(6,6), this);
		
		// 8th row
		itemMap[7][2] = new Wall(new Position(7,2), this);
		itemMap[7][4] = new Wall(new Position(7,4), this);
		itemMap[7][8] = new Wall(new Position(7,8), this);
		itemMap[7][10] = new Wall(new Position(7,10), this);
		
		// 9th row
		currentRow = itemMap[8];
		for (int col = 2; col < 11; col++) {
			if (col == 1 || col == 3 || col == 9 || col == 11) continue;
			currentRow[col] = new Wall(new Position(8,col), this);
		}
		
		// 10th row
		itemMap[9][2] = new Wall(new Position(9,2), this);
		itemMap[9][10] = new Wall(new Position(9,10), this);
		
		// 11th row
		currentRow = itemMap[10];
		for (int col = 2; col < 11; col++) {
			if (col == 6) continue;
			currentRow[col] = new Wall(new Position(10,col), this);
		}
		
		// 12th row is blank tiles only
		
		// 13th row is just wall
		currentRow = itemMap[12];
		for (int i = 0; i < 13; i++) {
			currentRow[i] = new Wall(new Position(0,i), this);
		}
	}

	/**
	 * Resets the position PacMan and the Ghosts
	 */
	@Override
	public void resetPlayingField() {
		movableTiles.get(0).setPosition(DEFAULT_PACMAN_POS);
		// check to see if ghosts are still alive, if so, refresh their position
		if(movableTiles.get(1).getLives() == 1)
			movableTiles.get(1).setPosition(DEFAULT_GHOST1_POS);
		if(movableTiles.get(2).getLives() == 1)
			movableTiles.get(2).setPosition(DEFAULT_GHOST2_POS);
		if(movableTiles.get(3).getLives() == 1)
			movableTiles.get(3).setPosition(DEFAULT_GHOST3_POS);
	}
	
	/**
	 * PacGame is won when no PacDots are left on the itemMap
	 * 
	 * @return true when no PacDots are left
	 */
	protected boolean checkWin()  {
		for (int i = 0; i < itemMap.length; i++) {
			for (int j = 0; j < itemMap[i].length; j++) {
				if (itemMap[i][j] instanceof PacDot) return false;
			}
		}
		return true;
	}
}
