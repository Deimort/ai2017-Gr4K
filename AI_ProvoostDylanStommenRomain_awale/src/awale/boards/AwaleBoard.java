package awale.boards;

import java.util.Arrays;

public class AwaleBoard {
	
	private int[][] board;
	private int eatenSeeds;
	
	public AwaleBoard() {
		board = new int[2][6];
		for(int i = 0;i < board.length;i++) {
			Arrays.fill(board[i],4);
		}
	}
	
	public AwaleBoard(int[][] board) {
		this.board = board;
	}
	
	private int[] sow(int[] coord) {
		int nbSeed = board[coord[0]][coord[1]];
		board[coord[0]][coord[1]] = 0;
		for (int i = nbSeed; i > 0; i--) {
			coord = determineCoordSow(coord);
			board[coord[0]][coord[1]] += 1;

		}
		return coord;
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
			coord = new int[] {1,5};
		}else if(coord[0] == 1 && coord[1] == 0) {
			coord = new int[] {0,0};
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
		AwaleBoard copy = new AwaleBoard(this.board());
		eatenSeeds = copy.eat(copy.sow(coord));
		
		return copy;
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
