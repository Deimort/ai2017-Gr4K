package awale.domains;

import java.util.ArrayList;
import java.util.List;


import awale.boards.AwaleBoard;
import awale.boards.Coordinate;

public abstract class Player {
	
	private int id;
	private int score;
	
	public Player(int id) {
		this.id = id;
		this.score = 0;
	}
	
	/**
	 * Retourne le score du joueur
	 * @return un entier représentant le score du joueur
	 */
	
	public int getScore() {
		return score;
	}
	
	
	/**
	 * Incrémente le score d'une certaine valeur.
	 * @param score un entier avec lequel on souhaite incrémenter le score
	 */
	public void setScore(int score) {
		this.score += score;
	}
	
	/**
	 * Retourne l'id du joueur
	 * @return l'entier représentant l'id du joueur
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Retourne la coordonnée actuelle du joueur
	 * @return la coordonnée actuelle
	 */
	public abstract Coordinate getCurrentCoord();
	
	/**
	 * Définis la valeur de la coordonnée actuelle du joueur avec la valeur de la coordonnée passée
	 * en paramètre.
	 * @param currentCoord la coordonnée qu'on souhaite donner au joueur
	 * @param ab AwaleBoard actuelle
	 * @param starvation booléen représentant l'état de famine du joueur adverse
	 * @return 1 si la coordonnée est valide, 0 si elle n'est pas valide ou -1 si il n'y pas de coordonnées
	 * valides
	 */
	public abstract int setCurrentCoord(Coordinate currentCoord,AwaleBoard ab,boolean starvation);
	
	/**
	 * Vérifie si la coordonnée passée en argument est jouable dans la situation de famine donnée
	 * par l'AwaleBoard passée en argument.
	 * @param coord Coordonnée dont on souhaite vérifier la validité
	 * @param ab AwaleBoard actuelle
	 * @return 1 si la coordonnée est valide, 0 si elle n'est pas valide ou -1 si il n'y pas de coordonnées
	 * possibles
	 */
	public int coordWhenStarvation(Coordinate coord,AwaleBoard ab) {
		List<Coordinate> coordPossible = new ArrayList<>();
		determineCoordStarvation(coordPossible,ab);
		if(coordPossible.contains(coord)) {
			return 1;
		}else if(coordPossible.isEmpty()) {
			return -1;
		}else {
			return 0;
		}
	} 
	
	/**
	 * Ajoute toutes les coordonnées possibles dans une situation de famine à la liste passée
	 * en argument.
	 * @param coordPossible La liste dans laquelle on souhaite stocker les coordonnées.
	 * @param ab AwaleBoard actuelle
	 */
	
	public void determineCoordStarvation(List<Coordinate> coordPossible,AwaleBoard ab) {
		if(getId() == 0) {
			addPossibleCoordFirstRow(coordPossible, ab);
		}else {
			addPossibleCoordSecondRow(coordPossible, ab);
		}
	}

	private void addPossibleCoordSecondRow(List<Coordinate> coordPossible, AwaleBoard ab) {
		for(int i = 0;i < 6;i++) {
			if(i + ab.getSeeds(1, i) > 5) {
				coordPossible.add(new Coordinate(1,i));
			}
		}
	}

	private void addPossibleCoordFirstRow(List<Coordinate> coordPossible, AwaleBoard ab) {
		for(int i = 5;i >= 0;i--) {
			if(i - ab.getSeeds(0, i) < 0) {
				coordPossible.add(new Coordinate(0,i));
			}
		}
	}

}
