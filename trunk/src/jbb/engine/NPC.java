/**
 * The NPC is an Avatar that generally has the primary goal of ruining the life of
 * the hero by attempting to kill it. The NPC operates using A.I. to dodge walls and
 * reach the hero.
 */

package jbb.engine;

import javax.swing.ImageIcon;


public abstract class NPC extends Avatar{

	/**
	 * Constructor for NPC using specified Position.
	 * 
	 * @param image pictorial representation of the NPC to be used on a Tile
	 * @param hitPoints represents the starting health of the NPC
	 * @param lives represents the starting number of lives of the NPC
	 * @param board represents the board that is associated to this NPC
	 * @param position represents the position of the NPC on the Board
	 */
	public NPC(ImageIcon image, int hitPoints, int lives, Board board, Position position) {
		super(image, hitPoints, lives, board, position);
	}
	
	/**
	 * Constructor for NPC using default Position (0,0).
	 * 
	 * @param image pictorial representation of the NPC to be used on a Tile
	 * @param hitPoints hitPoints represents the starting health of the NPC
	 * @param lives lives represents the starting number of lives of the NPC
	 * @param board board represents the board that is associated to this NPC
	 */
	public NPC(ImageIcon image, int hitPoints, int lives, Board board) {
		this(image, hitPoints, lives, board, DEFAULT_POSITION);
	}

	protected abstract boolean hasGoodie(Position position);
}