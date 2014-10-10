package engine;

import java.awt.Dimension;

import javax.swing.JFrame;

import network.Network;
import recources.Recources;
import render.Render;
import script.Scripts;
import main.Config;
import misc.Const;
import misc.Tools;

public class Engine {
	
	public static JFrame frame;
	public static Render render;
	public static Recources recources;
	public static Scripts scripts;
	public static Tools tools;
	public static Network nerwork;
	
	public Engine(){
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
	
	private void initEngine(){
		tools = new Tools();
		nerwork = new Network();
		scripts = new Scripts();
		recources = new Recources(Config.renderMode);
		render = new Render(Config.renderMode, frame);
	}
}
