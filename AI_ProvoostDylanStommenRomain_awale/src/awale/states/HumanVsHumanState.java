package awale.states;

import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class HumanVsHumanState extends AwaleStates{
	
	private Map<Character,int[]> conversion;
	private int[] coord;

	public HumanVsHumanState(StateBasedGame sb) {
		super(1, "HumanVsHuman",sb);
		conversion = new TreeMap<>();
		conversion.put('a', new int[] {0,0});
		conversion.put('z', new int[] {0,1});
		conversion.put('e', new int[] {0,2});
		conversion.put('r', new int[] {0,3});
		conversion.put('t', new int[] {0,4});
		conversion.put('y', new int[] {0,5});
		conversion.put('q', new int[] {1,0});
		conversion.put('s', new int[] {1,1});
		conversion.put('d', new int[] {1,2});
		conversion.put('f', new int[] {1,3});
		conversion.put('g', new int[] {1,4});
		conversion.put('h', new int[] {1,5});
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString(coord[0] + " " + coord[1], gc.getWidth()/2, gc.getHeight()/2);
		
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
		
	}

}
