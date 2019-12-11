package fr.insee.adventofcode.model;

public class IntCode {

	private Integer output;
	private Integer[] tab;
	private Integer entree1 = 0;
	private Integer entree2 = 0;
	private Boolean hasOutput = false;
	private Integer position;

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

	public void lancer() {
		String opcode = getOpcode(String.valueOf(tab[position]));
		boolean changeInput = false;
		hasOutput = false;
		while (!"99".equals(opcode) && !hasOutput) {
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
				output = val(1, modes); // output
				hasOutput = true;
				position = position + 2;
				break;
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

	/**
	 * @return the output
	 */
	public Integer getOutput() {
	    return output;
	}

	/**
	 * @param output the output to set
	 */
	public void setOutput(Integer output) {
	    this.output = output;
	}

	/**
	 * @return the hasOutput
	 */
	public Boolean getHasOutput() {
	    return hasOutput;
	}

	/**
	 * @param hasOutput the hasOutput to set
	 */
	public void setHasOutput(Boolean hasOutput) {
	    this.hasOutput = hasOutput;
	}

	/**
	 * @return the entree2
	 */
	public Integer getEntree2() {
	    return entree2;
	}

	/**
	 * @param entree1 the entree2 to set
	 */
	public void setEntree2(Integer entree2) {
	    this.entree2 = entree2;
	}

}
