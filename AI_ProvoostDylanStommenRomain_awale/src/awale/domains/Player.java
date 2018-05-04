package awale.domains;

import awale.boards.AwaleBoard;

public interface Player {
	
	public static Player ofId(int id) {
		return null;
	}
	
	public int getScore();
	
	public void setScore(int score);
	
	public int getId();
	
	public Coordinate getCurrentCoord();
	
	public int setCurrentCoord(Coordinate currentCoord,AwaleBoard ab,boolean starvation);

}
