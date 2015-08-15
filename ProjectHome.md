# KOJAGE #

KOJAGE is a group of classes which works as a Game Engine for 2D web games in applets.

## History ##

For the last 5 years, I've been developing a Game Engine for Java Applets games with all the knowledge I've been adquiring, being at that time the only way free-to-make games (By these days, Flex has changed that). Now, I've come with a moreless complex but simple library for making web games through Java Applets, but beyond personal recreation, it is not quite useful... So I decided to release it as Open Source, hopping this library might be useful to others too.

## Classes ##

  * **Game.java:** The main class for game development. Must be extended for starting coding the game. Includes many basic functional methods.
  * **Sprite.java:** Creates an interactive element, which can be used as player, enemy, item, or anything needed.
  * **SpriteVector.java:** Helps to manage with more facility many sprites with similar properties.
  * **World.java:** Creates the basis for an interactive world, most helpful with mazes or platform games.
  * **Particle.java:** Creates a single element which can be used as a ornative feature. Managed with the ParticleSystem.
  * **ParticleSystem.java:** Creates a group of elements, which can be used for fireworks, explosions and many more.
  * **Animation.java:** Simplifies the process for making an animation from a animation sheet or a group of images.
  * **Line.java:** Creates an interactive line.

## Demo ##

KOJAGE comes with 4 code demos that help programmers to learn the use of the engine with basic examples. You can test those examples next:

**Maze Basic:** Shows basic functions like solid walls, collision with enemies and special tiles. Resizable character with '<' and '>' to watch it's work with different sprite scale.
<br><a href='http://kojage.googlecode.com/hg/demo/maze_bas.html'>{Normal}</a> <a href='http://kojage.googlecode.com/hg/demo/maze_bas-full.html'>{Full}</a>

<b>Platform Basic:</b> Shows the basic of how-to-make a platform game.<br>
<br><a href='http://kojage.googlecode.com/hg/demo/platform_bas.html'>{Normal}</a> <a href='http://kojage.googlecode.com/hg/demo/platform_bas-full.html'>{Full}</a>

<b>Platform Advanced:</b> A complex platform game that includes 8 different kind of tiles, and many different ways to move a player. Recollect the golden shines to test all features.<br>
<br><a href='http://kojage.googlecode.com/hg/demo/platform_adv.html'>{Normal}</a> <a href='http://kojage.googlecode.com/hg/demo/platform_adv-full.html'>{Full}</a>

<b>Lines:</b> An experimental test to introduce the all-new lines feature. It's still a little buggy.<br>
<br><a href='http://kojage.googlecode.com/hg/demo/lines.html'>{Normal}</a> <a href='http://kojage.googlecode.com/hg/demo/lines-full.html'>{Full}</a>

You can see many more examples for the Particle Systems at <a href='http://juegos.octabot.net/test/'><a href='http://juegos.octabot.net/test/'>http://juegos.octabot.net/test/</a></a> (In Spanish).