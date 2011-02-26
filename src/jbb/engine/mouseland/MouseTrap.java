package jbb.engine.mouseland;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.Item;
import jbb.engine.Position;

public class MouseTrap extends Item {
	
	private boolean powerPellet;

	public MouseTrap(Position position, Board board, boolean powerPellet) {
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
		MouseMan p = (MouseMan) picker;
		p.addPoints(pointValue);
		if (powerPellet) {
			p.setInvulnerable(true);
		}
	}

	public String toString() {
		if (powerPellet) {
			return "T";
		}
		return ".";
	}
}
