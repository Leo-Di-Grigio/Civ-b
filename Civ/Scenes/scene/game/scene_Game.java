package scene.game;

import java.awt.Color;
import java.awt.Graphics;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.awt.TextRenderer;

import misc.Enums;
import misc.Environment;
import scene.Scene;
import scenedata.game.GameData;

public class scene_Game extends Scene {
	
	protected GameData gamedata;
	
	public scene_Game(GameData gamedata){
		super(Enums.Scene.GAME, new scenedata_Game(gamedata));
		this.gamedata = gamedata;
	}

	@Override
	public void draw(Graphics g, long tic) {
		gamedata.map.draw(g, tic);
		
		g.setColor(Color.white);
		g.drawString("Scene: Game", 0, 10);
		g.drawString("Camera x: " +Environment.cameraX + " y: " + Environment.cameraY , 0, 20);
		g.drawString("Node selected x: " +Environment.nodeSelectedX + " y: " + Environment.nodeSelectedY , 0, 30);
		g.drawString("Node draw x: " +Environment.nodeDrawCursorX + " y: " + Environment.nodeDrawCursorY , 0, 40);
		g.drawString("Frame: " + tic , 0, 50);
	}

	@Override
	public void draw(GL2 gl, TextRenderer textrender) {
		gamedata.map.draw(gl, textrender);
		
		textrender.beginRendering(Environment.frameSizeX, Environment.frameSizeY);
		textrender.draw("Scene: Game", 0, Environment.frameSizeY - 10);
		textrender.draw("Camera x: " +Environment.cameraX + " y: " + Environment.cameraY, 0, Environment.frameSizeY - 20);
		textrender.draw("Node selected x: " + Environment.surfaceX + " y: " + Environment.surfaceY + " z: " + Environment.surfaceZ, 0, Environment.frameSizeY - 30);
		textrender.draw("Angle: " + Environment.angle, 0, Environment.frameSizeY - 40);
		textrender.endRendering();
	}
}
