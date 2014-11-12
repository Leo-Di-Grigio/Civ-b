package player.units;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.media.opengl.GL3;

import database.DB;
import recources.Recources;
import render.Drawble;
import misc.Const;
import net.Message;
import interfaces.Sentble;

public class Unit implements Sentble, Drawble {
	
	// CLIENT
	// id
	public int id;
	
	// params
	public int playerId;
	public int x;
	public int y;
	public int type;

	// items
	public UnitInventory inventory;
	
	// unit stats
	public int hp;
	public int power;
	public int exp;
	
	// city stats
	
	public Unit(String data){
		String [] arr = data.split(":");
		this.inventory = new UnitInventory();
		buildObj(arr);
	}

	@Override
	public Message toMessage() {
		return null;
	}

	@Override
	public Message toMessageUpdate(String field) {
		return null;
	}
	
	@Override
	public void buildObj(String [] arr) {
		// read
		this.id = Integer.parseInt(arr[0]);
		this.playerId = Integer.parseInt(arr[1]);
		this.x = Integer.parseInt(arr[2]);
		this.y = Integer.parseInt(arr[3]);
		this.type = Integer.parseInt(arr[4]);
		
		if(DB.isUnit(this.type)){
			this.hp = Integer.parseInt(arr[5]);
			this.power = Integer.parseInt(arr[6]);
			this.exp = Integer.parseInt(arr[7]);
		}
	}

	@Override
	public void updateObj(String [] arr) {
		switch(arr[1]){
			case "id": 		 id = Integer.parseInt(arr[2]); break;
			case "playerId": playerId = Integer.parseInt(arr[2]); break;
			case "x": 		 x = Integer.parseInt(arr[2]); break;
			case "y": 		 y = Integer.parseInt(arr[2]); break;
			case "xy":		 x = Integer.parseInt(arr[2]); y = Integer.parseInt(arr[3]); break;
			case "type": 	 type = Integer.parseInt(arr[2]); break;
			case "hp":       hp = Integer.parseInt(arr[2]); break;
			case "power":    power = Integer.parseInt(arr[2]); break;
			case "exp":      exp = Integer.parseInt(arr[2]); break;
		}
	}

	// DRAW UNIT
	private int drawX;
	private int drawY;

	public void setPath(UnitsMng units, ArrayList<Point> way) {
		if(way == null){
			units.removeWay(id);
		}
		else{
			units.addWay(id, way);
		}
	}	
	
	public void draw(Graphics g, int drawX, int drawY, long tic) {
		this.drawX = drawX;
		this.drawY = drawY;
		draw(g, tic);
	}
	

	@Override
	public void draw(Graphics g, long tic) {
		g.drawImage(Recources.getImage(Const.imgUnitPlayerAtlas), drawX, drawY, 32, 32, null);
		g.drawImage(Recources.getUnitImage(this.type), drawX, drawY, 32, 32, null);
		
		if(hp > 0){
			// get HP bar width
			int maxHP = DB.getUnitHP(type);
			float hpPerPixel = 32.0f/maxHP;
			int barWidth = (int)(hp * hpPerPixel);
			
			g.setColor(Color.red);
			g.fillRect(drawX, drawY + 29, barWidth, 3);
		}
	}

	@Override
	public void draw(GL3 gl) {
		
	}
}
