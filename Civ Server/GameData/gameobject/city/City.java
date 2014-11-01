package gameobject.city;

import database.DB;
import gameobject.Building;

public class City extends Building {

	public City(int playerId, int x, int y) {
		super(playerId, DB.buildingCity, x, y);
	}
}
