package table_try;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements ActionListener, MouseMotionListener, MouseListener{
	
	private TableGenerator map;
	private Menu menu;
	private int a = 10;
	
	
	private int x, y, x1, y1, x2, y2;
	ArrayList<Point> points = new ArrayList<Point>();
	ArrayList<Point> points1 = new ArrayList<Point>();
	ArrayList<Point> points2 = new ArrayList<Point>();
	
	private Timer timer;
	private int delay = 8;
	
	private int b,c;
	public int game = 0;
	
	
	public Gameplay() {
		map = new TableGenerator(a,a);
		menu = new Menu();
		
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		
		timer = new Timer(delay,this);
		timer.start();
		
	}
	
	public void paint(Graphics g) {
		if(game==0) {
			menu.draw((Graphics2D)g);
		}
		else {
		//pisanie polecenia
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 1280, 1024);
		//g.fillRect(790, 160, 400, 50);
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 40));
		g.drawString("Zaznacz 12 kratek!", 800, 85);
		
		g.drawString(b+" * "+c+" = "+(b*c), 800, 190);
		
		map.draw((Graphics2D)g);
		
		}
		g.dispose();
	}

	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		for(int i = 0; i<map.map.length; i++) {
			for(int j = 0; j<map.map[0].length; j++) {
				if( x > (j*map.width+80) && y > (i*map.height+50) )
				{
					map.map[i][j] = 0;
					b=i+1;
					c=j+1;
				}
				else
				{
					map.map[i][j] = 1;
					
				}
				
			}
		}
		
		for(int i=0; i<menu.menu.length; i++)
		{
			if(x1>450 && x1<750 && y1>(150 +i*100) && y1<(220 + i*100))
			{
				menu.menu[i] = 1;
			}
			else
				menu.menu[i] = 0;
		}
		
		for(int i=0; i<menu.menu.length; i++)
		{
			if(x2>450 && x2<750 && y2>(150+menu.f) && y2<(220+menu.f))
			{
				game =1;
			}
		}
		
		repaint();
		
	}
	


	@Override
	public void mouseDragged(MouseEvent m) {
		x=m.getX();
		y=m.getY();
		points.add(new Point(x,y));
		repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent m) {
		x1=m.getX();
		y1=m.getY();
		points1.add(new Point(x1,y1));
		
		repaint();
		
	}

	@Override
	public void mouseClicked(MouseEvent m) {
		x2=m.getX();
		y2=m.getY();
		points2.add(new Point(x2,y2));
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
