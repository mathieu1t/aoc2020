package fr.insee.adventofcode.model;

public class IntCode {

	private boolean stop = false;
	private Integer[] tab;
	private Integer entree1 = 0;
	private Integer entree2 = 0;
	private Integer sortie;
	private Integer position;
	
	/**
	 * @return the sortie
	 */
	public Integer getSortie() {
		return sortie;
	}

	/**
	 * @param sortie the sortie to set
	 */
	public void setSortie(Integer sortie) {
		this.sortie = sortie;
	}

	public IntCode(Integer[] tab, Integer entree1, Integer entree2, int position, int sortie) {
		this.tab = tab;
		this.entree1 = entree1;
		this.entree2 = entree2;
		this.position = position;
		this.sortie = sortie;
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
		String opcode = getOpcode(String.valueOf(tab[position]));
		boolean changeInput = false;
		while (!"99".equals(opcode)) {
			Integer[] modes = getModes(String.valueOf(tab[position]));
			switch (opcode) {
			case "01":
				tab[tab[position + 3]] = val(1, modes) + val(2, modes);
				position = position + 4;
				break;
			case "02":
				tab[tab[position + 3]] = val(1, modes) * val(2, modes);
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
				sortie = val(1, modes); // output
				position = position + 2;
				return sortie;
			case "05":
				if (val(1, modes) != 0) {
					tab[position] = val(2, modes);
					position = tab[position];
				} else {
					position = position + 3;
				}
				break;
			case "06":
				if (val(1,modes) == 0) {
					tab[position] = val(2, modes);
					position = tab[position];
				} else {
					position = position + 3;
				}
				break;
			case "07":
				tab[tab[position + 3]] = val(1, modes) < val(2, modes) ? 1 : 0;
				position = position + 4;
				break;
			case "08":
				tab[tab[position + 3]] = val(1, modes) == val(2, modes) ? 1 : 0;
				position = position + 4;
				break;
			}
			opcode = getOpcode(String.valueOf(tab[position]));
		}
		setStop(true);
		return sortie;
	}

	public int val(int numParam, Integer[] modes) {
		boolean mode = modes.length >= numParam && modes[numParam - 1] == 1;
		return tab[mode ? position + numParam : tab[position + numParam]];
	}

	/**
	 * @return the tab
	 */
	public Integer[] getTab() {
		return tab;
	}

	/**
	 * @param tab the tab to set
	 */
	public void setTab(Integer[] tab) {
		this.tab = tab;
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
	public Integer getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}

}
