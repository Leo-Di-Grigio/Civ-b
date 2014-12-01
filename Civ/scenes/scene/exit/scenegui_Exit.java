package scene.exit;

import misc.Const;
import misc.Enums;
import script.gui.button.gui_button_ChangeScene;
import script.gui.button.gui_button_Exit;
import gui.GUI;
import gui.elements.GuiElementButton;
import gui.elements.GuiMenuBackground;

public class scenegui_Exit extends GUI {
	
	public static final String uiMenuBack 	= "UI_menu";
	public static final String uiCancel	 	= "UI_cancel";
	public static final String uiExit 		= "UI_exit";
	
	public scenegui_Exit(){
		super();
		
		GuiMenuBackground menuBack = new GuiMenuBackground(uiMenuBack);
		menuBack.setLayer(-1);
		menuBack.setVisible(true);
		this.add(menuBack);
		
		// GUI init
		GuiElementButton button = null;
		
		// Cancel
		button = new GuiElementButton(uiCancel);
		button.setPosition(-100, 0);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture(Const.imgButtonMenu);
		button.setTextureSelected(Const.imgButtonMenuSelect);
		button.setText("Cancel");
		button.setVisible(true);
		button.setScript(new gui_button_ChangeScene(Enums.Scene.MENU));
		this.add(button);
		
		// Exit
		button = new GuiElementButton(uiExit);
		button.setPosition(100, 0);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture(Const.imgButtonMenu);
		button.setTextureSelected(Const.imgButtonMenuSelect);
		button.setText("Exit");
		button.setVisible(true);
		button.setScript(new gui_button_Exit());
		this.add(button);
	}
}
