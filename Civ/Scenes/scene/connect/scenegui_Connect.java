package scene.connect;

import main.Config;
import misc.Const;
import misc.Enums;
import script.gui.button.gui_button_ChangeScene;
import gui.GUI;
import gui.elements.GuiElementButton;
import gui.elements.GuiMenuBackground;

public class scenegui_Connect extends GUI {
	
	public static final String uiMenuBack = "UI_menu";
	public static final String uiConnect = "UI_connect";
	public static final String uiBack = "UI_back";
	
	public scenegui_Connect() {
		super();
		
		GuiMenuBackground menuBack = new GuiMenuBackground(uiMenuBack);
		menuBack.setLayer(-1);
		menuBack.setVisible(true);
		this.add(menuBack);
		
		// GUI init
		GuiElementButton button = null;
		
		// Conntect to address
		button = new GuiElementButton(uiConnect);
		button.setPosition(0, 0);
		button.setSize(256, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture(Const.imgButtonMenu);
		button.setTextureSelected(Const.imgButtonMenuSelect);
		button.setText("Connect to " + Config.serverAddress + ":" + Config.serverPort);
		button.setVisible(true);
		button.setScript(new gui_button_ChangeScene(Enums.Scene.CHOOSE_GAME));
		this.add(button);
				
		// Back
		button = new GuiElementButton(uiBack);
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
