import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

public class Level {
	
	private static HashMap<String, Rectangle2D.Float> rectangles= new HashMap<String, Rectangle2D.Float>();	
	Paddle user = new Paddle(true);
	Ball ball = new Ball();
	Paddle ai = new Paddle(false);
	
	public Level(boolean main){
		this.setup();
	}
	
	public void setup(){
		int height = Display.getDisplayMode().getHeight();	
		int width = Display.getDisplayMode().getWidth();	
			
		rectangles.put("ball", new Rectangle2D.Float());
		rectangles.put("user", new Rectangle2D.Float());
		rectangles.put("ai", new Rectangle2D.Float());
		rectangles.put("upper", new Rectangle2D.Float(0, 0, width ,5));
		rectangles.put("lower", new Rectangle2D.Float(0, 475, 720 ,5));
		rectangles.put("bounds", new Rectangle2D.Float(0, 0, 720, 480));
	}
	
	public void render(){
		this.renderBounds();
		user.render();
		ball.render();
		ai.render();
	}
	
	public static Rectangle2D.Float getRectangle(String name){
		return rectangles.get(name);
	}
	public static void setRectangle(Rectangle2D.Float rect, String name){
		rectangles.remove(name);
		rectangles.put(name, rect);
	}
	public void renderBounds(){
			float x;
			float y;
			x = 0;
			y = 475;
			GL11.glRectf(0, 475, (float)rectangles.get("lower").getWidth(), y+(float)rectangles.get("lower").getHeight());	
			x = 0;
			y = 0;
			GL11.glRectf(x, y, (float)rectangles.get("upper").getWidth(), (float)rectangles.get("upper").getHeight());					
	}
	
}
