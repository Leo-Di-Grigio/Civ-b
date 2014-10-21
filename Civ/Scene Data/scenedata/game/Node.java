package scenedata.game;

import java.awt.Graphics;
import java.util.HashSet;

import javax.media.opengl.GL3;

import player.units.Unit;
import player.units.UnitsMng;
import render.Drawble;
import misc.Enums;

public class Node implements Drawble {
	
	// Land data
	public byte height = 0;
	
	// Units data
	private HashSet<Integer> units;
	
	// Drawing data
	public Enums.Terrain terrainType;
	public int border;
	
	// Geology
	public byte geology;
	
	public Node() {
		units = new HashSet<Integer>();
	}
	
	public void addUnit(Unit unit){
		units.add(unit.id);
	}

	public void removeUnit(int unitId) {
		units.remove(unitId);
	}
	
	public HashSet<Integer> getAll() {
		return units;
	}
	
	// Draw units
	private int drawX;
	private int drawY;
	
	public void draw(Graphics g, int drawX, int drawY) {
		this.drawX = drawX;
		this.drawY = drawY;
		
		draw(g);
	}
	
	@Override
	public void draw(Graphics g) {
		for(Integer unitId: units){
			UnitsMng.getUnit(unitId).draw(g, drawX, drawY);
		}
	}

	@Override
	public void draw(GL3 gl) {
		
	}
}
