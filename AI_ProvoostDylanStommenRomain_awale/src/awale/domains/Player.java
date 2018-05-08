package awale.domains;

import java.util.Set;
import java.util.TreeSet;

import awale.boards.AwaleBoard;

public interface Player {
	
	public static Player ofId(int id) {
		return null;
	}
	
	public int getScore();
	
	public void setScore(int score);
	
	public int getId();
	
	public Coordinate getCurrentCoord();
	
	public int setCurrentCoord(Coordinate currentCoord,AwaleBoard ab,boolean starvation);
	
	public default int coordWhenStarvation(Coordinate coord,AwaleBoard ab) {
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
	
	public default void determineCoordStarvation(Set<Coordinate> coordPossible,AwaleBoard ab) {
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
