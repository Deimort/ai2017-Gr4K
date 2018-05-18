package awale.boards;

import java.util.Arrays;

/**
 * Représente un plateau de jeu d'awale, permet aussi de jouer un coup complet à une coordonnée donnée.
 * Collabore avec Coordinate.
 *
 */

public class AwaleBoard {
	
	private final int[][] board;
	private final int eatenSeeds;
	
	/**
	 * Initialise un nouveau plateau avec 4 graines dans chaque trou
	 */
	public AwaleBoard() {
		board = new int[2][6];
		for(int i = 0;i < board.length;i++) {
			Arrays.fill(board[i],4);
		}
		this.eatenSeeds = 0;
	}
	
	/**
	 * Initalise un nouveau plateau selon un tableau et un nombre de graines mangées en paramètre
	 * @param board
	 * @param eatenSeeds
	 */
	public AwaleBoard(int[][] board,int eatenSeeds) {
		this.board = board;
		this.eatenSeeds = eatenSeeds;
	}
	
	
	/**
	 * Sème les graines contenues dans le trou désigné par la coordonnée. L'OCT de l'algorithme est de l'ordre
	 * de O(n), car il n'y a qu'une seule boucle qui, dans le pire des cas, doit faire une ou plusieurs fois
	 * le tour du plateau.
	 * @param coord coordonnée du trou que l'on souhaite semer
	 * @return la coordonnée du trou où la dernière graine a été semée
	 */
	private Coordinate sow(Coordinate coord) {
		int nbSeed = board[coord.getX()][coord.getY()];
		Coordinate newCoord = new Coordinate(coord);
		board[coord.getX()][coord.getY()] = 0;
		while (nbSeed > 0) {
			newCoord = determineCoordSow(newCoord);
			nbSeed = checkIfNotOriginHole(coord, nbSeed, newCoord);
		}
		return newCoord;
	}

	private int checkIfNotOriginHole(Coordinate coord, int nbSeed, Coordinate newCoord) {
		if(newCoord.getX() == coord.getX() && newCoord.getY() == coord.getY()) {
			board[newCoord.getX()][newCoord.getY()] = 0;
		}else {
			board[newCoord.getX()][newCoord.getY()] += 1;
			nbSeed--;
		}
		return nbSeed;
	}

	private Coordinate determineCoordSow(Coordinate coord) {
		if(checkIfBeginningFirstRow(coord)) {
			coord = new Coordinate(1,0);
		}else if(checkIfBeginningSecondRow(coord)) {
			coord = new Coordinate(0,5);
		}else {
			if(coord.getX() == 0) {
				coord.setY(-1);
			}else {
				coord.setY(1);
			}
		}
		return coord;
	}

	private boolean checkIfBeginningSecondRow(Coordinate coord) {
		return coord.getX() == 1 && coord.getY() == 5;
	}

	private boolean checkIfBeginningFirstRow(Coordinate coord) {
		return coord.getX() == 0 && coord.getY() == 0;
	}
	
	/**
	 * Mange les graines possibles dans le sens horaire à partir de la coordonnée passée en paramètre. L'OCT de
	 * l'algorithme est de l'ordre de O(N), car il n'y a qu'une seule boucle à travers le plateau. Dans le pire des
	 * cas, on parcourrait quatre cases.
	 * @param coord coordonnée du trou où la dernière graine a été semée
	 * @return
	 */
	private int eat(Coordinate coord) {
		int seedCount = 0;
		int count = 0;
		while (count < 4 && (board[coord.getX()][coord.getY()] == 2 || board[coord.getX()][coord.getY()] == 3)) {
			seedCount += board[coord.getX()][coord.getY()];
			board[coord.getX()][coord.getY()] = 0;
			coord = determineCoordEat(coord);
			count++;
		}
		return seedCount;
	}

	/**
	 * Vérifie si il y a une situation de famine à la ligne demandée en argument
	 * @param row ligne que l'on souhaite vérifier
	 * @return true si tous les trous sont à 0, false sinon
	 */
	public boolean checkStarvation(int row) {
		for(int a : board[row]) {
			if(a != 0) {
				return false;
			}
		}
		return true;
	}
	
	private Coordinate determineCoordEat(Coordinate coord) {
		if(checkIfEndFirstRow(coord) || checkIfEndSecondRow(coord)) {
			return coord;
		}else {
			incrementY(coord);
		}
		return coord;
	}

	private boolean checkIfEndFirstRow(Coordinate coord) {
		return coord.getX() == 0 && coord.getY() == 5;
	}

	private boolean checkIfEndSecondRow(Coordinate coord) {
		return coord.getX() == 1 && coord.getY() == 0;
	}

	private void incrementY(Coordinate coord) {
		if(coord.getX() == 0) {
			coord.setY(1);
		}else {
			coord.setY(-1);
		}
	}
	
	
	/**
	 * Retourne le nombre de graines mangées ce tour-ci
	 * @return le nombre de graines mangées ce tour-ci
	 */
	public int getEatenSeeds() {
		return eatenSeeds;
	}
	
	/**
	 * Permet de jouer un tour à la coordonnée donnée en appelant les méthodes eat et sow
	 * @param coord coordonnée à laquelle on souhaite jouer
	 * @return la nouvelle AwaleBoard
	 */
	public AwaleBoard play(Coordinate coord) {
		AwaleBoard copy = new AwaleBoard(this.board(),this.getEatenSeeds());
		Coordinate eatCoord = copy.sow(coord);
		int eatenSeeds = 0;
		if(eatCoord.getX() != coord.getX()) {
			eatenSeeds = copy.eat(eatCoord);
		}
		
		return new AwaleBoard(copy.board(),eatenSeeds);
	}

	/**
	 * Retourne une copie du tableau à deux dimensions représentant le plateau
	 * @return une copie du tableau à deux dimensions
	 */
	public int[][] board(){
		int[][] boardCopy = new int[2][];
		for(int i = 0;i < board.length;i++) {
			boardCopy[i] = Arrays.copyOf(board[i],board[i].length);
		}
		return boardCopy;
	}
	
	private boolean equalsBoard(int[][] otherBoard) {
		for(int i = 0;i < board.length;i++) {
			for(int j = 0;j<board[i].length;j++) {
				if(board[i][j] != otherBoard[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Retourne le nombre de graines dans la case souhaitée
	 * @param i le numéro de la ligne souhaitée
	 * @param j le numéro de la colonne souhaitée
	 * @return retourne le nombre de graines dans le trou
	 */
	public int getSeeds(int i,int j) {
		return board[i][j];
	}
	
	@Override
	public boolean equals(Object other) {
		boolean equals = false;
		if(other instanceof AwaleBoard) {
			AwaleBoard ab = (AwaleBoard) other;
			equals =  this.equalsBoard(ab.board());
		}
		
		return equals;
	}
	
	@Override
	public int hashCode() {
		int code = 0;
		for(int i = 0;i < board.length;i++) {
			for(int j = 0;j < board[i].length;j++) {
				code += board[i][j];
			}
		}
		return code;
	}
	

}
