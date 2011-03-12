package jbb.engine.mouseland;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import jbb.engine.BoardView;

public class MouseGameView extends BoardView {

	private JTextArea lives;
	private JButton drop;
	
	public MouseGameView(MouseLand board) {
		super(board);
		lives = new JTextArea();
		drop = new JButton();
		drop.addActionListener(new DropButtonHandler(board));
		updateComponents();
		Component[] more = {lives,drop};
		addMoreComponents(more);
	}
	
	public static void main(String[] args) {
		MouseLand ml = new MouseLand();
		@SuppressWarnings("unused")
		MouseGameView game = new MouseGameView(ml);
	}

	@Override
	protected void updateComponents() {
		MouseHero hero = (MouseHero) board.getHero();
		lives.setText("Lives: " + hero.getLives());
		drop.setText("Set trap (" + hero.getNumMouseTraps() + ")");
	}
	
	private class DropButtonHandler implements ActionListener {
		
		MouseLand board = null;

		public DropButtonHandler(MouseLand board) {
			super();
			this.board = board;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			MouseHero hero = (MouseHero) board.getHero();
			hero.setTrap();
		}
		
	}

}
