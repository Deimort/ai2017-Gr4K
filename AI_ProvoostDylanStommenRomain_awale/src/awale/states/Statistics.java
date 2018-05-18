package awale.states;

public class Statistics {
	
	private int currentGameTime;
	private int totalTime;
	private int minTime,maxTime;
	private int[] scores;
	private int winnerId,gameCount;
	private int[] victories;
	
	public Statistics() {
		currentGameTime = 0;
		totalTime = 0;
		minTime = Integer.MAX_VALUE;
		maxTime = 0;
		scores = new int[2];
		winnerId = 0;
		victories = new int[2];
		gameCount = 0;
	}
	
	public void endGame(int currentGameTime,int[] scores) {
		this.currentGameTime = currentGameTime;
		this.totalTime += currentGameTime;
		setMinTime(currentGameTime);
		setMaxTime(currentGameTime);
		this.winnerId = getWinner(scores);
		this.scores = scores;
		incrementGames();
	}

	private void incrementGames() {
		gameCount++;
		
		if(winnerId != -1)
		victories[winnerId]++;
	}

	private int getWinner(int[] scores) {
		if(scores[0] < scores[1]) {
			return 1;
		}else if(scores[0] > scores[1]) {
			return 0;
		}else {
			return -1;
		}
	}

	private void setMinTime(int currentGameTime) {
		if(currentGameTime < this.minTime) {
			this.minTime = currentGameTime;
		}
		
	}
	
	private void setMaxTime(int currentGameTime) {
		if(currentGameTime > this.maxTime) {
			this.maxTime = currentGameTime;
		}
		
	}
	
	private String getTime(int time) {
		int seconds = (time/1000);
		return String.format("%02d", seconds/60) + ":" + String.format("%02d", seconds%60);
	}

	public String getCurrentGameTime() {
		return getTime(currentGameTime);
	}

	public String getMinTime() {
		return getTime(minTime);
	}

	public String getMaxTime() {
		return getTime(maxTime);
	}

	public int getScore(int id) {
		return scores[id];
	}

	public int getWinnerId() {
		return winnerId;
	}

	public int getGameCount() {
		return gameCount;
	}

	public int getVictories(int id) {
		return victories[id];
	}
	
	public String getAverageTime() {
		String time;
		try {
			time = getTime(totalTime / gameCount);
		} catch (ArithmeticException e) {
			time = "00:00";
		}
		return time;
	}

}
