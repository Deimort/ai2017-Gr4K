package awale.states;

import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import awale.domains.Game;

public class HumanVsHumanState extends AwaleStates{
	
	private Map<Character,int[]> conversion;
	private int[] coord = new int[2];
	private Game currentGame;

	MenuGameState ms;
	public HumanVsHumanState(StateBasedGame sb) {
		super(1, "HumanVsHuman",sb);
		conversion = new TreeMap<>();
		String azerty = "azertyqsdfgh";
		int compteur = 0;
		for(int i = 0;i < 2;i++) {
			for(int j = 0;j < 6;j++) {
				conversion.put(azerty.charAt(compteur), new int[] {i,j});
				compteur++;
			}
		}
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.currentGame = new Game();
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		int[][] board = currentGame.getCurrentBoard().board();
		for(int i = 0;i < board.length;i++) {
			for(int j = 0;j < board[i].length;j++) {
				String afficheToiConnard = "";
				afficheToiConnard += board[i][j];
				g.drawString(afficheToiConnard,gc.getWidth()/2+j*10 , gc.getHeight()/2 + i*15);
			}
		}
		
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
		
		coord = conversion.get(c);
		if(coord == null) {
			coord = new int[] {-1,-1};
		}
		if(currentGame.isCycling()) {
			sb.enterState(3);
		}
		if(coord[0] >= 0 && currentGame.giveCoord(coord)) {
			currentGame.play();
		}
		
	}

}
