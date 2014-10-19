package engine;

import java.awt.Dimension;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JFrame;

import recources.Recources;
import render.Render;
import script.ScriptsAPI;
import main.Config;
import misc.Const;
import misc.Tools;

public class Engine {
	
	public static JFrame frame;

	public Engine() throws IOException, FontFormatException{
		initFrame();
		initEngine();
	}
	
	private void initFrame(){
		frame = new JFrame();
		frame.setTitle(Const.title + " v" + Const.version + "." + Const.subVersion 
				+ " mode: " + Config.renderMode);
		
		Dimension dimension = new Dimension(Config.frameWidth, Config.frameHeight);
		frame.setSize(dimension);
		frame.setMinimumSize(dimension);
		
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initEngine() throws IOException, FontFormatException{
		new Tools();
		new ScriptsAPI();
		new Recources(Config.renderMode);		
		new Thread(new Render(Config.renderMode, frame)).start();
	}
}
