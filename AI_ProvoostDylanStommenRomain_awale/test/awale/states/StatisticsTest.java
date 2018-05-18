package awale.states;

import static org.junit.Assert.*;

import org.junit.Test;

public class StatisticsTest {

	@Test
	public void getterReturnsTheRightAttributes() {
		Statistics st = new Statistics();
		st.endGame(90000, new int[] {24,24});
		assertEquals("01:30",st.getCurrentGameTime());
		assertEquals(24,st.getScore(0));
		st.endGame(30000, new int[] {22,2});
		assertEquals("00:30",st.getCurrentGameTime());
		assertEquals("01:00",st.getAverageTime());
	}

}
