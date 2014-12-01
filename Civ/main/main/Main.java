package main;

import java.awt.FontFormatException;
import java.io.IOException;

import engine.Engine;
import misc.Const;
import misc.ToolsConst;
import misc.Log;

public class Main {
	
	public static void main(String [] args) throws IOException, FontFormatException{
		Log.debug(Const.title + " v" + ToolsConst.version + "." + ToolsConst.subVersion);
		
		new Config();
		new Engine();
	}
}
