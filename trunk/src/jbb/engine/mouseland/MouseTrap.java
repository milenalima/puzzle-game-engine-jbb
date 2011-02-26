package jbb.engine.mouseland;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.Item;
import jbb.engine.Position;

public class MouseTrap extends Item {


	public MouseTrap(Position position, Board board) {
		super(position, board);
	}


	@Override
	public void pickedUp(Avatar picker) {
		MouseHero p = (MouseHero) picker;
		p.addPoints(pointValue);
//		if (powerPellet) {
			p.setInvulnerable(true);
		}
//	}

	public String toString() {
			return "T";
	}
}
