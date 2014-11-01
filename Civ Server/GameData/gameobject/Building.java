package gameobject;

import gameobject.building.stats.BuildingSkills;
import gameobject.building.stats.BuildingStats;

public class Building extends GameObject {

	public BuildingSkills skills;
	public BuildingStats stats;

	public Building(int playerId, int type, int x, int y) {
		super(playerId, type, x, y);

		this.skills = new BuildingSkills();
		this.stats = new BuildingStats();
	}
}
