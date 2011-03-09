package jbb.engine;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
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
		ButtonPressHandler handler = new ButtonPressHandler(board);
		for (int row = 0; row < board.getHeight(); row++) {
			for (int col = 0; col < board.getWidth(); col++) {
				tile = board.getTile(new Position(row,col));
				// if this tile has no listeners, add one
				if (tile.getActionListeners().length == 0) {
					tile.addActionListener(handler);
				}
				content.add(tile);
			}
		}
		frame.setContentPane(content);
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
				// JOptionPane.showMessageDialog(null, "Invalid Input");
				// This is really annoying, so for now, invalid input
				// will just be ignored.
				//warning.setVisible(true);
			} catch (GameOver e1) {
				updateView();
				String[] options = {"Play again","Quit"};
				int result = JOptionPane.showOptionDialog(null, "YOU WIN!", "Congratulations",
						JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon("img/pacman-up"),
						options, options[0]);
				if (result == JOptionPane.CLOSED_OPTION) {
					// if the user closes the YOU WIN window, just linger
				} else if (result == 0) {
					// if option 0 is chosen, start a new game
					board.restartGame();
					updateView();
				} else {
					// if any other option is chosen, quit the game
					frame.dispose();
				}
			}
		}
	}
}
