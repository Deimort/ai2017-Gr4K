package awale;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;

public class AwaleGameTest {

	AwaleGame ag;
	AppGameContainer appgc;
	@Before
	public void createparameters() throws SlickException {
		ag = new AwaleGame("Awale");
		try {
			ag.initStatesList(appgc);
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void currentStateIsMainMenu() {
		assertEquals(0, ag.getCurrentStateID());
		
	}
	
	@Test
	public void changeState() {
		GameState gs = ag.getState(2);
		assertEquals(2, gs.getID());
	}
	


}
