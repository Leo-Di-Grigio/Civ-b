package scene.menu_newgame;

import misc.Enums;
import script.gui.button.gui_button_CreateNewGame;
import script.gui.button.gui_button_Menu;
import gui.GUI;
import gui.elements.GuiElementButton;

public class gui_SceneMenuNewGame extends GUI {

	public gui_SceneMenuNewGame() {
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
		button.setText("Start new game");
		button.setVisible(true);
		button.setScript(new gui_button_CreateNewGame());
		this.add("new_game", button);
		
		// Back
		button = new GuiElementButton();
		button.setPosition(0, -80);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Disconnect");
		button.setVisible(true);
		button.setScript(new gui_button_Menu());
		this.add("back", button);
	}
}
