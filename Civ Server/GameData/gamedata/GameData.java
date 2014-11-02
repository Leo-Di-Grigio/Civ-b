package gamedata;

import game.GamesMng;
import gamedb.GameDB;
import gameobject.GameObject;
import gameobject.GameObjectMng;
import gameobject.unit.Avatar;

import java.awt.Point;
import java.io.IOException;
import java.util.HashSet;

import actions.GameActions;
import net.Message;
import net.Message.Prefix;
import network.Client;
import network.ClientPool;
import player.Player;
import map.GameMap;
import misc.Enums;
import misc.Log;
import misc.Enums.GameState;

public class GameData{
	// game Id
	public int gameId;
	
	// State
	private Enums.GameState state;
	
	// Data base
	public GameRules rules;
	public GameDB database;
	// Data
	private long seed;
	public GamePlayers players;
	public GameTeams teams;
	public GameMap map;
	public GameObjectMng gameObjects;
	
	// Network
	public GameBroadcasting broad;
	
	// Actions
	public GameActions actions;
	
	public GameData(int gameId, long seed, int mapSizeX, int mapSizeY, int playersMax, GameDB database) throws IOException {
		// id
		this.gameId = gameId;
		this.seed = seed;
		this.state = Enums.GameState.PREPEARING;
		
		// map
		this.map = new GameMap(seed, mapSizeX, mapSizeY);
		this.gameObjects = new GameObjectMng(this.map);
		
		// players
		this.players = new GamePlayers(gameId, playersMax);
		this.teams = new GameTeams(gameId);
		
		// broad
		this.broad = new GameBroadcasting(players, teams);
		
		// actions
		this.actions = new GameActions(this);
		
		// rules
		this.database = database;
		this.rules = database.getRules();
	}
	
	public GameState getState() {
		return state;
	}
	
	public void addPlayer(int clientId, String playerName) throws IOException{
		int playerId = players.add(clientId, playerName, seed, map.sizeX, map.sizeY, broad);
		
		if(playerId != -1){ 
			// successful created player
			gameObjects.registerPlayer(playerId);
			teams.registerPlayer(0, playerId);
			actions.registerPlayer(playerId);
		}
	}
	
	public void removePlayer(int clientId) throws IOException{
		Player player = players.remove(clientId, broad);
		
		if(player != null){
			Client client = ClientPool.getClient(clientId);
			
			if(client != null){
				client.gameId = -1;
			}
			
			actions.unregisterPlayer(player.id);
			teams.unregisterPlayer(player.teamId, player.id);
			if(teams.deleteTeamIfNoPlayers(player.teamId, broad)){
				actions.unregisterTeam(player.teamId);
			}
			
			broad.sendToPlayer(clientId, new Message(Prefix.DATA_GAMES_LIST, GamesMng.getList()));
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
			actions.registerTeam(teamId);
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
			if(unregistered){ // team existing check
				if(teams.deleteTeamIfNoPlayers(player.teamId, broad)){
					actions.unregisterTeam(player.teamId);
				}
				
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
			broad.sendToTeam(teams.getTurnedTeam(), new Message(Prefix.GAME_TURN, null));
		}
	}
	
	public void spawnAvatars() throws IOException{
		HashSet<Point> spawnPoints = map.getSpawnPoints(teams.getTeamsCount());
		HashSet<GameObject> avatars = new HashSet<GameObject>();
		teams.setSpawns(spawnPoints);
		Point spawn = null;
		
		for(Integer teamId: teams.getTeamsId()){
			spawn = teams.getSpawn(teamId);
			
			for(Integer playerId: teams.getPlayers(teamId)){
				avatars.add(new Avatar(playerId, spawn.x, spawn.y));
			}
		}
		
		gameObjects.addObjects(avatars, broad);
	}
	
	public void addPlayerAction(int clientId, String data) throws IOException {
		actions.addPlayerAction(clientId, data);
	}

	public void gameTurnEnd(int clientId) throws IOException {
		if(clientId == teams.getTeamOwner(teams.getTurnedTeam())){
			if(teams.newTurn){
				teams.newTurn = false;
				actions.nextTurn();
			}
			
			actions.teamActionsProcess(teams.getTurnedTeam());
			
			teams.nextTeamTurn();
			broad.sendToPlayer(teams.getTeamOwner(players.get(clientId).teamId), new Message(Prefix.GAME_TURN, null));
		}
	}

	public void gameChat(int clientId, String data) throws IOException {
		String name = players.get(clientId).name;
		broad.sendToPlayers(new Message(Prefix.CHAT_MSG, "[" + name + "]: " + data));
	}
}
