package scene.prepare;

import misc.Const;
import misc.Enums;
import script.gui.button.gui_button_CreateTeam;
import script.gui.table.gui_table_Select;
import gui.GUI;
import gui.elements.GuiElementButton;
import gui.elements.GuiElementTable;
import gui.elements.GuiElementWindow;
import gui.elements.GuiMenuBackground;

public class scenegui_Prepare extends GUI {

	public static final String uiMenuBack 			= "UI_menu";
	public static final String uiWindiwNewTeam 		= "UI_windowNewTeam";
	public static final String uiPlayersTable 		= "UI_playersTable";
	public static final String uiButtonCreateTeam 	= "UI_buttonCreateTeam";
	public static final String uiButtonJoinTeam 	= "UI_buttonJoinTeam";
	public static final String uiButtonLeaveTeam 	= "UI_buttonLeaveTeam";
	public static final String uiButtonReadyCheck 	= "UI_buttonReadyCheck";
	public static final String uiButtonBack 		= "UI_buttonBack";
	
	public scenegui_Prepare() {
		super();
		
		GuiMenuBackground menuBack = new GuiMenuBackground(uiMenuBack);
		menuBack.setLayer(-1);
		menuBack.setVisible(true);
		this.add(menuBack);
		
		// Window
		GuiElementWindow window = new GuiElementWindow(uiWindiwNewTeam);
		window.setText("Enter team title");
		window.setEnterText("Title: ");
		window.setLayer(1);
		window.setPositionType(Enums.GuiPosition.CENTER);
		window.setPosition(0, 0);
		window.setSize(300, 200);
		window.setTexture(Const.imgWindow);
		window.setVisible(false);
		window.setScript(new gui_window_Click());
		this.add(window);
		
		// GUI init
		GuiElementButton button = null;
		
		// Players Table
		GuiElementTable players = new GuiElementTable(uiPlayersTable, 2);
		players.setCollumns(2);
		players.setSize(400, 600);
		players.setPositionType(Enums.GuiPosition.CENTER);
		players.setPosition(0, 0);
		players.setVisible(true);
		players.setScript(new gui_table_Select());
		this.add(players);
		
		// Create Team
		button = new GuiElementButton(uiButtonCreateTeam);
		button.setPosition(270, -248);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture(Const.imgButtonMenu);
		button.setTextureSelected(Const.imgButtonMenuSelect);
		button.setText("Create new Team");
		button.setVisible(true);
		button.setScript(new gui_button_CreateTeam());
		this.add(button);
		
		// Join the Team
		button = new GuiElementButton(uiButtonJoinTeam);
		button.setPosition(270, -211);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture(Const.imgButtonMenu);
		button.setTextureSelected(Const.imgButtonMenuSelect);
		button.setText("Join Team");
		button.setVisible(true);
		button.setScript(new gui_button_JoinTeam());
		this.add(button);
		
		// Leave team
		button = new GuiElementButton(uiButtonLeaveTeam);
		button.setPosition(270, -174);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture(Const.imgButtonMenu);
		button.setTextureSelected(Const.imgButtonMenuSelect);
		button.setText("Leave Team");
		button.setVisible(true);
		button.setScript(new gui_button_LeaveTeam());
		this.add(button);
		
		// Ready check
		button = new GuiElementButton(uiButtonReadyCheck);
		button.setPosition(270, -285);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture(Const.imgButtonMenu);
		button.setTextureSelected(Const.imgButtonMenuSelect);
		button.setText("Ready!");
		button.setVisible(false);
		button.setScript(new gui_button_ReadyCheck());
		this.add(button);
		
		// Back
		button = new GuiElementButton(uiButtonBack);
		button.setPosition(270, 285);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture(Const.imgButtonMenu);
		button.setTextureSelected(Const.imgButtonMenuSelect);
		button.setText("Back");
		button.setVisible(true);
		button.setScript(new gui_button_LeaveGame());
		this.add(button);
	}
}
