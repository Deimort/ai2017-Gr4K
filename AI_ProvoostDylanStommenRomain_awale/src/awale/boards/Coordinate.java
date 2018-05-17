package awale.boards;

public class Coordinate implements Comparable<Coordinate> {
	
	private int[] coord;
	
	public Coordinate(int...coord) {
		this.coord = coord;
	}
	
	public Coordinate(Coordinate coord) {
		this.coord = new int[] {coord.getX(),coord.getY()};
	}
	
	public int getX() {
		return coord[0];
	}
	
	public int getY() {
		return coord[1];
	}
	
	public void setX(int valeur) {
		this.coord[0] += valeur;
	}
	
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