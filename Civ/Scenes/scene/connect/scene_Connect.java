package scene.connect;

import java.awt.Graphics;
import javax.media.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;
import misc.Enums;
import scene.Scene;

public class scene_Connect extends Scene {

	public scene_Connect() {
		super(Enums.Scene.CONNECT, new scenedata_Connect());
	}

	@Override
	public void draw(Graphics g, long tic) {
		
	}
	
	@Override
	public void draw(GL2 gl, TextRenderer textrender) {
		
	}
}
