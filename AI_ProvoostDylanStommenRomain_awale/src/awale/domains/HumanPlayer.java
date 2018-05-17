package awale.domains;

import awale.boards.AwaleBoard;
import awale.boards.Coordinate;

public class HumanPlayer implements Player {
	private int score;
	private int id;
	private Coordinate currentCoord;
	
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
		this.score += score;
	}

	public int getId() {
		return id;
	}

	public Coordinate getCurrentCoord() {
		return currentCoord;
	}
	
	public int setCurrentCoord(Coordinate currentCoord,AwaleBoard ab,boolean starvation) {
		this.currentCoord = new Coordinate(currentCoord);
		if(starvation) {
			return coordWhenStarvation(currentCoord,ab);
		}else if(currentCoord.getX() != this.getId() || ab.getSeeds(currentCoord.getX(), currentCoord.getY()) == 0) {
			return 0;
		}
		
		return 1;
	}
	
	

}
