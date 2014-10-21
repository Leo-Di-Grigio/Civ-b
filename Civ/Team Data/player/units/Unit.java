package player.units;

import java.awt.Graphics;

import javax.media.opengl.GL3;

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
	
	public Unit(String data){
		String [] arr = data.split(":");
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
	}

	@Override
	public void updateObj(String [] arr) {
		switch(arr[1]){
			case "id": 		 id = Integer.parseInt(arr[1]); break;
			case "playerId": playerId = Integer.parseInt(arr[1]); break;
			case "x": 		 x = Integer.parseInt(arr[1]); break;
			case "y": 		 y = Integer.parseInt(arr[1]); break;
			case "type": 	 type = Integer.parseInt(arr[1]); break;
		}
	}

	// DRAW UNIT
	private int drawX;
	private int drawY;
	
	public void draw(Graphics g, int drawX, int drawY) {
		this.drawX = drawX;
		this.drawY = drawY;
		draw(g);
	}
	

	@Override
	public void draw(Graphics g) {
		g.drawImage(Recources.getImage(Const.imgUnitAvatar), drawX, drawY-37, 32, 69, null);
	}

	@Override
	public void draw(GL3 gl) {
		
	}	
}
