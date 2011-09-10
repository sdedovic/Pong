import java.awt.geom.Rectangle2D;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

public class Paddle{
	Rectangle2D.Float paddle = new Rectangle2D.Float();
	
	private static float width = 15;
	private static float height = 100;
	
	private boolean isUser;
	private boolean moveUp;
	private boolean moveDown;
	private Vector2f pos = new Vector2f();
	private Vector2f vel = new Vector2f(0.0f, 0.0f);
	
	public Paddle(boolean isUserTemp){
		pos.setY((480/2)-(height/2));
		moveUp = true;
		moveDown = true;
		
		if(isUserTemp){
			isUser = true;
			pos.setX(720- 40);
		}
		if(!isUserTemp){
			isUser = false;
			pos.setX(25);
		}
	}
	public void render(){
		GL11.glPushMatrix();
		
		logic();
		draw();
		checkBounds();
		
    	GL11.glPopMatrix();
	}
	
	private void draw(){
		GL11.glRectf(pos.getX(), pos.getY(), pos.getX()+width, pos.getY()+height);
	}
	private void translate(float direction){
		vel.setY(direction);
		Vector2f.add(pos, vel, pos);
		if(direction != 0 && isUser == true){
			paddle.setRect(pos.getX(), pos.getY(), pos.getX()+width, pos.getY()+height);
			Level.setRectangle(paddle, "user");
		}
		else if(isUser == false){
			paddle.setRect(pos.getX(), pos.getY(), pos.getX()+width, pos.getY()+height);
			Level.setRectangle(paddle, "ai");
		}
	}
	private void checkBounds(){
		if(pos.getY() <= 5){
			moveUp = false;
		}
		else if(pos.getY() > 5){
			moveUp = true;
		}
		if(pos.getY()+height >= Display.getDisplayMode().getHeight() - 5){
			moveDown = false;
		}
		else if(pos.getY()+height < Display.getDisplayMode().getHeight() - 5){
			moveDown = true;
		}
	}
	private void logic(){
		if(isUser){
			if(Keyboard.isKeyDown(Keyboard.KEY_UP ) == true && moveUp == true){
				translate(-0.1f);
			}
			else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN) == true && moveDown == true){
				translate(0.1f);
			}
			else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN) == false || Keyboard.isKeyDown(Keyboard.KEY_UP) == false || moveUp == false || moveDown == false){
				translate(0.0f);
			}
		}
		else if(!isUser){
			if(paddle.getCenterY() < Level.getRectangle("ball").getCenterY() && moveDown == true){
				translate(-0.1f);
			}
			else if(paddle.getCenterY() < Level.getRectangle("ball").getCenterY() && moveUp == true){
				translate(-0.1f);
			}
			
		}
	}
}
