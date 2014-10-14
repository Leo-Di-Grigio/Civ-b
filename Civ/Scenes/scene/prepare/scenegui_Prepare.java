package scene.prepare;

import misc.Enums;
import script.gui.button.gui_button_ChangeScene;
import gui.GUI;
import gui.elements.GuiElementButton;

public class scenegui_Prepare extends GUI {

	public scenegui_Prepare() {
		super();
		
		// GUI init
		GuiElementButton button = null;
		
		// Join the game		
		button = new GuiElementButton();
		button.setPosition(0, 0);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Join, MF!");
		button.setVisible(true);
		button.setScript(new gui_button_ChangeScene(Enums.Scene.GAME));
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
		button.setScript(new gui_button_ChangeScene(Enums.Scene.CHOOSE_GAME));
		this.add("back", button);
	}
}
