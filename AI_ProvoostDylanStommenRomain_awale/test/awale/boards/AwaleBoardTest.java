package awale.boards;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import awale.boards.AwaleBoard;

public class AwaleBoardTest {
	private AwaleBoard ab;
	private AwaleBoard zero;
	private AwaleBoard bigNumbers;
	private AwaleBoard normalSize;
	private AwaleBoard canNotEatAll;
	private AwaleBoard bigNumbersCopy;
	
	@Before
	public void determineParamaters() {
		ab = new AwaleBoard();
		zero = new AwaleBoard(new int[][] {{0,0,0,0,0,0},{0,0,0,0,0,0}},0);
		bigNumbers = new AwaleBoard(new int[][]{{0,0,0,0,0,14},{0,0,0,0,0,0}},0);
		normalSize = new AwaleBoard(new int[][] {{4,0,0,0,0,0}, {1,1,1,1,0,0}},0);
		canNotEatAll = new AwaleBoard(new int[][] {{5,0,0,0,0,0}, {1,1,1,1,1,0}},0);
		bigNumbersCopy = new AwaleBoard(new int[][]{{0,0,0,0,0,14},{0,0,0,0,0,0}},0);
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
		int[] coord = new int[] {0,5};
		AwaleBoard result = bigNumbers.play(coord);
		int[][] expected = new int[][] {{1,1,2,2,2,0},{1,1,1,1,1,1}};
		
		assertArrayEquals(expected,result.board());
	}
	
	@Test
	public void eatFourSeeds() {
		AwaleBoard result = normalSize.play(new int[] {0,0});
		int [][] expected = new int[][] {{0,0,0,0,0,0}, {0,0,0,0,0,0}};
		
		assertArrayEquals(expected, result.board());
	}
	
	@Test
	public void eatsFourSeedsOnFive() {
		AwaleBoard result = canNotEatAll.play(new int[] {0,0});
		int [][] expected = new int[][] {{0,0,0,0,0,0}, {2,0,0,0,0,0}};
		
		assertArrayEquals(expected, result.board());
	}
	
	@Test
	public void testCheckStarvation() {
		assertTrue(zero.checkStarvation(0));
		assertTrue(zero.checkStarvation(1));
		assertFalse(normalSize.checkStarvation(0));
	}
	
	@Test
	public void hashCodeEqualsOverride() {
		assertTrue(bigNumbers.equals(bigNumbersCopy));
		assertEquals(bigNumbers.hashCode(),bigNumbersCopy.hashCode());
	}

}
