package scene.connect;

import main.Config;
import misc.Enums;
import script.gui.button.gui_button_ChangeScene;
import gui.GUI;
import gui.elements.GuiElementButton;

public class scenegui_Connect extends GUI {

	public scenegui_Connect() {
		super();
		
		// GUI init
		GuiElementButton button = null;
		
		// Conntect to address
		button = new GuiElementButton();
		button.setPosition(0, 0);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Connect to " + Config.serverAddress + ":" + Config.serverPort);
		button.setVisible(true);
		button.setScript(new gui_button_ChangeScene(Enums.Scene.CHOOSE_GAME));
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
		button.setScript(new gui_button_ChangeScene(Enums.Scene.MENU));
		this.add("back", button);
	}
}
