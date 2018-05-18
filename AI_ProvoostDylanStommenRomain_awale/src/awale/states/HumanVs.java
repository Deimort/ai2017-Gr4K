package awale.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import awale.boards.AwaleBoard;
import awale.boards.Coordinate;
import awale.domains.Game;

public abstract class HumanVs extends AwaleStates{
	
	private Game currentGame;
	private int[] scores;
	private AwaleBoard board;
	private TrueTypeFont font;
	private Statistics stats;
	private int currentGameTime;

	public HumanVs(int id, String description, StateBasedGame sb,Statistics stats) {
		super(id, description, sb);
		this.stats = stats;
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(getImage(), 0, 0);
		g.setFont(font);
		g.setColor(Color.white);
		
		drawScore(g);
		
		g.setColor(Color.yellow);
		g.drawString("AU TOUR DU JOUEUR " + (currentGame.getCurrentPlayerID() + 1), 20, 130);
		
		generateSquare(g);
		
		g.setColor(Color.white);
		g.drawString("Appuyez sur P pour abandonner", gc.getWidth() - 300, gc.getHeight() - 35);
		
	}
	
	private void drawScore(Graphics g) {
		g.drawString("JOUEUR CONTRE " + getDescription(), 20, 40);
		g.drawString("PLAYER 1 : " + scores[0], 20, 70);
		g.drawString("PLAYER 2 : " + scores[1], 20, 100);
	}

	private void generateSquare(Graphics g) {
		g.setColor(Color.white);
		for(int ligne = 0; ligne < 2; ligne++) {
			for(int colonne = 0; colonne < 6; colonne++) {
				g.drawRect(100+colonne*100, 200 + ligne*100, 100, 100);
				g.drawString(String.format("%02d", board.getSeeds(ligne, colonne)), 145+colonne*100, 240+ ligne*100);
			}
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int deltaTime) throws SlickException {
		this.scores = currentGame.getScores();
		board = currentGame.getCurrentBoard();
		currentGameTime += deltaTime;
		
	}
	
	public void setGame(Game game) {
		this.currentGame = game;
	}
	
	public void setFont(TrueTypeFont font) {
		this.font = font;
	}
	
	public void setCurrentGameTime(int time) {
		this.currentGameTime = time;
	}
	
	public int giveCoord(Coordinate coord) {
		return currentGame.giveCoord(coord);
	}
	
	public void play() {
		currentGame.play();
	}
	
	public void end() {
		currentGame.end();
	}
	
	public void isCyclingOrWinningStarv() {
		if(currentGame.isCycling() || currentGame.starvationSelf()) {
			endGame();
		}
	}
	
	public void endGame() {
		currentGame.end();
		stats.endGame(currentGameTime, currentGame.getScores());
		setState(3);
	}

}
