package hw4;

public class CFGame {
	//state[i][j]= 0 means the i,j slot is empty
	//state[i][j]= 1 means the i,j slot has red
	//state[i][j]=-1 means the i,j slot has black
	private final int[][] state;
	private boolean isRedTurn;
	private boolean draw;
  
	{
		state = new int[7][6];
		for (int i=0; i<7; i++) {
			for (int j=0; j<6; j++) {
				state[i][j] = 0;
			}
		}
		isRedTurn = true; //red goes first
	}
    
	/**
	 * Gives the state of the board
	 * @return 7x6 array representing the board
	 */
	public int[][] getState() {
		int[][] ret_arr = new int[7][6];
		for (int i=0; i<7; i++) {
			for (int j=0; j<6; j++) {
				ret_arr[i][j] = state[i][j];
			}	
		}
		return ret_arr;
	}
  
	/**
	 * Tells if it's red's turn
	 * @return true if it's red's turn
	 */
	public boolean isRedTurn() {
		return isRedTurn;
	}
  
	/**
	 * Plays a move
	 * @param column the column to play
	 * @return true if that column can be played
	 */
	public boolean play(int column) {
		if(state[column-1][0] == 0 ) { //if the top row is empty
			for(int i = 5; i >= 0; --i) { //look for the lowest open position
				if(state[column-1][i] == 0) {
					if(isRedTurn) {
						state[column-1][i] = 1; //fill with 1 for red
					}
					else {
						state[column-1][i] = -1; //-1 for black
					}
					break;
				}
			}
			isRedTurn = !isRedTurn; 
			return true;
		}
		else { //not a valid move
			return false;
		}
	}
  
	/**
	 * Checks if the game is over
	 * @return true if the game is over
	 */
	public boolean isGameOver() {
		boolean gameOver = false;
		
		//check for horizontal win
		for(int i = 0; i < 4; ++i) {
			for(int j = 0; j < 6; ++j) {
				if( (state[i][j]+state[i+1][j]+state[i+2][j]+state[i+3][j]) == 4 
						|| (state[i][j]+state[i+1][j]+state[i+2][j]+state[i+3][j]) == -4 ){
					gameOver = true;
				}
			}
		}
		
		//check for vertical win
		for(int i = 0; i < 7; ++i) {
			for(int j = 0; j < 3; ++j) {
				if( (state[i][j]+state[i][j+1]+state[i][j+2]+state[i][j+3]) == 4 
						|| (state[i][j]+state[i][j+1]+state[i][j+2]+state[i][j+3]) == -4 ){
					gameOver = true;
				}
			}
		}
		
		//Check LR diagonal win
		for(int i = 0; i < 4; ++i) {
			for(int j = 5; j > 2; --j) {
				if( (state[i][j]+state[i+1][j-1]+state[i+2][j-2]+state[i+3][j-3]) == 4 
						|| (state[i][j]+state[i+1][j-1]+state[i+2][j-2]+state[i+3][j-3]) ==-4) {
					gameOver = true;
				}
			}
		}
		
		//check RL diagonal win
		for(int i = 0; i < 4; ++i) {
			for(int j = 0; j < 3; ++j) {
				if( (state[i][j]+state[i+1][j+1]+state[i+2][j+2]+state[i+3][j+3]) == 4 
						|| (state[i][j]+state[i+1][j+1]+state[i+2][j+2]+state[i+3][j+3]) == -4 ) {
					gameOver = true;
				}
			}
		}
		
		//all top rows full
		if( state[0][0] != 0 && (state[1][0] != 0) && (state[2][0] != 0) && (state[3][0] != 0) 
				&& (state[4][0] != 0) && (state[5][0] != 0) && (state[6][0] != 0) ){
			draw = true;
			gameOver = true; 
		}
		
		return gameOver;
	}
  
	/**
	 * returns integer value of winner of the game
	 * @return 0 if draw, 1 if red won, -1 if black won
	 */
	public int winner() {
		if(draw) { //draw
			return 0;
		}
		else {
			if(isRedTurn) { //black just went, so black won
				return -1;
			}
			else { //red just went, so red won
				return 1;
			}
		}
	}
}