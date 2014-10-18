package game;

import java.util.HashMap;

import misc.Const;
import misc.Log;

public class GamesMng {
	
	public static HashMap<Integer, Game> list;
	
	public GamesMng(){
		list = new HashMap<Integer, Game>();
		
		// test
		add(new Game("Test 1 ", Const.mapSizeX, Const.mapSizeY, Const.playersMax));
		add(new Game("Test 2 ", Const.mapSizeX, Const.mapSizeY, Const.playersMax));
		add(new Game("Test 3 ", Const.mapSizeX, Const.mapSizeY, Const.playersMax));
		add(new Game("Test 4 ", Const.mapSizeX, Const.mapSizeY, 1));
		add(new Game("Test 5 ", Const.mapSizeX, Const.mapSizeY, 0));
	}
	
	public static void add(Game game){
		list.put(game.id, game);
	}
	
	public static Game get(int id){
		if(list.containsKey(id)){
			return list.get(id);
		}
		else{
			Log.err("Game ID: " + id + " is not found");
			return null;
		}
	}
	
	public static String getList(){
		// "intSize:(id:name:status):(id:name:status):(...):...
		String str = "";
		
		str += list.size() + ":";
		
		for(Game game: list.values()){
			str += game.id + ":" + game.name + ":" + game.state + ":";
		}
		
		return str;
	}
}