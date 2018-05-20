package awale.states;



import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameOverState extends AwaleStates{
	private Statistics stats;

	public GameOverState(StateBasedGame sb,Statistics stats) {
		super(3, "GameOver",sb);
		this.stats = stats;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		drawScores(g);
		
		
		drawWinnerAndDuration(g);
		
		g.drawLine(375, 150, 375, 375);
		
		drawVictories(g);
		
		drawTimeStats(g);
		
	}

	private void drawTimeStats(Graphics g) {
		g.drawString("DUREE MOYENNE : " + stats.getAverageTime(), 425, 325);
		g.drawString("DUREE MINIMUM : " + stats.getMinTime(), 425, 350);
		g.drawString("DUREE MAXIMUM : " + stats.getMaxTime(), 425, 375);
	}

	private void drawVictories(Graphics g) {
		g.drawString("STATISTIQUES", 425, 150);
		
		g.drawString("VICTOIRES J1", 425, 225);
		g.drawString("" + stats.getVictories(0), 425, 250);
		
		g.drawString("VICTOIRES J2", 625, 225);
		g.drawString("" + stats.getVictories(1), 625, 250);
	}

	private void drawWinnerAndDuration(Graphics g) {
		String winner = "Égalité";
		if(stats.getWinnerId() != -1) {
			winner = "VICTOIRE DU JOUEUR " + (stats.getWinnerId()+1);
		}
		
		g.drawString(winner, 50, 300);
		g.drawString("Duree de la partie : " + stats.getCurrentGameTime() , 50, 325);
	}

	private void drawScores(Graphics g) {
		g.drawImage(getImage(), 0, 0);
		g.drawString("Fin de partie", 50, 150);
		
		g.drawString("SCORE J1", 50, 225);
		g.drawString("" + stats.getScore(0), 50, 250);
		
		g.drawString("SCORE J2", 250, 225);
		g.drawString("" + stats.getScore(1), 250, 250);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int deltaTime) throws SlickException {
		
	}
	@Override
	public void keyPressed(int key,char c) {
		if(key == Input.KEY_SPACE) {
			setState(0);
		}
		
	}

}
