package game;

import java.util.HashMap;

import misc.Log;

public class GameList {
	
	public static HashMap<Integer, Game> list;
	
	public GameList(){
		list = new HashMap<Integer, Game>();
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
