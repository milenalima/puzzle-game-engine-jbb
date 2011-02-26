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
	
	protected ArrayList<Avatar> moveableTiles;
	
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
		moveableTiles = new ArrayList<Avatar>();
		populatePlayingField();
		populateItemMap();
	}
	
	/**
	 * Abstract method to be implemented by game-specific classes that 
	 * extend Board. The method will populate the board with tiles.
	 */
	protected abstract void populatePlayingField();
	
	/**
	 * Will copy all the content from playingField, other than the avatars (or
	 * movable objects)
	 */
	protected void populateItemMap() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (!(playingField[i][j] instanceof Avatar)) {
					itemMap[i][j] = playingField[i][j];
				} else {
					itemMap[i][j] = new Tile(new Position(i,j),this);
				}
			}
		}
	}
	
	/**
	 * This method will handle a turn in the game.
	 * @param The argument position is the Position of the Tile the player selected.
	 */
	public void playTurn(Position position)
	{
		boolean itemPickedUp = false;
		//Hero is always the last element of the ArrayList
		Avatar hero = moveableTiles.get(moveableTiles.size()-1);
		for(int i = 0; i<moveableTiles.size()-1; i++)
		{
			//check if an item was picked up by an NPC
			itemPickedUp = moveableTiles.get(i).moveTo(hero.getPosition());
			if(itemPickedUp)
			{	
				//remove item from itemMap
				Position current = moveableTiles.get(i).getPosition();
				itemMap[current.getRow()][current.getCol()] = new Tile(current,this);
			}
		}
		//check if an item was picked up by the Hero
		itemPickedUp = hero.moveTo(position);
		if(itemPickedUp)
		{
			//remove item from itemMap
			Position current = hero.getPosition();
			itemMap[current.getRow()][current.getCol()] = new Tile(current,this);
		}
		syncItemMapAndField();
	}
	
	public void syncItemMapAndField()
	{
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				playingField[i][j] = itemMap[i][j];
			}
		}
		
		for(Avatar mT: moveableTiles)
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
