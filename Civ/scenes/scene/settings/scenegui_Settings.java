package scene.settings;

import misc.Const;
import misc.Enums;
import script.gui.button.gui_button_ChangeScene;
import gui.GUI;
import gui.elements.GuiElementButton;
import gui.elements.GuiMenuBackground;

public class scenegui_Settings extends GUI {
	
	public static final String uiMenuBack 	= "UI_menu";
	public static final String uiBack 		= "UI_back";
	
	public scenegui_Settings() {
		super();
		
		GuiMenuBackground menuBack = new GuiMenuBackground(uiMenuBack);
		menuBack.setLayer(-1);
		menuBack.setVisible(true);
		this.add(menuBack);
		
		// GUI init
		GuiElementButton button = null;
		
		// Back
		button = new GuiElementButton(uiBack );
		button.setPosition(0, -80);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture(Const.imgButtonMenu);
		button.setTextureSelected(Const.imgButtonMenuSelect);
		button.setText("Back");
		button.setVisible(true);
		button.setScript(new gui_button_ChangeScene(Enums.Scene.MENU));
		this.add(button);
	}
}
