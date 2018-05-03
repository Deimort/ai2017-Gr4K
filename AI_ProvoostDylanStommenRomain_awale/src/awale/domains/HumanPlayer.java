package awale.domains;

import awale.boards.AwaleBoard;

public class HumanPlayer implements Player {
	private int score;
	private int id;
	private int[] currentCoord;
	
	public static HumanPlayer ofId(int id) {
		HumanPlayer newPlayer = new HumanPlayer();
		newPlayer.id = id;
		newPlayer.score = 0;
		
		return newPlayer;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public int[] getCurrentCoord() {
		return currentCoord;
	}

	public boolean setCurrentCoord(int[] currentCoord,AwaleBoard ab) {
		if(currentCoord[0] != this.getId() || ab.getSeeds(currentCoord[0], currentCoord[1]) == 0) {
			return false;
		}
		this.currentCoord = currentCoord;
		return true;
	}
	

}
