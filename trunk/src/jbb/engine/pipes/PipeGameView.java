package jbb.engine.pipes;

import java.awt.Component;

import javax.swing.JTextArea;

import jbb.engine.BoardView;
import jbb.engine.Hero;
/**
 * GUI View for the Pipes game.
 * @author Jonathan Gravel
 */
public class PipeGameView extends BoardView {
	
	private JTextArea lives;
	private JTextArea nextPipe;
	private JTextArea timer;

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
		updateComponents();
		Component[] more = {lives, nextPipe, timer};
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

}
