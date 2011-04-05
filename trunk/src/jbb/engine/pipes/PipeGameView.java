package jbb.engine.pipes;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import jbb.engine.BoardView;
import jbb.engine.Hero;

/**
 * GUI View for the Pipes game.
 * @author Jonathan Gravel
 */
public class PipeGameView extends BoardView {
	
	private JTextArea nextPipe;
	private JTextArea timer;
	private JButton runWater;
	private JButton undoButton;
	private JButton redoButton;

	/**
	 * Constructor of the PipeGameView class.
	 * Initialises instance variables, 
	 * which are the Components of the board.
	 */
	public PipeGameView(PipeMap board) {
		super(board);
		nextPipe = new JTextArea();
		timer = new JTextArea();
		runWater = new JButton("Run");
		runWater.setPreferredSize(new Dimension(1,1));
		runWater.addActionListener(new RunWaterButtonHandler(board));
	    undoButton = new JButton("Undo");
	    undoButton.setPreferredSize(new Dimension(1,1));
	    undoButton.addActionListener(new UndoButtonHandler(board));
	    redoButton = new JButton("Redo");
	    redoButton.setPreferredSize(new Dimension(1,1));
	    redoButton.addActionListener(new RedoButtonHandler(board));
		updateComponents();
		Component[] more = {nextPipe, timer, runWater, undoButton, redoButton};
		addMoreComponents(more);
	}
	
	public static void main(String[] args) {
		PipeMap pm = new PipeMap();
		@SuppressWarnings("unused")
		PipeGameView game = new PipeGameView(pm);
	}

	@Override
	protected void updateComponents() {
		Hero hero = board.getHero();
		Plumber p = (Plumber) hero;
		PipeMap pM = (PipeMap) board;
		nextPipe.setText("Next: "+p.getNextPipeType());
		if (pM.getTurnsUntilWater() > 0) 
			timer.setText("Flow: " + (pM.getTurnsUntilWater()));
		else
			timer.setText("Water!");
	}
	
	private class RunWaterButtonHandler implements ActionListener {

		PipeMap board = null;

		public RunWaterButtonHandler(PipeMap board) {
			super();
			this.board = board;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			board.runWaterPressed();
		}
		
	}
	
	private class UndoButtonHandler implements ActionListener {

		PipeMap board = null;

		public UndoButtonHandler(PipeMap board) {
			super();
			this.board = board;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			board.undoMove();
		}
		
	}
	private class RedoButtonHandler implements ActionListener {

		PipeMap board = null;

		public RedoButtonHandler(PipeMap board) {
			super();
			this.board = board;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			board.redoMove();
		}
		
	}

}
