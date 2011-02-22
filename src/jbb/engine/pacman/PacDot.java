package jbb.engine.pacman;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.Item;
import jbb.engine.Position;

public class PacDot extends Item {
	
	private boolean powerPellet;

	public PacDot(Position position, Board board, boolean powerPellet) {
		super(position, board);
		this.powerPellet = powerPellet;
	}

	public boolean isPowerPellet() {
		return powerPellet;
	}

	public void setPowerPellet(boolean powerPellet) {
		this.powerPellet = powerPellet;
	}

	@Override
	public void pickedUp(Avatar picker) {
		PacMan p = (PacMan) picker;
		p.addPoints(pointValue);
	}

}
