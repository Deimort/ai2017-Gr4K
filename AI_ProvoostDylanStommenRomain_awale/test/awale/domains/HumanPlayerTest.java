package awale.domains;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import awale.boards.AwaleBoard;
import awale.boards.Coordinate;

public class HumanPlayerTest {
	AwaleBoard starvationEnd;
	AwaleBoard starvation;
	Player p1;
	
	@Before
	public void createParameters() {
		starvationEnd = new AwaleBoard(new int[][] {{0,1,2,3,4,5},{0,0,0,0,0,0}},0);
		starvation = new AwaleBoard(new int[][] {{0,6,2,3,4,5},{0,0,0,0,0,0}},0);
		p1 = new HumanPlayer(0);
	}

	@Test
	public void determineStarvationIsEndingGame() {
		assertEquals(p1.setCurrentCoord(new Coordinate(0,0),starvationEnd,true),-1);
		assertEquals(p1.setCurrentCoord(new Coordinate(0,1),starvationEnd,true),-1);
		assertEquals(p1.setCurrentCoord(new Coordinate(0,2),starvationEnd,true),-1);
		assertEquals(p1.setCurrentCoord(new Coordinate(0,3),starvationEnd,true),-1);
		assertEquals(p1.setCurrentCoord(new Coordinate(0,4),starvationEnd,true),-1);
		assertEquals(p1.setCurrentCoord(new Coordinate(0,5),starvationEnd,true),-1);
		
	}
	
	@Test
	public void determineStarvationIsDetectingTheCoordinates() {
		assertEquals(p1.setCurrentCoord(new Coordinate(0,0),starvation,true),0);
		assertEquals(p1.setCurrentCoord(new Coordinate(0,1),starvation,true),1);
		assertEquals(p1.setCurrentCoord(new Coordinate(0,2),starvation,true),0);
		assertEquals(p1.setCurrentCoord(new Coordinate(0,3),starvation,true),0);
		assertEquals(p1.setCurrentCoord(new Coordinate(0,4),starvation,true),0);
		assertEquals(p1.setCurrentCoord(new Coordinate(0,5),starvation,true),0);
		
	}

}
