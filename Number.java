package table_try;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;

public class Number {
	
	Random rand = new Random();
	
	private int tab[][];
	public int number;
	public int row=0;
	private int spr;
	private int s = 0;
	

	public Number(int a, int b)
	{
		row=a;
		tab = new int[][] {
			{4,8,9,12,16,14,18,20},
			{5,8,9,10,15,20,25,28},
			{6,12,14,16,18,24,30,36},
			{7,14,15,20,21,25,28,35},
			{42,32,40,56,64,24,30,36},
			{27,42,48,56,45,54,63,72},
			{50,60,70,80,90,63,68,81}
		};
	}
	
	public void pick(int row)
	{
		
		for(int i=0; i<1;i++) {
		int pickcolumn = rand.nextInt(7);
		//int pickrow = rand.nextInt(row);
		number = tab[row][pickcolumn];}
	
	
	
	public void draw(Graphics2D g) {
		
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 40));
		g.drawString("Liczba do zaznaczenia: " +number, 800, 85);
		//g.setFont(new Font("serif", Font.BOLD, 30));
	}
}
