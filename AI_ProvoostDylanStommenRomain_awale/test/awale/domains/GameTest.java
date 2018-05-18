package awale.domains;

import static org.junit.Assert.*;

import org.junit.Test;

import awale.boards.Coordinate;

public class GameTest {

	@Test
	public void movesTheSeeds() {
		Game g = new Game(new HumanPlayer(0),new HumanPlayer(1),new int[][] {{1,0,0,0,0,0},{0,0,0,0,0,0}});
		assertEquals(0,g.giveCoord(new Coordinate(0,2)));
		assertEquals(1,g.giveCoord(new Coordinate(0,0)));
		assertEquals(0,g.getCurrentPlayerID());
		g.play();
		assertEquals(1,g.getCurrentPlayerID());
		assertEquals("Joueur 2",g.end());
		assertArrayEquals(new int[] {0,1},g.getScores());
	}

}
