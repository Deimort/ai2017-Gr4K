package awale.states;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import awale.boards.AwaleBoard;
import awale.domains.Coordinate;
import awale.domains.Game;

public class HumanVsHumanState extends AwaleStates{
	
	private Map<Character,Coordinate> conversion;
	private Game currentGame;
	private int[] scores;
	private AwaleBoard board;
	private TrueTypeFont font;

	MenuGameState ms;
	public HumanVsHumanState(StateBasedGame sb) {
		super(1, "HumanVsHuman",sb);
		conversion = new TreeMap<>();
		String azerty = "azertyqsdfgh";
		int compteur = 0;
		for(int i = 0;i < 2;i++) {
			for(int j = 0;j < 6;j++) {
				conversion.put(azerty.charAt(compteur), new Coordinate (i,j));
				compteur++;
			}
		}
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.currentGame = new Game();
		font = new TrueTypeFont(new java.awt.Font("RockWell", Font.PLAIN, 20), false);
		winner = new ArrayList<>();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(getImage(), 0, 0);
		g.setFont(font);
		g.setColor(Color.white);
		
		g.drawString("JOUEUR CONTRE JOUEUR ", 20, 40);
		g.drawString("PLAYER 1 : " + scores[0], 20, 70);
		g.drawString("PLAYER 2 : " + scores[1], 20, 100);
		
		g.setColor(Color.yellow);
		g.drawString("AU TOUR DU JOUEUR " + (currentGame.getCurrentPlayerID() + 1), 20, 130);
		
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
		
	}
	
	@Override
	public void keyPressed(int key,char c) {
		if(key == Input.KEY_SPACE) {
			sb.enterState(3);
		}
		
		Coordinate coord = conversion.get(c);
		if(coord == null) {
			coord = new Coordinate(-1,-1);
		}
		if(currentGame.isCycling()) {
			currentGame.end();
			sb.enterState(3);
		}
		
		if(coord.getX() >= 0 && currentGame.giveCoord(coord) == 1) {
			currentGame.play();
		}else if(currentGame.giveCoord(coord) == -1) {
			super.setWinner(currentGame.end());
			sb.enterState(3);
		}
		
	}

}
