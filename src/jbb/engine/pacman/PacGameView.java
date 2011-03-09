package jbb.engine.pacman;

import java.awt.Component;

import javax.swing.JTextField;

import jbb.engine.BoardView;

public class PacGameView extends BoardView {

	public PacGameView(PacWorld board) {
		super(board);
		/*
		JTextField lives = new JTextField("LIVES...");
		JTextField points = new JTextField("POINTS...");
		Component[] more = {lives, points};
		addMoreComponents(more);
		*/
	}
	
	public static void main(String[] args) {
		PacWorld pw = new PacWorld();
		@SuppressWarnings("unused")
		PacGameView game = new PacGameView(pw);
	}

}
