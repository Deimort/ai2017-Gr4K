package awale.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MenuGameState extends AwaleStates{

	private Image background;
	public MenuGameState(StateBasedGame sb) {
		super(0, "Menu Principal",sb);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("Images/Savane.jpg");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);
		g.drawString("Awale", 30, 70);
		g.drawString("H - Humain VS Humain", 30, 100);
		g.drawString("M - Humain VS Machine", 30, 130);
		g.drawString("Q - Quitter", 30, 160);
		
		
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
	
	public Image getImage() {
		return background;
	}

}
