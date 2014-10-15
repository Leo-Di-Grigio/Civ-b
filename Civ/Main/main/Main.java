package main;

import java.io.IOException;

import engine.Engine;
import misc.Const;
import misc.ToolsConst;
import misc.Log;

public class Main {
	
	public static void main(String [] args) throws IOException{
		Log.debug(Const.title + " v" + Const.version + "." + Const.subVersion);
		Log.debug(ToolsConst.title + " v" + ToolsConst.version + "." + ToolsConst.subVersion);
		
		new Config();
		new Engine();
	}
}
