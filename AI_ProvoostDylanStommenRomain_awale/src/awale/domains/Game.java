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
	
	public Game(Player player1,Player player2) {
		players = new Player[] {player1,player2};
		currentPlayer = players[0];
		currentBoard = new AwaleBoard();
		isCycling = false;
		previousBoards = new HashSet<>();
		turn = false;
	}
	
	public Game(Player player1,Player player2,int[][] board) {
		players = new Player[] {player1,player2};
		currentPlayer = players[0];
		currentBoard = new AwaleBoard(board,0);
		isCycling = false;
		previousBoards = new HashSet<>();
		turn = false;
	}
	
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
	
	public boolean isCycling() {
		return isCycling;
	}
	
	public AwaleBoard getCurrentBoard() {
		return currentBoard;
	}
	
	public int getCurrentPlayerID() {
		return currentPlayer.getId();
	}
	
	public int[] getScores() {
		int[] scores = new int[2];
		for(int i = 0; i < players.length; i++) {
			scores[i] = players[i].getScore();
		}
		
		return scores;
	}
	
	public String end() {
		String winner = "Égalité";
		setPlayerScore();
		
		if(players[0].getScore() > players[1].getScore()) {
			winner = "Joueur 1";
		}else if(players[0].getScore() < players[1].getScore()) {
			winner = "Joueur 2";
		}
		
		return winner;
	}

	private void setPlayerScore() {
		for(int i = 0;i < 6;i++) {
			players[0].setScore(currentBoard.getSeeds(players[0].getId(),i));
			players[1].setScore(currentBoard.getSeeds(players[1].getId(),i));
		}
	}
	
	public boolean starvationSelf() {
		return currentBoard.checkStarvation(currentPlayer.getId());
	}

}
