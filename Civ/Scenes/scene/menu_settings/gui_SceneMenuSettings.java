package scene.menu_settings;

import misc.Enums;
import script.gui.button.gui_button_Menu;
import gui.GUI;
import gui.elements.GuiElementButton;

public class gui_SceneMenuSettings extends GUI {

	public gui_SceneMenuSettings() {
		super();
		// GUI init
		GuiElementButton button = null;
		
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
