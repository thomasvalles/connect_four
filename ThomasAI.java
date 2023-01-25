package hw4;

public class ThomasAI implements CFPlayer {

	@Override
	/**
	 * Gives the next move for a smart AI
	 * @param g the CFGame being played
	 * @return the column to play
	 */
	public int nextMove(CFGame g) {
		int threeInARow; //stores the sum 3 consecutive tiles
		int nextMove = (int)(Math.random()*7) + 1; //worst case scenario, we make a random move
		if(g.isRedTurn()) { //if it's red's turn
			threeInARow = 3; //we're going to check for three reds
		}
		else {//black's turn
			threeInARow = -3; //check for three blacks
		}
		int[][] state = g.getState(); //get the game state
		
		
		//check for blocking moves first, then moves to win
		//look for three in a row LR-diagonal
		//don't place a "blocking" move if the slot below the block is still empty
		for(int i = 0; i < 4; ++i) { 
			for(int j = 5; j > 2; --j) {
				if( (state[i][j]+state[i+1][j-1]
						+state[i+2][j-2]+state[i+3][j-3]) == -1*threeInARow) {
					if(state[i][j] == 0) {
						nextMove = (i) + 1;
						if(j < 5 && state[i][j+1] == 0) {
							nextMove = (int)(Math.random()*7) + 1;
						}
					}
					if(state[i+1][j-1] == 0) {
						nextMove = (i + 1) + 1;
						if(state[i+1][j] == 0) {
							nextMove = (int)(Math.random()*7) + 1;
						}
					}
					if(state[i+2][j-2] == 0) {
						nextMove = (i + 2) + 1;
						if(state[i+2][j-1] == 0) {
							nextMove = (int)(Math.random()*7) + 1;
						}
					}
					if(state[i+3][j-3] == 0) {
						nextMove = (i + 3) + 1;
						if(state[i+3][j-2] == 0) {
							nextMove = (int)(Math.random()*7) + 1;
						}
					}
				}
			}
		}
			
		//look for three in a row RL-diagonal
		for(int i = 0; i < 4; ++i) {
			for(int j = 0; j < 3; ++j) {
				if( (state[i][j]+state[i+1][j+1]
						+state[i+2][j+2]+state[i+3][j+3]) == -1*threeInARow ) {
					if(state[i][j] == 0) {
						nextMove = (i) + 1;
						if(state[i][j+1] == 0) {
							nextMove = (int)(Math.random()*7) + 1;
						}
					}
					if(state[i+1][j+1] == 0) {
						nextMove = (i + 1) + 1;
						if(state[i+1][j+2] == 0) {
							nextMove = (int)(Math.random()*7) + 1;
						}
					}
					if(state[i+2][j+2] == 0) {
						nextMove = (i + 2) + 1;
						if(state[i+2][j+3] == 0) {
							nextMove = (int)(Math.random()*7) + 1;
						}
					}
					if(state[i+3][j+3] == 0) {
						nextMove = (i + 3) + 1;
						if(j < 2 && state[i+3][j+4] == 0) {
							nextMove = (int)(Math.random()*7) + 1;
						}
					}
				}
			}
		}
		
		//look for three in a row horizontal
		for(int i = 0; i < 4; ++i) {
			for(int j = 0; j < 6; ++j) {
				if( (state[i][j]+state[i+1][j]
						+state[i+2][j]+state[i+3][j]) == -1*threeInARow) {
					if(state[i][j] == 0) {
						nextMove = i + 1;
						if(j < 5 && state[i][j+1] == 0) {
							nextMove = (int)(Math.random()*7) + 1;
						}
					}
					if(state[i+1][j] == 0) {
						nextMove = (i+1) + 1;
						if(j<5 && state[i+1][j+1]==0){
							nextMove = (int)(Math.random()*7) + 1;
						}
					}
					if(state[i+2][j] == 0) {
						nextMove = (i+2) + 1;
						if(j<5 && state[i+2][j+1]==0) {
							nextMove = (int)(Math.random()*7) + 1;
						}
					}
					if(state[i+3][j] == 0) {
						nextMove = (i+3) + 1;
						if(j<5 && state[i+3][j+1]==0) {
							nextMove = (int)(Math.random()*7) + 1;
						}
					}
						 
				}
			}
		}
		
		//look for three in a row vertical
		for(int i = 0; i < 7; ++i) {
			for(int j = 0; j < 3; ++j) {
				if( (state[i][j]+state[i][j+1]
						+state[i][j+2]+state[i][j+3]) == -1*threeInARow) {
					nextMove = i + 1;
				}
			}
		}
		
		//now check for winning moves
		//look for three in a row LR-diagonal
			for(int i = 0; i < 4; ++i) {
				for(int j = 5; j > 2; --j) {
					if( (state[i][j]+state[i+1][j-1]
							+state[i+2][j-2]+state[i+3][j-3]) == threeInARow) {
						if(state[i][j] == 0) nextMove = (i) + 1;
						if(state[i+1][j-1] == 0) nextMove = (i + 1) + 1;
						if(state[i+2][j-2] == 0) nextMove = (i + 2) + 1;
						if(state[i+3][j-3] == 0) nextMove = (i + 3) + 1;
					}
				}
			}
				
		//look for three in a row RL-diagonal
			for(int i = 0; i < 4; ++i) {
				for(int j = 0; j < 2; ++j) {
					if( (state[i][j]+state[i+1][j+1]
							+state[i+2][j+2]+state[i+3][j+3]) == threeInARow ) {
						if(state[i][j] == 0) nextMove = (i) + 1;
						if(state[i+1][j+1] == 0) nextMove = (i + 1) + 1;
						if(state[i+2][j+2] == 0) nextMove = (i + 2) + 1;
						if(state[i+3][j+3] == 0) nextMove = (i + 3) + 1;
					}
				}
			}
		
		//look for three in a row horizontal
		for(int i = 0; i < 4; ++i) {
			for(int j = 0; j < 6; ++j) {
				if( (state[i][j]+state[i+1][j]
						+state[i+2][j]+state[i+3][j]) == threeInARow) {
					if(state[i][j] == 0) nextMove = i + 1;
					if(state[i+1][j] == 0) nextMove = (i+1) + 1;
					if(state[i+2][j] == 0) nextMove = (i+2) + 1;
					if(state[i+3][j] == 0) nextMove = (i+3) + 1;
				}
			}
		}
		
		//look for three in a row vertical
		for(int i = 0; i < 7; ++i) {
			for(int j = 0; j < 3; ++j) {
				if( (state[i][j]+state[i][j+1]
						+state[i][j+2]+state[i][j+3]) == threeInARow) {
					nextMove = i + 1;
				}
			}
		}	
		return nextMove;
	}

	@Override
	/**
	 * Gives the name of this player
	 * @return "Thomas's AI"
	 */
	public String getName() {
		return "Thomas's AI";
	}

}
