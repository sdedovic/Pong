import java.awt.geom.Rectangle2D;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

public class Ball {
	Rectangle2D.Float box = new Rectangle2D.Float();
	
	private static float width = 15;
	private static float height = 15;
	
	private float speed;
	private Vector2f pos = new Vector2f();
	private Vector2f vel = new Vector2f(0.1f, -0.15f);
	private Vector2f start = new Vector2f(0.1f, -0.15f);
	
	public Ball(){
		pos.setX((float) ((720/2)-7.5));
		pos.setY((float) ((480/2)-7.5));
		Vector2f.add(pos, start, pos);
		box.setRect(pos.getX(), pos.getY(), width, height);
		Level.setRectangle(box, "ball");
		speed = 0.0f;
	}
	public void render(){
		GL11.glPushMatrix();
		
		draw();
		checkBounds();
		translate();
		
    	GL11.glPopMatrix();
	}
	
	private void draw(){
		GL11.glRectf(pos.getX(), pos.getY(), pos.getX()+width, pos.getY()+height);
	}
	
	private void translate(){
		if(box.intersects(Level.getRectangle("bounds"))){
			Vector2f.add(pos, vel, pos);
			box.setRect(pos.getX(), pos.getY(), width, height);
			Level.setRectangle(box, "ball");
		}
		else{
			pos.setX((float) ((720/2)-7.5));
			pos.setY((float) ((480/2)-7.5));
			vel.set(start);
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Vector2f.add(pos, vel, pos);
			box.setRect(pos.getX(), pos.getY(), width, height);
			Level.setRectangle(box, "ball");
			
		}
	}
	private void checkBounds(){
		if(Level.getRectangle("upper").intersects(box) || Level.getRectangle("lower").intersects(box)){
			vel.setY(-vel.getY());
		}
		if(Level.getRectangle("user").intersects(box) || Level.getRectangle("ai").intersects(box)){
			vel.setX(-vel.getX());
			speed += .005;
			vel.setX(vel.getX()+speed);
			vel.setY(vel.getY()+speed);
		}
	}
	public double getPos(){
		return box.getCenterY();
	}
}
