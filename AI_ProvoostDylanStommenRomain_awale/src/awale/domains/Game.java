package awale.domains;

import java.util.HashSet;
import java.util.Set;

import awale.boards.AwaleBoard;
import awale.boards.Coordinate;

/**
 * Implémente une partie avec son plateau de jeu. Permet de gérer le tour des joueurs ainsi que le score et 
 * la fin de la partie.
 */

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
	 * @param board le plateau de d�part
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
	 * Cette m�thode permet de donner une coordon�e au joueur courant 
	 * @param coord la coordonn�e donn�e
	 * @return 1 si la coordon�e est valide, 0 si elle ne l'est pas et -1 s'il n'y a pas de coordonn�e possible
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
	 * Cette m�thode permet de jouer un coup
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
	 * Cette m�thode permet de d�terminer s'il y a un cycle
	 * @return true si c'est le cas, false sinon
	 */
	public boolean isCycling() {
		return isCycling;
	}
	
	/**
	 * Cette m�thode permet d'obtenir le plateau actuel
	 * @return le plateau actuel
	 */
	public AwaleBoard getCurrentBoard() {
		return currentBoard;
	}
	
	/**
	 * Cette m�thode permet	d'obtenir l'id du joueur courant
	 * @return id du joueur courant
	 */
	public int getCurrentPlayerID() {
		return currentPlayer.getId();
	}
	
	/**
	 * Cette m�thode permet d'obtenir le score des deux joueurs
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
	 * Cette m�thode permet de d�terminer le vainqueurde la partie
	 * @return Joueur 1 si son score est plus �lev� que celui du J2
	 * Joueur2 si son score est plus �lev� que celui du J1
	 * Egalit� autrement
	 */
	public String end() {
		String winner = "Egalit�";
		setPlayerScore();
		
		if(players[0].getScore() > players[1].getScore()) {
			winner = "Joueur 1";
		}else if(players[0].getScore() < players[1].getScore()) {
			winner = "Joueur 2";
		}
		
		return winner;
	}

	/**
	 * Cette m�thode permet de d�finir les scores de joueurs � la fin d'une partie
	 */
	private void setPlayerScore() {
		for(int i = 0;i < 6;i++) {
			players[0].setScore(currentBoard.getSeeds(players[0].getId(),i));
			players[1].setScore(currentBoard.getSeeds(players[1].getId(),i));
		}
	}
	
	/**
	 * Cette m�thode permet de d�terminer si le joueur courant est en famine
	 * @return true s'il l'est, false sinon
	 */
	public boolean starvationSelf() {
		return currentBoard.checkStarvation(currentPlayer.getId());
	}

}
