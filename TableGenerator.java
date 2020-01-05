package table_try;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JPanel;

public class TableGenerator extends JPanel{
	
	public int map[][];
	public int column,row;
	public static final Color MEDIUM_blue = new Color(0,162,232);
	
	Random rand = new Random();
	
	public TableGenerator(int a) {
		map = new int[a][a];
		blanktable();
	}
	
	public void draw(Graphics2D g,int b) {
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				
				if(map[i][j]==1) {
					g.setColor(Color.white);
				}
				if(map[i][j]==0)
				{
					g.setColor(MEDIUM_blue);
				}
				if(map[i][j]==3)
				{
					g.setColor(Color.black);
				}
				
				g.fillRect(j * 60 + 80, i * 60 + 50, 56, 56);
				
				if(b==1)		//w trybie nauki rysowanie liczb przy planszy (numeracja wierszy i kolumn)
				{
					int d=0;
					
					g.setFont(new Font("serif", Font.BOLD, 40));
					
					for(int z=0; z<map[0].length; z++)
					{	
						if(map[0][z]==0)
							{g.setColor(MEDIUM_blue);
							g.drawString("  "+(z+1), 80+d, 40);}
						else
							{g.setColor(Color.white);
							g.drawString("  "+(z+1), 80+d, 40);}
						d = d+59;
					}
					
						if(map[i][0]==0)
							{g.setColor(MEDIUM_blue);
							g.drawString((i+1)+"  ", 40, i * 60 + 85);}
						else
							{g.setColor(Color.white);
							g.drawString((i+1)+"  ", 40, i * 60 + 85);}
				}
			}
		}
	}
	
	public void blanktable()		//wszystkie kafelki białe
	{
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				map[i][j] = 1;
			}
		}
	}
	public void blacktable()		//zmiana rozmiaru tablicy
	{
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				map[i][j] = 3;
			}
		}
		for(int i=0;i<1;i++) {
			column = rand.nextInt(5)+2;
			row = rand.nextInt(5)+2;}
			for(int k=0;k<column;k++) {
				for(int r=0;r<row;r++) {
					map[k][r] = 1;
				}
			}	
	}

}
