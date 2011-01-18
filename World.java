package kojage;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Color;
/**
 * Kojage's Java Open Applet Game Engine - World.java
 * @author daPhyre
 * @since 3.0, Tu/06/May/08
 * @version 5.1, Fr/11/Jul/08
 */
public class World
{
	public static int x = 0;
	public static int y = 0;
	public static int width = 0;
	public static int height = 0;
	public static double scale = 1;
	public static SpriteVector map = new SpriteVector();
	public static boolean seeCollision;

	public static void setMap (int Map[], int Width, int Height, int Size)
	{
		width = Width * Size;
		height = Height * Size;
		map.removeAll(map);
		map.addSprites(Map, Width, Height, Size);
	}

	public static void setSize (int Width, int Height)
	{
		width = Width;
		height = Height;
	}

	public static void focus (Sprite spr)
	{
		Point pnt = spr.getCenter();
		if (pnt.x < Game.screenWidth/2 || width < Game.screenWidth)
			x = 0;
		else if (pnt.x > width - Game.screenWidth/2)
			x = (int)(width - Game.screenWidth);
		else
			x = (int)(pnt.x - Game.screenWidth/2);
		if (pnt.y < Game.screenHeight/2 || height < Game.screenHeight)
			y = 0;
		else if (pnt.y > height - Game.screenHeight/2)
			y = (int)(height - Game.screenHeight);
		else
			y = (int)(pnt.y - Game.screenHeight/2);
	}

	public static void drawMap (Graphics g)
	{
		g.setColor(Color.red);
		for (int i = 0; i < map.size(); i++)
		{
			Sprite spr = map.getSprite(i);
			g.drawRect((int)(spr.x-x), (int)(spr.y-y), (int)(spr.width), (int)(spr.height));
		}
	}

	public static void drawMap (Graphics g, Image img, Component comp)
	{
		g.setColor(Color.red);
		for (int i = 0; i < map.size(); i++)
		{
			Sprite spr = map.getSprite(i);
			g.drawImage(img, (int)(spr.x-x), (int)(spr.y-y), comp);
			if (seeCollision)
				g.drawRect((int)(spr.x-x), (int)(spr.y-y), (int)(spr.width), (int)(spr.height));
		}
	}

	public static void drawMap (Graphics g, Image[] img, Component comp)
	{
		g.setColor(Color.red);
		for (int i = 0; i < map.size(); i++)
		{
			Sprite spr = map.getSprite(i);
			g.drawImage(img[spr.type], (int)(spr.x-x), (int)(spr.y-y), comp);
			if (seeCollision)
				g.drawRect((int)(spr.x-x), (int)(spr.y-y), (int)(spr.width), (int)(spr.height));
		}
	}
}
