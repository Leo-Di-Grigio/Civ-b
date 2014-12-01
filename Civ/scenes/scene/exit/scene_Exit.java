package scene.exit;

import java.awt.Graphics;
import javax.media.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;
import misc.Enums;
import scene.Scene;

public class scene_Exit extends Scene {

	public scene_Exit() {
		super(Enums.Scene.EXIT, new scenedata_Exit());
	}

	@Override
	public void draw(Graphics g, long tic) {
		
	}

	@Override
	public void draw(GL2 gl, TextRenderer textrender) {
		
	}
}
