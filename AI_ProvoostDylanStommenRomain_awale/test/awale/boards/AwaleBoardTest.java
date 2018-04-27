package awale.boards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import awale.boards.AwaleBoard;

public class AwaleBoardTest {
	private AwaleBoard ab;
	private AwaleBoard zero;
	private AwaleBoard bigNumbers;
	
	@Before
	public void determineParamaters() {
		ab = new AwaleBoard();
		int[][] board = new int[][] {{0,0,0,0,0,0},{0,0,0,0,0,0}};
		zero = new AwaleBoard(board);
		bigNumbers = new AwaleBoard(new int[][]{{0,0,0,0,0,14},{0,0,0,0,0,0}});
	}

	@Test
	public void sowsCorrectlyForFirstRow() {
		AwaleBoard result = ab.play(new int[] {0,5});
		int[][] expected = new int[][] {{4,5,5,5,5,0},{4,4,4,4,4,4}};
		
		assertArrayEquals(expected,result.board());
	}
	
	@Test
	public void sowsCorrectlyForSecondRow() {
		AwaleBoard result = ab.play(new int[] {1,0});
		int[][] expected = new int[][] {{4,4,4,4,4,4},{0,5,5,5,5,4}};
		
		assertArrayEquals(expected,result.board());
	}
	
	@Test
	public void sowsCorrectlyWhenAtEndOfFirstRow() {
		AwaleBoard result = ab.play(new int[] {0,0});
		int[][] expected = new int[][] {{0,4,4,4,4,4},{5,5,5,5,4,4}};
		
		assertArrayEquals(expected,result.board());
	}
	
	@Test
	public void sowsCorrectlyWhenAtEndOfSecondRow() {
		AwaleBoard result = ab.play(new int[] {1,5});
		int[][] expected = new int[][] {{4,4,5,5,5,5},{4,4,4,4,4,0}};
		
		assertArrayEquals(expected,result.board());
	}
	
	@Test
	public void doesntSowWhenCaseIsAtZero() {
		AwaleBoard result = zero.play(new int[] {1,5});
		int[][] expected = new int[][] {{0,0,0,0,0,0},{0,0,0,0,0,0}};
		
		assertArrayEquals(expected,result.board());
	}
	
	@Test
	public void sowsCorrectlyWhenNumberOfSeedsExceedsTwelve() {
		AwaleBoard result = bigNumbers.play(new int[] {0,5});
		int[][] expected = new int[][] {{1,1,1,0,0,1},{1,1,1,1,1,1}};
		
		assertArrayEquals(expected,result.board());
	}

}
