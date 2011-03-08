package jbb.engine;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public abstract class BoardView {
	
	// trial and error offset to fix the gaps near the edge of the frame
	private static final int FRAME_OFFSET = 7;
	// trial and error size of image because the size of img (20) did not
	// display the image fully.
	private static final int HEIGHT_OF_IMG = 23;
	private static final int WIDTH_OF_IMG = 21;
	
	JFrame frame = null;
	Board board = null;
	
	public BoardView(Board board) {
		super();
		this.board = board;
		frame = new JFrame(board.getClass().toString());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH_OF_IMG*board.getWidth()-FRAME_OFFSET, HEIGHT_OF_IMG*board.getHeight()-FRAME_OFFSET);
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
