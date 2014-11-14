package scene.connect;

import java.awt.Color;
import java.awt.Graphics;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.awt.TextRenderer;

import misc.Const;
import misc.Enums;
import misc.Environment;
import misc.ToolsConst;
import recources.Recources;
import scene.Scene;

public class scene_Connect extends Scene {

	public scene_Connect() {
		super(Enums.Scene.CONNECT, new scenedata_Connect());
	}

	@Override
	public void draw(Graphics g, long tic) {
		g.drawImage(Recources.getImage(Const.imgMenu), 0, 0, Environment.frameSizeX, Environment.frameSizeY, null);
		
		g.setColor(Color.white);
		g.drawString("" + Const.title + " v" + ToolsConst.version + "." + ToolsConst.subVersion, 0, 10);
		g.drawString("2014 (c) Leo di Grigio and Sivalent", 0, 20);
		g.drawString("Scene: Connect", 0, 30);
	}
	
	@Override
	public void draw(GL2 gl, TextRenderer textrender) {
		textrender.beginRendering(Environment.frameSizeX, Environment.frameSizeY);
		textrender.draw("" + Const.title + " v" + ToolsConst.version + "." + ToolsConst.subVersion, 0, Environment.frameSizeY - 10);
		textrender.draw("2014 (c) Leo di Grigio and Sivalent", 0, Environment.frameSizeY - 20);
		textrender.draw("Scene: Connect", 0, Environment.frameSizeY - 30);
		textrender.endRendering();
	}
}
