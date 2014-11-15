package scene.menu;

import misc.Const;
import misc.Enums;
import script.gui.button.gui_button_ChangeScene;
import gui.GUI;
import gui.elements.GuiElementButton;
import gui.elements.GuiMenuBackground;

public class scenegui_Menu extends GUI {
	
	public static final String uiMenuBack = "UI_menu";
	public static final String uiConnect = "UI_connect";
	public static final String uiLoad = "UI_load";
	public static final String uiSettings = "UI_settings";
	public static final String uiExit = "UI_exit";
	
	public scenegui_Menu(){
		super();
		
		GuiMenuBackground menuBack = new GuiMenuBackground(uiMenuBack);
		menuBack.setLayer(-1);
		menuBack.setVisible(true);
		this.add(menuBack);
		
		// GUI init
		GuiElementButton button = null;
		
		// New Game
		button = new GuiElementButton(uiConnect);
		button.setPosition(0, 0);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture(Const.imgButtonMenu);
		button.setTextureSelected(Const.imgButtonMenuSelect);
		button.setText("Connect");
		button.setVisible(true);
		button.setScript(new gui_button_ChangeScene(Enums.Scene.CONNECT));
		this.add(button);
		
		// Load Game
		button = new GuiElementButton(uiLoad);
		button.setPosition(0, 40);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture(Const.imgButtonMenu);
		button.setTextureSelected(Const.imgButtonMenuSelect);
		button.setText("Load game");
		button.setVisible(true);
		button.setScript(new gui_button_ChangeScene(Enums.Scene.LOAD));
		this.add(button);
		
		// Settings
		button = new GuiElementButton(uiSettings);
		button.setPosition(0, 80);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture(Const.imgButtonMenu);
		button.setTextureSelected(Const.imgButtonMenuSelect);
		button.setText("Settings");
		button.setVisible(true);
		button.setScript(new gui_button_ChangeScene(Enums.Scene.SETTINGS));
		this.add(button);
		
		// Exit
		button = new GuiElementButton(uiExit);
		button.setPosition(0, -80);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture(Const.imgButtonMenu);
		button.setTextureSelected(Const.imgButtonMenuSelect);
		button.setText("Exit");
		button.setVisible(true);
		button.setScript(new gui_button_ChangeScene(Enums.Scene.EXIT));
		this.add(button);
	}
}
