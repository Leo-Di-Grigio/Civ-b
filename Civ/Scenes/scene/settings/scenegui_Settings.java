package scene.settings;

import misc.Enums;
import script.gui.button.gui_button_ChangeScene;
import gui.GUI;
import gui.elements.GuiElementButton;

public class scenegui_Settings extends GUI {

	public scenegui_Settings() {
		super();
		// GUI init
		GuiElementButton button = null;
		
		// Back
		button = new GuiElementButton("back");
		button.setPosition(0, -80);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Back");
		button.setVisible(true);
		button.setScript(new gui_button_ChangeScene(Enums.Scene.MENU));
		this.add(button);
	}
}
