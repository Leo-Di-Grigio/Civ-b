package scenedata.game;

import player.units.UnitsMng;

public class GameData {
	
	// map
	public GameMap map;
	
	// units
	public UnitsMng units;
	
	public GameData(GameMap map) {
		this.map = map;
		units = new UnitsMng(map);
	}
}
