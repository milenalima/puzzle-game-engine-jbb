package jbb.engine.mouseland;

import jbb.engine.Avatar;
import jbb.engine.Board;
import jbb.engine.Item;
import jbb.engine.Position;

public class MouseTrap extends Item {


	public MouseTrap(Position position, Board board) {
		super(position, board);
	}

	public Position getTrapPosition(){
		return this.position;
	}

	public String toString() {
			return "T";
	}

	@Override
	public void pickedUp(Avatar avatar) {
		if (avatar instanceof Mouse) {
			avatar.removeLife();
		}
	}


}
