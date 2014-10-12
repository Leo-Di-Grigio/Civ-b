package scene.menu;

import misc.Enums;
import script.gui.button.gui_button_MenuExit;
import script.gui.button.gui_button_MenuLoadGame;
import script.gui.button.gui_button_MenuConnect;
import script.gui.button.gui_button_MenuSettings;
import gui.GUI;
import gui.elements.GuiElementButton;

public class gui_SceneMenu extends GUI {
	
	public gui_SceneMenu(){
		super();
		
		// GUI init
		GuiElementButton button = null;
		
		// New Game
		button = new GuiElementButton();
		button.setPosition(0, 0);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Connect");
		button.setVisible(true);
		button.setScript(new gui_button_MenuConnect());
		this.add("new_game", button);
		
		// Load Game
		button = new GuiElementButton();
		button.setPosition(0, 40);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Load Game");
		button.setVisible(true);
		button.setScript(new gui_button_MenuLoadGame());
		this.add("load_game", button);
		
		// Settings
		button = new GuiElementButton();
		button.setPosition(0, 80);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Settings");
		button.setVisible(true);
		button.setScript(new gui_button_MenuSettings());
		this.add("settings", button);
		
		// Exit
		button = new GuiElementButton();
		button.setPosition(0, -80);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Exit");
		button.setVisible(true);
		button.setScript(new gui_button_MenuExit());
		this.add("exit", button);
	}
}
