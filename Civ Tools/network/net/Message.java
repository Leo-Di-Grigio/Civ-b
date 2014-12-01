package net;

import java.io.Serializable;

public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum Prefix {
		// connection control
		CONNECTION_OK,
		CONNECTION_ERR,
		REQ_DISCONNECT,
		
		// version
		CHECK_VERSION,
		
		// requests
		REQ_GAMES_LIST,// for Scene.ChooseGame (Games List)
		REQ_GAME_JOIN, // for Scene.Prepare (Request Teams, Players data)
		REQ_GAME_LEAVE,// for Scene.Prepare (Player levae the lobby)
		REQ_GAME_PLAY, // for Scene.Game (ready check)
		REQ_GAME_DATA,

		// data
		DATA_GAMES_LIST, // for Scene.ChooseGame (Games List)
		DATA_GAME, // (map, teams, players)
		
		// game connection
		GAME_JOIN_ERR,
		
		// chat
		CHAT_MSG,
		
		// team control
		REQ_TEAM_CHOOSE,
		REQ_TEAM_CREATE, 
		REQ_TEAM_ERR,
		
		// scene Prepearing
		REQ_READY_CHECK,
		
		// game
		GAME_BEGIN, // --> begin transfer start data
		GAME_PLAYER_DATA,   // updates and new game objects
		GAME_PLAYER_ACTION, // player turn actions		
		
		// turns
		GAME_TURN, 		// end of data transfer, back control to player
		GAME_TURN_END, 	//
		
		// data (server)
		OBJ_TEAM,
		OBJ_PLAYER,
		OBJ_GAMEOBJECT,
		OBJ_UNIT,
		OBJ_IVENTORY,
		
		// update data (server)
		UPD_TEAM,
		UPD_PLAYER,
		UPD_GAMEOBJECT,
		UPD_UNIT,
		UPD_INVENTORY,
		
		// del objects (server)
		DEL_TEAM,
		DEL_PLAYER,
		DEL_GAMEOBJECT,
		DEL_UNIT,
		
		// player actions
		PLAYER_ACTION, // action opcodes -> data.units.ConstAction
		
		// other
		DEBUG;
	};
	
	public long timestamp;
	public Prefix prefix;
	public String data;
	
	public Message(Prefix prefix, String data) {
		this.timestamp = System.currentTimeMillis();
		this.prefix = prefix;
		this.data = data;
	}
}
