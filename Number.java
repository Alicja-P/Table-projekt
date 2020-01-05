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

	public Number(int a)
	{
		row=a;
		this.tab = new int[][] {
			{4,8,9,12,16,14,18,20},
			{32,6,9,10,15,20,25,28},
			{8,12,14,16,18,24,30,36},
			{45,27,15,20,21,25,28,35},
			{42,32,40,56,64,24,30,36},
			{27,42,48,56,45,54,63,72},
			{50,60,70,80,90,63,64,81},
			{56,28,72,32,42,63,90,81}
		};
	}
	
	public void pick(int row)		//losowanie liczby z tablicy w zależności od poziomu
	{
		if(row<8) 	
		{
			for(int i=0; i<1;i++) 
			{
				int pickcolumn = rand.nextInt(7);
				number = tab[row][pickcolumn];
			}
		}
		else		//w 9 poziomie liczby losowane z całej tablicy
		{
			int pickcolumn = rand.nextInt(7);
			int pickrow = rand.nextInt(7);
			number = tab[pickrow][pickcolumn];
		}
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 40));
		g.drawString("Liczba do zaznaczenia: " +number, 800, 85);
	}
}
