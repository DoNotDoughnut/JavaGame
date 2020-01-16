package rhys.game.objects.entity.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.util.ArrayList;

import rhys.game.main.GameRenderer;
import rhys.game.objects.entity.Entity;

public class GameText extends Entity {

	public int x, y;
	public String text;
	public Color color;
	public Font font;
	public final int id;
	public boolean precise;
	private static GameRenderer gr;
	
	private static Font basicFont;
	public static Font dialogueFont, guiFont;
	private static ArrayList<GameText> texts = new ArrayList<>();
	private static int ids = 0;
	
	public GameText(int x, int y, Font font, Color color, String text, boolean precise) {
		this.x=x;
		this.y=y;
		this.font=font;
		this.color=color;
		this.text=text;
		this.precise=precise;
		
		this.id = ids;
		texts.add(id, null);
		ids++;
	}
	
	public GameText(int x, int y, Font font, Color color, String text) {
		this(x,y,font,color,text,false);
		
	}
	
	public void spawn() {
		texts.set(id, this);
		alive = true;
	}
	
	public void despawn() {
		texts.set(id, null);
		alive = false;
	}

	public static void init(GameRenderer gr) {
		GameText.gr=gr;
		
		try {
			basicFont = Font.createFont(Font.TRUETYPE_FONT, GameText.class.getClassLoader().getResourceAsStream("rhys/game/resources/fonts/monogram.ttf"));			
		} catch (FontFormatException | IOException e) {e.printStackTrace();}
		
		guiFont = basicFont.deriveFont((float) (12*gr.scale));
		dialogueFont = basicFont.deriveFont((float) (16*gr.scale));
		
	}

	public static void render(Graphics graphics) {
		
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		for(GameText text : texts)
			if(text!=null) {
				
			g.setFont(text.font);
			g.setColor(text.color);
			if(text.precise)
				g.drawString(text.text, text.x, text.y);
			else
				g.drawString(text.text, (text.x*gr.scale), (text.y*gr.scale));
			
			}
		
	}
	
}
