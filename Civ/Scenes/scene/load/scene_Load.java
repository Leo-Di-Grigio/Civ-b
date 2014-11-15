package scene.load;

import java.awt.Graphics;
import javax.media.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;
import misc.Enums;
import scene.Scene;

public class scene_Load extends Scene {

	public scene_Load() {
		super(Enums.Scene.LOAD, new scenedata_Load());
	}

	@Override
	public void draw(Graphics g, long tic) {
		
	}

	@Override
	public void draw(GL2 gl, TextRenderer textrender) {
		
	}
}
