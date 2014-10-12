package main;

import java.io.IOException;

import engine.Engine;
import misc.Const;
import misc.ToolsConst;
import misc.Log;

public class Main {
	
	public static void main(String [] args) throws IOException{
		Log.msg(Const.title + " v" + Const.version + "." + Const.subVersion);
		Log.msg(ToolsConst.title + " v" + ToolsConst.version + "." + ToolsConst.subVersion);
		
		new Config();
		new Engine();
	}
}
