package jbb.engine.pacman;

import java.awt.Component;

import javax.swing.JTextArea;

import jbb.engine.BoardView;
import jbb.engine.Hero;

public class PacGameView extends BoardView {
	
	private JTextArea lives;
	private JTextArea points;

	public PacGameView(PacWorld board) {
		super(board);
		lives = new JTextArea();
		points = new JTextArea();
		updateComponents();
		Component[] more = {lives, points};
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

}
