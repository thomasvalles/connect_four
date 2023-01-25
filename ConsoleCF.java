package hw4;
import java.util.Scanner;
public class ConsoleCF extends CFGame {
	
	
	private CFPlayer player1;
	private CFPlayer player2;
	
	/**
	 * Human/ai constructor
	 * @param ai an ai to play with
	 */
	public ConsoleCF(CFPlayer ai) {
		if(Math.random() < 0.5) { //50% chance ai goes first
			player1 = ai;
			player2 = new HumanPlayer();
		}
		else { //50% you go first
			player1 = new HumanPlayer();
			player2 = ai;
		}
	}
	
	/**
	 * ai/ai constructor
	 * @param ai1 the first ai
	 * @param ai2 the second ai
	 */
	public ConsoleCF(CFPlayer ai1, CFPlayer ai2) {
		if(Math.random() < 0.5) { //50% chance first ai goes first
			player1 = ai1;
			player2 = ai2;
		}
		else { //50% chance second ai goes first
			player1 = ai2;
			player2 = ai1;
		}
	}
	
	/**
	 * Play until game is over
	 */
	public void playOut() {
		while(!isGameOver()) { //while the game isn't over
			while(!play(player1.nextMove(this))) {}
			if(isGameOver()) {
				break;
			}
			while(!play(player2.nextMove(this))) {}
		}
		
		//if there is a human player, we should print the board
		if(player1 instanceof HumanPlayer || player2 instanceof HumanPlayer) {
			for(int i = 0; i < 6; ++i) {
				for(int j = 0; j < 7; ++j) {
					System.out.print(getState()[j][i] + "  ");
				}
				System.out.println();
			}
		}
		System.out.println(getWinner() + " is the winner!");
	}
	
	/**
	 * Returns the winner as a string
	 * @return the winner as a string
	 */
	public String getWinner() {
		if(winner() == 1) {
			return player1.getName();
		}
		else if(winner() == -1) {
			return player2.getName();
		}
		else {
			return "draw";
		}
	}
	
	//class representing a human player
	private class HumanPlayer implements CFPlayer{
		
		/**
		 * helper function to check if human entered a valid column
		 * @param column the column trying to be played
		 * @param g the CFGame
		 * @return true if the column can be played
		 */
		private boolean checkValidMove(int column, CFGame g) {
			boolean valid = true;
			//if column less than 1, greater than 7, or full, not a valid move
			if(column < 1 || column > 7 || g.getState()[column-1][0] != 0) {
				valid = false;
			}
			return valid;
		}
		
		@Override
		/**
		 * Get's user input for next play (ensures validity)
		 * @param g the CFGame
		 * @return the valid column
		 */
		public int nextMove(CFGame g) {
			int[][] state = g.getState();
			for(int i = 0; i < 6; ++i) { //print the board
				for(int j = 0; j < 7; ++j) {
					System.out.print(state[j][i] + "  ");
				}
				System.out.println();
			}
			Scanner in = new Scanner(System.in);
			System.out.println("Enter a column");
			int input = in.nextInt();
			
			while(!checkValidMove(input, g)) { //wait until valid input
				System.out.println("Column invalid, please try again");
				input = in.nextInt();
			}
			return input;
		}

		@Override
		/**
		 * Gives name of player
		 * @return "Human Player"
		 */
		public String getName() {
			return "Human Player";
		}	
	}
}


