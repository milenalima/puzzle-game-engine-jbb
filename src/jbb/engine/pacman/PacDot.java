package jbb.engine.pacman;

import javax.swing.ImageIcon;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.Item;
import jbb.engine.Position;

/**
 * The PacDot provides points for PacMan, and if it is additionally a
 * PowerPellet, it grants PacMan invulnerability for 10 turns.
 * @author Jonathan Gravel
 */
@SuppressWarnings("serial")
public class PacDot extends Item {
	
	private boolean powerPellet;

	/**
	 * @param position
	 * @param board
	 * @param powerPellet true if it is additionally a PowerPellet
	 */
	public PacDot(Position position, Board board, boolean powerPellet) {
		super(position, board);
		this.powerPellet = powerPellet;
		if(powerPellet) {
			this.setImage(new ImageIcon("img/pacdot-powerpellet.png"));
		} else this.setImage(new ImageIcon("img/pacdot.png"));
		setPointValue(10);
	}

	/**
	 * @return true if it is a PowerPellet
	 */
	public boolean isPowerPellet() {
		return powerPellet;
	}

	/**
	 * Set as a PowerPellet or unset
	 * @param powerPellet
	 */
	public void setPowerPellet(boolean powerPellet) {
		this.powerPellet = powerPellet;
	}

	/**
	 * If the PacDot is picked up, it provides points to PacMan. If it also
	 * a PowerPellet, it grants invulnerability.
	 */
	@Override
	public void pickedUp(Avatar picker) {
		PacMan p = (PacMan) picker;
		p.addPoints(pointValue);
		if (powerPellet) {
			p.setInvulnerable(true);
		}
	}

	/**
	 * return "o" when powerPellet or "." when just a regular PacDot
	 */
	public String toString() {
		if (powerPellet) {
			return "o";
		}
		return ".";
	}
}
