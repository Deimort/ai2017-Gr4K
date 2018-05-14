package awale.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class AwaleStates extends BasicGameState {
	
	private int id;
	private String description;
	private StateBasedGame sb;
	private Image background;

	public AwaleStates(int id, String description,StateBasedGame sb) {
		this.id = id;
		this.description = description;
		this.sb = sb;
		try {
			this.background = new Image("Images/Savane.jpg");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int deltaTime) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	public Image getImage() {
		return background;
	}

	public void setState(int id) {
		sb.enterState(id);
	}
	
	public void exitGame() {
		sb.getContainer().exit();
	}
	
	public GameState getState(int id) {
		return sb.getState(id);
	}
	
	public GameContainer getContainer() {
		return sb.getContainer();
	}
	
	
	public void initNewGame(int id) {
		try {
			getState(id).init(getContainer(), sb);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
