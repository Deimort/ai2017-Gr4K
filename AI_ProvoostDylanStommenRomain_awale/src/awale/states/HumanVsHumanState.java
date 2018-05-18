package awale.states;

import java.awt.Font;
import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import awale.boards.Coordinate;
import awale.domains.Game;
import awale.domains.HumanPlayer;

public class HumanVsHumanState extends HumanVs{
	
	private Map<Character,Coordinate> conversion;
	

	public HumanVsHumanState(StateBasedGame sb,Statistics stats) {
		super(1, "HUMAIN",sb,stats);
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
		setGame(new Game(new HumanPlayer(0),new HumanPlayer(1)));
		setFont(new TrueTypeFont(new java.awt.Font("RockWell", Font.PLAIN, 20), false));
		setCurrentGameTime(0);
	}

	
	@Override
	public void keyPressed(int key,char c) {
	
		Coordinate coord = conversion.get(c);
		if(coord == null) {
			coord = new Coordinate(-1,-1);
		}
		if(c == 'p') {
			endGame();
		}
		
		isCyclingOrWinningStarv();
		
		play(coord);
		
	}

	private void play(Coordinate coord) {
		if(coord.getX() >= 0 && this.giveCoord(coord) == 1) {
			play();
		}else if(giveCoord(coord) == -1) {
			endGame();
		}
	}


}
