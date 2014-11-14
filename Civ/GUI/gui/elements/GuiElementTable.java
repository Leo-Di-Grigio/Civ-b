package gui.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.awt.TextRenderer;

import misc.Enums;
import player.Player;
import player.Team;
import scenedata.game.GameData;
import gui.GuiElement;
import gui.misc.TableLine;

public class GuiElementTable extends GuiElement {

	public static final int lineSize = 20;
	
	protected int selectedLine = -1;

	protected int collumns;
	protected Vector<TableLine> list;
	
	public GuiElementTable(String title, int collumns) {
		super(title);
		setTexture("pane");
		this.collumns = collumns;
		list = new Vector<TableLine>();
	}
	
	public int getLineSize(){
		return lineSize;
	}

	public void setCollumns(int collumns) {
		this.collumns = collumns;
	}
	
	public int getCollumns(){
		return collumns;
	}
	
	public TableLine getSelectedLine(){
		if(selectedLine == -1){
			return null;
		}
		else{
			return list.get(selectedLine);
		}
	}
	
	public int getSelectedLineNumber(){
		return selectedLine;
	}
	
	public void updateList(String data){
		list.clear();
		String [] arr = data.split(":");
		
		int size = Integer.valueOf(arr[0]);
		
		for(int i = 0; i < size; ++i){
			TableLine line = new TableLine(collumns);
			
			for(int j = 1; j <= collumns; ++j){
				line.setCell(j - 1, arr[i*collumns + j]);
			}
			
			list.add(line);
		}
	}
	
	public void select(int lineId){
		if(lineId >= list.size() || lineId < 0){
			this.selectedLine = -1; // no selected lines
		}
		else{
			this.selectedLine = lineId;
		}
	}
	
	public void add(TableLine line){
		list.add(line);
	}
	
	public void sort(GameData gamedata){
		// SECIAL FOR PLAYERS AND TEAMS!!
		HashMap <Integer, Set<Player>> table = new HashMap<Integer, Set<Player>>();
			
		for(Team team: gamedata.users.teams.values()){
			if(team != null){ // !!check for null, because Team can receive AFTER receiving Player
				table.put(team.id, new HashSet<Player>());
			}
		}
		
		for(Player player: gamedata.users.players.values()){
			if(table.containsKey(player.teamId)){ // !!check for null, because Player can be receive AFTER receiving Team
				table.get(player.teamId).add(player);
			}
		}
		
		list.clear();
		
		for(Team team: gamedata.users.teams.values()){
			TableLine teamLine = new TableLine(2);
			
			teamLine.setCell(0, "" + team.id);
			teamLine.setCell(1, " " + team.name);
			teamLine.metadata = Enums.TableMetadata.TEAM;
			teamLine.selectingColor = Color.red;
			
			list.add(teamLine);
			
			for(Player player: table.get(team.id)){
				TableLine playerLine = new TableLine(4);
				playerLine.setCell(0, "    ");
				playerLine.setCell(1, ""+player.id);
				playerLine.setCell(2, " "+player.name);
				
				if(player.ready){
					playerLine.setCell(3, " ready");
				}
				else{
					playerLine.setCell(3, " not ready");
				}
				playerLine.metadata = Enums.TableMetadata.PLAYER;
				playerLine.selectingColor = Color.blue;
				
				list.add(playerLine);
			}
		}
	}
	
	public void clear(){
		list.clear();
	}
	
	public void remove(int collumn, String data) { // search specify (String)data in (int)collumn and remove line while data finded
		for(int i = 0; i < list.size(); ++i){
			if(list.get(i).getCell(collumn).compareTo(data) == 0){
				list.remove(i);
				return;
			}
		}
	}
	
	@Override
	public void draw(Graphics g, long tic) {
		if(visible){
			g.drawImage(textureNormal, drawX, drawY, sizeX, sizeY, null);
			
			for(int i = 0; i < list.size(); ++i){
				if(i == selectedLine){
					g.setColor(list.get(i).selectingColor);
					g.fillRect(drawX + 10, drawY + i * lineSize + 5, sizeX - 20, lineSize);
					g.setColor(Color.white);
					g.drawString(list.get(i).toString(), drawX + 10, drawY + i * lineSize + 20);
				}
				else{
					g.setColor(Color.black);
					g.drawString(list.get(i).toString(), drawX + 10, drawY + i * lineSize + 20);
				}
			}
		}
	}

	@Override
	public void draw(GL2 gl, TextRenderer textrender) {

	}
}
