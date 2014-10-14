package scene.exit;

import misc.Enums;
import script.gui.button.gui_button_ChangeScene;
import script.gui.button.gui_button_Exit;
import gui.GUI;
import gui.elements.GuiElementButton;

public class scenegui_Exit extends GUI {
	
	public scenegui_Exit(){
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
		button.setScript(new gui_button_ChangeScene(Enums.Scene.MENU));
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
