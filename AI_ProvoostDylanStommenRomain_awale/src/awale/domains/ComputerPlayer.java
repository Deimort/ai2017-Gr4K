package awale.domains;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import awale.boards.AwaleBoard;
import awale.boards.Coordinate;

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
		this.currentCoord = max(ab,4,0,starvation);
		if(starvation) {
			return coordWhenStarvation(this.currentCoord, ab);
		}
		
		return 1;
	}

	private Coordinate max(AwaleBoard ab,int profondeurMax,int profondeur,boolean starvation) {
		List<Coordinate> coord = determineCoord(ab,starvation);
		
		Map<Coordinate,AwaleBoard> boardsMap = generateBoardMap(coord,ab);
		
		return calculateMax(profondeurMax, profondeur, boardsMap);
	}

	private Coordinate calculateMax(int profondeurMax, int profondeur, Map<Coordinate, AwaleBoard> boardsMap) {
		int maximum = Integer.MIN_VALUE;
		Coordinate goodMove = new Coordinate(1,0);
		for(Map.Entry<Coordinate, AwaleBoard> entries : boardsMap.entrySet()) {
			int valeur = getValueMax(profondeurMax, profondeur, entries);
			if(valeur > maximum) {
				maximum = valeur;
				goodMove = entries.getKey();
			}
		}
		return goodMove;
	}

	private int getValueMax(int profondeurMax, int profondeur, Map.Entry<Coordinate, AwaleBoard> entries) {
		int valeur = entries.getValue().getEatenSeeds();
		if(profondeur <= profondeurMax) {
			valeur = min(entries.getValue(),profondeurMax,profondeur+1);
		}
		return valeur;
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

	private int min(AwaleBoard ab,int profondeurMax,int profondeur){
		List<Coordinate> coord = determineCoord(ab, ab.checkStarvation(getId()));
		Map<Coordinate,AwaleBoard> boardMap = generateBoardMap(coord,ab);
		int minimum = calculateMin(ab, profondeurMax, profondeur, boardMap);
		return minimum;
	}

	private int calculateMin(AwaleBoard ab, int profondeurMax, int profondeur, Map<Coordinate, AwaleBoard> boardMap) {
		int minimum = Integer.MAX_VALUE;
		for(Map.Entry<Coordinate, AwaleBoard> entries : boardMap.entrySet()) {
			int valeur = getValueMin(ab, profondeurMax, profondeur, entries);
			
			if(valeur < minimum) {
				minimum = valeur;
			}
		}
		return minimum;
	}

	private int getValueMin(AwaleBoard ab, int profondeurMax, int profondeur,
			Map.Entry<Coordinate, AwaleBoard> entries) {
		AwaleBoard playerMove = entries.getValue();
		int valeur = ab.getEatenSeeds() - playerMove.getEatenSeeds();
		if(profondeur <= profondeurMax) {
			AwaleBoard max = ab.play(max(playerMove,profondeurMax,profondeur+1,playerMove.checkStarvation(0)));
			valeur = ab.getEatenSeeds() - max.getEatenSeeds();
		}
		return valeur;
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
