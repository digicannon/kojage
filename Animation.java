package kojage;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.ArrayList;
/**
 * Kojage's Java Open Applet Game Engine - Map.java
 * @author daPhyre
 * @since 4.1, Su/22/Jun/08
 * @version 6.0, Tu/04/Jan/11
 */
public class Animation
{
	public ArrayList<Image> frames = new ArrayList();
	int frame = 0, p = 0, pause = 0, frameWidth;
	Boolean hasntStacked;
	
	public Animation ()
	{
	}

	public Animation (Image img, int frameWidth)
	{
		frames.add(img);
		hasntStacked = !stack();
		this.frameWidth = frameWidth;
	}

	public Animation (Image img, int frameWidth, int pause)
	{
		frames.add(img);
		hasntStacked = !stack();
		this.frameWidth = frameWidth;
		this.pause = pause;
	}
	
	public void addFrame (Image img)
	{
		this.frames.add(img);
	}
	
	public Image currentFrame()
	{
		if (hasntStacked)
			hasntStacked = !stack();
		return (Image)(frames.get(frame));
	}
	
	public Image prevFrame()
	{
		p++;
		if (p > pause)
		{
			frame--;
			p = 0;
		}
		if (frame < 0)
			frame = frames.size()-1;
		return currentFrame();
	}
	
	public Image nextFrame()
	{
		p++;
		if (p > pause)
		{
			frame++;
			p = 0;
		}
		if (frame > frames.size()-1)
			frame = 0;
		return currentFrame();
	}
	
	private Boolean stack()
	{
		Component comp = new Component() {};
		Image tImg = frames.get(frames.size()-1);
		if (tImg.getHeight(null) > 0)
		{
			 frames.remove(frames.size()-1);
			 for (int i = 0; i < tImg.getWidth(null)/frameWidth; i++)
				frames.add(comp.createImage(new FilteredImageSource(tImg.getSource(), new CropImageFilter(frameWidth*i, 0, frameWidth, tImg.getHeight(null)))));
			 return true;
		}
		else
			return false;
	}
}
