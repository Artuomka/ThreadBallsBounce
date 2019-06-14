import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements MouseListener, Runnable
{
Panel pan;
Thread t;
Kugel tmpKug;
private int x, y, delta;
ArrayList<Kugel> kugels = new ArrayList<>();

	public Panel()
	{		
	delta = 1;	
	setBounds(110, 0, 1050, 640);
	setBackground(Color.LIGHT_GRAY);
	addMouseListener(this);		
	}
	
public static Panel instance;		
	public static Panel getInstance()
	{
		if (instance == null)
		{
			instance = new Panel();
		}		
		return instance;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Thread th = new Thread(new Runnable() {
		@Override
		public void run() {		
		x = e.getX();
		y = e.getY();
		int diametr =  _randomInt (50, 50);
		int dx = delta*_randomIntNotZero (-1, 1);
		int dy = delta*_randomIntNotZero (-1, 1);
		Kugel kgl = new Kugel(x, y, diametr, dx, dy, (new Color(_randomInt(0, 255), _randomInt(0, 255), _randomInt(0 ,255))));
		kugels.add(kgl);			
		}
	
		});
		th.start();
		paint(this.getGraphics());
	
		
	}
	
	private static int _randomInt (int max, int min)
	{
		 int randomInt =  (int)Math.round(Math.random()*(max-min)+min);
		 return randomInt;
	}
	
	private static int _randomIntNotZero (int max, int min)
	{
		int	randomInt;
		do {
		  randomInt =  (int)Math.round(Math.random()*(max-min)+min);
		} while (randomInt==0);
		 return randomInt;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	
		
	}
	
	
	public void paint (Graphics g) 
	{

		super.paint(g);
		Graphics2D  gg = (Graphics2D) g;
		synchronized (kugels) {
			
		for (Kugel k: kugels)
		{	
			if (k.diametr<=3) {
				kugels.remove(k);
			}
			k.draw(gg, k.color );
			k.move();
		}
		}
	}
	
	public int distance (int x1, int y1, int x2, int y2)
	  {
	    int dist = (int)(Math.sqrt(((x2-x1)*(x2-x1))+(y2-y1)*(y2-y1)));
	    return dist;
	  }
	
	public void eating () {		
		synchronized (kugels) {
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
//					for (Kugel k: kugels) {
//						for (Kugel j: kugels) {
					for (int k=0; k<kugels.size(); k++) {
						for (int j=0+k; j<kugels.size(); j++) {
							if ((distance(kugels.get(k).x, kugels.get(k).y, kugels.get(j).x, kugels.get(j).y)<=(Math.round(kugels.get(k).diametr/2)+Math.round(kugels.get(j).diametr/2))+2)) {
								kugels.get(k).dx = kugels.get(k).dx*(-1);
								kugels.get(k).dy = kugels.get(k).dy*(-1);
								kugels.get(j).dx = kugels.get(j).dx*(-1);
								kugels.get(j).dy = kugels.get(j).dy*(-1);
							
							}							
						}					
					}					
				}				
			});
			th.start();
			try {
				th.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	@Override
	public void run() {
		
		while (true) {
			eating();
			repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
	}
}
