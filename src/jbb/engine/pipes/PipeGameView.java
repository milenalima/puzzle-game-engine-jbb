package jbb.engine.pipes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import jbb.engine.BoardView;
import jbb.engine.GameOver;
import jbb.engine.Hero;
/**
 * GUI View for the Pipes game.
 * @author Jonathan Gravel
 */
public class PipeGameView extends BoardView {
	
	private JTextArea lives;
	private JTextArea nextPipe;
	private JTextArea timer;
	private JButton runWater;

	/**
	 * Constructor of the PipeGameView class.
	 * Initialises instance variables, 
	 * which are the Components of the board.
	 */
	public PipeGameView(PipeMap board) {
		super(board);
		lives = new JTextArea();
		nextPipe = new JTextArea();
		timer = new JTextArea();
		runWater = new JButton("Run Water");
		runWater.addActionListener(new RunWaterButtonHandler(board));
		updateComponents();
		Component[] more = {lives, nextPipe, timer, runWater};
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
		lives.setText("Lives: " + hero.getLives());
		nextPipe.setText("Next Pipe: "+p.getNextPipeType());
		if (pM.getTurnsUntilWater() > 0) 
			timer.setText("Water in: " + (pM.getTurnsUntilWater()));
		else
			timer.setText("Water is flowing!");
	}
	
	private class RunWaterButtonHandler implements ActionListener {

		PipeMap board = null;

		public RunWaterButtonHandler(PipeMap board) {
			super();
			this.board = board;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				board.runWaterPressed();
			} catch (GameOver e1) {
				// TODO Auto-generated catch block
				handleGameOver(e1);
			}
		}
		
	}

}
