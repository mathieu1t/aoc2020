package fr.insee.adventofcode.model;

public class IntCode {

    private boolean stop = false;
    private Integer[] tab;
    private Integer entree1 = 0;
    private Integer entree2 = 0;
    private int position;

    public IntCode(Integer[] tab, Integer entree1, Integer entree2, int position) {
	this.tab = tab;
	this.entree1 = entree1;
	this.entree2 = entree2;
	this.position = position;
    }

    public String getOpcode(String s) {
	String opcode = s;
	if (opcode.length() < 2) {
	    opcode = "0" + opcode;
	} else if (opcode.length() > 2) {
	    opcode = opcode.substring(opcode.length() - 2);
	}
	return opcode;
    }

    public Integer[] getModes(String s) {
	if (s.length() >= 2) {
	    int nbModes = s.length() - 2;
	    Integer[] modes = new Integer[nbModes];
	    for (int n = 0; n < nbModes; n++) {
		modes[nbModes - n - 1] = Character.getNumericValue(String.valueOf(tab[position]).charAt(n));
	    }

	    return modes;
	} else {
	    return new Integer[0];
	}
    }

    public int lancer() {
	int sortie = 0;
	String opcode = getOpcode(String.valueOf(tab[position]));
	boolean changeInput = false;
	while (!"99".equals(opcode)) {
	    Integer[] modes = getModes(String.valueOf(tab[position]));
	    switch (opcode) {
	    case "01":
		tab[tab[position + 3]] = val(1, position, modes, tab) + val(2, position, modes, tab);
		position = position + 4;
		break;
	    case "02":
		tab[tab[position + 3]] = val(1, position, modes, tab) * val(2, position, modes, tab);
		position = position + 4;
		break;
	    case "03":
		if (!changeInput && entree1 != null) {
		    tab[tab[position + 1]] = entree1; // input
		    changeInput = true;
		} else {
		    tab[tab[position + 1]] = entree2; // secondInput
		}
		position = position + 2;
		break;
	    case "04":
		sortie = val(1, position, modes, tab); // output
		position = position + 2;
		return sortie;
	    case "05":
		if (val(1, position, modes, tab) != 0) {
		    //tab[position] = val(2, position, modes, tab);
		    position = tab[position];
		} else {
		    position = position + 3;
		}
		break;
	    case "06":
		if (val(1, position, modes, tab) == 0) {
		    //tab[position] = val(2, position, modes, tab);
		    position = tab[position];
		} else {
		    position = position + 3;
		}
		break;
	    case "07":
		tab[tab[position + 3]] = val(1, position, modes, tab) < val(2, position, modes, tab) ? 1 : 0;
		position = position + 4;
		break;
	    case "08":
		tab[tab[position + 3]] = val(1, position, modes, tab) == val(2, position, modes, tab) ? 1 : 0;
		position = position + 4;
		break;
	    }
	    opcode = getOpcode(String.valueOf(tab[position]));
	}
	setStop(true);
	return sortie;
    }

    /**
     * @return the tab
     */
    public Integer[] getTab() {
	return tab;
    }

    /**
     * @param tab
     *            the tab to set
     */
    public void setTab(Integer[] tab) {
	this.tab = tab;
    }

    public int val(int numParam, int position, Integer[] modes, Integer[] puzzle) {
	boolean mode = modes.length >= numParam && modes[numParam - 1] == 1;
	return puzzle[mode ? position + numParam : puzzle[position + numParam]];
    }

    public boolean isStop() {
	return stop;
    }

    public void setStop(boolean stop) {
	this.stop = stop;
    }

    /**
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(int position) {
        this.position = position;
    }

}
