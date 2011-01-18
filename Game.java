package kojage;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Color;
import java.awt.Event;
/**
 * Kojage's Java Open Applet Game Engine - Game.java
 * @author daPhyre
 * @since 1.0, Fr/04/Apr/08
 * @version 6.0, Tu/04/Jan/11
 */
public class Game extends java.applet.Applet implements Runnable
{
	public boolean UP, DOWN, LEFT, RIGHT, PAUSE;
	Thread thd;
	int sleep = 50;
	Image bgImage;
	Boolean bgFixed, mustClear = false;
	public static double mousex = 0;
	public static double mousey = 0;
	public static int bgx = 0;
	public static int bgy = 0;
	public static int screenWidth = 350;
	public static int screenHeight = 200;
	
	public void init()
	{
		
	}

	public void start()
	{
		if(thd==null)
		{
			thd=new Thread(this);
			thd.start();
		}
	}

	public void stop()
	{
		if(thd!=null)
		{
			thd.stop();
			thd=null;
		}
	}
	
	public static Dimension getScreenSize()
	{
		return new Dimension ((int)(screenWidth*World.scale),(int)(screenHeight*World.scale));
	}

	@Override
	public void setSize(int width, int height)
	{
		super.setSize(width, height);
		screenWidth = width;
		screenHeight = height;
	}

	public void setBackground(Color c, Image img, boolean fixed)
	{
		this.setBackground(c);
		bgImage = img;
		bgFixed = fixed;
	}

	public void setSleepTime(int sleepTime)
	{
		sleep = sleepTime;
	}

	public void run()
	{
		while(true)
		{
			game();
			resize();
			repaint();
				
			try
			{
				Thread.sleep(sleep);
			}
			catch(InterruptedException e){}	
		}
	}
	
	public void game()
	{
		
	}
	
	public void resize()
	{
		Dimension dim = this.getSize();
		double w = (double)(dim.width)/(double)(screenWidth);
		double h = (double)(dim.height)/(double)(screenHeight);
		if (World.scale != Math.min(h, w))
		{
			World.scale = Math.min(h, w);
			mustClear = true;
		}
	}

	public void fillBackground(Graphics g, Image bgImage, boolean fixed)
	{
		int x = 0, y = 0;
		while(x < World.width) {
			y = 0;
			while(y < World.height) {
				if (!fixed)
				{
					bgx = World.x;
					bgy = World.y;
				}
				g.drawImage(bgImage, x-bgx, y-bgy, this);
				y += bgImage.getHeight(null);
			}
			x += bgImage.getWidth(null);
		}
	}

	public void update(Graphics g)
	{
		Graphics g2;
		Image img;
		int w = (int)(screenWidth*World.scale);
		int h = (int)(screenHeight*World.scale);
		if (mustClear)
		{
			g.setColor(Color.black);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			mustClear = false;
		}
		img = createImage(screenWidth, screenHeight);
		g2 = img.getGraphics();
		if (bgImage != null)
			fillBackground(g2, bgImage, bgFixed);
		paint(g2);
		g.drawImage(img, this.getWidth()/2-w/2, this.getHeight()/2-h/2, w, h, this);
		if (mustClear)
		{
			g.setColor(Color.black);
			g.drawRect(0, 0, this.getWidth(), this.getHeight());
			mustClear = false;
		}
	}

	public void paint(Graphics g)
	{
		g.drawString("It Works!", 20, 20);
	}

	public static int random(int Value)
	{
		return (int)(Math.random() * Value);
	}

	public static double random(double Value)
	{
		return (Math.random() * Value);
	}
	
	public double getAngle(double x1, double y1, double x2, double y2)
	{
		double angle = ( (Math.atan2(y1-y2, x1-x2)) / (Math.PI/180) + 90 );
		if (angle > 360)
			angle -= 360;
		if (angle < 0)
			angle += 360;
		return angle;
	}

	public boolean mouseMove(Event e, int x, int y)
	{
		mousex = x+World.x*World.scale;
		mousey = y+World.y*World.scale;
		return true;
	}

	public boolean keyDown(Event e, int key)
	{
		switch (key)
		{
			case Event.UP:
				UP = true;
				break;
			case Event.DOWN:
				DOWN = true;
				break;
			case Event.LEFT:
				LEFT = true;
				break;
			case Event.RIGHT:
				RIGHT = true;
				break;
			case Event.ENTER:
				PAUSE = !PAUSE;
				break;

			case 'K':
			case 'k':
				World.seeCollision = !World.seeCollision;
				break;
		}
		return true;
	}

	public boolean keyUp(Event e, int key)
	{
		switch(key)
		{
			case Event.UP:
				UP = false;
				break;
			case Event.DOWN:
				DOWN = false;
				break;
			case Event.LEFT:
				LEFT = false;
				break;
			case Event.RIGHT:
				RIGHT = false;
				break;
		}
		return true;
	}
}
