package scene.prepare;

import java.awt.Graphics;
import javax.media.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;
import misc.Enums;
import scene.Scene;
import scenedata.game.GameData;

public class scene_Prepare extends Scene {

	protected GameData gamedata;
	
	public scene_Prepare(GameData gamedata) {		
		super(Enums.Scene.PREPEARE, new scenedata_Prepare(gamedata));
		this.gamedata = gamedata;
	}

	@Override
	public void draw(Graphics g, long tic) {

	}

	@Override
	public void draw(GL2 gl, TextRenderer textrender) {
	
	}
}
