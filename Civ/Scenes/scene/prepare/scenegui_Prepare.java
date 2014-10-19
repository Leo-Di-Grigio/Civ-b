package scene.prepare;

import misc.Const;
import misc.Enums;
import script.gui.button.gui_button_CreateTeam;
import script.gui.table.gui_table_Select;
import gui.GUI;
import gui.elements.GuiElementButton;
import gui.elements.GuiElementTable;
import gui.elements.GuiElementWindow;

public class scenegui_Prepare extends GUI {

	public scenegui_Prepare() {
		super();
		
		// Window
		GuiElementWindow window = new GuiElementWindow("window_new_team");
		window.setText("Enter team title");
		window.setLayer(1);
		window.setPositionType(Enums.GuiPosition.CENTER);
		window.setPosition(0, 0);
		window.setSize(300, 200);
		window.setTexture(Const.imgWindow);
		window.setVisible(true);
		this.add(window);
		
		// GUI init
		GuiElementButton button = null;
		
		// Players Table
		GuiElementTable players = new GuiElementTable("players", 2);
		players.setCollumns(2);
		players.setSize(400, 600);
		players.setPositionType(Enums.GuiPosition.CENTER);
		players.setPosition(0, 0);
		players.setVisible(true);
		players.setScript(new gui_table_Select());
		this.add(players);
		
		// Create Team
		button = new GuiElementButton("create_team");
		button.setPosition(270, -248);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Create new Team");
		button.setVisible(true);
		button.setScript(new gui_button_CreateTeam());
		this.add(button);
		
		// Join the Team
		button = new GuiElementButton("join_team");
		button.setPosition(270, -211);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Join Team");
		button.setVisible(true);
		button.setScript(new gui_button_JoinTeam());
		this.add(button);
		
		// Leave team
		button = new GuiElementButton("leave_team");
		button.setPosition(270, -174);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Leave Team");
		button.setVisible(true);
		button.setScript(new gui_button_LeaveTeam());
		this.add(button);
		
		// Ready check
		button = new GuiElementButton("ready_check");
		button.setPosition(270, -285);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Ready!");
		button.setVisible(true);
		button.setScript(new gui_button_ReadyCheck());
		this.add(button);
		
		// Back
		button = new GuiElementButton("back");
		button.setPosition(270, 285);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Back");
		button.setVisible(true);
		button.setScript(new gui_button_LeaveGame());
		this.add(button);
	}
}
