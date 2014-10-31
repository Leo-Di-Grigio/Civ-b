package gameobject;

import game.GameBroadcasting;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import net.Message;
import net.Message.Prefix;
import map.GameMap;

public class GameObjectMng {
	
	private HashMap<Integer, GameObject> objects;
	private HashMap<Integer, HashSet<Integer>> playerObjects; //<playerId, Set<unitId> >
	private GameMap map;
	
	public GameObjectMng(GameMap map){
		this.objects = new HashMap<Integer, GameObject>();
		this.playerObjects = new HashMap<Integer, HashSet<Integer>>();
		this.map = map;
	}
	
	public void registerPlayer(int playerId){
		playerObjects.put(playerId, new HashSet<Integer>());
	}
	
	public void removePlayer(int playerId){
		playerObjects.remove(playerId);
	}
	
	public void addUnit(GameObject object, GameBroadcasting broad) throws IOException{
		objects.put(object.id, object);
		playerObjects.get(object.playerId).add(object.id);
		map.addObject(object.x, object.y, object.id);
		
		broad.sendToPlayers(object.toMessage());
	}
	
	public void addObjects(HashSet<GameObject> objectSet, GameBroadcasting broad) throws IOException{
		for(GameObject object: objectSet){
			addUnit(object, broad); 
		}
	}
	
	public void removeUnit(int objectId, GameBroadcasting broad) throws IOException{
		GameObject object = objects.remove(objectId);
		
		if(object != null){
			map.removeObject(object.x, object.y, object.id);
			playerObjects.get(object.playerId).remove(object.id);
			
			broad.sendToPlayers(new Message(Prefix.DEL_UNIT, "" + object.id));
		}
	}
	
	public GameObject getObject(int objectId){
		return objects.get(objectId);
	}
	
	public HashSet<Integer> getObjects(int x, int y){
		return map.getObjects(x, y);
	}
	
	public HashSet<Integer> getPlayersObjects(int playerId){
		return playerObjects.get(playerId);
	}
}