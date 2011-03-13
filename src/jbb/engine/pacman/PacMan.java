package jbb.engine.pacman;

import javax.swing.ImageIcon;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.Hero;
import jbb.engine.Item;
import jbb.engine.Position;

/**
 * PacMan is the Hero of PacGame. He collects PacDots to gain points and win.
 * @author Jonathan Gravel
 */
@SuppressWarnings("serial")
public class PacMan extends Hero {
	
	public static final int INVULN_LEN = 10; // invulnerability timer lasts 10 turns
	public static final int LIVES = 3;
	
	private String direction;
	private int timer;
	private boolean invulnerable;

	/**
	 * Constructor for Hero using specified Position.
	 * 
	 * @param board represents the board that is associated to this Hero
	 * @param position represents the position of the Hero on the Board
	 */
	public PacMan(Position position, Board board) {
		super(new ImageIcon("img/pacman-right.png"), LIVES, position, board);
		direction = "right";
		invulnerable = false;
		timer = 0;
	}
	
	/**
	 * @return true if invulnerable
	 */
	public boolean isInvulnerable() {
		return invulnerable;
	}
	
	/**
	 * set invulnerability
	 * @param invulnerable
	 */
	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
		timer = INVULN_LEN;
		updateImage();
	}
	
	private void updateImage() {
		if (!invulnerable) {
			setImage(new ImageIcon("img/pacman-"+direction+".png"));
		} else {
			setImage(new ImageIcon("img/pacman-invuln-"+direction+".png"));
		}
	}
	
	/**
	 * Get the next position to move to, and decrement the invulnerability timer
	 * if PacMan is invulnerable.
	 * @throws IllegalArgumentException if no possible move is found
	 */
	public Position getNextPosition(Position position) throws IllegalArgumentException {
		Position nextPos = super.getNextPosition(position);
		if (timer > 0 && --timer == 0) {
			setInvulnerable(false);
		}
		if (nextPos.isSouthOf(this.position)) {
			direction = "down";
		}
		if (nextPos.isNorthOf(this.position)) {
			direction = "up";
		}
		if (nextPos.isEastOf(this.position)) {
			direction = "right";
		}
		if (nextPos.isWestOf(this.position)) {
			direction = "left";
		}
		updateImage();
 		return nextPos;
	}

	/**
	 * @return true if the position contains an PacDot
	 */
	public boolean hasGoodie(Position position) {
		Item item = board.getItem(position);
		if (item == null) return false;
		if (item instanceof PacDot) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return "C" when invulnerable, "c" otherwise.
	 */
	public String toString() {
		if (invulnerable) {
			return "C";
		}
		return "c";
	}

	/**
	 * The playing field is reset when a Ghost kills Pacman
	 * 
	 * @return true if PacMan collides with Ghost and is not invulnerable
	 */
	@Override
	public boolean collidesWith(Avatar avatar) {
		if (avatar instanceof Ghost) {
			if (!invulnerable) {
				this.removeLife();
				return true;
			} else {
				// kill ghost
				avatar.removeLife();
			}
		}
		return false;
	}
	
	public void reset() {
		direction = "right";
		invulnerable = false;
	}

}
