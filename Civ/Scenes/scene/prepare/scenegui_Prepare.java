package scene.prepare;

import misc.Enums;
import script.gui.button.gui_button_ChangeScene;
import script.gui.button.gui_button_CreateTeam;
import script.gui.table.gui_table_Select;
import gui.GUI;
import gui.elements.GuiElementButton;
import gui.elements.GuiElementTable;

public class scenegui_Prepare extends GUI {

	public scenegui_Prepare() {
		super();
		
		// GUI init
		GuiElementButton button = null;
		
		// Players Table
		GuiElementTable players = new GuiElementTable(2);
		players.setCollumns(2);
		players.setSize(400, 600);
		players.setPositionType(Enums.GuiPosition.CENTER);
		players.setPosition(0, 0);
		players.setVisible(true);
		players.setScript(new gui_table_Select());
		this.add("players", players);
		
		// Join the game		
		button = new GuiElementButton();
		button.setPosition(270, -285);
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
		button.setPosition(270, -248);
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
		button.setPosition(270, -211);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Join Team");
		button.setVisible(true);
		button.setScript(new gui_button_JoinTeam());
		this.add("join_team", button);
		
		// Leave team
		button = new GuiElementButton();
		button.setPosition(270, -174);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Leave Team");
		button.setVisible(true);
		button.setScript(new gui_button_LeaveTeam());
		this.add("leave_team", button);
		
		// Back
		button = new GuiElementButton();
		button.setPosition(270, 285);
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
