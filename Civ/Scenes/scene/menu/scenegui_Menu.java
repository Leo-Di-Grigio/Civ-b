package scene.menu;

import misc.Enums;
import script.gui.button.gui_button_ChangeScene;
import gui.GUI;
import gui.elements.GuiElementButton;

public class scenegui_Menu extends GUI {
	
	public scenegui_Menu(){
		super();
		
		// GUI init
		GuiElementButton button = null;
		
		// New Game
		button = new GuiElementButton("new_game");
		button.setPosition(0, 0);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Connect");
		button.setVisible(true);
		button.setScript(new gui_button_ChangeScene(Enums.Scene.CONNECT));
		this.add(button);
		
		// Load Game
		button = new GuiElementButton("load_game");
		button.setPosition(0, 40);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Load Game");
		button.setVisible(true);
		button.setScript(new gui_button_ChangeScene(Enums.Scene.LOAD));
		this.add(button);
		
		// Settings
		button = new GuiElementButton("settings");
		button.setPosition(0, 80);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Settings");
		button.setVisible(true);
		button.setScript(new gui_button_ChangeScene(Enums.Scene.SETTINGS));
		this.add(button);
		
		// Exit
		button = new GuiElementButton("exit");
		button.setPosition(0, -80);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Exit");
		button.setVisible(true);
		button.setScript(new gui_button_ChangeScene(Enums.Scene.EXIT));
		this.add(button);
	}
}
