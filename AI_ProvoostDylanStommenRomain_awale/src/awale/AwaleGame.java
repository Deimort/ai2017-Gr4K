package awale;

import org.newdawn.slick.state.StateBasedGame;

import awale.states.GameOverState;
import awale.states.HumanVsComputerState;
import awale.states.HumanVsHumanState;
import awale.states.MenuGameState;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class AwaleGame extends StateBasedGame {

	public AwaleGame(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new MenuGameState());
		addState(new HumanVsHumanState());
		addState(new HumanVsComputerState());
		addState(new GameOverState());
		
	}

	@Override
	public void keyPressed(int key, char c) {
		if(key == Input.KEY_H && this.getCurrentStateID() == 0) {
			System.out.println("HVH");
			this.enterState(1);
		}
		else if(key == Input.KEY_M && this.getCurrentStateID() == 0) {
			System.out.println("HVM");
			this.enterState(2);
		}
		else if(key == Input.KEY_SPACE && (this.getCurrentStateID() == 1 || this.getCurrentStateID() == 2)) {
			System.out.println("Game Over");
			this.enterState(3);
		}
		else {
			System.out.println("MM");
		}
		
	}

}
