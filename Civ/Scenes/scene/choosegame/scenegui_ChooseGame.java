package scene.choosegame;

import misc.Enums;
import script.gui.button.gui_button_ChangeScene;
import script.gui.button.gui_button_CreateNewGame;
import script.gui.button.gui_button_JoinTheGame;
import script.gui.table.gui_table_Select;
import gui.GUI;
import gui.elements.GuiElementButton;
import gui.elements.GuiElementTable;

public class scenegui_ChooseGame extends GUI {

	public scenegui_ChooseGame() {
		super();
		
		// GUI init
		GuiElementButton button = null;
		
		// Games List
		GuiElementTable gameslist = new GuiElementTable(3);
		gameslist.setSize(400, 400);
		gameslist.setPositionType(Enums.GuiPosition.CENTER);
		gameslist.setVisible(true);
		gameslist.setScript(new gui_table_Select());
		this.add("gameslist", gameslist);
		
		// Create new game
		button = new GuiElementButton();
		button.setPosition(-280, -185);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Create new game");
		button.setVisible(true);
		button.setScript(new gui_button_CreateNewGame());
		this.add("new_game", button);

		// Create new game
		button = new GuiElementButton();
		button.setPosition(280, -185);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Join the game");
		button.setVisible(true);
		button.setScript(new gui_button_JoinTheGame());
		this.add("join_the_game", button);
		
		// Back
		button = new GuiElementButton();
		button.setPosition(0, -80);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Disconnect");
		button.setVisible(true);
		button.setScript(new gui_button_ChangeScene(Enums.Scene.MENU));
		this.add("back", button);
	}
}
