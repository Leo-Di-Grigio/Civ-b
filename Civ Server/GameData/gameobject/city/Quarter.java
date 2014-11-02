package gameobject.city;

import java.util.ArrayList;
import java.util.Vector;

import actions.Action;
import actions.Action.PlayerAction;
import database.DB;
import gameobject.Building;
import gameobject.struct.QuarterBuilding;

public class Quarter extends Building {
	
	// buildings
	public int buildingsCapacity;
	public Vector<QuarterBuilding> buildings;
	
	// quarter stats
	public int food = 0;
	public int scince = 0;
	public int culture = 0;
	
	public Quarter(int playerId, int x, int y) {
		super(playerId, DB.buildingQuarter, x, y);
		
		this.buildingsCapacity = 1;
		this.buildings = new Vector<QuarterBuilding>();
	}

	public void updateRecources() {
		this.food += this.stats.foodIncrease;
		this.scince += this.stats.scinceIncrease;
		this.culture += this.stats.cultureIncrease;
	}
	
	public ArrayList<Action> getActions(){
		ArrayList<Action> list = new ArrayList<Action>();
		
		if(this.food >= DB.foodForNovice){
			list.add(new Action(PlayerAction.QUARTER_SPAWN_NOVICE, this.id));
		}
		
		return list;
	}
}