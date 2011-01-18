import kojage.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Event;
import java.awt.Cursor;

/**
 * Platforms Test - platform_adv.java
 * @author daPhyre
 * @date 05/Nov/07
 */
public class platform_adv extends Game
{
	boolean BUTTON_A, BUTTON_B, START, SELECT, BUTTON_L, BUTTON_R;
	
	Sprite player = new Sprite (0, 0, 32, 64), dummie = new Sprite (0, 0, 32, 8), shine = new Sprite (0, 0, 16, 16);
	boolean ONAIR, DUCK, ONWATER, ONWALL, ONCLIMB;
	byte jumper = 7;
	byte bouncer = 0;
	byte life = 0;
	int score = 0;
	ParticleSystem ps = new ParticleSystem();
	Image iIdle, iJump, iDuck, iWall, iBreak, iClimb, iDeath, iLookup, iBlock[] = new Image[10];
	Animation aRun, aCrawl, aSwim;
	int map1[] = {
		 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
		 1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,
		 1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,
		 1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,
		 1,0,0,0,0,0,0,0,0,0,0,0,1,9,9,9,9,9,1,7,0,1,3,3,3,1,9,9,9,1,
		 1,8,8,8,8,1,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,1,1,1,1,1,0,0,0,1,
		 1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,1,
		 1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,1,
		 1,0,0,0,0,1,0,0,0,0,0,1,9,9,9,1,0,0,0,7,0,0,0,0,0,0,0,0,0,1,
		 1,0,0,0,0,1,4,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,5,1,9,9,9,1,
		 1,0,0,0,0,1,0,4,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,5,0,0,0,0,0,1,
		 1,0,0,0,0,1,0,0,4,0,0,0,0,0,0,0,0,0,0,7,0,0,5,0,0,0,0,0,0,1,
		 1,0,0,0,0,0,0,0,0,4,0,0,0,0,0,5,1,1,4,7,0,5,0,0,0,0,0,0,0,1,
		 1,0,0,0,0,1,1,1,1,1,1,1,9,9,1,1,1,1,1,1,8,8,8,8,8,1,0,0,0,1,
		 1,0,0,0,0,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,1,
		 1,9,9,9,9,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,1,
		 1,0,0,0,0,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,1,
		 1,2,2,2,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,1,
		 1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,1,
		 1,1,1,1,1,1,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,6,6,6,1
	};
	int map2[] = {
		 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 1,1,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 1,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,8,8,1,0,
		 0,0,7,1,9,9,9,1,0,0,1,0,1,0,1,0,1,0,1,0,1,3,3,3,3,1,0,0,0,0,
		 0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,
		 0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,
		 0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,7,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,1,8,8,8,8,8,8,1,0,
		 0,0,7,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,
		 0,0,7,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,7,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,7,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,1,6,6,6,1,0,
		 3,1,1,1,1,1,3,3,3,3,3,1,3,3,3,3,3,1,1,1,1,1,1,1,1,3,3,3,1,3
	};
	
	public void init()
	{
		this.setBackground(Color.cyan);
		this.setCursor(Cursor.getPredefinedCursor(1));
		this.setSize(480, 320);
		PAUSE = true;
		
		World.setMap(map1, 30, 20, 32);
		
		iIdle = getImage (getCodeBase(), "media/idle.png");
		iJump = getImage (getCodeBase(), "media/jump.png");
		iDuck = getImage (getCodeBase(), "media/duck.png");
		iWall = getImage (getCodeBase(), "media/wall.png");
		iBreak = getImage (getCodeBase(), "media/break.png");
		iClimb = getImage (getCodeBase(), "media/climb.png");
		iDeath = getImage (getCodeBase(), "media/death.png");
		iLookup = getImage (getCodeBase(), "media/lookup.png");
		
		aRun = new Animation(getImage (getCodeBase(), "media/run.png"), 32);
		aCrawl = new Animation(getImage (getCodeBase(), "media/crawl.png"), 32, 2);
		aSwim = new Animation(getImage (getCodeBase(), "media/swim.png"), 32, 3);
		
		iBlock[1] = getImage (getCodeBase(), "media/block.png");
		iBlock[2] = getImage (getCodeBase(), "media/water.png");
		iBlock[3] = getImage (getCodeBase(), "media/lava.png");
		iBlock[4] = getImage (getCodeBase(), "media/lurd.png");
		iBlock[5] = getImage (getCodeBase(), "media/ldru.png");
		iBlock[6] = getImage (getCodeBase(), "media/bouncer.png");
		iBlock[7] = getImage (getCodeBase(), "media/ud.png");
		iBlock[8] = getImage (getCodeBase(), "media/tube.png");
		iBlock[9] = getImage (getCodeBase(), "media/rope.png");
	}
	
	public void reset()
	{
		player.x = 192;
		player.y = 64;
		player.vx = 0;
		player.vy = 0;
		player.rotation = 0;
		jumper = 0;
		life = 100;
		score = 0;
	}
	
	public void movePlayer()
	{
		//	Move Rigth
		if (RIGHT)
		{
			if (BUTTON_B && !DOWN && !player.collisionMap(2))
			{
				if (player.vx < 16)
					player.vx++;
			}
			else
			{
				if (player.vx < 8)
					player.vx++;
				else
					if (player.vx > 8)
						player.vx--;
			}
			player.HFlip = false;
		}
		else
			if (player.vx > 0)
				player.vx--;
		
		//	Move Left
		if (LEFT)
		{
			if (BUTTON_B && !DOWN && !player.collisionMap(2))
			{
				if (player.vx > -16)
					player.vx--;
			}
			else
			{
				if (player.vx > -8)
					player.vx--;
				else
					if (player.vx < -8)
						player.vx++;
			}
			player.HFlip = true;
		}
		else
			if (player.vx < 0)
				player.vx++;
		
		//	Jump
		if (BUTTON_A)
		{
			if (DOWN)							//DownRope
			{
				if (dummie.collisionMap(9))
				{
					player.y += 33;
					dummie.y -= 33;
				}
			}
			else if (jumper > 0 && !ONCLIMB)	//Jump
			{
				player.vy-=jumper*2;
				jumper--;
			}
			else if (bouncer != 0)				//Bounce
			{
				player.vx += bouncer;
				player.vy -= 5;
			}
		}
		else
			jumper = 0;
		
		//	Climb
		if (UP)
		{
			if (player.collisionMap(7))
			{
				ONCLIMB = true;
			}
			if (ONCLIMB)
			{
				player.vy = -4;
			}
		}
		
		//	Duck & Rise
		if (DOWN)
		{
			if (player.height == 64 && !ONAIR)
			{
				player.y += 32;
				player.height = 32;
				DUCK = true;
			}
			else if (player.collisionMap(7))
			{
				ONCLIMB = true;
			}
			if (ONCLIMB)
			{
				player.vy = 4;
			}
		}
		else
		{
			if (player.height == 32)
			{
				player.y -= 32;
				player.height = 64;
				DUCK = false;
			}
			if (player.collisionMap(1))
			{
				player.y += 32;
				player.height = 32;
				DUCK = true;
			}
		}
		
		
		
		//	Power
		if (BUTTON_B)
		{
			for (int j = 0; j < 10; j++)
				ps.addParticle(player.getCenter().x, player.getCenter().y, 1, 24, random(2f), random(360), Color.blue, Color.cyan);
		}				
		
		//	Move player on X
		ONWALL = false;
		player.x += player.vx;
		if (player.collisionMap(1))
		{
			if (player.vx > 0)					//RIGHT
			{
				player.x = (int)(player.x/32)*32;
				if (player.vy > 4 && !ONWATER)
				{
					player.vy = 4;
					ONWALL = true;
					if (!BUTTON_A)
						bouncer = -7;
				}
			}
			else								//LEFT
			{
				player.x = (int)(player.x/32+1)*32;
				if (player.vy > 4 && !ONWATER)
				{
					player.vy = 4;
					ONWALL = true;
					if (!BUTTON_A)
						bouncer = 7;
				}
			}
			player.vx = 0;
		}
		
		//	Move player on Y
		ONAIR = true;
		player.y += player.vy;
		if (player.collisionMap(1))
		{
			if (player.vy > 0)					//DOWN
			{
				player.y = (int)(player.y/32)*32;
				ONAIR = false;
				ONCLIMB = false;
				bouncer = 0;
				if (!BUTTON_A)
					jumper = 5;
			}
			else								//UP
			{
				player.y = (int)(player.y/32+1)*32;
			}
			player.vy = 0;
		}
		
		//	Down rope
		/*int i = player.collisionMapY(4);
		if (i > -1)
		{
			if (player.vy > 0)					//DOWN
			{
				//player.y = (int)((player.y)/32)*32+(int)(player.x%32);
				player.y = i - player.height + (int)(player.x%32);
				ONAIR = false;
				ONCLIMB = false;
				bouncer = 0;
				if (!BUTTON_A)
					jumper = 5;
			}
			/*else								//UP
			{
				player.y = (int)((player.y-2)/32+1)*32+(int)(player.x%32)-4;
			}/
			player.vy = 0;
			
		}
		
		//	Up rope
		i = player.collisionMapY(5);
		if (i > -1)
		{
			if (player.vy > 0)					//DOWN
			{
				player.y = i + 32 - player.height - (int)((player.x-1)%32) - 1;
				ONAIR = false;
				ONCLIMB = false;
				bouncer = 0;
				if (!BUTTON_A)
					jumper = 5;
			}
			/*else								//UP
			{
				player.y = (int)(player.y/32+2)*32-(int)(player.x%32);
			}/
			player.vy = 0;
			
		}*/
				
		//	Rope and bar
		if (dummie.collisionMapRange(8, 9))
		{
			if (player.vy > 0)
			{
				player.y = (int)(player.y/32)*32;
				ONAIR = false;
				player.vy = 0;
				if (!BUTTON_A)
					jumper = 5;
			}
		}
		
		//	Bouncer
		if (player.collisionMap(6))
		{
			if (player.vy > 0)
			{
				player.y = (int)(player.y/32-1)*32;
				ONAIR = false;
				player.vy = 0;
				jumper = 7;
			}
		}
		
		//	Water
		if (player.collisionMap(2))
		{
			ONWATER = true;
			player.vy += 0.5;
			if (player.vy > 8)
				player.vy = 5;
			if (!BUTTON_A)
				jumper = 2;
		}
		else
		{
			ONWATER = false;
			player.vy += 2;
			if (player.vy > 30)
				player.vy = 30;
		}
		
		//	Magma
		if (player.collisionMap(3))
			life--;
		
		//	Stairs
		if(player.collisionMap(7))
		{
			if (ONCLIMB)
			{
				player.HFlip = !player.HFlip;
				player.vy = 0;
				jumper = 5;
			}
		}
		else
			ONCLIMB = false;
		
		//	Bouncer Changes
		if (bouncer > 0)
			bouncer--;
		if (bouncer < 0)
			bouncer++;
		
		dummie.x = player.x;
		dummie.y = player.y + player.getHeight();
	}
	
	public void game()							//Starts Game
	{
		if (!PAUSE)
		{
			movePlayer();
			
			//	Dies
			if (player.y > World.height)
				life = 0;
			
			if (life < 1)
			{
				PAUSE = true;
				player.vy = -16;
			}
			
			//	Focus to player
			World.focus(player);
			
			//	Player-Shine Collision
			if (player.collisionBox(shine))
			{
				score++;
				shine.x = random(World.width);
				shine.y = random(World.height);
				
			}
			
			//	Shine-Wall Collision
			if (shine.collisionMap(1))
			{
				shine.x = random(World.width);
				shine.y = random(World.height);
			}
			
			//	Shine
			for (int j = 0; j < 10; j++)
				ps.addParticle(shine.getCenter().x, shine.getCenter().y, 1, 24, random(2f), random(360), Color.yellow, Color.orange);
			
			ps.moveParticles();
		}
		if (life < 1)
		{
			//	Death Animation
			if (player.y < World.height+32)
			{
				player.vy+=1;
				player.y += player.vy;
				player.rotation += 15;
					
			}
			//	Switch map
			if(BUTTON_L)
			{
				this.setBackground(Color.CYAN);
				World.setMap(map1, 30, 20, 32);
			}
			if(BUTTON_R)
			{
				this.setBackground(Color.PINK);
				World.setMap(map2, 30, 20, 32);
			}
		}
			
	}

	public void paint(Graphics g)
	{
		World.drawMap(g, iBlock, this);
		if (life < 1)
			player.drawSprite(g, iDeath, this);
		else if (DUCK)
		{
			if (LEFT || RIGHT)
				player.drawSprite(g, aCrawl.nextFrame(), this);
			else
				player.drawSprite(g, iDuck, this);
		}
		else if (ONCLIMB)
				player.drawSprite(g, iClimb, this);
		else if (ONAIR)
		{
			if (ONWATER)
				player.drawSprite(g, aSwim.nextFrame(), this);
			else if (ONWALL)
				player.drawSprite(g, iWall, this);
			else
				player.drawSprite(g, iJump, this);
		}
		else if (player.vx == 0)
			player.drawSprite(g, iIdle, this);
		else
		{
			if (LEFT && player.vx > 0 || RIGHT && player.vx < 0 )
				player.drawSprite(g, iBreak, this);
			else
				player.drawSprite(g, aRun.nextFrame(), this);
		}
		if (UP && !ONCLIMB)
			player.drawSprite(g, iLookup, this);
		if (World.seeCollision)
			dummie.drawSprite(g);
		ps.drawParticles(g);
		g.drawOval((int)(shine.x-World.x), (int)(shine.y-World.y), shine.width, shine.height);
		g.setColor(Color.green);
		g.fillRect(screenWidth-(int)(110), 4, life, 8);
		g.setColor(Color.gray);
		g.drawRect(screenWidth-(int)(110), 4, 100, 8);
		
		g.setColor(Color.black);
		g.drawString (""+player.x + ","+ player.y, 20,20);
		g.drawString ("Health: "+life+"%", screenWidth-(int)(80),30);
		g.drawString ("Score: "+score, screenWidth-(int)(80),50);
		if (PAUSE)
		{
			if (life < 1)
				g.drawString ("GAME OVER", screenWidth/2-(int)(16), screenHeight/2);
			else
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
			case 'A':
			case 'a':
				BUTTON_A = true;
				break;
				
			case 'S':
			case 's':
				BUTTON_B = true;
				break;
				
			case Event.ENTER:
				START = true;
				if (life < 1)
					reset();
				break;
				
			case Event.TAB:
				SELECT = true;
				break;
				
			case 'Q':
			case 'q':
				BUTTON_L = true;
				break;
				
			case 'W':
			case 'w':
				BUTTON_R = true;
				break;
		}
		return true;
	}
	
	public boolean keyUp (Event e, int key)
	{
		super.keyUp(e, key);
		switch(key)
		{
			case 'A':
			case 'a':
				BUTTON_A = false;
				break;
				
			case 'S':
			case 's':
				BUTTON_B = false;
				break;
				
			case Event.ENTER:
				START = false;
				break;
				
			case Event.TAB:
				SELECT = false;
				break;
				
			case 'Q':
			case 'q':
				BUTTON_L = false;
				break;
				
			case 'W':
			case 'w':
				BUTTON_R = false;
				break;
		}
		return true;
	}
}
