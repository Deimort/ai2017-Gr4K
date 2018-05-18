package awale.domains;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import awale.boards.AwaleBoard;
import awale.boards.Coordinate;

/**
 * Implémente un joueur machine. Permets de calculer son coup grâce à l'algorithme minimax. Hérite de la classe
 * abstraite Player.
 */

public class ComputerPlayer extends Player{
	
	private Coordinate currentCoord;
	
	public ComputerPlayer(int id) {
		super(id);
	}
	
	@Override
	public Coordinate getCurrentCoord() {
		return currentCoord;
	}
	
	/*
	 * Rédéfinition dans laquelle on implémente l'algorithme minimax. L'argument currentCoord est
	 * ignoré. Elle retourne désormais toujours 1 si la coordonnée est valide (elle l'est toujours)
	 * ou -1 si il n'y pas de coordonnées valides possibles.
	 */
	@Override
	public int setCurrentCoord(Coordinate currentCoord,AwaleBoard ab,boolean starvation) {
		this.currentCoord = max(ab,4,0,starvation);
		if(starvation) {
			return coordWhenStarvation(this.currentCoord, ab);
		}
		
		return 1;
	}

	/**
	 * Calcule récursivement la meilleur coup en appelant la méthode min. Retourne la coordonnée
	 * du meilleur coup possible selon les prédictions de l'algorithme.
	 * @param ab AwaleBoard actuelle
	 * @param profondeurMax Profondeur maximum à laquelle on souhaite arrêter la recherche des AwaleBoard possibles.
	 * @param profondeur Profondeur actuelle de l'appel
	 * @param starvation État de famine du joueur actuelle
	 * @return la coordonnée du meilleur coup possible
	 */
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
	
	/**
	 * Calcule récursivement l'AwaleBoard ou la machine rassemble le moins de points. On calcule
	 * cela en soustrayant les points gagnés par le joueur dans les AwaleBoard calculées au score
	 * de la machine dans l'AwaleBoard passée en paramètre. Retourne la valeur minimum de toutes
	 * les AwaleBoard calculées.
	 * @param ab AwaleBoard du coup joué par la machine
	 * @param profondeurMax Profondeur maximum à laquelle on souhaite arrêter la recherche des AwaleBoard possibles.
	 * @param profondeur Profondeur actuelle de l'appel
	 * @return la valeur minimum de toutes les AwaleBoard calculées
	 */

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
