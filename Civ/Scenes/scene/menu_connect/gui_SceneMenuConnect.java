package scene.menu_connect;

import main.Config;
import misc.Enums;
import script.gui.button.gui_button_Connect;
import script.gui.button.gui_button_Menu;
import gui.GUI;
import gui.elements.GuiElementButton;

public class gui_SceneMenuConnect extends GUI {

	public gui_SceneMenuConnect() {
		super();
		
		// GUI init
		GuiElementButton button = null;
				
		// Cancel
		button = new GuiElementButton();
		button.setPosition(0, 0);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Connect to " + Config.serverAddress + ":" + Config.serverPort);
		button.setVisible(true);
		button.setScript(new gui_button_Connect());
		this.add("new_game", button);
				
		// Back
		button = new GuiElementButton();
		button.setPosition(0, -80);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Back");
		button.setVisible(true);
		button.setScript(new gui_button_Menu());
		this.add("back", button);
	}
}
