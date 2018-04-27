package awale.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MenuGameState extends AwaleStates{

	public MenuGameState(StateBasedGame sb) {
		super(0, "Menu Principal",sb);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("MM", gc.getWidth()/2, gc.getHeight()/2);
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int deltaTime) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(int key,char c) {
		if(key == Input.KEY_H) {
			sb.enterState(1);
		}
		else if(key == Input.KEY_M) {
			sb.enterState(2);
		}
		else if(key == Input.KEY_Q) {
			sb.getContainer().exit();
		}
	}

}
