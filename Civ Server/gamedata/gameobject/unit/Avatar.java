package gameobject.unit;

import database.DB;
import gameobject.Unit;

public class Avatar extends Unit {

	public Avatar(int playerId, int x, int y) {
		super(playerId, DB.unitAvatar, x, y);
	}
}
