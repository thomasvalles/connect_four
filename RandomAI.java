package hw4;

public class RandomAI implements CFPlayer{

	@Override
	/**
	 * Gives the column for the next move for a random AI
	 * @param g the CFGame we are playing
	 * @return a random int from 1-7
	 */
	public int nextMove(CFGame g) {
		return (int)(Math.random()*7) + 1;
	}

	@Override
	/**
	 * Gives this player's name
	 * @return "Random Player"
	 */
	public String getName() {
		return "Random Player";
	}

}
