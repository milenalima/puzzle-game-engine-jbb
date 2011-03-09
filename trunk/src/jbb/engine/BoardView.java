package jbb.engine;

import java.awt.BorderLayout;
import java.awt.Component;
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
	
	private JFrame frame = null;
	private Board board = null;
	private Component[] components = null;
	// max number of components stacked on each other
	private static final int STACK_AMOUNT = 1;
	// height to allocate for components
	private static final int COMPONENTS_HEIGHT = 510;
	
	public BoardView(Board board) {
		super();
		this.board = board;
		frame = new JFrame(board.getClass().getSimpleName());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH_OF_IMG*board.getWidth()-FRAME_OFFSET, HEIGHT_OF_IMG*board.getHeight()-FRAME_OFFSET);
		frame.setResizable(false);
		updateView();
	}
	
	/**
	 * Allows specific game to have additional components on the board,
	 * other than the graphical representation of the playing field.
	 * @param components a list of components to add to the top of the playing
	 * field
	 */
	public void addMoreComponents(Component[] components) {
		this.components = components;
		updateView();
	}

	private void updateView() {
		// wraps the additional components and the board
		Container wrapper = new Container();
		wrapper.setLayout(new BorderLayout());
		// if there are components, add them to the frame
		/* THIS DOESNTWORKYET...
		if (components != null) {
			// additionalComponent stored on top of gameField
			Container additionalComponent = new Container();
			additionalComponent.setLayout(new GridLayout(components.length,STACK_AMOUNT));
			// make room for the new components.
			frame.setSize(frame.getWidth(),HEIGHT_OF_IMG*board.getHeight()-FRAME_OFFSET+COMPONENTS_HEIGHT);
			for (int i = 0; i < components.length; i++) {
				additionalComponent.add(components[i]);
			}
			wrapper.add(additionalComponent,BorderLayout.NORTH);
		}
		*/
		// gameField contains the tiles
		Container gameField = new Container();
		gameField.setLayout(new GridLayout(board.getWidth(),board.getHeight()));
		wrapper.add(gameField,BorderLayout.CENTER);
		// Place playingField tiles on the board.
		Tile tile;
		ButtonPressHandler handler = new ButtonPressHandler(board);
		for (int row = 0; row < board.getHeight(); row++) {
			for (int col = 0; col < board.getWidth(); col++) {
				tile = board.getTile(new Position(row,col));
				// if this tile has no listeners, add one
				if (tile.getActionListeners().length == 0) {
					tile.addActionListener(handler);
				}
				gameField.add(tile);
			}
		}
		frame.setContentPane(gameField);
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
				int result = JOptionPane.showOptionDialog(null, e1.getMessage(), "End of Game",
						JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
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
