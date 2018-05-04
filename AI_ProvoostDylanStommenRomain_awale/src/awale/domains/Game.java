package awale.domains;

import java.util.HashSet;
import java.util.Set;

import awale.boards.AwaleBoard;

public class Game {
	
	private Player[] players;
	private Player currentPlayer;
	private AwaleBoard currentBoard;
	private Set<AwaleBoard> previousBoards = new HashSet<>();
	private boolean isCycling = false;
	private boolean turn;
	
	public Game() {
		players = new Player[] {HumanPlayer.ofId(0),HumanPlayer.ofId(1)};
		currentPlayer = players[0];
		currentBoard = new AwaleBoard(new int[][] {{0,0,2,0,0,0}, {0,0,0,0,0,0}},0);
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
		isCycling = previousBoards.contains(currentBoard);
		previousBoards.add(currentBoard);
		
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
		for(int i = 0;i < 6;i++) {
			players[0].setScore(currentBoard.getSeeds(players[0].getId(),i));
			players[1].setScore(currentBoard.getSeeds(players[1].getId(),i));
		}
		
		if(players[0].getScore() < players[1].getScore()) {
			winner = "Joueur 1";
		}else if(players[0].getScore() > players[1].getScore()) {
			winner = "Joueur 2";
		}
		
		return winner;
	}

}
