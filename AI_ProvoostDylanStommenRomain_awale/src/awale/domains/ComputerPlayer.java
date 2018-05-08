package awale.domains;

import java.util.Set;
import java.util.TreeSet;

import awale.boards.AwaleBoard;

public class ComputerPlayer implements Player{
	private int id;
	private int score;
	private Coordinate currentCoord;
	
	public static ComputerPlayer ofId(int id) {
		ComputerPlayer newPlayer = new ComputerPlayer();
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
		this.currentCoord = max(ab,starvation);
		
		return 1;
	}

	private Coordinate max(AwaleBoard ab, boolean starvation) {
		if(starvation) {
			Set<Coordinate> coordStarvation = new TreeSet<>();
			determineCoordStarvation(coordStarvation,ab);
		}
		return null;
	}
	
	
	
	
}
