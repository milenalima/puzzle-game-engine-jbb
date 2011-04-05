package jbb.engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import jbb.engine.mouseland.*;
import jbb.engine.pacman.*;
import jbb.engine.pipes.*;

/**
 * This abstract class contains a two-dimensional array of Tiles, that
 * comprises the playing field of a game, as well as a two-dimensional
 * array that contains the map of set items. It handles an individual
 * turn of the game. The class will be extended by game-specific board
 * classes, which will implement the populatePlayingField()method to
 * populate the board as required by the game.
 * @author Boris Ionine, Jonathan Gravel
 */
public abstract class Board extends Observable{
	protected Tile[][] playingField;
	protected Tile[][] itemMap;
	protected int width;
	protected int height;
	
	protected ArrayList<Avatar> movableTiles;
	
	protected LinkedList<Tile[][]> prevItemMaps;
	protected LinkedList<ArrayList<Avatar>> prevMovableTiles;
	protected int undoIndex = 0;
	protected int undoCount = 0;
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
		playingField = new Tile[height][width];	
		itemMap = new Tile[height][width];
		movableTiles = new ArrayList<Avatar>();
		prevItemMaps = new LinkedList<Tile[][]>();
		prevMovableTiles = new LinkedList<ArrayList<Avatar>>();
		populateItemMap();
		updateUndoLists();
	}
	
	/**
	 * Set up tiles on the board that do not move (walls, tiles and items)
	 */
	protected abstract void populateItemMap();	
	
	public void updateUndoLists(){
		//undo stuff-----------------------------------------------------------------------------
		undoIndex = 0;
		//save the previous state for undo purposes:
		
		Tile[][] oldItems = new Tile[height][width];
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				if(itemMap[i][j] instanceof MouseTrap){
					oldItems[i][j] = new MouseTrap((MouseTrap)itemMap[i][j]);
				}
				else if(itemMap[i][j] instanceof PacDot){
					oldItems[i][j] = new PacDot((PacDot)itemMap[i][j]);
				}
				else if(itemMap[i][j] instanceof Pipe){
					oldItems[i][j] = new Pipe((Pipe)itemMap[i][j]);
				}
				else{
					oldItems[i][j] = itemMap[i][j];
				}
			}
		}
		prevItemMaps.addLast(oldItems);
		ArrayList<Avatar> oldMovableTiles = new ArrayList<Avatar>(); 
		for(int t = 0; t < movableTiles.size(); t++){
			//oldMovableTiles.add(e)
			if(movableTiles.get(t) instanceof Mouse) {
				//something like this but with a copy constructor
				oldMovableTiles.add(new Mouse((Mouse)movableTiles.get(t))); 
			}
			else if(movableTiles.get(t) instanceof MouseHero){
				oldMovableTiles.add(new MouseHero((MouseHero)movableTiles.get(t))); 
			}
			else if(movableTiles.get(t) instanceof Cam){
				oldMovableTiles.add(new Cam((Cam)movableTiles.get(t))); 
			}
			else if(movableTiles.get(t) instanceof Danny){
				oldMovableTiles.add(new Danny((Danny)movableTiles.get(t))); 
			}
			else if(movableTiles.get(t) instanceof Sam){
				oldMovableTiles.add(new Sam((Sam)movableTiles.get(t))); 
			}
			else if(movableTiles.get(t) instanceof PacMan){
				oldMovableTiles.add(new PacMan((PacMan)movableTiles.get(t))); 
			}
			else if(movableTiles.get(t) instanceof Plumber){
				oldMovableTiles.add(new Plumber((Plumber)movableTiles.get(t))); 
			}
			else if(movableTiles.get(t) instanceof Water){
				oldMovableTiles.add(new Water((Water)movableTiles.get(t))); 
			}
		}
		
		prevMovableTiles.addLast(oldMovableTiles);
		//----------------------------------------------------------------end stuff for undo
	}
	/**
	 * This method will handle a turn in the game.
	 * @param position is the Position of the Tile the player selected.
	 * @throws GameOver if the Hero loses all his lives or wins
	 */
	public void playTurn(Position position)
	{
		updateUndoLists();
		
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
			if (((Item) itemMap[nextPos.getRow()][nextPos.getCol()]).pickedUp(hero))
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
						// refresh the board and quit
						setChanged();
						notifyObservers("Sorry, you are out of lives!");
						return;
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
						// refresh the board and quit
						syncItemMapAndField(movableTiles);
						setChanged();
						notifyObservers("Sorry, you are out of lives!");
						return;
					}
					this.resetPlayingField();
					break; // don't do anything after reset
				}
			}
			//check if an item was picked up by an NPC
			itemPickedUp = npc.hasGoodie(nextPos);
			if(itemPickedUp)
			{	
				if(((Item) itemMap[nextPos.getRow()][nextPos.getCol()]).pickedUp(npc))
					itemMap[nextPos.getRow()][nextPos.getCol()] = new Tile(nextPos,this);
			}
			npc.setPosition(nextPos);
		}
		// reload playingField
		syncItemMapAndField(movableTiles);
		if (checkWin()) {
			if(lastLevel()){
				setChanged();
				notifyObservers("Congratulations: You win!");
			}
			nextLevel();
		}
	}

	public abstract void nextLevel();	


	protected abstract boolean lastLevel();

	
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
		for(int row = 0; row < height; row++)
		{
			for(int col = 0; col < width; col++)
			{
				playingField[row][col] = itemMap[row][col];
			}
		}
		
		for(Avatar mT: movableTiles)
		{
			// only place if alive
			if (mT.getLives() != 0) {
				playingField[mT.getPosition().getRow()][mT.getPosition().getCol()] = mT;
			}
		}
		setChanged();
		notifyObservers("update");
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
		if(position.getRow() < 0 || position.getRow() >= height)
			throw new IndexOutOfBoundsException("Row out of bounds."+position.getRow());
		else if(position.getCol() < 0 || position.getCol() >= width)
			throw new IndexOutOfBoundsException("Col out of bounds"+position.getCol());
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
		if(position.getRow() < 0 || position.getRow() >= height)
			throw new IndexOutOfBoundsException("Row out of bounds.");
		else if(position.getCol() < 0 || position.getCol() >= width)
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

	/**
	 * This function is invoked when the user selects to restart game.
	 * It should reset all the game variables.
	 */
	public abstract void restartGame();
	
	public void undoMove(){
		System.out.println("BeforeUndo: "+undoIndex);
		if(undoCount == 0)
		{
			updateUndoLists();
			undoIndex++;
		}
		if(prevMovableTiles.size()>=2 && prevItemMaps.size() >= 2){			
			undoIndex++;
			movableTiles = prevMovableTiles.get(prevMovableTiles.size()-undoIndex);
			itemMap = prevItemMaps.get(prevItemMaps.size()-undoIndex);
			syncItemMapAndField(movableTiles);
			System.out.println("AfterUndo: "+ undoIndex);
		}
		else
			return;
		undoCount++;
	}
	
	public void redoMove(){
		System.out.println("BeforeRedo:" +undoIndex);
		undoCount = 0;
		if(undoIndex == 1)
		{
			undoIndex--;
			System.out.println(prevMovableTiles.size()+" is size of movable tile");
			System.out.println("Redo:" +undoIndex);
			if(prevMovableTiles.size()>=1 && prevItemMaps.size()>=1){
				movableTiles = prevMovableTiles.get(prevMovableTiles.size()-1);
				itemMap = prevItemMaps.get(prevItemMaps.size()-1);
				syncItemMapAndField(movableTiles);
				//System.out.println("AfterRedo: "+undoIndex);
			}
			else
				return;
		}
		else if(undoIndex > 1){
			undoIndex--;
			if(prevMovableTiles.size()-undoIndex>=0){
				movableTiles = prevMovableTiles.get(prevMovableTiles.size()-undoIndex);
				itemMap = prevItemMaps.get(prevItemMaps.size()-undoIndex);
				syncItemMapAndField(movableTiles);
				//System.out.println("AfterRedo: "+undoIndex);
			}
			else
				return;
		}
		
		System.out.println("AfterRedo: "+undoIndex);
	}
}
