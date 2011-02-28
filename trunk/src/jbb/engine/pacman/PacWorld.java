package jbb.engine.pacman;

import jbb.engine.*;

public class PacWorld extends Board{
	public static final int WIDTH = 5;
	public static final int HEIGHT = 5;
	
	public PacWorld() {
		super(WIDTH, HEIGHT);
		// Hero is always at the start of the list;
		movableTiles.add(new PacMan(new Position(4,2), this));
		movableTiles.add(new Ghost(new Position(0,4), this));
		movableTiles.add(new Ghost(new Position(4,0), this));
		populatePlayingField(movableTiles);
	}
	
	@Override
	protected void populateItemMap()
	{
		itemMap[0][0] = new PacDot(new Position(0,0),this, true);
		itemMap[0][1] = new PacDot(new Position(0,1),this, false);
		itemMap[0][2] = new PacDot(new Position(0,2),this, false);
		itemMap[0][3] = new PacDot(new Position(0,3),this, false);
		itemMap[0][4] = new PacDot(new Position(0,4),this, false);
		
		itemMap[1][0] = new PacDot(new Position(1,0),this, false);
		itemMap[1][1] = new Wall(new Position(1,1),this);
		itemMap[1][2] = new Wall(new Position(1,2),this);
		itemMap[1][3] = new Wall(new Position(1,3),this);
		itemMap[1][4] = new PacDot(new Position(1,4),this, false);
		
		itemMap[2][0] = new PacDot(new Position(2,0),this, false);
		itemMap[2][1] = new PacDot(new Position(2,1),this, false);
		itemMap[2][2] = new PacDot(new Position(2,2),this, false);
		itemMap[2][3] = new Tile(new Position(2,3),this);
		itemMap[2][4] = new PacDot(new Position(2,4),this, false);

		itemMap[3][0] = new PacDot(new Position(3,0),this, false);
		itemMap[3][1] = new Wall(new Position(3,1),this);
		itemMap[3][2] = new Wall(new Position(3,2),this);
		itemMap[3][3] = new Wall(new Position(3,3),this);
		itemMap[3][4] = new PacDot(new Position(3,4),this, false);
		
		itemMap[4][0] = new PacDot(new Position(4,0),this, false);
		itemMap[4][1] = new PacDot(new Position(4,1),this, false);
		itemMap[4][2] = new Tile(new Position(4,2),this);
		itemMap[4][3] = new PacDot(new Position(4,3),this, false);
		itemMap[4][4] = new PacDot(new Position(4,4),this, false);
	}

	@Override
	protected void resetPlayingField() {
		movableTiles.add(new PacMan(new Position(4,2), this));
		// check to see if ghosts are still alive, if so, refresh their position
		if(movableTiles.get(1).getLives() == 1);
			movableTiles.add(new Ghost(new Position(0,4), this));
		if(movableTiles.get(2).getLives() == 1);
			movableTiles.add(new Ghost(new Position(4,0), this));
		populatePlayingField(movableTiles);
	}
}
