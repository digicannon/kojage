package kojage;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Component;
import java.util.ArrayList;
/**
 * Kojage's Java Open Applet Game Engine - SpriteVector.java
 * @author Thermos Pyro
 * @since 4.3, Fr/29/Ago/08
 * @version 4.3, Fr/29/Ago/08
 */
public class SpriteVector extends ArrayList<Sprite>
{
	public SpriteVector()
	{
	}

	public void addSprite(Sprite spr)
	{
		this.add(spr);
	}

	public void addSprite(double x, double y, int diameter)
	{
		this.add(new Sprite(x, y, diameter));
	}
	
	public void addSprite(double x, double y, int width, int height)
	{
		this.add(new Sprite(x, y, width, height));
	}
	
	public void addSprites(int map[], int width, int height, int size)
	{
		for (int a = 0; a < width; a++)
			for (int b = 0; b < height; b++)
				if (map[b*width+a] > 0)
				{
					Sprite spr = new Sprite (a*size, b*size, size);
					spr.type = map[b*width+a];
					this.addSprite(spr);
				}
	}

	public void addSprites(int map[], int width, int height, int size, SpriteVector sprV)
	{
		for (int a = 0; a < width; a++)
			for (int b = 0; b < height; b++)
				if (map[b*width+a] > 0)
				{
					Sprite spr = new Sprite (sprV.getSprite(map[b*width+a]));
					spr.type = map[b*width+a];
					spr.setOrigin(a*size, b*size);
					spr.resetPosition();
					this.addSprite(spr);
				}
	}
	
	public Sprite getSprite(int i)
	{
		return this.get(i);
	}
	
	public void moveSprites()
	{
		for ( int j = 0; j < size(); ++j )
		{
			Sprite spr = this.get(j);
			spr.Move();
			this.set(j, spr);
		}
	}
	
	public void drawSprites(Graphics g)
	{
		for ( int i = 0; i < this.size(); ++i )
		{
			Sprite spr = this.get(i);
			spr.drawSprite(g);
		}
	}
	
	public void drawSprites(Graphics g, Image img, Component comp)
	{
		for ( int i = 0; i < this.size(); ++i )
		{
			Sprite spr = this.get(i);
			spr.drawSprite(g, img, comp);
		}
	}
	
	public void drawSprites(Graphics g, Image img, int a, int b, Component comp)
	{
		for ( int i = 0; i < this.size(); ++i )
		{
			Sprite spr = this.get(i);
			spr.drawSprite(g, img, a, b, comp);
		}
	}
}
