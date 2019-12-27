package table;

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

import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Random;



public class Gameplay extends JPanel implements ActionListener, MouseMotionListener, MouseListener{
	
	private TableGenerator map;
	private Menu menu;
	private Number number;
	public int round = 1;
	
	//Random rand = new Random();
	
	
	private int x, y, x1, y1, x2, y2;						// współrzędne kursora przy przeciąganiu po kafelkach, przesuwaniu w menu i klikaniu w menu
	ArrayList<Point> points = new ArrayList<Point>();
	ArrayList<Point> points1 = new ArrayList<Point>();
	ArrayList<Point> points2 = new ArrayList<Point>();
	
	private Timer timer;
	private int delay = 8;
	
	private int b=0,c=0;	// zmienne do wyświetlania wyniku
	public int game = 0;	// menu/gra/tryby
	
	
	public Gameplay() {
		map = new TableGenerator(10,10);
		menu = new Menu();
		number = new Number(round,10);
		number.pick();					//Wybieranie liczby do zaznaczenia
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		
		timer = new Timer(delay,this);
		timer.start();
		
	}
	
	public void paint(Graphics g) {
		if(game==0) {
			menu.draw((Graphics2D)g);		//tryb menu
		}
		else {
			//pisanie polecenia
			g.setColor(Color.black);
			g.fillRect(0, 0, 1280, 1024);
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 40));
			//pisanie wyniku
			g.drawString(b+" * "+c+" = "+(b*c), 800, 190);
			//opcja kolejny poziom
			g.setColor(Color.gray);
			g.fillRect(900, 420, 330, 60);
			g.fillRect(900, 520, 330, 60);
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 40));
			g.drawString("Kolejny poziom", 920, 460);
			g.drawString("Powrót do menu", 920, 560);
			
			map.draw((Graphics2D)g);
			number.draw((Graphics2D)g);
		}
		g.dispose();
	}

	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		for(int i = 0; i<map.map.length; i++) {
			for(int j = 0; j<map.map[0].length; j++) {
				if( x > (j*map.width+80) && y > (i*map.height+50) )		//wartość elementu (pokolorowany lub nie) w zależności od pozycji kursora
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
			if(x1>450 && x1<750 && y1>(150 +i*100) && y1<(220 + i*100))		//zaznaczanie przycisku menu gdy kursor nad nim się przesuwa
			{
				menu.menu[i] = 1;
			}
			else
				menu.menu[i] = 0;
		}
		
		for(int i=0; i<menu.menu.length; i++)
		{
			if(x2>450 && x2<750 && y2>(150+menu.f) && y2<(220+menu.f))		//wybranie opcji menu po kliknięciu myszką w przycisk
			{
				game = 1;
			}
		}
		
		if(x2>900 && x2<1200 && y2>420 && y2<480)		//wybranie opcji "kolejny poziom"
		{
			number.row+=1;
			x2 = 0;
			y2 = 0;
			
		}
		if(x2>900 && x2<1200 && y2>520 && y2<580)		//wybranie opcji "kolejny poziom"
		{
			game = 0;
			x2 = 0;
			y2 = 0;
			
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
		if(b*c == number.number)
		{
			number.pick();
		}
		
	}

	
}
