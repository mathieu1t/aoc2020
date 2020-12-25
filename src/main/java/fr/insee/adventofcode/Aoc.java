package fr.insee.adventofcode;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.commons.lang3.ArrayUtils;

import fr.insee.adventofcode.days.Day;

public class Aoc {

    private static String[][] tabAoc = new String[25][5];
    private static String[][] tabAocR = new String[25][5];

    public static void main(String[] args)
	    throws InstantiationException, IllegalAccessException, ClassNotFoundException {

	for (int i = 25; i <= 25; i++) {
	    String d = i < 10 ? "0" + i : i + "";
	    Day day = (Day) Class.forName("fr.insee.adventofcode.days.Day" + d).newInstance();
	    day.run();
	    Day dayR = (Day) Class.forName("fr.insee.adventofcode.days.Day" + d + "Refactor").newInstance();
	    dayR.run();
	    tabAoc[i-1][0] = "Day"+d;
	    tabAoc[i-1][1] = day.getResult1();
	    tabAoc[i-1][2] = day.getTime1();
	    tabAoc[i-1][3] = day.getResult2();
	    tabAoc[i-1][4] = day.getTime2();
	    tabAocR[i-1][0] = "Day"+d+" Refactor";
	    tabAocR[i-1][1] = dayR.getResult1();
	    tabAocR[i-1][2] = dayR.getTime1();
	    tabAocR[i-1][3] = dayR.getResult2();
	    tabAocR[i-1][4] = dayR.getTime2();
	}
	String[][] data = ArrayUtils.addAll(tabAoc, tabAocR);
	String[] entete = {"Day","Part 1","Time 1","Part 2","Time 2"};
	JFrame frame = new JFrame("Advent of code");
	JTable table = new JTable(data, entete);
	JScrollPane scroll = new JScrollPane(table);
        frame.getContentPane().add(scroll,BorderLayout.CENTER);       
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 1000);
        frame.setVisible(true);
	
    }
}
