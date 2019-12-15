package fr.insee.adventofcode.days;

import fr.insee.adventofcode.model.Lune;

public class Day12 extends Day {

    @Override
    public String part1(String filepath, Object... params) {
	Lune io = new Lune(6, -2, -7);
	Lune europa = new Lune(-6, -7, -4);
	Lune ganymède = new Lune(-9, 11, 0);
	Lune callisto = new Lune(-3, -4, 6);

	for (int i = 0; i < 1000; i++) {
	    modifVitesse(io, europa);
	    modifVitesse(io, ganymède);
	    modifVitesse(io, callisto);
	    modifVitesse(europa, ganymède);
	    modifVitesse(europa, callisto);
	    modifVitesse(ganymède, callisto);

	    modifPosition(io);
	    modifPosition(europa);
	    modifPosition(ganymède);
	    modifPosition(callisto);

	}

	return String.valueOf(io.getEnergie() + europa.getEnergie() + ganymède.getEnergie() + callisto.getEnergie());
    }

    private void modifPosition(Lune l) {
	l.posX += l.velX;
	l.posY += l.velY;
	l.posZ += l.velZ;
    }

    private void modifVitesse(Lune l1, Lune l2) {
	if (l1.posX > l2.posX) {
	    l2.velX++;
	    l1.velX--;
	} else if (l1.posX < l2.posX) {
	    l2.velX--;
	    l1.velX++;
	}
	if (l1.posY > l2.posY) {
	    l2.velY++;
	    l1.velY--;
	} else if (l1.posY < l2.posY) {
	    l2.velY--;
	    l1.velY++;
	}
	if (l1.posZ > l2.posZ) {
	    l2.velZ++;
	    l1.velZ--;
	} else if (l1.posZ < l2.posZ) {
	    l2.velZ--;
	    l1.velZ++;
	}

    }

    @Override
    public String part2(String filepath, Object... params) {
	Lune io = new Lune(6, -2, -7);
	Lune europa = new Lune(-6, -7, -4);
	Lune ganymède = new Lune(-9, 11, 0);
	Lune callisto = new Lune(-3, -4, 6);
//	Lune io = new Lune(-8, -10, 0);
//	Lune europa = new Lune(5, 5, 10);
//	Lune ganymède = new Lune(2, -7, 3);
//	Lune callisto = new Lune(9, -8, -3);

	Lune ioClone = io.clone();
	Lune europaClone = europa.clone();
	Lune ganymèdeClone = ganymède.clone();
	Lune callistoClone = callisto.clone();

	long pas = 0;
	long cycleX = 0;
	long cycleY = 0;
	long cycleZ = 0;
	while (cycleX == 0 || cycleY == 0 || cycleZ == 0) {
	    pas++;
	    modifVitesse(io, europa);
	    modifVitesse(io, ganymède);
	    modifVitesse(io, callisto);
	    modifVitesse(europa, ganymède);
	    modifVitesse(europa, callisto);
	    modifVitesse(ganymède, callisto);

	    modifPosition(io);
	    modifPosition(europa);
	    modifPosition(ganymède);
	    modifPosition(callisto);

	    if (cycleX == 0 && io.posX == ioClone.posX && europa.posX == europaClone.posX && ganymède.posX == ganymèdeClone.posX && callisto.posX == callistoClone.posX) {
		cycleX = pas+1;
	    }
	    if (cycleY == 0 && io.posY == ioClone.posY && europa.posY == europaClone.posY && ganymède.posY == ganymèdeClone.posY && callisto.posY == callistoClone.posY) {
		cycleY = pas+1;
	    }
	    if (cycleZ == 0 && io.posZ == ioClone.posZ && europa.posZ == europaClone.posZ && ganymède.posZ == ganymèdeClone.posZ && callisto.posZ == callistoClone.posZ) {
		cycleZ = pas+1;
	    }
	}
	long ppcmXY = Calcule_PPCM(cycleX, cycleY);
	long ppcmXYZ = Calcule_PPCM(ppcmXY, cycleZ);
	return String.valueOf(ppcmXYZ);
    }
    
    public static long Calcule_PPCM (long Nb1, long Nb2) {
   	long Produit, Reste, PPCM;
   		
   	Produit = Nb1*Nb2;
   	Reste   = Nb1%Nb2;
   	while(Reste != 0){
   	    Nb1 = Nb2;
   	    Nb2 = Reste;
   	    Reste = Nb1%Nb2;
           }
   	PPCM = Produit/Nb2;
   	return PPCM;		
       } 

}
