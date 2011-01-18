package kojage;

import java.awt.geom.Line2D;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Component;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
/**
 * Kojage's Java Open Applet Game Engine - Line.java
 * @author daPhyre
 * @since 6.0, Tu/04/Jan/11
 * @version 6.0, Tu/04/Jan/11
 */
public class Line extends Line2D.Double
{
	public Line ()
	{

	}

	public Line (Point p1, Point p2)
	{
		this.setLine(p1, p2);
	}
	
	public Line (double x1, double y1, double x2, double y2)
	{
		this.setLine(x1, y1, x2, y2);
	}

	public double getLenght()
	{
		double dx = x2 - x1;
		double dy = y2 - y1;
		return (Math.sqrt(dx * dx + dy * dy));
	}

	public double getAngle()
	{
		double angle = ( (Math.atan2(y2 - y1, x2 - x1)) / (Math.PI/180) );
		if (angle > 360)
			angle -= 360;
		if (angle < 0)
			angle += 360;
		return angle;
	}

	public double getXatY(double y)
	{
		double m = (this.y2 - this.y1) / (this.x2 - this.x1);
		return (y-this.y1)/m + this.x1;
	}

	public double getYatX(double x)
	{
		double m = (this.y2 - this.y1) / (this.x2 - this.x1);
		return m * (x-this.x1) + this.y1;
	}

	public boolean collisionCircle (Sprite spr)
	{
		return collisionCircle(spr, false);
	}

	public boolean collisionCircle (Sprite spr, boolean inner)
	{
		if ( (this.getAngle() < spr.getAngle() && this.getAngle()+180 > spr.getAngle()) || (this.getAngle()-360 < spr.getAngle() && this.getAngle()-180 > spr.getAngle()) )
			return this.ptSegDist(spr.getCenter()) < (spr.getDiameter(inner)/2);
		else
			return false;
	}

	public void drawLine (Graphics g)
	{
		g.setColor(Color.white);
		double dx = 8 * Math.cos((Math.PI / 180) * (getAngle()+90));
		double dy = 8 * Math.sin((Math.PI / 180) * (getAngle()+90));
		g.drawLine((int)x1 - World.x, (int)y1 - World.y, (int)x2 - World.x, (int)y2 - World.y);
		g.drawLine((int)x1 - World.x, (int)y1 - World.y, (int)(x1+dx) - World.x, (int)(y1+dy) - World.y);
		g.drawLine((int)(x1+x2)/2 - World.x, (int)(y1+y2)/2 - World.y, (int)((x1+x2)/2+dx) - World.x, (int)((y1+y2)/2+dy) - World.y);
		g.drawLine((int)x2 - World.x, (int)y2 - World.y, (int)(x2+dx) - World.x, (int)(y2+dy) - World.y);
	}

	public void drawLine (Graphics g, Image img, Component comp)
	{
		AffineTransform at = new AffineTransform();
		Graphics2D g2d = (Graphics2D)g;
		Graphics g2;
		Image tImg;
		tImg= comp.createImage((int)this.getLenght(), img.getHeight(comp));
		g2 = tImg.getGraphics();

		int x = 0;
		while(x < World.width) {
			g2.drawImage(img, x, 0, comp);
			x += img.getWidth(null);
		}

		at.translate(x1 - World.x, y1 - World.y);
		at.rotate(Math.atan2(y2 - y1, x2 - x1));
		g2d.drawImage(tImg, at, comp);

		if (World.seeCollision)
		{
			drawLine(g);
		}
	}
}
