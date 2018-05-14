package awale;

import org.newdawn.slick.state.StateBasedGame;

import awale.states.GameOverState;
import awale.states.HumanVsComputerState;
import awale.states.HumanVsHumanState;
import awale.states.MenuGameState;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class AwaleGame extends StateBasedGame {

	public AwaleGame(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		List<String> winner = new ArrayList<>();
		addState(new MenuGameState(this));
		addState(new HumanVsHumanState(this,winner));
		addState(new HumanVsComputerState(this,winner));
		addState(new GameOverState(this,winner));
		
	}

}
