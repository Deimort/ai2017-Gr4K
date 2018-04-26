package awale;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Program {
	
	private static final int MAX_FPS = 60;
	
	public static void main(String[] args) {
		
		AppGameContainer appgc;
		
		try {
			appgc = new AppGameContainer(new AwaleGame("Awale"), 800, 600, false);
			appgc.setTargetFrameRate(MAX_FPS);
			appgc.setVSync(true);
			appgc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		
	}
}
