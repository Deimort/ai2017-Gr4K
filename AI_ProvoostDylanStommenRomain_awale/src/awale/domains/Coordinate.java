package awale.domains;

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
		if(this.getX() == coord.getX() && this.getY() == coord.getY()) {
			return 0;
		}else if(this.getX() < coord.getX() || (this.getX() == coord.getX() && this.getY() < coord.getY())) {
			return -1;
		}else {
			return 1;
		}
	}

}
