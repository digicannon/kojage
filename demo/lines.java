import kojage.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Event;
import java.util.ArrayList;

/**
 * Line Test - lines.java
 * @author daPhyre
 * @since Tu/04/Jan/11
 */
public class lines extends Game
{
	Sprite player = new Sprite (280, 80, 40);
	ArrayList<Line> li = new ArrayList();
	byte jumper = 0;
	Image iFloor;

	public void init()
	{
		this.setBackground(Color.gray);
		this.setCursor(Cursor.getPredefinedCursor(1));
		this.setSize(600, 400);
		World.setSize(1200, 800);

		li.add(new Line (10, 0, 10, 800));
		li.add(new Line (0, 790, 1200, 790));
		li.add(new Line (1190, 800, 1190, 0));
		li.add(new Line (1200, 10, 0, 10));

		//	Sector 1out
		li.add(new Line (0, 300, 200, 400));
		li.add(new Line (400, 400, 600, 300));
		li.add(new Line (600, 100, 400, 0));
		li.add(new Line (200, 0, 0, 100));

		//	Sector 1in
		li.add(new Line (200, 150, 300, 100));
		li.add(new Line (300, 100, 400, 150));
		li.add(new Line (200, 250, 200, 150));
		li.add(new Line (400, 150, 400, 250));
		li.add(new Line (200, 250, 300, 300));
		li.add(new Line (300, 300, 400, 250));
		
		//	Sector 2 & 3
		li.add(new Line (900, 0, 600, 100));
		li.add(new Line (600, 300, 900, 600));
		li.add(new Line (900, 300, 1200, 100));
		li.add(new Line (400, 600, 700, 600));

		//	Area 4
		li.add(new Line (0, 400, 700, 400));
		li.add(new Line (400, 800, 400, 410));
		li.add(new Line (100, 400, 300, 500));
		li.add(new Line (100, 600, 300, 500));
		li.add(new Line (100, 600, 300, 700));
		li.add(new Line (100, 800, 300, 700));
		
		iFloor = getImage (getCodeBase(), "media/tube.png");
	}

	public void game()
	{
		if (!PAUSE)
		{
			if (UP)
			{
				if (jumper > 0)
				{
					player.vy-=jumper*2;
					jumper--;
				}
			}

			if (LEFT)
			{
				if (player.vx > -8)
					player.vx--;
				else
					if (player.vx < 8)
						player.vx++;
			}
			else
				if (player.vx < 0)
					player.vx++;

			if (RIGHT)
			{
				if (player.vx < 8)
					player.vx++;
				else
					if (player.vx > -8)
						player.vx--;
			}
			else
				if (player.vx > 0)
					player.vx--;
			
			player.Move();

			//	Fall
			player.vy += 2;
			if (player.vy > player.getHeight())
				player.vy = player.getHeight();

			//	Out of world
			if (player.y > World.height)
			{
				player.x = 280;
				player.y = 80;
			}
			
			//	Line collision
			for (int i = 0; i < li.size(); i++)
			{
				//	Style 1 - Half works.
				if (li.get(i).collisionCircle(player))
				{
					double vx = player.vx;
					if(player.vy > 0)
						jumper = 5;
					player.setDirection(li.get(i).getAngle() - 90, player.getDiameter()/2 - li.get(i).ptSegDist(player.getCenter()));
					player.Move();
					player.vx = vx;
					player.vy = 0;
				}

				//	Style 2 -  Don't even try...
				/*player.x += player.vx;
				if (li.get(i).collisionCircle(player))
				{
					if (player.vx < 0)
						player.x = li.get(i).getXatY(player.getCenter().y) - 1;
					else if (player.vx > 0)
						player.x = li.get(i).getXatY(player.getCenter().y) - player.width - 1;
					player.vx = 0;
				}
				
				player.y += player.vy;
				if (li.get(i).collisionCircle(player))
				{
					if (player.vy < 0)
						player.y = li.get(i).getYatX(player.getCenter().x);
					else if (player.vy > 0)
					{
						player.y = li.get(i).getYatX(player.getCenter().x) - player.height;
						jumper = 5;
					}
					player.vy = 0;
				}*/
			}
			
			World.focus(player);
		}
	}

	public void paint(Graphics g)
	{
		for (int i = 0; i < li.size(); i++)
		{
			li.get(i).drawLine(g, iFloor, this);
			//g.drawString("L" + i + " Angle: " + li[i].getAngle() + " && " + (li[i].getAngle()+180), 20, 80 + 20*i);
		}
		player.drawSprite(g);
		g.drawString("(" + (int)player.x + ", " +(int)player.y + ")", 20, 20);
		g.drawString("Angle: " + player.getAngle(), 20, 40);
		g.drawString("Speed: " + player.getSpeed(), 20, 60);
		g.drawString("Jumper: " + jumper, 20, 80);
		if (PAUSE)
		{
			g.drawString ("Pause", screenWidth/2-(int)(16), screenHeight/2);
			g.drawString ("Programmed by: daPhyre", screenWidth-(int)(160), screenHeight-(int)(20));
			g.drawString ("Octabot Network, 2011", screenWidth-(int)(150), screenHeight-(int)(4));
		}
	}

	public boolean keyDown(Event e, int key)
	{
		super.keyDown(e, key);
		switch (key)
		{			
			case '>':
				if (player.scale < 2)
					player.scale += 0.1;
				break;

			case '<':
				if (player.scale > 0.1)
					player.scale -= 0.1;
				break;
		}
		return true;
	}
}