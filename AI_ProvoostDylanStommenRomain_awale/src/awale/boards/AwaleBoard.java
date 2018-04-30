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
		if(coord[0] >= 0) {
			int nbSeed = board[coord[0]][coord[1]];
			board[coord[0]][coord[1]] = 0;
			for(int i = nbSeed;i > 0;i--) {
				coord = determineCoordSow(coord);
				board[coord[0]][coord[1]] += 1;
				
			}
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
		if(coord[0] >= 0) {
			int count = 0;
			while(count < 4 && (board[coord[0]][coord[1]] == 2 || board[coord[0]][coord[1]] == 3)) {
				seedCount += board[coord[0]][coord[1]];
				board[coord[0]][coord[1]] = 0;
				coord = determineCoordEat(coord);
				count++;
			}
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
		return board;
	}
	

}
