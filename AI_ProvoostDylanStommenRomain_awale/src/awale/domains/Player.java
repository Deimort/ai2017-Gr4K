package awale.domains;

import awale.boards.AwaleBoard;

public interface Player {
	
	public static Player ofId(int id) {
		return null;
	}
	
	public int getScore();
	
	public void setScore(int score);
	
	public int getId();
	
	public int[] getCurrentCoord();
	
	public boolean setCurrentCoord(int[] currentCoord,AwaleBoard ab);

}
