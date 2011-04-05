package jbb.engine.pacman;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import jbb.engine.BoardView;
import jbb.engine.Hero;

public class PacGameView extends BoardView {
	
	private JTextArea lives;
	private JTextArea points;
	private JButton undoButton;
	private JButton redoButton;

	public PacGameView(PacWorld board) {
		super(board);
		lives = new JTextArea();
		points = new JTextArea();
	    undoButton = new JButton("Undo");
	    undoButton.setPreferredSize(new Dimension(1,1));
	    undoButton.addActionListener(new UndoButtonHandler(board));
	    redoButton = new JButton("Redo");
	    redoButton.setPreferredSize(new Dimension(1,1));
	    redoButton.addActionListener(new RedoButtonHandler(board));
		updateComponents();
		Component[] more = {lives, points, undoButton, redoButton};
		addMoreComponents(more);
	}
	
	public static void main(String[] args) {
		PacWorld pw = new PacWorld();
		@SuppressWarnings("unused")
		PacGameView game = new PacGameView(pw);
	}
	
	protected void updateComponents() {
		Hero hero = board.getHero();
		lives.setText("Lives: " + hero.getLives());
		points.setText("Points: " + hero.getPoints());
	}
	
	private class UndoButtonHandler implements ActionListener {

		PacWorld board = null;

		public UndoButtonHandler(PacWorld board) {
			super();
			this.board = board;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			board.undoMove();
		}
		
	}
	
	private class RedoButtonHandler implements ActionListener {

		PacWorld board = null;

		public RedoButtonHandler(PacWorld board) {
			super();
			this.board = board;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			board.redoMove();
		}
		
	}

}
