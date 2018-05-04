package awale.boards;

import java.util.Arrays;

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
	
	private int[] sow(int[] coord) {
		int nbSeed = board[coord[0]][coord[1]];
		int[] newCoord = Arrays.copyOf(coord, coord.length);
		board[coord[0]][coord[1]] = 0;
		while (nbSeed > 0) {
			newCoord = determineCoordSow(newCoord);
			if(newCoord[0] == coord[0] && newCoord[1] == coord[1]) {
				board[newCoord[0]][newCoord[1]] = 0;
			}else {
				board[newCoord[0]][newCoord[1]] += 1;
				nbSeed--;
			}
		}
		return newCoord;
	}

	private int[] determineCoordSow(int[] coord) {
		if(coord[0] == 0 && coord[1] == 0) {
			coord = new int[] {1,0};
		}else if(coord[0] == 1 && coord[1] == 5) {
			coord = new int[] {0,5};
		}else {
			if(coord[0] == 0) {
				coord[1] -= 1;
			}else {
				coord[1] += 1;
			}
		}
		return coord;
	}
	
	private int eat(int[] coord) {
		int seedCount = 0;
		int count = 0;
		while (count < 4 && (board[coord[0]][coord[1]] == 2 || board[coord[0]][coord[1]] == 3)) {
			seedCount += board[coord[0]][coord[1]];
			board[coord[0]][coord[1]] = 0;
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
	
	private int[] determineCoordEat(int[] coord) {
		if(coord[0] == 0 && coord[1] == 5) {
			coord = new int[] {0,5};
		}else if(coord[0] == 1 && coord[1] == 0) {
			coord = new int[] {1,0};
		}else {
			if(coord[0] == 0) {
				coord[1] += 1;
			}else {
				coord[1] -= 1;
			}
		}
		return coord;
	}
	
	public int getEatenSeeds() {
		return eatenSeeds;
	}
	
	public AwaleBoard play(int[] coord) {
		AwaleBoard copy = new AwaleBoard(this.board(),this.getEatenSeeds());
		int[] eatCoord = copy.sow(coord);
		int eatenSeeds = 0;
		if(eatCoord[0] != coord[0]) {
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
