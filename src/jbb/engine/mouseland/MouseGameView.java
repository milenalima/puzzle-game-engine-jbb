package jbb.engine.mouseland;

import jbb.engine.BoardView;

public class MouseGameView extends BoardView {

	public MouseGameView(MouseLand board) {
		super(board);
	}
	
	public static void main(String[] args) {
		MouseLand ml = new MouseLand();
		@SuppressWarnings("unused")
		MouseGameView game = new MouseGameView(ml);
	}

}
