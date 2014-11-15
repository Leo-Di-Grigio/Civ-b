package scene.choosegame;

import misc.Const;
import misc.Enums;
import script.gui.button.gui_button_CreateNewGame;
import script.gui.button.gui_button_JoinTheGame;
import script.gui.table.gui_table_Select;
import gui.GUI;
import gui.elements.GuiElementButton;
import gui.elements.GuiElementTable;
import gui.elements.GuiMenuBackground;

public class scenegui_ChooseGame extends GUI {

	public static final String uiMenuBack 	= "UI_menu";
	public static final String uiGameList 	= "UI_gamelist";
	public static final String uiNewGame 	= "UI_newgame";
	public static final String uiJoin 		= "UI_join";
	public static final String uiBack 		= "UI_back";
	
	public scenegui_ChooseGame() {
		super();
		
		GuiMenuBackground menuBack = new GuiMenuBackground(uiMenuBack);
		menuBack.setLayer(-1);
		menuBack.setVisible(true);
		this.add(menuBack);
		
		// GUI init
		GuiElementButton button = null;
		
		// Games List
		GuiElementTable gameslist = new GuiElementTable(uiGameList, 3);
		gameslist.setSize(400, 400);
		gameslist.setPositionType(Enums.GuiPosition.CENTER);
		gameslist.setVisible(true);
		gameslist.setScript(new gui_table_Select());
		this.add(gameslist);
		
		// Create new game
		button = new GuiElementButton(uiNewGame);
		button.setPosition(-280, -185);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture(Const.imgButtonMenu);
		button.setTextureSelected(Const.imgButtonMenuSelect);
		button.setText("Create new game");
		button.setVisible(true);
		button.setScript(new gui_button_CreateNewGame());
		this.add(button);

		// Create new game
		button = new GuiElementButton(uiJoin);
		button.setPosition(280, -185);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture(Const.imgButtonMenu);
		button.setTextureSelected(Const.imgButtonMenuSelect);
		button.setText("Join the game");
		button.setVisible(true);
		button.setScript(new gui_button_JoinTheGame());
		this.add(button);
		
		// Back
		button = new GuiElementButton(uiBack);
		button.setPosition(0, -80);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture(Const.imgButtonMenu);
		button.setTextureSelected(Const.imgButtonMenuSelect);
		button.setText("Disconnect");
		button.setVisible(true);
		button.setScript(new gui_button_Disconnect());
		this.add(button);
	}
}
