package team;

import java.awt.Point;
import java.util.HashMap;

import player.Player;
import net.Message;
import net.Message.Prefix;
import interfaces.Sentble;

public class Team implements Sentble  {
	
	// SERVER
	// ID
	private static int ID = 1;
	public int id;
	
	// data
	public String name;
	public int ownerPlayerId;
	
	// players spawn point
	public Point spawn;
	
	public Team(){
		this.id = 0;
		this.name = "No team";
		this.ownerPlayerId = -1;
	}
	
	public Team(int ownerPlayerId, String name) {
		this.id = ID++;
		this.name = name;
		this.ownerPlayerId = ownerPlayerId;
	}

	public int getTeamSize(HashMap<Integer, Player> players){
		int size = 0;
		
		for(Player player: players.values()){
			if(player.teamId == this.id){
				size++;
			}
		}
		
		return size;
	}
	
	@Override
	public Message toMessage() {
		String data = new String("");
		
		data += id 	 + ":";
		data += name + ":";
		data += ownerPlayerId + ":";
		
		return new Message(Prefix.OBJ_TEAM, data);
	}

	@Override
	public Message toMessageUpdate(String field) {
		String data = new String("");
		
		data += id + ":" + field + ":";
		
		switch(field){
			case "name": data += name; break;
			case "ownerPlayerId": data += ownerPlayerId; break;
		}
		
		return new Message(Prefix.UPD_TEAM, data);
	}

	@Override
	public void buildObj(String [] data) {
		
	}

	@Override
	public void updateObj(String [] data) {
		
	}
}
