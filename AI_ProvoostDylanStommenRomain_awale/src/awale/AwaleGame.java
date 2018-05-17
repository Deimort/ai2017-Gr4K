package awale;

import org.newdawn.slick.state.StateBasedGame;

import awale.domains.Statistics;
import awale.states.GameOverState;
import awale.states.HumanVsComputerState;
import awale.states.HumanVsHumanState;
import awale.states.MenuGameState;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class AwaleGame extends StateBasedGame {

	public AwaleGame(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		Statistics stats = new Statistics();
		addState(new MenuGameState(this));
		addState(new HumanVsHumanState(this,stats));
		addState(new HumanVsComputerState(this,stats));
		addState(new GameOverState(this,stats));
		
	}

}
