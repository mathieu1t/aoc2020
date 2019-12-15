package fr.insee.adventofcode.model;

import java.util.Objects;

public class Lune implements Cloneable{

    /**
     * @param posX
     * @param posY
     * @param posZ
     * @param velX
     * @param velY
     * @param velZ
     */
    public Lune(long posX, long posY, long posZ, long velX, long velY, long velZ) {
	super();
	this.posX = posX;
	this.posY = posY;
	this.posZ = posZ;
	this.velX = velX;
	this.velY = velY;
	this.velZ = velZ;
    }

    /**
     * @param posX
     * @param posY
     * @param posZ
     * @param velX
     * @param velY
     * @param velZ
     */
    public Lune(long posX, long posY, long posZ) {
	super();
	this.posX = posX;
	this.posY = posY;
	this.posZ = posZ;
	this.velX = 0;
	this.velY = 0;
	this.velZ = 0;
    }
    
    public long posX;
    public long posY;
    public long posZ;
    
    public long velX;
    public long velY;
    public long velZ;
    
    public long getEnergie() {
	long pot = Math.abs(posX) + Math.abs(posY) + Math.abs(posZ);
	long cin = Math.abs(velX) + Math.abs(velY) + Math.abs(velZ);
	return pot * cin;
	
    }

    @Override
    public int hashCode() {
	return Objects.hash(posX, posY, posZ, velX, velY, velZ);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Lune other = (Lune) obj;
	return posX == other.posX && posY == other.posY && posZ == other.posZ && velX == other.velX
		&& velY == other.velY && velZ == other.velZ;
    }


   @Override
   public Lune clone() {
       return new Lune(posX,posY,posZ,velX,velY,velZ);
   }
}
