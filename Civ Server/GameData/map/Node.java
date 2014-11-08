package map;

import gameobject.GameObject;

import java.util.ArrayList;
import database.DB;

public class Node {
	
	// Layers
	public byte height = 0;
	public byte geology = 0;
	
	// Units data
	private ArrayList<Integer> gameObjects;
	
	// temperature
	public byte termal = 0;
	
	// units flag
	private boolean city;
	private int cityId;
	private boolean nodeUpdate;
	private int nodeUpdateId;
	
	public Node(){
		gameObjects = new ArrayList<Integer>();
	}
	
	public void addObject(GameObject object){
		this.gameObjects.add(object.id);
		
		if(DB.isQuarter(object.type)){
			nodeUpdate = false;
			city = true;
			cityId = object.id;
		}
		
		if(DB.isNodeUpdata(object.type)){
			city = false;
			nodeUpdate = true;
			nodeUpdateId = object.id;
		}
	}
	
	public void removeObject(int objectId) {	
		for(int i = 0; i < gameObjects.size(); ++i){
			if(gameObjects.get(i) == objectId){
				gameObjects.remove(i);
				return;
			}
		}
	}
	
	public ArrayList<Integer> getAllObjects(){
		return gameObjects;
	}

	public boolean isCity(){
		return city;
	}
	
	public boolean isNodeUpdate(){
		return nodeUpdate;
	}
	
	public int getCityId(){
		return cityId;
	}
	
	public int getNodeUpdateId(){
		return nodeUpdateId;
	}
}
