package scene.prepare;

import misc.Enums;
import scene.menu_newgame.gui_Table;
import script.gui.button.gui_button_ChangeScene;
import script.gui.button.gui_button_CreateTeam;
import script.gui.button.gui_button_JoinTeam;
import gui.GUI;
import gui.elements.GuiElementButton;
import gui.elements.GuiElementMultiTable;
import gui.elements.GuiElementTable;
import gui.misc.MultiTableLine;

public class scenegui_Prepare extends GUI {

	public scenegui_Prepare() {
		super();
		
		// GUI init
		GuiElementButton button = null;
		
		// Players Table
		GuiElementTable players = new GuiElementTable(2);
		players.setCollumns(2);
		players.setSize(400, 400);
		players.setPositionType(Enums.GuiPosition.CENTER);
		players.setPosition(150, 0);
		players.setVisible(true);
		players.setScript(new gui_Table());
		this.add("players", players);
		
		// Players Table
		GuiElementMultiTable players2 = new GuiElementMultiTable();
		players2.setSize(400, 400);
		players2.setPositionType(Enums.GuiPosition.CENTER_LEFT);
		players2.setTexture("pane");
		players2.setPosition(50, 0);
		players2.setVisible(true);
		players2.setScript(null);
		this.add("players2", players2);
		
		// test data
		String [] arr = {"Test", "Array"};
		
		MultiTableLine line1 = new MultiTableLine(arr);
		line1.addLine(new MultiTableLine(arr));
		
		MultiTableLine line2 = new MultiTableLine(arr);
		line2.addLine(new MultiTableLine(arr));
		line2.addLine(new MultiTableLine(arr));
		
		MultiTableLine line3 = new MultiTableLine(arr);
		line3.addLine(new MultiTableLine(arr));
		line3.addLine(new MultiTableLine(arr));
		
		MultiTableLine line4 = new MultiTableLine(arr);
		MultiTableLine line41 = new MultiTableLine(arr);
		MultiTableLine line42 = new MultiTableLine(arr);
		MultiTableLine line411 = new MultiTableLine(arr);
		MultiTableLine line412 = new MultiTableLine(arr);
		MultiTableLine line421 = new MultiTableLine(arr);
		MultiTableLine line422 = new MultiTableLine(arr);
		
		line41.addLine(line411);
		line41.addLine(line412);
		line42.addLine(line421);
		line42.addLine(line422);
		line4.addLine(line41);
		line4.addLine(line42);
		
		MultiTableLine line5 = new MultiTableLine(arr);
		line5.addLine(new MultiTableLine(arr));
		line5.addLine(new MultiTableLine(arr));
		line5.addLine(new MultiTableLine(arr));
		
		players2.addLine(line1);
		players2.addLine(line2);
		players2.addLine(line3);
		players2.addLine(line4);
		players2.addLine(line5);
		
		// Join the game		
		button = new GuiElementButton();
		button.setPosition(430, -185);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Join, MF!");
		button.setVisible(true);
		button.setScript(new gui_button_ChangeScene(Enums.Scene.GAME));
		this.add("new_game", button);
		
		// Create Team
		button = new GuiElementButton();
		button.setPosition(430, -138);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Create new Team");
		button.setVisible(true);
		button.setScript(new gui_button_CreateTeam());
		this.add("create_team", button);
		
		// Join the Team
		button = new GuiElementButton();
		button.setPosition(430, -100);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Join Team");
		button.setVisible(true);
		button.setScript(new gui_button_JoinTeam());
		this.add("join_team", button);
		
		// Back
		button = new GuiElementButton();
		button.setPosition(430, 185);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Back");
		button.setVisible(true);
		button.setScript(new gui_button_LeaveGame());
		this.add("back", button);
	}
}
