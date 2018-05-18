package awale.boards;

/**
 * Permets de stocker et comparer des coordonnées entre elles.
 *
 */
public class Coordinate implements Comparable<Coordinate> {
	
	private int[] coord;
	
	public Coordinate(int...coord) {
		this.coord = coord;
	}
	
	public Coordinate(Coordinate coord) {
		this.coord = new int[] {coord.getX(),coord.getY()};
	}
	
	/**
	 * Retourne le point X de la coordonnée.
	 * @return un entier représentant le point X de la coordonnée
	 */
	public int getX() {
		return coord[0];
	}
	
	
	/**
	 * Retourne le point Y de la coordonnée.
	 * @return un entier représentant le point Y de la coordonnée
	 */
	public int getY() {
		return coord[1];
	}
	
	/**
	 * Incrémente le point X de la coordonnée d'une valeur donnée.
	 * @param valeur l'entier avec lequel on souhaite incrémenter X
	 */
	public void setX(int valeur) {
		this.coord[0] += valeur;
	}
	
	
	/**
	 * Incrémente le point Y de la coordonnée d'une valeur donnée.
	 * @param valeur l'entier avec lequel on souhaite incrémenter Y
	 */
	public void setY(int valeur) {
		this.coord[1] += valeur;
	}

	@Override
	public int compareTo(Coordinate other) {
		Coordinate coord = (Coordinate) other;
		if(equals(other)) {
			return 0;
		}else if(this.getX() < coord.getX() || isInferior(coord)) {
			return -1;
		}else {
			return 1;
		}
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Coordinate) {
			Coordinate c = (Coordinate)other;
			return this.getX() == c.getX() && this.getY() == c.getY();
		}else {
			return false;
		}
		
	}

	private boolean isInferior(Coordinate other) {
		return this.getX() == other.getX() && this.getY() < other.getY();
	}

}
