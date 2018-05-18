package awale.domains;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import awale.boards.AwaleBoard;
import awale.boards.Coordinate;

public class ComputerPlayerTest {
	private ComputerPlayer cpu;
	
	@Before
	public void prepareComputer() {
		cpu = new ComputerPlayer(1);
	}

	@Test
	public void playAValidCoordinate() {
		AwaleBoard ab = new AwaleBoard(new int[][] {{0,0,0,0,1,1},{0,0,0,4,0,0}},0);
		cpu.setCurrentCoord(new Coordinate(0,0), ab, ab.checkStarvation(0));
	    ab = ab.play(cpu.getCurrentCoord());
	    cpu.setScore(ab.getEatenSeeds());
	    assertEquals(4,cpu.getScore());
		assertEquals(new Coordinate(1,3),cpu.getCurrentCoord());
	}

	@Test
	public void playTheBestCoordinate() {
		AwaleBoard ab = new AwaleBoard(new int[][] {{0,0,0,0,1,1},{0,0,0,4,0,0}},0);
		cpu.setCurrentCoord(new Coordinate(0,0), ab, ab.checkStarvation(0));
		assertEquals(new Coordinate(1,3),cpu.getCurrentCoord());
	}
	
	@Test
	public void detectStarvation() {
		AwaleBoard ab = new AwaleBoard(new int[][] {{0,0,0,0,0,0},{0,0,0,1,0,0}},0);
		
		assertEquals(-1,cpu.setCurrentCoord(new Coordinate(0,0), ab, ab.checkStarvation(0)));
	}
}
