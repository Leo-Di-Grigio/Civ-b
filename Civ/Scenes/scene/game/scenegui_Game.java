package scene.game;

import java.awt.Color;

import misc.Const;
import misc.Enums;
import recources.Recources;
import script.gui.icon.gui_icon_Test;
import script.gui.minimap.gui_minimap_MoveCamera;
import script.gui.table.gui_table_Select;
import gui.GUI;
import gui.elements.GuiElementButton;
import gui.elements.GuiElementButtonUnitAction;
import gui.elements.GuiElementCursor;
import gui.elements.GuiElementIcon;
import gui.elements.GuiElementMinimap;
import gui.elements.GuiElementPane;
import gui.elements.GuiElementTable;
import gui.elements.GuiElementTitle;

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
	
	public static final String uiButtonEndTurn = "UI_button_endturn";
	
	// infopane
	public static final String uiInfopane   	= "UI_infopane";
	public static final String uiInfopaneIcon 	= "UI_infopaneIcon";
	public static final String uiInfopaneTitle0 = "UI_infopaneTitle0";
	public static final String uiInfopaneTitle1 = "UI_infopaneTitle1";
	public static final String uiInfopaneTitle2 = "UI_infopaneTitle2";
	
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
		minimap.setTexture("pane");
		minimap.setMinimapTexture(Recources.getImage(Const.imgMinimap));
		minimap.setVisible(true);
		minimap.setScript(new gui_minimap_MoveCamera());
		this.add(minimap);
		
		// info pane
		GuiElementPane pane = new GuiElementPane(uiInfopane);
		pane.setSize(300, 200);
		pane.setPosition(-5, 0);
		pane.setPositionType(Enums.GuiPosition.BOTTOM_RIGHT);
		pane.setTexture("pane");
		pane.setTextureSelected("pane");
		pane.setVisible(true);
		pane.setScript(null);
		
		// unit selecting Pane
		GuiElementTable select = new GuiElementTable(uiUnitSelect, 2);
		select.setSize(300, 100);
		select.setLayer(2);
		select.setPosition(-5, -205);
		select.setPositionType(Enums.GuiPosition.BOTTOM_RIGHT);
		select.setTexture("pane");
		select.setScript(new gui_table_Select());
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
		button.setTexture("button");
		button.setTextureSelected("button_select");
		button.setText("0");
		button.setScript(null);
		this.add(button);
		
		button = new GuiElementButtonUnitAction(uiButton1);
		button.setPosition(-98, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button");
		button.setTextureSelected("button_select");
		button.setText("1");
		button.setScript(null);
		this.add(button);
		
		button = new GuiElementButtonUnitAction(uiButton2);
		button.setPosition(-33, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button");
		button.setTextureSelected("button_select");
		button.setText("2");
		button.setScript(null);
		this.add(button);
		
		button = new GuiElementButtonUnitAction(uiButton3);
		button.setPosition(32, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button");
		button.setTextureSelected("button_select");
		button.setText("3");
		button.setScript(null);
		this.add(button);
		
		button = new GuiElementButtonUnitAction(uiButton4);
		button.setPosition(97, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button");
		button.setTextureSelected("button_select");
		button.setText("4");
		button.setScript(null);
		this.add(button);
		
		button = new GuiElementButtonUnitAction(uiButton5);
		button.setPosition(162, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button");
		button.setTextureSelected("button_select");
		button.setText("5");
		button.setScript(null);
		this.add(button);
		
		GuiElementButton endTurn = new GuiElementButton(uiButtonEndTurn);
		endTurn.setPositionType(Enums.GuiPosition.BOTTOM_LEFT);
		endTurn.setPosition(5, -205);
		endTurn.setSize(72, 32);
		endTurn.setTexture(Const.imgButtonEndTurn);
		endTurn.setText("End turn");
		endTurn.setScript(new game_EndTurn());
		endTurn.setVisible(true);
		this.add(endTurn);
	}
}
