package awale.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class HumanVsHumanState extends AwaleStates{

	MenuGameState ms;
	public HumanVsHumanState(StateBasedGame sb) {
		super(1, "HumanVsHuman",sb);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(ms.getImage(), 0, 0);
		g.drawString("HVH", gc.getWidth()/2, gc.getHeight()/2);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int deltaTime) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(int key,char c) {
		if(key == Input.KEY_SPACE) {
			sb.enterState(3);
		}
	}

}
