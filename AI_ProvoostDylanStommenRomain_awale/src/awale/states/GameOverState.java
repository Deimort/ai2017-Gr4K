package awale.states;


import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameOverState extends AwaleStates{
	
	private List<String> winner;
	private String winnerString;

	public GameOverState(StateBasedGame sb,List<String> winner) {
		super(3, "GameOver",sb);
		this.winner = winner;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.drawImage(getImage(), 0, 0);
		g.drawString("Fin de partie", 50, 150);
		
		g.drawString("SCORE J1", 50, 225);
		g.drawString("00", 50, 250);
		
		g.drawString("SCORE J2", 250, 225);
		g.drawString("00", 250, 250);
		
		g.drawString("VICTOIRE DU JOUEUR ...", 50, 300);
		g.drawString("Duree de la partie : non-définie", 50, 325);
		
		g.drawString("STATISTIQUES", 425, 150);
		
		g.drawString("VICTOIRES J1", 425, 225);
		g.drawString("00", 425, 250);
		
		g.drawString("VICTOIRES J2", 625, 225);
		g.drawString("00", 625, 250);
		
		g.drawString("DUREE MOYENNE", 425, 325);
		g.drawString("DUREE MINIMUM", 425, 350);
		g.drawString("DUREE DE LA PARTIE", 425, 375);
		//g.drawString("Gagnant : " + winnerString , gc.getWidth()/2-70, gc.getHeight()/2);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int deltaTime) throws SlickException {
		//winnerString = winner.get(0);
	}
	@Override
	public void keyPressed(int key,char c) {
		if(key == Input.KEY_SPACE && winnerString != null) {
			setState(0);
		}
		
	}

}
