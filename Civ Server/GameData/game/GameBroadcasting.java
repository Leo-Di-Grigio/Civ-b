package game;

import java.io.IOException;
import net.Message;

public class GameBroadcasting {
	
	private GamePlayers players;
	private GameTeams teams;
	
	public GameBroadcasting(GamePlayers players, GameTeams teams){
		this.players = players;
		this.teams = teams;
	}
	
	protected void sendToPlayers(Message msg) throws IOException{
		players.setMessageToAllPlayers(msg);
	}
	
	protected void sendToTeam(int teamId, Message msg) throws IOException{
		players.setMessageToAllTeams(teams.getPlayers(teamId), msg);
	}
}
