package main;

import engine.Engine;
import misc.Const;
import misc.Log;

public class Main {
	
	public static void main(String [] args){
		Log.msg(Const.title + " v" + Const.version + "." + Const.subVersion);
		
		new Config();
		new Engine();
	}
}
