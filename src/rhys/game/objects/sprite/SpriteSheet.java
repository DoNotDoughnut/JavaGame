package rhys.game.objects.sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	public final int spriteWidth, spriteHeight;
	public int width, height;
	public BufferedImage image;
	
	public SpriteSheet(String path, int spriteWidth, int spriteHeight) {
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		try {
			image = ImageIO.read(SpriteSheet.class.getResource(path)); //get image
			width = image.getWidth();
			height = image.getHeight();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public SpriteSheet(String path, int spriteSize) {
		this(path, spriteSize, spriteSize);
	}
}
