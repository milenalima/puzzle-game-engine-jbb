package jbb.engine.mouseland;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import jbb.engine.BoardView;
import jbb.engine.pacman.PacWorld;

public class MouseGameView extends BoardView {

	private JTextArea lives;
	private JButton drop;
	private JButton undoButton;
	private JButton redoButton;
/**
 * Constructor for MouseGameView
 * -It thats the map from MouseLand
 * -Initializes the size and the action listener
 * -Initializes what the buttons say
 * 
 * @param board
 */
	public MouseGameView(MouseLand board) {
		super(board);
		lives = new JTextArea();
		drop = new JButton();
		// make it as small as possible so it fits with the JTextArea
		drop.setPreferredSize(new Dimension(1,1));
		drop.addActionListener(new DropButtonHandler(board));
	    undoButton = new JButton("Undo");
	    undoButton.setPreferredSize(new Dimension(1,1));
	    undoButton.addActionListener(new UndoButtonHandler(board));
	    redoButton = new JButton("Redo");
	    redoButton.setPreferredSize(new Dimension(1,1));
	    redoButton.addActionListener(new RedoButtonHandler(board));
		updateComponents();
		Component[] more = {lives,drop,undoButton,redoButton};
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
		drop.setText("Trap(" + hero.getNumMouseTraps() + ")");
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
			drop.setText("Trap(" + hero.getNumMouseTraps() + ")");
		}
		
	}
	
	private class UndoButtonHandler implements ActionListener {

		MouseLand board = null;

		public UndoButtonHandler(MouseLand board) {
			super();
			this.board = board;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			board.undoMove();
		}
		
	}
	private class RedoButtonHandler implements ActionListener {

		MouseLand board = null;

		public RedoButtonHandler(MouseLand board) {
			super();
			this.board = board;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			board.redoMove();
		}
		
	}

}
