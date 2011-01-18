import kojage.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Cursor;

/**
 * Platforms Test - platform_bas.java
 * @author daPhyre
 * @date 05/Nov/07
 */
public class platform_bas extends Game
{
	boolean JUMP, GAMEOVER;
	
	Sprite player = new Sprite (224, 64, 32, 64);
	int jumper = 0;
	Image iIdle, iJump, iBlock[] = new Image[2], iFG;
	Animation aRun;
	int map1[] = {
		 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,
		 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,
		 1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,1,
		 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,
		 0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 1,1,1,1,1,1,1,1,0,0,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1
	};
	
	public void init()
	{
		this.setBackground(Color.cyan);
		this.setCursor(Cursor.getPredefinedCursor(1));
		this.setSize(480, 320);
		
		World.setMap(map1, 30, 10, 32);
		
		iIdle = getImage (getCodeBase(), "media/idle.png");
		iJump = getImage (getCodeBase(), "media/jump.png");
		iBlock[1] = getImage (getCodeBase(), "media/block.png");
		iFG = getImage (getCodeBase(), "media/fg1.png");
		
		aRun = new Animation(getImage (getCodeBase(), ("media/run.png")), 32);
		/*aRun.addFrame(getImage (getCodeBase(), "media/run1.png"));
		aRun.addFrame(getImage (getCodeBase(), "media/run2.png"));
		aRun.addFrame(getImage (getCodeBase(), "media/run3.png"));
		aRun.addFrame(getImage (getCodeBase(), "media/run2.png"));*/
	}
	
	public void reset()
	{
		player.x = 224;
		player.y = 32;
		player.vx = 0;
		player.vy = 0;
		jumper = 0;
		GAMEOVER = false;
	}
	
	public void game()
	{
			if (!PAUSE)
			{
				// Vector X fixed
				if (RIGHT)
				{
					if (player.vx < 16)
						player.vx++;
					player.HFlip = false;
				}
				else
				{
					if (player.vx > 0)
						player.vx--;
				}
				if (LEFT)
				{
					if (player.vx > -16)
						player.vx--;
					player.HFlip = true;
				}
				else
				{
					if (player.vx < 0)
						player.vx++;
				}
				
				//	Vector Y fixed
				if (UP)
				{
					if (jumper > 0)
					{
						player.vy-=jumper*2;
						jumper--;
					}
				}
				
				player.vy+=2;
				
				//	Move player in X
				player.x += player.vx;
				if (player.collisionMap())
				{
					if (player.vx > 0)
						player.x = (int)(player.x/32)*32;
					else
						player.x = (int)(player.x/32+1)*32;
					player.vx = 0;
				}
				
				JUMP = true;
				//	Move player in Y
				player.y += player.vy;
				if (player.collisionMap())
				{
					if (player.vy > 0)
					{
						player.y = (int)(player.y/32)*32;
						jumper = 5;
						JUMP = false;
					}
					else
						player.y = (int)(player.y/32+1)*32;
					player.vy = 0;
				}
				
				//	OutSight
				if (player.x < -16)
					player.x = World.width -16;
				if (player.x > World.width - 16)
					player.x = -16;
				if (player.y > World.height)
				{
					PAUSE = true;
					GAMEOVER = true;
				}
				
				//	Focus to player
				World.focus(player);
			}
	}
	
	public void paint(Graphics g)
	{
		World.drawMap(g, iBlock, this);
		if (JUMP)
			player.drawSprite(g, iJump, this);
		else if (player.vx == 0)
			player.drawSprite(g, iIdle, this);
		else
			player.drawSprite(g, aRun.nextFrame(), this);
		
		g.drawString (""+player.x + ","+ player.y, 20,20);
		g.drawString (""+aRun.frames.size(), 20,40);
		g.drawImage(iFG, -(int)(World.x*2)%400, 300, this);
		if (PAUSE)
		{
			if (GAMEOVER)
				g.drawString ("GAME OVER", screenWidth/2-(int)(16), screenHeight/2);
			else
				g.drawString ("Pause", screenWidth/2-(int)(16), screenHeight/2);
			g.drawString ("Programmed by: daPhyre", screenWidth-(int)(160), screenHeight-(int)(20));
			g.drawString ("Octabot Network, 2007", screenWidth-(int)(150), screenHeight-(int)(4));
		}
	}
}
