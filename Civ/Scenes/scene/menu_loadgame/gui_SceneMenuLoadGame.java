package scene.menu_loadgame;

import misc.Enums;
import script.gui.button.gui_button_Menu;
import gui.GUI;
import gui.elements.GuiElementButton;

public class gui_SceneMenuLoadGame extends GUI {

	public gui_SceneMenuLoadGame() {
		super();
		
		// GUI init
		GuiElementButton button = null;
		
		// Cancel
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
