package jbb.engine;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The BoardView displays a window with the graphical representation of a board.
 * The user interacts with the game by clicking tiles visible in the window.
 * @author Jonathan Gravel
 */
public abstract class BoardView implements Observer {
	
	private JFrame frame = null;
	protected Board board = null;
	private Component[] components = null;
	private boolean frameIsSized = false;
	
	/**
	 * Sets up the JFrame by defining properties and position on the screen.
	 * It also runs updateView() once.
	 * 
	 * @param board the game board which contains the tile grid and the playTurn fn.
	 */
	public BoardView(Board board) {
		super();
		this.board = board;
		board.addObserver(this);
		frame = new JFrame(board.getClass().getSimpleName());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		// frame needs a resize
		frameIsSized = false;
		updateView();
	}

	/**
	 * The view gets updated with the tiles on board.playingField, and with the components,
	 * if any, set in addMoreComponents(...)
	 */
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
		BoardController handler = new BoardController(board);
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
		if (!frameIsSized) {
			frame.pack(); // wrap window around content.
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (dim.width-frame.getSize().width)/2;
			int y = (dim.height-frame.getSize().height)/2;
			frame.setLocation(x, y);
			// frame has been sized once, no need to do it again
			frameIsSized = true;
		}
		frame.setVisible(true);
	}
	
	private void handleGameOver(String str) {
		// either the game is won, or the game is lost
		updateView();
		// ask the user if he wants to play again, or quit this game.
		String[] options = {"Play again","Quit"};
		int result = JOptionPane.showOptionDialog(null, str, "End of Game",
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
			// if any other option is chosen, show game select menu
			frame.dispose();
			GameLauncher.main(null);
		}
	}
	
	/**
	 * This function will change the value of any additional components that have
	 * been added to the board, e.g. updating lives remaining on JTextArea.
	 */
	protected abstract void updateComponents();
	
	public void update(Observable o, Object arg) {
		if (!(arg instanceof String)) return; // do nothing
		String str = (String) arg;
		// either an update or a game over
		if (str.equals("update")) updateView();
		else handleGameOver(str);
	}

}
