package awale.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameOverState extends AwaleStates{
	private String winner;

	public GameOverState(StateBasedGame sb) {
		super(3, "GameOver",sb);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(getImage(), 0, 0);
		g.drawString("Gagnant : "+ winner , gc.getWidth()/2, gc.getHeight()/2);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int deltaTime) throws SlickException {
		winner = super.getWinner();
		
	}
	@Override
	public void keyPressed(int key,char c) {
		if(key == Input.KEY_SPACE) {
			sb.enterState(0);
		}
	}

}
