package jbb.engine.pacman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import jbb.engine.*;
import jbb.engine.mouseland.Mouse;
import jbb.engine.mouseland.MouseHero;

public class PacWorld extends Board{
	public static final int WIDTH = 15;
	public static final int HEIGHT = 15;
	private static int map=1;
	private static String mapName = "doc/mouseLandMap1.txt";
	
	
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
		Tile.setBlankImage(new ImageIcon("img/black-tile.png"));
		
		if(map == 1){
			mapName= "doc/packManMap1.txt";
			movableTiles.add(new PacMan(new Position(7,1),this));
			movableTiles.add(new Danny(new Position(1,13),this));
			movableTiles.add(new Sam(new Position(7,13),this));
			movableTiles.add(new Cam(new Position(13,13),this));
			}
		else if(map == 2){
			mapName = "doc/packManMap2.txt";
			movableTiles.add(new PacMan(new Position(5,1),this));
			movableTiles.add(new Danny(new Position(13,5),this));
			movableTiles.add(new Sam(new Position(1,13),this));
			movableTiles.add(new Cam(new Position(6,13),this));
			}
		else if(map == 3){
			mapName = "doc/packManMap3.txt";
			movableTiles.add(new PacMan(new Position(13,7),this));
			movableTiles.add(new Danny(new Position(1,1),this));
			movableTiles.add(new Sam(new Position(1,13),this));
			movableTiles.add(new Cam(new Position(7,7),this));
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
						itemMap[count][col] = new PacDot(new Position(count,col), this, false);
						//itemMap[count][col] =new Tile(new Position(count,col),this);
					}
					else if(tiles[col] == ('u')){
						itemMap[count][col] = new PacDot(new Position(count,col), this, true);
						//itemMap[count][col] =new Tile(new Position(count,col),this);
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

	/**
	 * Resets the position PacMan and the Ghosts
	 */
	@Override
	public void resetPlayingField() {
		if(mapName.equals("doc/packManMap1.txt")){
			movableTiles.get(0).setPosition(new Position(7,1));
			//Check to see if the mouse are still alive, if yes they are created again,
			//if they are dead they will remain dead.
			if(movableTiles.get(1).getLives() == 1)
				movableTiles.get(1).setPosition(new Position(1,13));
			if(movableTiles.get(2).getLives() == 1)
				movableTiles.get(2).setPosition(new Position(7,13));
			if(movableTiles.get(3).getLives() == 1)
				movableTiles.get(3).setPosition(new Position(13,13));
		}
		else if(mapName.equals("doc/packManMap2.txt")){
			movableTiles.get(0).setPosition(new Position(5,1));
			//Check to see if the mouse are still alive, if yes they are created again,
			//if they are dead they will remain dead.
			if(movableTiles.get(1).getLives() == 1)
				movableTiles.get(1).setPosition(new Position(13,5));
			if(movableTiles.get(2).getLives() == 1)
				movableTiles.get(2).setPosition(new Position(1,13));
			if(movableTiles.get(3).getLives() == 1)
				movableTiles.get(3).setPosition(new Position(6,13));
		}
		else if(mapName.equals("doc/packManMap3.txt")){
			movableTiles.get(0).setPosition(new Position(13,7));
			//Check to see if the mouse are still alive, if yes they are created again,
			//if they are dead they will remain dead.
			if(movableTiles.get(1).getLives() == 1)
				movableTiles.get(1).setPosition(new Position(1,1));
			if(movableTiles.get(2).getLives() == 1)
				movableTiles.get(2).setPosition(new Position(1,13));
			if(movableTiles.get(3).getLives() == 1)
				movableTiles.get(3).setPosition(new Position(7,7));
		}

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
	
	public void restartGame() {
		this.width = WIDTH;
		this.height = HEIGHT;
		playingField = new Tile[width][height];	
		itemMap = new Tile[width][height];
		movableTiles = new ArrayList<Avatar>();
		populateItemMap();
		// Hero is always at the start of the list;
	//	movableTiles.add(new PacMan(DEFAULT_PACMAN_POS,this));
	//	movableTiles.add(new Danny(DEFAULT_GHOST1_POS,this));
	//	movableTiles.add(new Sam(DEFAULT_GHOST2_POS,this));
	//	movableTiles.add(new Cam(DEFAULT_GHOST3_POS,this));
		// you have to call the following to initialise playing field
		syncItemMapAndField(movableTiles);
	}

	@Override
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

	@Override
	protected boolean lastLevel() {
		if(map == 1){
			return false;
		}
		else if(map == 2){
			return false;
			}
		else if(map == 3){
			return true;
			}
		return false;
	}



}
