package scenedata.game;

import java.awt.Graphics;
import java.util.HashSet;

import javax.media.opengl.GL2;

import player.units.Unit;
import recources.Recources;
import render.Drawble;
import misc.Const;
import misc.Enums;

public class Node implements Drawble {
	
	// Land data
	public byte height = 0;
	
	// Units data
	private HashSet<Integer> units;	   // set<unitId>
	private HashSet<Integer> waypoint; // set<unitId>
	
	// Drawing data
	public Enums.Terrain terrainType;
	public int border;
	
	// Geology
	public byte geology;
	
	// Climat
	public int termal; // temperature
	
	public Node() {
		units = new HashSet<Integer>();
		waypoint = new HashSet<Integer>();
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
	
	public void addWaypoint(int unitId){
		waypoint.add(unitId);
	}
	
	public void removeWaypoint(int unitId){
		waypoint.remove(unitId);
	}
	
	public boolean haveWaypoints(){
		if(waypoint.size() > 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	// Draw units
	private int drawX;
	private int drawY;
	
	public void draw(Graphics g, int drawX, int drawY, int tic) {
		this.drawX = drawX;
		this.drawY = drawY;
		draw(g, tic);
	}
	
	@Override
	public void draw(Graphics g, long tic) {
		if(haveWaypoints()){
			g.drawImage(Recources.getImage(Const.imgNull), drawX, drawY, 32, 32, null);
		}
	}

	@Override
	public void draw(GL2 gl) {
		
	}
}
