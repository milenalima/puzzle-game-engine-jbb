/**
 * This abstract class contains a two-dimensional array of Tiles, that
 * comprises the playing field of a game. It handles an individual turn of the game. 
 * The class will be extended by game-specific board classes, 
 * which will implement the populatePlayingField()method to populate 
 * the board as required by the game.
 * @author Boris Ionine
 */
package jbb.engine;

import java.util.ArrayList; 

public abstract class Board {
	protected Tile[][] playingField;
	protected Tile[][] itemMap;
	private int width;
	private int height;
	
	protected ArrayList<Avatar> movableTiles;
	
	/**
	 * Constructor. Initialises instance variables 
	 * width, height and playingField. Calls populatePlayingField() to 
	 * set up the board. 
	 * @param width Width of board
	 * @param height Height of board
	 */
	public Board(int width, int height)
	{
		this.width = width;
		this.height = height;
		playingField = new Tile[width][height];	
		itemMap = new Tile[width][height];
		movableTiles = new ArrayList<Avatar>();
		populateItemMap();
	}
	
	/**
	 * Place items on the game board, as well as the movable tiles. The
	 * movableTiles should contain the Hero at index 0.
	 */
	protected void populatePlayingField(ArrayList<Avatar> movableTiles)
	{
		// Hero is always at the beginning of the list
		for (int i = 0; i < itemMap.length; i++) {
			for (int j = 0; j < itemMap[i].length; j++) {
				playingField[i][j] = itemMap[i][j];
			}
		}
		for (Tile t: movableTiles) {
			playingField[t.getPosition().getRow()][t.getPosition().getCol()] = t;
		}
	}
	
	/**
	 * Set up tiles on the board that do not move
	 */
	protected abstract void populateItemMap();	
	
	/**
	 * This method will handle a turn in the game.
	 * @param The argument position is the Position of the Tile the player selected.
	 * @throws GameOver if the Hero loses all his lives
	 */
	public void playTurn(Position position) throws GameOver
	{
		boolean itemPickedUp = false;
		//Hero is always the first element of the ArrayList
		Avatar hero = movableTiles.get(0);
		NPC npc;
		Position oldPosition = hero.getPosition();
		/* 
		 * move the hero first, so the move selection error will be
		 * thrown before the npc's are moved (if the move is invalid)
		 */
		//check if an item was picked up by the Hero
		itemPickedUp = hero.moveTo(position);
		if(itemPickedUp)
		{
			//remove item from itemMap
			Position current = hero.getPosition();
			itemMap[current.getRow()][current.getCol()] = new Tile(current,this);
		}
		// move all other movable tiles
		for(int i = 1; i < movableTiles.size(); i++)
		{
			npc = (NPC) movableTiles.get(i);
			//check if an item was picked up by an NPC
			itemPickedUp = npc.moveTo(oldPosition);
			Position current = npc.getPosition();
			if(itemPickedUp)
			{	
				//replace item with blank tile from itemMap
				itemMap[current.getRow()][current.getCol()] = new Tile(current,this);
			}
			if (playingField[current.getRow()][current.getCol()] instanceof Hero){
				// check to see if hero died
				boolean heroDied = hero.damageHitPoints(npc.getDamage());
				if (heroDied) {
					// check to see if Game Over reached
					boolean heroOutOfLives = hero.removeLife();
					if (heroOutOfLives) {
						throw new GameOver("You have no more lives");
					} else resetPlayableField();
				}
			}
		}
		syncItemMapAndField();
	}

	protected abstract void resetPlayableField();

	public void syncItemMapAndField()
	{
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				playingField[i][j] = itemMap[i][j];
			}
		}
		
		for(Avatar mT: movableTiles)
		{
			playingField[mT.getPosition().getRow()][mT.getPosition().getCol()] = mT;
		}
	}
	/**
	 * Returns the width of the board.
	 * @return width of board
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Returns the height of the board.
	 * @return height of board
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * Returns the tile at said position or throws an exception if the position is invalid.
	 * @param position The position of interest
	 * @return tile at position
	 */
	public Tile getTile(Position position) throws IndexOutOfBoundsException{
		if(position.getRow() < 0 || position.getRow() >= width)
			throw new IndexOutOfBoundsException("Row out of bounds.");
		else if(position.getCol() < 0 || position.getCol() >= height)
			throw new IndexOutOfBoundsException("Col out of bounds");
		else
			return playingField[position.getRow()][position.getCol()];
	}
	
	public Hero getHero() {
		return (Hero) movableTiles.get(0);
	}
	
	public void placeItem(Item item) {
		itemMap[item.getPosition().getRow()][item.getPosition().getCol()] = item;
	}
	
	public String toString()
	{
		String s = "";
		
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				s += " " + playingField[i][j].toString();
			}
			s += "\n";
		}
		
		return s;
	}
}
