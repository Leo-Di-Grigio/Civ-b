package scene.menu_exit;

import misc.Enums;
import script.gui.button.gui_button_Exit;
import script.gui.button.gui_button_Menu;
import gui.GUI;
import gui.elements.GuiElementButton;

public class gui_SceneMenuExit extends GUI {
	
	public gui_SceneMenuExit(){
		super();
		
		// GUI init
		GuiElementButton button = null;
		
		// Cancel
		button = new GuiElementButton();
		button.setPosition(-100, 0);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Cancel");
		button.setVisible(true);
		button.setScript(new gui_button_Menu());
		this.add("cancel", button);
		
		// Exit
		button = new GuiElementButton();
		button.setPosition(100, 0);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Exit");
		button.setVisible(true);
		button.setScript(new gui_button_Exit());
		this.add("exit", button);
	}
}
