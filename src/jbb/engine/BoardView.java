package jbb.engine;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public abstract class BoardView {
	
	public static final int SIZE_OF_IMG = 20;
	
	JFrame frame = null;
	Board board = null;
	
	public BoardView(Board board) {
		super();
		this.board = board;
		frame = new JFrame(board.getClass().toString());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(SIZE_OF_IMG*board.getWidth(), SIZE_OF_IMG*board.getHeight());
		frame.setResizable(false);
		Container content = frame.getContentPane();
		content.setLayout(new GridLayout(board.getWidth(), board.getHeight()));
		Tile tile;
		for (int row = 0; row < board.getHeight(); row++) {
			for (int col = 0; col < board.getWidth(); col++) {
				tile = board.getTile(new Position(row, col));
				tile.addActionListener(new ButtonPressHandler(board));
				content.add(tile);
			}
		}
		frame.setVisible(true);
	}

	private void updateView() {
		Container content = new Container();
		content.setLayout(new GridLayout(board.getWidth(),board.getHeight()));
		Tile tile;
		for (int row = 0; row < board.getHeight(); row++) {
			for (int col = 0; col < board.getWidth(); col++) {
				tile = board.getTile(new Position(row,col));
				tile.addActionListener(new ButtonPressHandler(board));
				content.add(tile);
			}
		}
		frame.setContentPane(content);
		frame.setVisible(false);
		frame.setVisible(true);
	}
	
	private class ButtonPressHandler implements ActionListener{

		private Board board;

		public ButtonPressHandler(Board board) {
			this.board = board;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Tile tile = (Tile) e.getSource();
			try {
				board.playTurn(tile.getPosition());
				updateView();
			} catch (IllegalArgumentException ex) {
				JOptionPane warning = new JOptionPane("Invalid Move");
				warning.setVisible(true);
			} catch (GameOver e1) {
				System.out.println(e1.getMessage());
			}
		}
	}
}
