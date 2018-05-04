package awale.domains;

import java.util.Set;
import java.util.TreeSet;

import awale.boards.AwaleBoard;

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
	
	private int coordWhenStarvation(Coordinate coord,AwaleBoard ab) {
		Set<Coordinate> coordPossible = new TreeSet<>();
		determineCoordStarvation(coordPossible,ab);
		if(coordPossible.contains(coord)) {
			return 1;
		}else if(coordPossible.isEmpty()) {
			return -1;
		}else {
			return 0;
		}
	}
	
	private void determineCoordStarvation(Set<Coordinate> coordPossible,AwaleBoard ab) {
		if(getId() == 0) {
			for(int i = 5;i >= 0;i--) {
				if(i - ab.getSeeds(0, i) < 0) {
					coordPossible.add(new Coordinate(0,i));
				}
			}
		}else {
			for(int i = 0;i < 6;i++) {
				if(i + ab.getSeeds(0, i) > 5) {
					coordPossible.add(new Coordinate(1,i));
				}
			}
		}
	}
	

}
