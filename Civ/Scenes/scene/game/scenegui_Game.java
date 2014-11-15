package scene.game;

import java.awt.Color;

import misc.Const;
import misc.Enums;
import recources.Recources;
import script.gui.icon.gui_icon_Test;
import script.gui.minimap.gui_minimap_MoveCamera;
import gui.GUI;
import gui.elements.GuiElementButton;
import gui.elements.GuiElementButtonUnitAction;
import gui.elements.GuiElementChat;
import gui.elements.GuiElementCursor;
import gui.elements.GuiElementIcon;
import gui.elements.GuiElementMinimap;
import gui.elements.GuiElementPane;
import gui.elements.GuiElementTechnologies;
import gui.elements.GuiElementTitle;
import gui.elements.GuiElementUnits;
import gui.elements.GuiElementWindow;
import gui.elements.GuiElementInventory;

public class scenegui_Game extends GUI {

	// registered UI elements
	public static final String uiCursor = "UI_cursor";
	public static final String uiUnitSelect = "UI_unitSelectPane";
	public static final String uiMinimap 	= "UI_minimap";
	
	// buttons
	public static final String uiButton0 = "UI_button_0";
	public static final String uiButton1 = "UI_button_1";
	public static final String uiButton2 = "UI_button_2";
	public static final String uiButton3 = "UI_button_3";
	public static final String uiButton4 = "UI_button_4";
	public static final String uiButton5 = "UI_button_5";
	
	// interact buttons
	public static final String uiInteractButton0 = "UI_interactButton_0";
	public static final String uiInteractButton1 = "UI_interactButton_1";
	public static final String uiInteractButton2 = "UI_interactButton_2";
	public static final String uiInteractButton3 = "UI_interactButton_3";
	public static final String uiInteractButton4 = "UI_interactButton_4";
	public static final String uiInteractButton5 = "UI_interactButton_5";
	
	public static final String uiButtonEndTurn	 = "UI_button_endturn";
	public static final String uiButtonExit		 = "UI_button_exit";
	
	// infopane
	public static final String uiInfopane   	= "UI_infopane";
	public static final String uiInfopaneIcon 	= "UI_infopaneIcon";
	public static final String uiInfopaneTitle0 = "UI_infopaneTitle0";
	public static final String uiInfopaneTitle1 = "UI_infopaneTitle1";
	public static final String uiInfopaneTitle2 = "UI_infopaneTitle2";
	
	// chat
	public static final String uiChat 		= "UI_chat";
	public static final String uiChatEntry 	= "UI_chatEntry";
	
	// inventory
	public static final String uiInventory 	= "UI_Inventory";
	
	// technologies
	public static final String uiTechButton = "UI_TechButton";
	public static final String uiTech 		= "UI_Tech";
	
	public scenegui_Game() {
		super();
		
		// cursor
		GuiElementCursor cursor = new GuiElementCursor(uiCursor);
		this.add(cursor);
		
		// minimap
		GuiElementMinimap minimap = new GuiElementMinimap(uiMinimap);
		minimap.setSize(300, 200);
		minimap.setPosition(5, 0);
		minimap.setPositionType(Enums.GuiPosition.BOTTOM_LEFT);
		minimap.setTexture(Const.imgPane);
		minimap.setMinimapTexture(Recources.getImage(Const.imgMinimap));
		minimap.setVisible(true);
		minimap.setScript(new gui_minimap_MoveCamera());
		this.add(minimap);
		
		// info pane
		GuiElementPane pane = new GuiElementPane(uiInfopane);
		pane.setSize(300, 200);
		pane.setPosition(-5, 0);
		pane.setPositionType(Enums.GuiPosition.BOTTOM_RIGHT);
		pane.setTexture(Const.imgPane);
		pane.setTextureSelected(Const.imgPane);
		pane.setVisible(true);
		pane.setScript(null);
		
		// unit selecting Pane
		GuiElementUnits select = new GuiElementUnits(uiUnitSelect);
		select.setSize(300, 100);
		select.setLayer(2);
		select.setPosition(-5, -175);
		select.setPositionType(Enums.GuiPosition.BOTTOM_RIGHT);
		select.setTexture("pane");
		select.setScript(new gui_unit_Select());
		select.setVisible(false);
		this.add(select);
		
		// info icon
		GuiElementIcon icon = new GuiElementIcon(uiInfopaneIcon);
		icon.setSize(32, 32);
		icon.setPosition(0, 0);
		icon.setPositionType(Enums.GuiPosition.TOP_LEFT);
		icon.setVisible(true);
		icon.setScript(new gui_icon_Test());
		pane.addElement(icon);
		
		// info string
		GuiElementTitle title = new GuiElementTitle(uiInfopaneTitle0);
		title.setText("0");
		title.setColor(Color.black);
		title.setPosition(36, 12);
		title.setPositionType(Enums.GuiPosition.TOP_LEFT);
		pane.addElement(title);
		

		title = new GuiElementTitle(uiInfopaneTitle1);
		title.setText("1");
		title.setColor(Color.black);
		title.setPosition(36, 24);
		title.setPositionType(Enums.GuiPosition.TOP_LEFT);
		pane.addElement(title);
		
		title = new GuiElementTitle(uiInfopaneTitle2);
		title.setText("2");
		title.setColor(Color.black);
		title.setPosition(36, 36);
		title.setPositionType(Enums.GuiPosition.TOP_LEFT);
		pane.addElement(title);
		
		this.add(pane);
		
		// button-0
		GuiElementButtonUnitAction button = null;
		button = new GuiElementButtonUnitAction(uiButton0);
		button.setPosition(-163, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture(Const.imgButton);
		button.setTextureSelected(Const.imgButtonSelected);
		button.setText("0");
		button.setScript(null);
		this.add(button);
		
		button = new GuiElementButtonUnitAction(uiButton1);
		button.setPosition(-98, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture(Const.imgButton);
		button.setTextureSelected(Const.imgButtonSelected);
		button.setText("1");
		button.setScript(null);
		this.add(button);
		
		button = new GuiElementButtonUnitAction(uiButton2);
		button.setPosition(-33, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture(Const.imgButton);
		button.setTextureSelected(Const.imgButtonSelected);
		button.setText("2");
		button.setScript(null);
		this.add(button);
		
		button = new GuiElementButtonUnitAction(uiButton3);
		button.setPosition(32, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture(Const.imgButton);
		button.setTextureSelected(Const.imgButtonSelected);
		button.setText("3");
		button.setScript(null);
		this.add(button);
		
		button = new GuiElementButtonUnitAction(uiButton4);
		button.setPosition(97, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture(Const.imgButton);
		button.setTextureSelected(Const.imgButtonSelected);
		button.setText("4");
		button.setScript(null);
		this.add(button);
		
		button = new GuiElementButtonUnitAction(uiButton5);
		button.setPosition(162, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture(Const.imgButton);
		button.setTextureSelected(Const.imgButtonSelected);
		button.setText("5");
		button.setScript(null);
		this.add(button);
		
		GuiElementButton endTurn = new GuiElementButton(uiButtonEndTurn);
		endTurn.setPositionType(Enums.GuiPosition.BOTTOM_LEFT);
		endTurn.setPosition(5, -430);
		endTurn.setSize(128, 32);
		endTurn.setTexture(Const.imgButtonEndTurn);
		endTurn.setTextureSelected(Const.imgButtonSelected);
		endTurn.setText("End turn");
		endTurn.setScript(new game_Turn());
		this.add(endTurn);
		
		GuiElementButton exit = new GuiElementButton(uiButtonExit);
		exit.setPositionType(Enums.GuiPosition.TOP_RIGHT);
		exit.setLayer(2);
		exit.setSize(64, 32);
		exit.setPosition(-5, 5);
		exit.setText("Exit");
		exit.setTexture(Const.imgButton);
		exit.setTextureSelected(Const.imgButtonSelected);
		exit.setVisible(true);
		exit.setScript(new game_Exit());
		this.add(exit);
		
		GuiElementChat chat = new GuiElementChat(uiChat);
		chat.setPositionType(Enums.GuiPosition.BOTTOM_LEFT);
		chat.setPosition(5, -225);
		chat.setSize(400, 200);
		chat.setLayer(2);
		chat.setTexture(Const.imgChat);
		chat.setTextureSelected(Const.imgChatSelected);
		chat.setVisible(true);
		this.add(chat);
		
		GuiElementWindow chatWindow = new GuiElementWindow(uiChatEntry);
		chatWindow.setPositionType(Enums.GuiPosition.BOTTOM_LEFT);
		chatWindow.setPosition(5, -208);
		chatWindow.setSize(400, 20);
		chatWindow.setLayer(2);
		chatWindow.setTexture(Const.imgWindow);
		this.add(chatWindow);
		
		GuiElementInventory inventory = new GuiElementInventory(uiInventory);
		inventory.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		inventory.setPosition(0, -70);
		inventory.setSize(330, 74);
		inventory.setLayer(3);
		inventory.setTexture(Const.imgWindow);
		this.add(inventory);
		
		// Interact options
		GuiElementButtonUnitAction interact = null;
		interact = new GuiElementButtonUnitAction(uiInteractButton0);
		interact.setPositionType(Enums.GuiPosition.ABSOLUTE);
		interact.setSize(48, 48);
		interact.setText("A-0");
		interact.setTexture(Const.imgButton);
		interact.setTextureSelected(Const.imgButtonSelected);
		interact.setLayer(3);
		this.add(interact);
		
		interact = new GuiElementButtonUnitAction(uiInteractButton1);
		interact.setPositionType(Enums.GuiPosition.ABSOLUTE);
		interact.setSize(48, 48);
		interact.setText("A-1");
		interact.setTexture(Const.imgButton);
		interact.setTextureSelected(Const.imgButtonSelected);
		interact.setLayer(3);
		this.add(interact);
		
		interact = new GuiElementButtonUnitAction(uiInteractButton2);
		interact.setPositionType(Enums.GuiPosition.ABSOLUTE);
		interact.setSize(48, 48);
		interact.setText("A-2");
		interact.setTexture(Const.imgButton);
		interact.setTextureSelected(Const.imgButtonSelected);
		interact.setLayer(3);
		this.add(interact);
		
		interact = new GuiElementButtonUnitAction(uiInteractButton3);
		interact.setPositionType(Enums.GuiPosition.ABSOLUTE);
		interact.setSize(48, 48);
		interact.setText("A-3");
		interact.setTexture(Const.imgButton);
		interact.setTextureSelected(Const.imgButtonSelected);
		interact.setLayer(3);
		this.add(interact);
		
		interact = new GuiElementButtonUnitAction(uiInteractButton4);
		interact.setPositionType(Enums.GuiPosition.ABSOLUTE);
		interact.setSize(48, 48);
		interact.setText("A-4");
		interact.setTexture(Const.imgButton);
		interact.setTextureSelected(Const.imgButtonSelected);
		interact.setLayer(3);
		this.add(interact);
		
		interact = new GuiElementButtonUnitAction(uiInteractButton5);
		interact.setPositionType(Enums.GuiPosition.ABSOLUTE);
		interact.setSize(48, 48);
		interact.setText("A-5");
		interact.setTexture(Const.imgButton);
		interact.setTextureSelected(Const.imgButtonSelected);
		interact.setLayer(3);
		this.add(interact);
		
		GuiElementButton techButton = new GuiElementButton(uiTechButton);
		techButton.setPositionType(Enums.GuiPosition.TOP_LEFT);
		techButton.setPosition(5, 100);
		techButton.setLayer(2);
		techButton.setSize(72, 32);
		techButton.setText("Technology");
		techButton.setTexture(Const.imgButton);
		techButton.setTextureSelected(Const.imgButtonSelected);
		techButton.setVisible(true);
		techButton.setScript(new show_Tech());
		this.add(techButton);
		
		GuiElementTechnologies tech = new GuiElementTechnologies(uiTech);
		tech.setPositionType(Enums.GuiPosition.CENTER);
		tech.setSize(700, 512);
		tech.setLayer(4);
		tech.setTexture("pane");
		tech.setVisible(false);
		tech.setScript(new gui_Tech());
		this.add(tech);
	}
}
