package scene.prepare;

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
import scenedata.game.GameData;

public class scene_Prepare extends Scene {

	protected GameData gamedata;
	
	public scene_Prepare(GameData gamedata) {		
		super(Enums.Scene.PREPEARE, new scenedata_Prepare(gamedata));
		this.gamedata = gamedata;
	}

	@Override
	public void draw(Graphics g, long tic) {
		g.drawImage(Recources.getImage(Const.imgMenu), 0, 0, Environment.frameSizeX, Environment.frameSizeY, null);
		
		g.setColor(Color.white);
		g.drawString("" + Const.title + " v" + ToolsConst.version + "." + ToolsConst.subVersion, 0, 10);
		g.drawString("2014 (c) Leo di Grigio and Sivalent", 0, 20);
		g.drawString("Scene: Prepare", 0, 30);
	}

	@Override
	public void draw(GL2 gl, TextRenderer textrender) {
		textrender.beginRendering(Environment.frameSizeX, Environment.frameSizeY);
		textrender.draw("" + Const.title + " v" + ToolsConst.version + "." + ToolsConst.subVersion, 0, Environment.frameSizeY - 10);
		textrender.draw("2014 (c) Leo di Grigio and Sivalent", 0, Environment.frameSizeY - 20);
		textrender.draw("Scene: Prepare", 0, Environment.frameSizeY - 30);
		textrender.endRendering();
	}
}
