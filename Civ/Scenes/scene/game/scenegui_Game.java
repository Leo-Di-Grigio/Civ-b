package scene.game;

import java.awt.Color;

import misc.Const;
import misc.Enums;
import recources.Recources;
import script.gui.icon.gui_icon_Test;
import script.gui.minimap.gui_minimap_MoveCamera;
import gui.GUI;
import gui.elements.GuiElementButton;
import gui.elements.GuiElementCursor;
import gui.elements.GuiElementIcon;
import gui.elements.GuiElementMinimap;
import gui.elements.GuiElementPane;
import gui.elements.GuiElementTitle;

public class scenegui_Game extends GUI {

	public scenegui_Game() {
		super();
		
		// cursor
		GuiElementButton button = null;
		GuiElementCursor cursor = new GuiElementCursor("cursor");
		this.add(cursor);
		
		// minimap
		GuiElementMinimap minimap = new GuiElementMinimap("minimap");
		minimap.setSize(300, 200);
		minimap.setPosition(5, 0);
		minimap.setPositionType(Enums.GuiPosition.BOTTOM_LEFT);
		minimap.setTexture("pane");
		minimap.setMinimapTexture(Recources.getImage(Const.imgMinimap));
		minimap.setVisible(true);
		minimap.setScript(new gui_minimap_MoveCamera());
		this.add(minimap);
		
		// info pane
		GuiElementPane pane = new GuiElementPane("infopane");
		pane.setSize(300, 200);
		pane.setPosition(-5, 0);
		pane.setPositionType(Enums.GuiPosition.BOTTOM_RIGHT);
		pane.setTexture("pane");
		pane.setTextureSelected("pane");
		pane.setVisible(true);
		pane.setScript(null);
		
		// info icon
		GuiElementIcon icon = new GuiElementIcon("icon");
		icon.setSize(32, 32);
		icon.setPosition(0, 0);
		icon.setPositionType(Enums.GuiPosition.TOP_LEFT);
		icon.setVisible(true);
		icon.setScript(new gui_icon_Test());
		pane.addElement("icon", icon);
		
		// info string
		GuiElementTitle title = new GuiElementTitle("title-0");
		title.setText("0");
		title.setColor(Color.black);
		title.setPosition(36, 12);
		title.setPositionType(Enums.GuiPosition.TOP_LEFT);
		pane.addElement("title-0", title);
		

		title = new GuiElementTitle("title-1");
		title.setText("1");
		title.setColor(Color.black);
		title.setPosition(36, 24);
		title.setPositionType(Enums.GuiPosition.TOP_LEFT);
		pane.addElement("title-1", title);
		
		title = new GuiElementTitle("title-2");
		title.setText("2");
		title.setColor(Color.black);
		title.setPosition(36, 36);
		title.setPositionType(Enums.GuiPosition.TOP_LEFT);
		pane.addElement("title-2", title);
		
		this.add(pane);
		
		// button-0
		button = new GuiElementButton("button-0");
		button.setPosition(-163, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button");
		button.setTextureSelected("button_select");
		button.setText("0");
		button.setVisible(true);
		button.setScript(null);
		this.add(button);
		
		button = new GuiElementButton("button-1");
		button.setPosition(-98, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button");
		button.setTextureSelected("button_select");
		button.setText("1");
		button.setVisible(true);
		button.setScript(null);
		this.add(button);
		
		button = new GuiElementButton("button-2");
		button.setPosition(-33, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button");
		button.setTextureSelected("button_select");
		button.setText("2");
		button.setVisible(true);
		button.setScript(null);
		this.add(button);
		
		button = new GuiElementButton("button-3");
		button.setPosition(32, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button");
		button.setTextureSelected("button_select");
		button.setText("3");
		button.setVisible(true);
		button.setScript(null);
		this.add(button);
		
		button = new GuiElementButton("button-4");
		button.setPosition(97, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button");
		button.setTextureSelected("button_select");
		button.setText("4");
		button.setVisible(true);
		button.setScript(null);
		this.add(button);
		
		button = new GuiElementButton("button-5");
		button.setPosition(162, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button");
		button.setTextureSelected("button_select");
		button.setText("5");
		button.setVisible(true);
		button.setScript(null);
		this.add(button);
	}
}
