package main;

import java.io.IOException;
import engine.Engine;
import misc.Const;
import misc.Log;

public class Main {
	
	public static void main(String [] args) throws IOException{
		Log.msg(Const.title + " v" + Const.version + "." + Const.subVersion);
		
		new Config();
		new Engine();
	}
}
