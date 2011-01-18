package kojage;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Component;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
/**
 * Kojage's Java Open Applet Game Engine - ParticleSystem.java
 * @author Thermos Pyro
 * @date Fr/18/Apr/08
 * @since 2.0, Th/17/Apr/08
 * @version 2.1, Fr/18/Apr/08
 */
public class ParticleSystem extends ArrayList<Particle>
{
	public double gravity = 0;
	public double wind = 0;
	
	public ParticleSystem()
	{
	}

	public void addParticle(Particle p)
	{
		this.add(p);
	}

	public void addParticle(double x, double y, int diameter, int maxage, double speed, double angle, Color start, Color end)
	{
		this.add(new Particle(x, y, diameter, maxage, speed, angle, start, end));
	}
	
	public void moveParticles()
	{
		for ( int j = 0; j < this.size(); ++j )
		{
			Particle p = this.get(j);
			if (p.age < p.maxage)
			{
				p.x += p.speed * (Math.cos(p.angle * (Math.PI / 180))) + wind * p.age;
				p.y += p.speed * (Math.sin(p.angle * (Math.PI / 180))) + gravity * p.age;
				p.age++;
				p.color = (Color)p.colorList.remove(0);
				this.set(j, p);
			}
			else
			this.remove(j);
		}
	}
	
	public void moveParticlesO()
	{
		for ( int j = 0; j < this.size(); ++j )
		{
			Particle p = this.get(j);
			if (p.age < p.maxage)
			{
				p.ox = p.x;
				p.oy = p.y;
				double piRad = (Math.PI / 180);
				p.x += p.speed * (Math.cos(p.angle *piRad)) + wind * p.age;
				p.y += p.speed * (Math.sin(p.angle *piRad)) + gravity * p.age;
				p.age++;
				p.color = (Color)p.colorList.remove(0);
				this.set(j, p);
			}
			else
			this.remove(j);
		}
	}
	
	public void drawParticles(Graphics g)
	{
		for ( int i = 0; i < this.size(); ++i )
		{
			Particle p = this.get(i);
			g.setColor(p.color);
			g.fillOval ((int)(p.x-World.x), (int)(p.y-World.y), (int)(p.diameter), (int)(p.diameter));
		}
	}
	
	public void drawParticlesO(Graphics g)
	{
		for ( int i = 0; i < this.size(); ++i )
		{
			Particle p = this.get(i);
			g.setColor(p.color);
			g.drawLine((int)p.ox, (int)p.oy, (int)p.x, (int)p.y);
			g.fillOval ((int)p.x, (int)p.y, p.diameter, p.diameter);
		}
	}
	
	public void drawParticles(Graphics g, Image img, Component comp)
	{
		for ( int i = 0; i < this.size(); ++i )
		{
			Particle p = this.get(i);
			AffineTransform at = new AffineTransform();
			Graphics2D g2d = (Graphics2D)g;
			at.translate((p.x-img.getWidth(comp)/2)-World.x,(p.y-img.getHeight(comp)/2)-World.y);
			at.rotate(p.rotation*(Math.PI/180), img.getWidth(comp)/2, img.getHeight(comp)/2);
			g2d.drawImage(img, at, comp);
		}
	}
}
