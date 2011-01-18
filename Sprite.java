package kojage;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Component;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
/**
 * Kojage's Java Open Applet Game Engine - Sprite.java
 * @author daPhyre
 * @since 0.0, Tu/18/Sep/07
 * @version 6.0, Tu/04/Jan/11
 */
public class Sprite
{
	public double ox;
	public double oy;
    public int ohealth;
	public short width;
	public short height;
	
	public double x;
	public double y;
	public double vx;
	public double vy;
	public int type;
    public int health;
	//public double speed;
	//public double angle;
	public double rotation;
	public double scale;
	public boolean HFlip;
	public boolean VFlip;

    public int var1;
    public int var2;
	public boolean flag1;
	public boolean flag2;

	public Sprite (Sprite spriteMaster)
	{
		this.ox = spriteMaster.ox;
		this.oy = spriteMaster.oy;
		this.ohealth = spriteMaster.ohealth;
		this.width = spriteMaster.width;
		this.height = spriteMaster.height;

		this.x = spriteMaster.x;
		this.y = spriteMaster.y;
		this.vx = spriteMaster.vx;
		this.vy = spriteMaster.vy;
		this.type = spriteMaster.type;
		this.health = spriteMaster.health;
		//this.speed = spriteMaster.speed;
		//this.angle = spriteMaster.angle;
		this.rotation = spriteMaster.rotation;
		this.scale = spriteMaster.scale;
		this.HFlip = spriteMaster.HFlip;
		this.VFlip = spriteMaster.VFlip;

		this.var1 = spriteMaster.var1;
		this.var2 = spriteMaster.var2;
		this.flag1 = spriteMaster.flag1;
		this.flag2 = spriteMaster.flag2;
	}
	
	public Sprite (double x, double y, int diameter)
	{
		this.ox = x;
		this.oy = y;
        this.ohealth = 1;
		this.width = (short)diameter;
		this.height = (short)diameter;
		this.Reset();
	}

	public Sprite (double x, double y, int width, int height)
	{
		this.ox = x;
		this.oy = y;
        this.ohealth = 1;
		this.width = (short)width;
		this.height = (short)height;
		this.Reset();
	}
	
	public void setOrigin(double x, double y)
	{
		this.ox = x;
		this.oy = y;
	}

    public void setHealth(int health)
    {
        this.ohealth = health;
        this.health = health;
    }

	public void resetPosition()
	{
		this.x = this.ox;
		this.y = this.oy;
	}
	
	public void Reset()
	{
		this.x = this.ox;
		this.y = this.oy;
        this.health = this.ohealth;
		this.vx = 0;
		this.vy = 0;
		//this.speed = 0;
		//this.angle = 0;
		this.rotation = 0;
		this.scale = 1;
	}
	
	public void Move()
	{
		x += vx;
		y += vy;
	}

	public void setDirection(double angle, double speed)
	{
		vx += speed * Math.cos((Math.PI / 180) * angle);
		vy += speed * Math.sin((Math.PI / 180) * angle);
	}

	/*public double getRadius()
	{
		return getRadius(false);
	}

	public double getRadius(boolean inner)
	{
		if (inner)
			return Math.min(width, height)*scale/2;
		else
			return Math.max(width, height)*scale/2;
	*/

	public double getAngle()
	{
		double angle = ( (Math.atan2(vy, vx)) / (Math.PI/180) );
		if (angle > 360)
			angle -= 360;
		if (angle < 0)
			angle += 360;
		return angle;
	}

	public double getSpeed()
	{
		return ( vx / Math.cos((Math.PI / 180) * getAngle() ) );
	}

	public Point getCenter()
	{
		return new Point((int)(x + width*scale/2),(int)(y + height*scale/2));
	}
	
	public double getWidth()
	{
		return width*scale;
	}
	
	public double getHeight()
	{
		return height*scale;
	}

	public double getDiameter()
	{
		return getDiameter(false);
	}
	
	public double getDiameter(boolean inner)
	{
		if (inner)
			return Math.min(width, height)*scale;
		else
			return Math.max(width, height)*scale;
	}
	
	/*	Sprite Collision	*/
    public double distance(Sprite spr, boolean inner)
    {
        double dx = getCenter().x - spr.getCenter().x;
		double dy = getCenter().y - spr.getCenter().y;
		return (Math.sqrt(dx * dx + dy * dy) - (getDiameter(inner)/2 + spr.getDiameter(inner)/2));
    }

	public boolean collisionCircle(Sprite spr)
	{
		return collisionCircle(spr, false);
	}

	public boolean collisionCircle(Sprite spr, boolean inner)
	{
		return (distance(spr, inner) < 0);
	}

	public boolean collisionBox(Sprite spr)
	{
		return
		(
			x < spr.x + spr.getWidth() &&
			x + getWidth() > spr.x &&
			y < spr.y + spr.getHeight() &&
			y + getHeight() > spr.y
		);
	}
	
	public boolean collisionMap ()
	{
		boolean collision = false;
		for (int i = 0; i < World.map.size(); i++)
		{
			Sprite spr = World.map.getSprite(i);
			if	(
					x < spr.x + spr.getWidth() &&
					x + getWidth() > spr.x &&
					y < spr.y + spr.getHeight() &&
					y + getHeight() > spr.y
				)
			collision = true;
		}
		return collision;
	}
	
	public boolean collisionMap (int type)
	{
		boolean collision = false;
		for (int i = 0; i < World.map.size(); i++)
		{
			Sprite spr = World.map.getSprite(i);
			if	(
					spr.type == type &&
					x < spr.x + spr.getWidth() &&
					x + getWidth() > spr.x &&
					y < spr.y + spr.getHeight() &&
					y + getHeight() > spr.y
				)
			collision = true;
		}
		return collision;
	}

	public boolean collisionMapRange (int typeMin, int typeMax)
	{
		boolean collision = false;
		for (int i = 0; i < World.map.size(); i++)
		{
			Sprite spr = World.map.getSprite(i);
			if	(
					spr.type >= typeMin &&
					spr.type <= typeMax &&
					x < spr.x + spr.getWidth() &&
					x + getWidth() > spr.x &&
					y < spr.y + spr.getHeight() &&
					y + getHeight() > spr.y
				)
			collision = true;
		}
		return collision;
	}

	public boolean collisionMapSwitch (int type, int newType)
	{
		boolean collision = false;
		for (int i = 0; i < World.map.size(); i++)
		{
			Sprite spr = World.map.getSprite(i);
			if	(
					spr.type == type &&
					x < spr.x + spr.getWidth() &&
					x + getWidth() > spr.x &&
					y < spr.y + spr.getHeight() &&
					y + getHeight() > spr.y
				)
			{
				collision = true;
				if (newType > 0)
					spr.type = newType;
				else
					World.map.remove(i);
			}
		}
		return collision;
	}
	
	/*	DrawSprite	*/
	public void drawSprite (Graphics g)
	{
		g.setColor(Color.GREEN);
		g.drawRect((int)(x-World.x), (int)(y-World.y), (int)(getWidth()), (int)(getHeight()));
		g.setColor(Color.CYAN);
		g.drawOval((int)(getCenter().x-getDiameter(true)/2-World.x), (int)(getCenter().y-getDiameter(true)/2-World.y), (int)(getDiameter(true)), (int)(getDiameter(true)));
		g.setColor(Color.BLUE);
		g.drawOval((int)(getCenter().x-getDiameter(false)/2-World.x), (int)(getCenter().y-getDiameter(false)/2-World.y), (int)(getDiameter(false)), (int)(getDiameter(false)));
	}
	
	public void drawSprite (Graphics g, Image img, Component comp)
	{
		drawSprite(g, img, 0, 0, comp);
	}
	
	public void drawSprite (Graphics g, Image img, int a, int b, Component comp)
	{
		AffineTransform at = new AffineTransform();
		Graphics2D g2d = (Graphics2D)g;
		if (HFlip)
		{
			if (VFlip)
			{
				at.translate((x+(a+img.getWidth(comp))*scale)-World.x, (y+(b+img.getHeight(comp))*scale)-World.y);
				at.scale(-(scale), -(scale));
				at.rotate(rotation*(Math.PI/180), getWidth()/2, getHeight()/2);
			}
			else
			{
				at.translate((x+(a+img.getWidth(comp))*scale)-World.x, (y+b*scale)-World.y);
				at.scale(-(scale), (scale));
				at.rotate(rotation*(Math.PI/180), getWidth()/2, getHeight()/2);
			}
		}
		else
		{
			if (VFlip)
			{
				at.translate((x+a*scale)-World.x, (y+(b+img.getHeight(comp))*scale)-World.y);
				at.scale((scale), -(scale));
				at.rotate(rotation*(Math.PI/180), getWidth()/2, getHeight()/2);
			}
			else
			{
				at.translate((x+a*scale)-World.x, (y+b*scale)-World.y);
				at.scale((scale), (scale));
				at.rotate(rotation*(Math.PI/180), getWidth()/2, getHeight()/2);
			}
		}
		g2d.drawImage(img, at, comp);
			
		if (World.seeCollision)
		{
			drawSprite(g);
		}
	}
}
