package jbb.engine.mouseland;
import java.util.InputMismatchException;
import java.util.Scanner;

import jbb.engine.GameOver;
import jbb.engine.Position;

public class MouselandGame{
	
	private MouseLand board;
	
	public MouselandGame() {
		board = new MouseLand();
	}
	
	public void play() {
		Position newPos;
		Scanner s1;
		Scanner s2;
		System.out.println("You must play until the end");
		while (true) {
			//Random randomGenerator = new Random();
		    //for (int idx = 1; idx <= 10; ++idx){
		      //int randomInt = randomGenerator.nextInt(5);
		      //log("Generated : " + randomInt);
		    //}
		    
		    //log("Done.");
		  
			System.out.println(board);
			System.out.print("Press 'y' to drop mousetrap or anything else not to: "); 
			s1 = new Scanner(System.in);
			if(s1.hasNext("y")){
				MouseHero mh = (MouseHero) board.getHero();
				mh.setTrap();
			}
			System.out.print("Type next position in form [row](space)[col]: "); 
			s2 = new Scanner(System.in);
			try {
				
				newPos = new Position(s2.nextInt(),s2.nextInt());
				try {
					board.playTurn(newPos);
				} catch (GameOver go) {
					System.out.println(go.getMessage());
					// quit
					break;
				}
			} catch (IllegalArgumentException iae) {
				System.out.println(iae.getMessage());
			} catch (IndexOutOfBoundsException ioobe) {
				System.out.println(ioobe.getMessage());
			} catch (InputMismatchException ime) {
				System.out.println("Invalid Input)");
			}
		}
	
	}
	  
	  public static void main(String[] args) {
		MouselandGame game = new MouselandGame();
	
		game.play();
	}
}

