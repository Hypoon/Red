package client;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {
	public Window() {
		try {
			Display.setDisplayMode(new DisplayMode(1280,800));
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (!Display.isCloseRequested()) {
			Display.update();
		}
		Display.destroy();
	}
}
