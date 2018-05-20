package awale.states;


/**
 * Permet de stocker et calculer les statistiques de la partie terminée et des parties précédentes.
 */
public class Statistics {
	
	private int currentGameTime;
	private int totalTime;
	private int minTime,maxTime;
	private int[] scores;
	private int winnerId,gameCount;
	private int[] victories;
	
	/**
	 * Ceci est un constructeur de Statistics
	 */
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
	
	/**
	 * Cette m�thode permet d'obtenir les valeurs des diff�rents attributs � la fin d'une partie
	 * @param currentGameTime le temps de jeu actuel
	 * @param scores les scores des deux joueurs
	 */
	public void endGame(int currentGameTime,int[] scores) {
		this.currentGameTime = currentGameTime;
		this.totalTime += currentGameTime;
		setMinTime(currentGameTime);
		setMaxTime(currentGameTime);
		this.winnerId = getWinner(scores);
		this.scores = scores;
		manageGameCounts();
	}

	private void manageGameCounts() {
		gameCount++;
		if(winnerId != -1) {
			victories[winnerId]++;
		}
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

	/**
	 * Cette m�thode permet d'obtenir le temps de jeu actuel
	 * @return le temps de jeu
	 */
	public String getCurrentGameTime() {
		return getTime(currentGameTime);
	}

	/**
	 * Cette m�thode permet d'obtenir le temps de jeu minimum lors de cette utilisation
	 * de l'application
	 * @return le temps de jeu minimum
	 */
	public String getMinTime() {
		return getTime(minTime);
	}

	/**
	  * Cette m�thode permet d'obtenir le temps de jeu maximum lors de cette utilisation
	 * de l'application
	 * @return
	 */
	public String getMaxTime() {
		return getTime(maxTime);
	}
	
	/**
	 * Cette m�thode permet d'obtenir le score pour un joueur donn�
	 * @param id l'id du joueur dont on souhaite obtenir le score
	 * @return le score du joueur
	 */
	public int getScore(int id) {
		return scores[id];
	}

	/**
	 * Cette m�thode permet d'obtenir l'id du joueur gagnant
	 * @return l'id du joueur gagnant
	 */
	public int getWinnerId() {
		return winnerId;
	}

	/**
	 * Cette m�thode permet d'obtenir le nombre de parties jou�es lors de cette execution de l'application
	 * @return le nombre de parties jou�es lors de cette execution de l'application
	 */
	public int getGameCount() {
		return gameCount;
	}

	/**
	 * Cette m�thode permet d'obtenir le nombre de parties gagn�es lors de cette execution de l'application
	 * pour un joueur donn�
	 * @return le nombre de parties gagn�es lors de cette execution de l'application
	 */
	public int getVictories(int id) {
		return victories[id];
	}
	
	/**
	 * Cette m�thode permet d'obtenir le temps moyen d'une partie lors de cette execution de l'application
	 * @return le temps moyen d'une partie lors de cette execution de l'application
	 */
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
