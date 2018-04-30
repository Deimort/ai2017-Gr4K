package awale.domains;

public class Player {
	private int score;
	private int id;
	private int[] currentCoord;
	
	public static Player ofId(int id) {
		Player newPlayer = new Player();
		newPlayer.id = id;
		newPlayer.score = 0;
		
		return newPlayer;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public int[] getCurrentCoord() {
		return currentCoord;
	}

	public void setCurrentCoord(int[] currentCoord) {
		if(currentCoord[0] != this.getId()) {
			currentCoord = new int[] {-1,-1};
		}
		this.currentCoord = currentCoord;
	}
	

}
