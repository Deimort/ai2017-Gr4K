package awale.boards;

import java.util.Arrays;

import awale.domains.Coordinate;

public class AwaleBoard {
	
	private final int[][] board;
	private final int eatenSeeds;
	
	public AwaleBoard() {
		board = new int[2][6];
		for(int i = 0;i < board.length;i++) {
			Arrays.fill(board[i],4);
		}
		this.eatenSeeds = 0;
	}
	
	public AwaleBoard(int[][] board,int eatenSeeds) {
		this.board = board;
		this.eatenSeeds = eatenSeeds;
	}
	
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

	public boolean checkStarvation(int row) {
		for(int a : board[row]) {
			if(a != 0) {
				return false;
			}
		}
		return true;
	}
	
	private Coordinate determineCoordEat(Coordinate coord) {
		if(checkIfEndFirstRow(coord)) {
			coord = new Coordinate(0,5);
		}else if(checkIfEndSecondRow(coord)) {
			coord = new Coordinate(1,0);
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
	
	public int getEatenSeeds() {
		return eatenSeeds;
	}
	
	public AwaleBoard play(Coordinate coord) {
		AwaleBoard copy = new AwaleBoard(this.board(),this.getEatenSeeds());
		Coordinate eatCoord = copy.sow(coord);
		int eatenSeeds = 0;
		if(eatCoord.getX() != coord.getX()) {
			eatenSeeds = copy.eat(eatCoord);
		}
		
		return new AwaleBoard(copy.board(),eatenSeeds);
	}

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
//		String[] code = new String[] {"",""};
//		for(int i = 0;i < board.length;i++) {
//			for(int j = 0;j < board[i].length;j++) {
//				code[i] += board[i][j];
//			}
//		}
//		
//		return Integer.parseInt(code[0])+Integer.parseInt(code[1]);
		int[] code = new int[2];
		for(int i = 0;i < board.length;i++) {
			for(int j = 0;j < board[i].length;j++) {
				code[i] += board[i][j];
			}
		}
		return code[0] + code[1];
	}
	

}
