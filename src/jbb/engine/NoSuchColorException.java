package jbb.engine;

@SuppressWarnings("serial")
public class NoSuchColorException extends Exception {

	public NoSuchColorException() {
		super("Tile.BLACK or Tile.WHITE only");
	}
}
