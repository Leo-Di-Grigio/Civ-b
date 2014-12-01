package scene.settings;

import java.awt.Graphics;
import javax.media.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;
import misc.Enums;
import scene.Scene;

public class scene_Settings extends Scene {

	public scene_Settings() {
		super(Enums.Scene.SETTINGS, new scenedata_Settings());
	}

	@Override
	public void draw(Graphics g, long tic) {

	}

	@Override
	public void draw(GL2 gl, TextRenderer textrender) {
		
	}
}
