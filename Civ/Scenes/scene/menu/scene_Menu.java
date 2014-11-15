package scene.menu;

import java.awt.Graphics;
import javax.media.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;
import misc.Enums;
import scene.Scene;

public class scene_Menu extends Scene {
	
	public scene_Menu(){
		super(Enums.Scene.MENU, new scenedata_Menu());
	}

	@Override
	public void draw(Graphics g, long tic) {
		
	}

	@Override
	public void draw(GL2 gl, TextRenderer textrender) {
		
	}
}