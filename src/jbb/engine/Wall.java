package jbb.engine;

import javax.swing.ImageIcon;

/**
 * The wall is a tile which blocks the path of an Avatar. It is pictorially represented
 * by a Wall, and it simply a Tile that is not accessible (generally).
 * @author Bruno Colantonio
 */
@SuppressWarnings("serial")
public class Wall extends Tile{

	/**
	 * The constructor will create a wall, or in other words a tile that is not accessible
	 */
	public Wall(Position position, Board board) {
		super(position, board);
		setAccessible(false);
		setImage(new ImageIcon("img/wall-brick.png"));
	}
	
	public String toString() {
		return "X";
	}
}
