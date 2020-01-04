package table_try;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TableGenerator extends JPanel{
	
	public int map[][];
	public int width = 60;
	public int height = 60;
	public int column,row;
	
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
					g.setColor(Color.blue);
				}
				if(map[i][j]==3)
				{
					g.setColor(Color.black);
				}
				
				g.fillRect(j * width + 80, i * height + 50, width-4, height-4);
				
				if(b==1)
				{
				int d=0;
				
				g.setFont(new Font("serif", Font.BOLD, 40));
				
				for(int z=0; z<map[0].length; z++)
				{	
					if(map[0][z]==0)
						{g.setColor(Color.blue);
						g.drawString("  "+(z+1), 80+d, 40);}
					else
						{g.setColor(Color.white);
						g.drawString("  "+(z+1), 80+d, 40);}
					d = d+59;
				}
				
				
			
				
					if(map[i][0]==0)
						{g.setColor(Color.blue);
						g.drawString((i+1)+"  ", 40, i * height + 85);}
					else
						{g.setColor(Color.white);
						g.drawString((i+1)+"  ", 40, i * height + 85);}
				}
			}
		}
	}
	
	public void blanktable()
	{
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				map[i][j] = 1;
			}
		}
	}
	public void blacktable()
	{
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				map[i][j] = 3;
			}
		}
		for(int i=0;i<1;i++) {
			column = rand.nextInt(5)+3;
			row = rand.nextInt(5)+3;}
			for(int k=0;k<column;k++) {
				for(int r=0;r<row;r++) {
					map[k][r] = 1;
				}
			}	
			
	}

}
