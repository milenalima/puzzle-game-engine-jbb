package jbb.engine.pacman;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import jbb.engine.GameOver;
import jbb.engine.Tile;

public class PacWorldView {
	
	public static final int SIZE_OF_IMG = 20;
	
	JFrame frame = null;
	
	public PacWorldView(PacWorld board) {
		super();
		frame = new JFrame("PacWorld");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(SIZE_OF_IMG*board.WIDTH, SIZE_OF_IMG*board.HEIGHT);
		frame.setResizable(false);
		Container content = frame.getContentPane();
		content.setLayout(new GridLayout(board.WIDTH, board.HEIGHT));
		Tile tile;
		for (int row = 0; row < board.HEIGHT; row++) {
			for (int col = 0; col < board.WIDTH; col++) {
				tile = board.playingField[row][col];
				tile.addActionListener(new ButtonPressHandler(this));
				content.add(board.playingField[row][col]);
			}
		}
		frame.setVisible(true);
	}
	
	private class ButtonPressHandler implements ActionListener{

		private PacWorldView board;

		public ButtonPressHandler(PacWorldView pacWorldView) {
			this.board = pacWorldView;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Tile tile = (Tile) e.getSource();
			try {
				board.playTurn(tile.getPosition());
				board.updateView();
			} catch (IllegalArgumentException ex) {
				JOptionPane warning = new JOptionPane("Invalid Move");
				warning.setVisible(true);
			} catch (GameOver e1) {
				System.out.println(e1.getMessage());
			}
		}
	}
	
	public static void main(String[] args) {
		PacWorldView pwv = new PacWorldView();
	}

	public void updateView() {
		Container content = new Container();
		content.setLayout(new GridLayout(WIDTH,HEIGHT));
		Tile tile;
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				tile = playingField[row][col];
				tile.addActionListener(new ButtonPressHandler(this));
				content.add(playingField[row][col]);
			}
		}
		frame.setContentPane(content);
		frame.setVisible(false);
		frame.setVisible(true);
	}
}
