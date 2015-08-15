#Kojage's Java Open Applet Game Engine - Game.java

# Class Game #
public class **Game**
extends Applet
implements Runnable

The `Game` class is designed help into the creation of web games, working as an Applet, facilitating the implementation of basic features for their creation.

**Since:**
> KOJAGE 0.1

---


## Fields ##
boolean **UP**
> If up keyarrow is being pressed.

boolean **DOWN**
> If down keyarrow is being pressed.

boolean **LEFT**
> If left keyarrow is being pressed.

boolean **RIGHT**
> If right keyarrow is being pressed.

boolean **PAUSE**
> If the game is at pause.

double **mousex**
> The cursor position in x.

double **mousey**
> The cursor position in y.

int **bgx**
> The background position in x.

int **bgy**
> The background position in y.

int **screenWidth**
> The game screen width.

int **screenHeight**
> The game screen height.

---


## Constructors ##
None.

---


## Methods ##

void **setSize**(int width, int height)
> Sets the size of the game screen. If the html code containing the game has a different size than the one set by this line, the game screen will resize to fit into it. Predefined is 350x200.

void **setBackground**(Color c, Image img, boolean fixed)
> Sets the background color and image tile of the game. If fixed, the background image will remain still on world movement, else, it will move along the world movement.

void **setSleepTime**(int sleepTime)
> Sets the milliseconds before the game refreshes. Predefined is 50.

void **game**()
> Must be filled with instructions to be done before the screen refresh (The game code itself).

void **paint**(Graphics g)
> Must be filled with the instructions to be drawn into the screen.

int **random**(int Value)
> Returns an integer random number between 0 and value.

double **random**(double Value)
> Returns a double point random number between 0 and value.

double **getAngle**(double x1, double y1, double x2, double y2)
> Gets the angle between the coordinates at (x1,y1) and (x2,y2).