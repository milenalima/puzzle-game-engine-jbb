package jbb.engine.mouseland;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import jbb.engine.BoardView;

public class MouseGameView extends BoardView {

	private JTextArea lives;
	private JButton drop;
	
/**
 * Constructor for MouseGameView
 * -It thats the map from MouseLand
 * -Initializes the size and the action listener
 * -Initializes what the buttons say
 * 
 * @param MouseLand board
 */
	public MouseGameView(MouseLand board) {
		super(board);
		lives = new JTextArea();
		drop = new JButton();
		// make it as small as possible so it fits with the JTextArea
		drop.setPreferredSize(new Dimension(1,1));
		drop.addActionListener(new DropButtonHandler(board));
		updateComponents();
		Component[] more = {lives,drop};
		addMoreComponents(more);
	}
	
	/**
	 * Main Function
	 * 
	 */
	public static void main(String[] args) {
		MouseLand ml = new MouseLand();
		@SuppressWarnings("unused")
		MouseGameView game = new MouseGameView(ml);
	}

/**
 * the updateComponents initializes what JTextArea and JButton say
 * 
 */
	@Override
	protected void updateComponents() {
		MouseHero hero = (MouseHero) board.getHero();
		lives.setText("Lives: " + hero.getLives());
		drop.setText("Set trap (" + hero.getNumMouseTraps() + ")");
	}
/**
 * This class is the action listener for the board.
 * 
 */
	private class DropButtonHandler implements ActionListener {
		
		MouseLand board = null;

		public DropButtonHandler(MouseLand board) {
			super();
			this.board = board;
		}
		
/**
 * When the button to drop a trap is pressed, it calls set trap and 
 * gets the number of mouse traps
 * 
 */		
		@Override
		public void actionPerformed(ActionEvent e) {
			MouseHero hero = (MouseHero) board.getHero();
			hero.setTrap();
			drop.setText("Set trap (" + hero.getNumMouseTraps() + ")");
		}
		
	}

}
