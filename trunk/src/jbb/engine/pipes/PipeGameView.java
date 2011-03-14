package jbb.engine.pipes;

import java.awt.Component;

import javax.swing.JTextArea;

import jbb.engine.BoardView;
import jbb.engine.Hero;

public class PipeGameView extends BoardView {
	
	private JTextArea lives;
	private JTextArea nextPipe;
	private JTextArea timer;
	private int tick;

	public PipeGameView(PipeMap board) {
		super(board);
		lives = new JTextArea();
		nextPipe = new JTextArea();
		timer = new JTextArea();
		tick = -1;
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
		lives.setText("Lives: " + hero.getLives());
		nextPipe.setText("Next Pipe: "+p.getNextPipeType());
		if (tick < PipeMap.WATER_START_TURN) // TODO donno the logic of you numTurns
			timer.setText("Water in: " + (PipeMap.WATER_START_TURN-tick++));
		else if (tick == PipeMap.WATER_START_TURN)
			timer.setText("Water is flowing!");
	}

}
