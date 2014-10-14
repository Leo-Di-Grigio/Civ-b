package scene.load;

import misc.Enums;
import script.gui.button.gui_button_ChangeScene;
import gui.GUI;
import gui.elements.GuiElementButton;

public class scenegui_Load extends GUI {

	public scenegui_Load() {
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
		button.setScript(new gui_button_ChangeScene(Enums.Scene.MENU));
		this.add("back", button);
	}
}
