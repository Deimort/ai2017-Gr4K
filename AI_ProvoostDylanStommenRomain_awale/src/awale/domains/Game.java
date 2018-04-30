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
		players = new Player[] {Player.ofId(0),Player.ofId(1)};
		currentPlayer = players[0];
		currentBoard = new AwaleBoard();
		turn = false;
	}
	
	public void giveCoord(int[] coord) {
		currentPlayer.setCurrentCoord(coord);
	}
	
	public void play() {
		isCycling = previousBoards.contains(currentBoard);
		previousBoards.add(currentBoard);
		
		currentBoard = currentBoard.play(currentPlayer.getCurrentCoord());
		turn = !turn;
		if(turn) {
			currentPlayer = players[0];
		}else {
			currentPlayer = players[1];
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


}
