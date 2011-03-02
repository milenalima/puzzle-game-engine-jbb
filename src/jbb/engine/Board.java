package jbb.engine;

import java.util.ArrayList; 

/**
 * This abstract class contains a two-dimensional array of Tiles, that
 * comprises the playing field of a game, as well as a two-dimensional
 * array that contains the map of set items. It handles an individual
 * turn of the game. The class will be extended by game-specific board
 * classes, which will implement the populatePlayingField()method to
 * populate the board as required by the game.
 * @author Boris Ionine, Jonathan Gravel
 */
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
	 * Set up tiles on the board that do not move (walls, tiles and items)
	 */
	protected abstract void populateItemMap();	
	
	/**
	 * This method will handle a turn in the game.
	 * @param position is the Position of the Tile the player selected.
	 * @throws GameOver if the Hero loses all his lives or wins
	 */
	public void playTurn(Position position) throws GameOver
	{
		Position nextPos;
		//Hero is always the first element of the ArrayList
		Avatar hero = movableTiles.get(0);
		NPC npc;
		Position oldPosition = hero.getPosition();
		/* 
		 * move the hero first, so the move selection error will be
		 * thrown before the npc's are moved (if the move is invalid)
		 */
		nextPos = hero.getNextPosition(position);
		// check to see if an item is picked up
		boolean itemPickedUp = hero.hasGoodie(nextPos); 
		if(itemPickedUp)
		{
			((Item) itemMap[nextPos.getRow()][nextPos.getCol()]).pickedUp(hero);
			//remove item from itemMap
			itemMap[nextPos.getRow()][nextPos.getCol()] = new Tile(nextPos,this);
		}
		hero.setPosition(nextPos);
		// move all other movable tiles
		for(int i = 1; i < movableTiles.size(); i++)
		{
			npc = (NPC) movableTiles.get(i);
			if (npc.getLives() == 0) continue; // don't move dead avatar
			// check to see if hero landed on npc
			if (npc.getPosition().equals(hero.getPosition())) {
				if(hero.collidesWith(npc)){ // if hero dies
					if (hero.getLives() <= 0) {
						throw new GameOver("\n\nNo more lives! YOU LOSE!");
					}
					this.resetPlayingField();
					break; // don't do anything after reset
				}
			}
			// check to see if npc will land on hero
			nextPos = npc.getNextPosition(oldPosition);
			if (nextPos.equals(hero.getPosition())) {
				if (hero.collidesWith(npc)){ // if hero dies
					if (hero.getLives() <= 0) {
						throw new GameOver("\n\nNo more lives! YOU LOSE!");
					}
					this.resetPlayingField();
					break; // don't do anything after reset
				}
			}
			//check if an item was picked up by an NPC
			itemPickedUp = npc.hasGoodie(nextPos);
			if(itemPickedUp)
			{	
				((Item) itemMap[nextPos.getRow()][nextPos.getCol()]).pickedUp(npc);
				//replace item with blank tile from itemMap
				itemMap[nextPos.getRow()][nextPos.getCol()] = new Tile(nextPos,this);
			}
			npc.setPosition(nextPos);
		}
		syncItemMapAndField(movableTiles);
		if (checkWin()) {
			throw new GameOver("\n\nYOU WIN!");
		}
	}
	
	/**
	 * checks win conditions
	 * 
	 * @return true if win
	 */
	protected abstract boolean checkWin();

	/**
	 * This is called when the collidesWith method declares a reset is required.
	 * For example: when a Ghost collides with PacMan or vice versa, all characters
	 * are reset to there original positions.
	 * This method simply modifies the moveableTiles list, giving the Avatars their
	 * original positions.
	 */
	public abstract void resetPlayingField();

	/**
	 * The Items, Tiles and Walls from itemMap are placed on the field, then the
	 * movableTiles are placed on the field.
	 */
	public void syncItemMapAndField(ArrayList<Avatar> movableTiles)
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
			// only place if alive
			if (mT.getLives() != 0) {
				playingField[mT.getPosition().getRow()][mT.getPosition().getCol()] = mT;
			}
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
	 * Returns the item at said position or throws an exception if the position is invalid.
	 * @param position The position of interest
	 * @return Item at position, null if no item
	 */
	public Item getItem(Position position) throws IndexOutOfBoundsException{
		if(position.getRow() < 0 || position.getRow() >= width)
			throw new IndexOutOfBoundsException("Row out of bounds.");
		else if(position.getCol() < 0 || position.getCol() >= height)
			throw new IndexOutOfBoundsException("Col out of bounds");
		else {
			if (itemMap[position.getRow()][position.getCol()] instanceof Item)
				return (Item) itemMap[position.getRow()][position.getCol()];
		} return null;
	}
	
	/**
	 * Returns the tile at said position or throws an exception if the position is invalid.
	 * @param position The position of interest
	 * @return Tile at position, null if no item
	 */
	public Tile getTile(Position position) throws IndexOutOfBoundsException{
		if(position.getRow() < 0 || position.getRow() >= width)
			throw new IndexOutOfBoundsException("Row out of bounds.");
		else if(position.getCol() < 0 || position.getCol() >= height)
			throw new IndexOutOfBoundsException("Col out of bounds");
		else
			return playingField[position.getRow()][position.getCol()];
	}
	
	/**
	 * returns the Hero of the current game
	 * @return Hero
	 */
	public Hero getHero() {
		return (Hero) movableTiles.get(0);
	}
	
	/**
	 * places an item on the map (used for mousetraps, etc).
	 * @param item
	 */
	public void placeItem(Item item) {
		itemMap[item.getPosition().getRow()][item.getPosition().getCol()] = item;
	}
	
	/**
	 * text representation of the playing field
	 */
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
