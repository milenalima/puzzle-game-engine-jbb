package jbb.engine.mouseland;
import java.util.Random;

import java.util.InputMismatchException;
import java.util.Scanner;

import jbb.engine.Position;

public class MouselandGame{
	
	private MouseLand board;
	
	public MouselandGame() {
		board = new MouseLand();
	}
	
	public void play() {
		Position newPos;
		Scanner s;
		System.out.println("You must play until the end");
		while (true) {
			Random randomGenerator = new Random();
		    for (int idx = 1; idx <= 10; ++idx){
		      int randomInt = randomGenerator.nextInt(5);
		      log("Generated : " + randomInt);
		    }
		    
		    log("Done.");
		  
			System.out.println(board);
			System.out.print("Type y to drop mousetrap or n not to drop it then (space) [row] (space) [col]: "); 
			s = new Scanner(System.in);
			try {
				if(s.hasNext("y")){
					
				}
				newPos = new Position(s.nextInt(),s.nextInt());
				board.playTurn(newPos);
			} catch (IllegalArgumentException iae) {
				System.out.println(iae.getMessage());
			} catch (IndexOutOfBoundsException ioobe) {
				System.out.println(ioobe.getMessage());
			} catch (InputMismatchException ime) {
				System.out.println("Invalid Input)");
			}
		}
	
	}
	  
	  private static void log(String aMessage){
	    System.out.println(aMessage);
	  }
	
	
	public static void main(String[] args) {
		MouselandGame game = new MouselandGame();
	
		game.play();
	}
}

