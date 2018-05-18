package awale.domains;

import java.util.HashSet;
import java.util.Set;

import awale.boards.AwaleBoard;
import awale.boards.Coordinate;

public class Game {
	
	private Player[] players;
	private Player currentPlayer;
	private AwaleBoard currentBoard;
	private Set<AwaleBoard> previousBoards;
	private boolean isCycling;
	private boolean turn;
	
	/**
	 * Ce constructeur permet d'initialiser une partie
	 * @param player1 le joueur 1
	 * @param player2 le joueur 2
	 */
	public Game(Player player1,Player player2) {
		players = new Player[] {player1,player2};
		currentPlayer = players[0];
		currentBoard = new AwaleBoard();
		isCycling = false;
		previousBoards = new HashSet<>();
		turn = false;
	}
	
	/**
	 * Ce constucteur permet d'initialiser une partie
	 * @param player1 le joueur 1
	 * @param player2 le joueur 2
	 * @param board le plateau de départ
	 */
	public Game(Player player1,Player player2,int[][] board) {
		players = new Player[] {player1,player2};
		currentPlayer = players[0];
		currentBoard = new AwaleBoard(board,0);
		isCycling = false;
		previousBoards = new HashSet<>();
		turn = false;
	}
	
	/**
	 * Cette méthode permet de donner une coordonée au joueur courant 
	 * @param coord la coordonnée donnée
	 * @return 1 si la coordonée est valide, 0 si elle ne l'est pas et -1 s'il n'y a pas de coordonnée possible
	 */
	public int giveCoord(Coordinate coord) {
		return currentPlayer.setCurrentCoord(coord,currentBoard,checkStarvation());
	}
	
	private boolean checkStarvation() {
		if(turn) {
			return currentBoard.checkStarvation(0);
		}else {
			return currentBoard.checkStarvation(1);
		}
	}
	
	/**
	 * Cette méthode permet de jouer un coup
	 */
	public void play() {
		isCycling = !previousBoards.add(currentBoard);
		
		
		currentBoard = currentBoard.play(currentPlayer.getCurrentCoord());
		currentPlayer.setScore(currentBoard.getEatenSeeds());
		
		if(turn) {
			currentPlayer = players[0];
			turn = false;
		}else {
			currentPlayer = players[1];
			turn = true;
		}
	}
	
	/**
	 * Cette méthode permet de déterminer s'il y a un cycle
	 * @return true si c'est le cas, false sinon
	 */
	public boolean isCycling() {
		return isCycling;
	}
	
	/**
	 * Cette méthode permet d'obtenir le plateau actuel
	 * @return le plateau actuel
	 */
	public AwaleBoard getCurrentBoard() {
		return currentBoard;
	}
	
	/**
	 * Cette méthode permet	d'obtenir l'id du joueur courant
	 * @return id du joueur courant
	 */
	public int getCurrentPlayerID() {
		return currentPlayer.getId();
	}
	
	/**
	 * Cette méthode permet d'obtenir le score des deux joueurs
	 * @return un tableau contenant les scores des deux joueurs
	 */
	public int[] getScores() {
		int[] scores = new int[2];
		for(int i = 0; i < players.length; i++) {
			scores[i] = players[i].getScore();
		}
		
		return scores;
	}
	
	/**
	 * Cette méthode permet de déterminer le vainqueurde la partie
	 * @return Joueur 1 si son score est plus élevé que celui du J2
	 * Joueur2 si son score est plus élevé que celui du J1
	 * Egalité autrement
	 */
	public String end() {
		String winner = "Egalité";
		setPlayerScore();
		
		if(players[0].getScore() > players[1].getScore()) {
			winner = "Joueur 1";
		}else if(players[0].getScore() < players[1].getScore()) {
			winner = "Joueur 2";
		}
		
		return winner;
	}

	/**
	 * Cette méthode permet de définir les scores de joueurs à la fin d'une partie
	 */
	private void setPlayerScore() {
		for(int i = 0;i < 6;i++) {
			players[0].setScore(currentBoard.getSeeds(players[0].getId(),i));
			players[1].setScore(currentBoard.getSeeds(players[1].getId(),i));
		}
	}
	
	/**
	 * Cette méthode permet de déterminer si le joueur courant est en famine
	 * @return true s'il l'est, false sinon
	 */
	public boolean starvationSelf() {
		return currentBoard.checkStarvation(currentPlayer.getId());
	}

}
