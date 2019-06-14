import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Timer;
import java.util.TimerTask;

public class Kugel {

	int x, y, dy, dx, delta, diametr, tm, delay, period;
	Color color;
	Panel pan = Panel.getInstance();
	static Timer timer = new Timer();
	static int interval;
	public Kugel(int x, int y, int diametr, int dx, int dy, Color color) 
	{
		delay = 1000;
		period = 1000;
		delta = 1;
		tm = 8;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;

		this.color = color;
		this.diametr = diametr;
	}

	public void draw(Graphics2D gg, Color color) 
	{	
		gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
	//	gg.setColor(new Color(_randomInt(0, 255), _randomInt(0, 255), _randomInt(0 ,255)));
		gg.setColor(color);
		gg.setStroke(new BasicStroke(2));
		gg.fillOval(x, y, diametr, diametr);			
	}
	
	public void move () {		
		if (x+dx<0) {
			dx = delta;
		}
		else if (x+dx > pan.getWidth()-diametr) {
			dx = -delta;
		}
		else if (y + dy < 0) {
			dy = delta;
		}
		else if (y + dy >pan.getHeight()-diametr) {
			dy = -delta;
		}
		x = x+dx;
		y = y+dy;
	}
	
	private static int _randomInt (int max, int min)
	{
		int	randomInt;
		// do {
		  randomInt =  (int)Math.round(Math.random()*(max-min)+min);
	//	 } while (randomInt==0);
		 return randomInt;
	}
}
