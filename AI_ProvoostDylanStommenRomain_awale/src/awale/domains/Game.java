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
		players = new HumanPlayer[] {HumanPlayer.ofId(0),HumanPlayer.ofId(1)};
		currentPlayer = players[0];
		currentBoard = new AwaleBoard();
		turn = false;
	}
	
	public boolean giveCoord(int[] coord) {
		return currentPlayer.setCurrentCoord(coord,currentBoard);
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

}
