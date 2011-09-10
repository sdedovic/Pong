import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;


public class Game {
	private boolean finished;
	private int width = 720;
	private int height = 480;
	private boolean add = true;
	
	Level level = new Level(true);
		
	public Game(){
	}
	
	
	public static void main(String[] args) {
		Game test = new Game();
		try {
			test.init();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void init() throws LWJGLException{
		finished = false;
		Display.setTitle("Game Test");
		Display.setFullscreen(false);
		Display.setVSyncEnabled(false);
		Display.setDisplayMode(new DisplayMode(width, height));
		Display.create();
		run();
	}
	private void run() {
		int space = 0;
		while(!finished){
			Display.update();
			logic();
			render();
		}
	}
	
	private void render(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluOrtho2D(0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight(), 0);
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT |GL11.GL_DEPTH_BUFFER_BIT);
		
	    GL11.glPushMatrix();
    	level.render();
    	GL11.glPopMatrix();
	}
	private void logic() {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			finished = true;
		}
		if(Display.isCloseRequested()){
			finished = true;
		}
	}
}