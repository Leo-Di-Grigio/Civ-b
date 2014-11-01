package gameobject.unit;

import database.DB;
import gameobject.Unit;

public class Novice extends Unit {

	public Novice(int playerId, int x, int y) {
		super(playerId, DB.unitNovice, x, y);
	}
}
