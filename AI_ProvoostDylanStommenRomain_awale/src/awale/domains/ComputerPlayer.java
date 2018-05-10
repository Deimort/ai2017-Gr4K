package awale.domains;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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
		List<Coordinate> coord = determineCoord(ab,starvation);
		
		Map<Coordinate,AwaleBoard> boardsMap = generateBoardMap(coord,ab);
		int maximum = Integer.MIN_VALUE;
		Coordinate goodMove = new Coordinate(1,0);
		for(Map.Entry<Coordinate, AwaleBoard> entries : boardsMap.entrySet()) {
			int valeur = min(entries.getValue());
			if(valeur > maximum) {
				maximum = valeur;
				goodMove = entries.getKey();
			}
		}
		return goodMove;
	}
	
	private List<Coordinate> determineCoord(AwaleBoard ab, boolean starvation) {
		List<Coordinate> coord = new ArrayList<>();
		if(starvation) {
			determineCoordStarvation(coord,ab);
		}else {
			determineCoordComputer(coord,ab);
		}
		return coord;
	}

	private int min(AwaleBoard ab){
		List<Coordinate> coord = determineCoord(ab, ab.checkStarvation(getId()));
		Map<Coordinate,AwaleBoard> boardMap = generateBoardMap(coord,ab);
		int minimum = Integer.MAX_VALUE;
		for(Map.Entry<Coordinate, AwaleBoard> entries : boardMap.entrySet()) {
			AwaleBoard playerMove = entries.getValue();
			int valeur = ab.getEatenSeeds() - playerMove.getEatenSeeds();
			if(valeur < minimum) {
				minimum = valeur;
			}
		}
		return minimum;
	}
	
	private Map<Coordinate,AwaleBoard> generateBoardMap(List<Coordinate> coordinates,AwaleBoard ab) {
		Map<Coordinate,AwaleBoard> boardMap = new TreeMap<>();
		for(int i = 0;i < coordinates.size();i++) {
			boardMap.put(coordinates.get(i), ab.play(coordinates.get(i)));
		}
		return boardMap;
	}

	private void determineCoordComputer(List<Coordinate> coord, AwaleBoard ab) {
		for(int i = 0;i < 6;i++) {
			if(ab.getSeeds(getId(), i) > 0) {
				coord.add(new Coordinate(getId(),i));
			}
		}
	}
	
	
	
	
	
	
}
