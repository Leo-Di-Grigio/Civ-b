package player;

import database.tech.TeamTech;
import database.tech.Tech;
import net.Message;
import interfaces.Sentble;

public class Team implements Sentble {
	
	// CLIENT
	// ID
	public int id;
	
	// data
	public String name;
	public int ownerPlayerId;

	// tech
	public TeamTech tech;
	
	public Team(){
		// default -1 team
		id = -1;
		name = "No team";
		ownerPlayerId = -1;
		tech = Tech.buildTechTree();
	}
	
	public Team(String data){
		String [] arr = data.split(":");
		tech = Tech.buildTechTree();
		
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
		this.id = Integer.parseInt(arr[0]);
		this.name = arr[1];
		this.ownerPlayerId = Integer.parseInt(arr[2]);
		this.tech.setData(arr[3]);
	}

	@Override
	public void updateObj(String [] arr) {
		switch(arr[1]){
			case "id": id = Integer.parseInt(arr[2]); break;
			case "name": name = arr[2]; break;
			case "ownerPlayerId": ownerPlayerId = Integer.parseInt(arr[2]); break;
			case "tech": tech.setData(arr[2]); break;
		}
	}
}
