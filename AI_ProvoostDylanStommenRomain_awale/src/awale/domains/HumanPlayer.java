package awale.domains;

import awale.boards.AwaleBoard;
import awale.boards.Coordinate;

public class HumanPlayer extends Player {
	private Coordinate currentCoord;
	
	public HumanPlayer(int id) {
		super(id);
	}

	@Override
	public Coordinate getCurrentCoord() {
		return currentCoord;
	}
	/*
	 * Affecte l'argument currentCoord à l'attribut currentCoord du joueur, après avoir vérifier que
	 * la coordonnée donnée en argument est valide (sur la ligne du joueur, avec plus de 0 graines)
	 * et, si l'adversaire est en état de famine, on regarde si la coordonnée est valide
	 * grâce à la méthode coordWhenStarvation.
	 */
	@Override
	public int setCurrentCoord(Coordinate currentCoord,AwaleBoard ab,boolean starvation) {
		this.currentCoord = new Coordinate(currentCoord);
		if(starvation) {
			return coordWhenStarvation(currentCoord,ab);
		}else if(currentCoord.getX() != this.getId() || ab.getSeeds(currentCoord.getX(), currentCoord.getY()) == 0) {
			return 0;
		}
		
		return 1;
	}
	
	

}
