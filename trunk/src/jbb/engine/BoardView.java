package jbb.engine;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public abstract class BoardView {
	
	private JFrame frame = null;
	protected Board board = null;
	private Component[] components = null;
	boolean val = false;
	
	public BoardView(Board board) {
		super();
		this.board = board;
		frame = new JFrame(board.getClass().getSimpleName());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (dim.width-frame.getSize().width)/2;
	    int y = (dim.height-frame.getSize().height)/2;
	    frame.setLocation(x, y);
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
		if (components != null) {
			updateComponents();
			// additionalComponent stored on top of gameField
			Container additionalComponent = new Container();
			additionalComponent.setLayout(new GridLayout(1, components.length));
			for (int i = 0; i < components.length; i++) {
				additionalComponent.add(components[i]);
			}
			wrapper.add(additionalComponent,BorderLayout.NORTH);
		}
		// gameField contains the tiles
		Container gameField = new Container();
		gameField.setLayout(new GridLayout(board.getHeight(),board.getWidth()));
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
		frame.setContentPane(wrapper);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * This function will change the value of any additional components that have
	 * been added to the board, e.g. updating lives remaining on JTextArea.
	 */
	protected abstract void updateComponents();

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
					// if the user closes the YOU WIN window, show game select menu
					frame.dispose();
					GameLauncher.main(null);
				} else if (result == 0) {
					// if option 0 is chosen, start a new game
					board.restartGame();
					updateView();
				} else {
					// if any other option is chosen, quit the game
					frame.dispose();
					GameLauncher.main(null);
				}
			}
		}
	}
}
