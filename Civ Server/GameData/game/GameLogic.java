package game;

import java.awt.Point;
import java.io.IOException;
import java.util.HashSet;

import data.units.ConstUnits;
import net.Message;
import net.Message.Prefix;
import network.Client;
import network.ClientPool;
import player.Player;
import player.units.Unit;
import player.units.UnitsMng;
import map.GameMap;
import misc.Enums;
import misc.Log;
import misc.Enums.GameState;

public class GameLogic{
	// game Id
	private int gameId;
	
	// Data
	private long seed;
	private Enums.GameState state;
	private GamePlayers players;
	private GameTeams teams;
	private GameMap map;
	private UnitsMng units;
	
	// Network
	private GameBroadcasting broad;
	
	public GameLogic(int gameId, long seed, int mapSizeX, int mapSizeY, int playersMax) throws IOException {
		// id
		this.gameId = gameId;
		this.seed = seed;
		this.state = Enums.GameState.PREPEARING;
		
		// map
		this.map = new GameMap(seed, mapSizeX, mapSizeY);
		this.units = new UnitsMng(this.map);
		
		// players
		this.players = new GamePlayers(gameId, playersMax);
		this.teams = new GameTeams(gameId);
		
		// broad
		this.broad = new GameBroadcasting(players, teams);
	}
	
	public GameState getState() {
		return state;
	}
	
	public void addPlayer(int clientId, String playerName) throws IOException{
		int playerId = players.add(clientId, playerName, seed, map.sizeX, map.sizeY, broad);
		
		if(playerId != -1){ 
			// successful created player
			units.registerPlayer(playerId);
			teams.registerPlayer(0, playerId);
		}
	}
	
	public void removePlayer(int clientId) throws IOException{
		Player player = players.remove(clientId, broad);
		
		if(player != null){
			Client client = ClientPool.getClient(clientId);
			
			if(client != null){
				client.gameId = -1;
			}
			
			teams.unregisterPlayer(player.teamId, player.id);
			teams.deleteTeamIfNoPlayers(player.teamId, broad);
			
			broad.sendToPlayers(new Message(Prefix.DEL_PLAYER, "" + clientId));
			Log.service("Player ID: " + clientId + " leave the game ID: " + gameId);
		}
	}
	
	public void sendGameData(int clientId) throws IOException{
		players.sendPlayesList(clientId);
		teams.sendTeamList(clientId);
	}

	public void addTeam(int clientId, String data) throws IOException {
		int teamId = teams.add(clientId, data, broad);
		
		if(teamId != -1){			
			Player player = players.get(clientId);
			teams.unregisterPlayer(player.teamId, player.id);
			player.teamId = teamId;
			teams.registerPlayer(teamId, player.id);
			broad.sendToPlayers(player.toMessageUpdate("teamId"));
		}
	}

	public void teamChoose(int clientId, String data) throws IOException {
		Player player = players.get(clientId);
		
		if(player != null){
			int teamId = 0;
			
			if(data != null){ // if data == null, player choose "Leave Team"
				teamId = Integer.parseInt(data);
			}
			
			boolean unregistered = teams.unregisterPlayer(player.teamId, player.id);
			if(unregistered){
				teams.deleteTeamIfNoPlayers(player.teamId, broad);
				
				// chose team
				player.teamId = teamId;
				teams.registerPlayer(teamId, player.id);
				broad.sendToPlayers(player.toMessageUpdate("teamId"));
			}
		}
	}

	public void playerReadyCheck(int clientId) throws IOException {
		boolean allPlayersReady = players.readyCheckPlayer(clientId, broad);
		
		if(allPlayersReady){
			spawnAvatars();
			this.state = Enums.GameState.PLAYING;
			broad.sendToPlayers(new Message(Prefix.GAME_BEGIN, null));
		}
	}
	
	public void spawnAvatars(){
		HashSet<Point> spawnPoints = map.getSpawnPoints(teams.getTeamsCount());
		HashSet<Unit> avatars = new HashSet<Unit>();
		teams.setSpawns(spawnPoints);
		Point spawn = null;
		
		for(Integer teamId: teams.getTeamsId()){
			spawn = teams.getSpawn(teamId);
			
			for(Integer playerId: teams.getPlayers(teamId)){
				avatars.add(new Unit(playerId, ConstUnits.unitAvatar, spawn.x, spawn.y));
			}
		}
		
		units.spawnAllPlayersAvatars(avatars);
	}
}
