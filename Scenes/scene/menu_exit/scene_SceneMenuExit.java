package scene.menu_exit;

import java.awt.Color;
import java.awt.Graphics;

import javax.media.opengl.GL3;

import misc.Const;
import misc.Enums;
import misc.Environment;
import recources.Recources;
import scene.Scene;

public class scene_SceneMenuExit extends Scene {

	public scene_SceneMenuExit() {
		super(Enums.Scene.MENU_EXIT, new data_SceneMenuExit());
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Recources.getImage("menu_background"), 0, 0, Environment.width, Environment.height, null);
		
		g.setColor(Color.white);
		g.drawString("" + Const.title + " v" + Const.version +"."+Const.subVersion, 0, 10);
		g.drawString("2014 (c) Leo di Grigio and Sivalent", 0, 20);
	}

	@Override
	public void draw(GL3 gl) {

	}
}
