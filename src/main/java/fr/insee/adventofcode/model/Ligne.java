package fr.insee.adventofcode.model;

import java.util.ArrayList;
import java.util.List;

public class Ligne {

	public Direction direction;
	public Point depart;
	public Point arrivee;
	public int distance;
	
	public Ligne(Point depart, String string) {
        direction = Direction.valueOf(string.substring(0, 1));
        this.depart = depart;
        this.distance = Integer.parseInt(string.substring(1));
        switch (direction) {
            case U:
                this.arrivee = new Point(depart.getX(), depart.getY() - distance);
                break;
            case R:
            	this.arrivee = new Point(depart.getX() + distance, depart.getY());
                break;
            case D:
            	this.arrivee = new Point(depart.getX(), depart.getY() + distance);
                break;
            case L:
            	this.arrivee = new Point(depart.getX() - distance, depart.getY());
                break;
        }
    }

    public List<Point> listeDesPoints() {
        List<Point> points = new ArrayList<>(distance);
        for(int n = 0; n < distance; n ++) {
            switch (direction) {
                case U:
                    points.add(new Point(depart.getX(), depart.getY() - n));
                    break;
                case R:
                    points.add(new Point(depart.getX() + n, depart.getY()));
                    break;
                case D:
                    points.add(new Point(depart.getX(), depart.getY() + n));
                    break;
                case L:
                    points.add(new Point(depart.getX() - n, depart.getY()));
                    break;
            }
        }
        return points;
    }

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * @return the depart
	 */
	public Point getDepart() {
		return depart;
	}

	/**
	 * @param depart the depart to set
	 */
	public void setDepart(Point depart) {
		this.depart = depart;
	}

	/**
	 * @return the arrivee
	 */
	public Point getArrivee() {
		return arrivee;
	}

	/**
	 * @param arrivee the arrivee to set
	 */
	public void setArrivee(Point arrivee) {
		this.arrivee = arrivee;
	}

	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

}
