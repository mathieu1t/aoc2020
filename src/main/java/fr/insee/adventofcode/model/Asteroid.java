package fr.insee.adventofcode.model;

public class Asteroid {

    private int x;
    private int y;
    
    /**
     * 
     */
    public Asteroid() {
	super();
	// TODO Auto-generated constructor stub
    }
    /**
     * @param x
     * @param y
     */
    public Asteroid(int x, int y) {
	super();
	this.x = x;
	this.y = y;
    }
    
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }
    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + x;
	result = prime * result + y;
	return result;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Asteroid other = (Asteroid) obj;
	if (x != other.x)
	    return false;
	if (y != other.y)
	    return false;
	return true;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Asteroid [x=" + x + ", y=" + y + "]";
    }
}
