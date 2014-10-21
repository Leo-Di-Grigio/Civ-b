package game;

import java.io.IOException;
import java.util.HashMap;

import misc.Const;

public class GamesMng {
	
	public static HashMap<Integer, Game> games;
	
	public GamesMng() throws IOException{
		games = new HashMap<Integer, Game>();
		
		// test
		add(new Game("Test 1 ", Const.mapSizeX, Const.mapSizeY, Const.playersMax));
		add(new Game("Test 2 ", Const.mapSizeX, Const.mapSizeY, Const.playersMax));
		add(new Game("Test 3 ", Const.mapSizeX, Const.mapSizeY, Const.playersMax));
		add(new Game("Test 4 ", Const.mapSizeX, Const.mapSizeY, 1));
		add(new Game("Test 5 ", Const.mapSizeX, Const.mapSizeY, 0));
	}
	
	public static void add(Game game){
		games.put(game.id, game);
	}
	
	public static Game get(int id){
		return games.get(id);
	}
	
	public static String getList(){
		// "intSize:(id:name:status):(id:name:status):(...):...
		String str = "";
		
		str += games.size() + ":";
		
		for(Game game: games.values()){
			str += game.id + ":" + game.title + ":" + game.logic.getState() + ":";
		}
		
		return str;
	}
}
