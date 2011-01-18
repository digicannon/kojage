import kojage.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Event;
import java.awt.Cursor;

/**
 * Test - maze_bas.java
 * @author daPhyre
 * @since 1.0, Ma/18/Sep/07
 * Modified for GameLib (now KOJAGE) on Fr/04/Apr/08
 */
public class maze_bas extends Game
{
	Sprite player = new Sprite (240, 176, 32);
	Sprite enemy[] = new Sprite [3];
	boolean colliding;
	Image iPlayer, iEnemy, iBlocks[] = new Image[4];
	ParticleSystem ps = new ParticleSystem();
	int map1[] = {
		 1,0,0,0,0,0,2,2,2,2,0,0,0,0,0,1,
		 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,
		 0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,
		 3,0,0,2,0,0,0,0,0,0,0,0,2,0,0,3,
		 3,0,0,2,0,0,0,0,0,0,0,0,2,0,0,3,
		 3,0,0,2,0,0,0,0,0,0,0,0,2,0,0,3,
		 3,0,0,2,0,0,0,0,0,0,0,0,2,0,0,3,
		 0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,
		 0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,
		 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 1,0,0,0,0,0,2,2,2,2,0,0,0,0,0,1
	};

	public void init()
	{
		this.setBackground(Color.black,getImage (getCodeBase(), "media/grass.png"), false);
		this.setCursor(Cursor.getPredefinedCursor(1));
		this.setSize(480, 320);
		
		World.setMap(map1, 16, 12, 32);
		for (int i = 0; i < 3; i++)
			enemy[i] = new Sprite (240*i, 64, 32);
		
		iPlayer = getImage (getCodeBase(), "media/player.png");
		iEnemy = getImage (getCodeBase(), "media/enemy.png");
		iBlocks[1] = getImage (getCodeBase(), "media/block.png");
		iBlocks[2] = getImage (getCodeBase(), "media/lr.png");
		iBlocks[3] = getImage (getCodeBase(), "media/ud.png");
	}

	public void game()
	{
		// If not in pause, moves player
		if (!PAUSE)
		{
			if (UP)
			{
				player.VFlip=true;
				if (player.y > 0)
					player.y-=4;
				if (player.collisionMap(1) || player.collisionMap(2))
					player.y+=4;
			}
			
			if (DOWN)
			{
				player.VFlip=false;
				if (player.y < World.height - player.getHeight())
					player.y+=4;
				if (player.collisionMap(1) || player.collisionMap(2))
					player.y-=4;
			}
			
			if (LEFT)
			{
			
				player.HFlip=true;
				if (player.x > 0)
					player.x-=4;
				if (player.collisionMap(1) || player.collisionMap(3))
					player.x+=4;
			}
			
			if (RIGHT)
			{
				player.HFlip=false;
				if (player.x < World.width - player.getWidth())
					player.x+=4;
				if (player.collisionMap(1) || player.collisionMap(3))
					player.x-=4;
			}
		}

		// Focus to player
		World.focus(player);
		// Move particles
		ps.moveParticles();

		// Enemy rotates
		for (int i = 0; i < 3; i++)
			enemy[i].rotation = 45 + getAngle(enemy[i].getCenter().x*World.scale, enemy[i].getCenter().y*World.scale, mousex, mousey);

		// Detects if collides
		colliding = false;
		for (int i = 0; i < 3; i++)
		{
			if ( player.collisionCircle(enemy[i]) )
			{
				colliding = true;
				for (int j = 0; j < 10; j++)
					ps.addParticle(player.getCenter().x, player.getCenter().y, 1, 24, random(2f), random(360), Color.yellow, Color.blue);
			}					
		}
	}
	
	public void paint(Graphics g)
	{
		World.drawMap(g, iBlocks, this);
		player.drawSprite(g, iPlayer, this);
		for (int i = 0; i < 3; i++)
			enemy[i].drawSprite(g, iEnemy, this);
		
		ps.drawParticles(g);
		
		g.setColor(Color.white);
		if (colliding)
			g.drawString ("COLLISION!!", (int)(222), (int)(140));
		g.drawString ("(" + (int)player.x + "," + (int)player.y + ")", (int)(10), (int)(10));
		if (PAUSE)
		{
			g.drawString ("Pause", screenWidth/2-(int)(16), screenHeight/2);
			g.drawString ("Programmed by: daPhyre", screenWidth-(int)(160), screenHeight-(int)(20));
			g.drawString ("Octabot Network, 2007", screenWidth-(int)(150), screenHeight-(int)(4));
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
