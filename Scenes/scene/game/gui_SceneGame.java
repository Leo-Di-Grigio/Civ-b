package scene.game;

import java.awt.Color;

import misc.Enums;
import recources.Recources;
import script.gui.minimap.gui_minimap_MoveCamera;
import gui.GUI;
import gui.elements.GuiElementButton;
import gui.elements.GuiElementCursor;
import gui.elements.GuiElementIcon;
import gui.elements.GuiElementMinimap;
import gui.elements.GuiElementPane;
import gui.elements.GuiElementTitle;

public class gui_SceneGame extends GUI {

	public gui_SceneGame() {
		super();
		
		// cursor
		GuiElementCursor cursor = new GuiElementCursor();
		this.add("cursor", cursor);
		
		// minimap
		GuiElementMinimap minimap = new GuiElementMinimap();
		minimap.setSize(300, 200);
		minimap.setPosition(5, 0);
		minimap.setPositionType(Enums.GuiPosition.BOTTOM_LEFT);
		minimap.setTexture("pane");
		minimap.setMinimapTexture(Recources.getImage("minimap_height"));
		minimap.setVisible(true);
		minimap.setScript(new gui_minimap_MoveCamera());
		this.add("minimap1", minimap);
		
		// info icon
		GuiElementIcon icon = new GuiElementIcon();
		icon.setSize(32, 32);
		icon.setPosition(0, 0);
		icon.setPositionType(Enums.GuiPosition.TOP_LEFT);
		icon.setVisible(true);
		
		// info string
		GuiElementTitle title = new GuiElementTitle();
		title.setText("Test");
		title.setColor(Color.black);
		title.setPosition(36, 15);
		title.setPositionType(Enums.GuiPosition.TOP_LEFT);
		
		// info pane
		GuiElementPane pane = new GuiElementPane();
		pane.setSize(300, 200);
		pane.setPosition(-5, 0);
		pane.setPositionType(Enums.GuiPosition.BOTTOM_RIGHT);
		pane.setTexture("pane");
		pane.setTextureSelected("pane");
		pane.setVisible(true);
		pane.setScript(null);
		
		pane.addElement("icon", icon);
		pane.addElement("title", title);
		this.add("infopane", pane);
		
		// buttons
		GuiElementButton button = null;
		
		// button-0
		button = new GuiElementButton();
		button.setPosition(-163, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button");
		button.setTextureSelected("button_select");
		button.setText("0");
		button.setVisible(true);
		button.setScript(null);
		this.add("button-0", button);
		
		button = new GuiElementButton();
		button.setPosition(-98, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button");
		button.setTextureSelected("button_select");
		button.setText("1");
		button.setVisible(true);
		button.setScript(null);
		this.add("button-1", button);
		
		button = new GuiElementButton();
		button.setPosition(-33, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button");
		button.setTextureSelected("button_select");
		button.setText("2");
		button.setVisible(true);
		button.setScript(null);
		this.add("button-2", button);
		
		button = new GuiElementButton();
		button.setPosition(32, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button");
		button.setTextureSelected("button_select");
		button.setText("3");
		button.setVisible(true);
		button.setScript(null);
		this.add("button-3", button);
		
		button = new GuiElementButton();
		button.setPosition(97, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button");
		button.setTextureSelected("button_select");
		button.setText("4");
		button.setVisible(true);
		button.setScript(null);
		this.add("button-4", button);
		
		button = new GuiElementButton();
		button.setPosition(162, 0);
		button.setSize(64, 64);
		button.setPositionType(Enums.GuiPosition.BOTTOM_CENTER);
		button.setTexture("button");
		button.setTextureSelected("button_select");
		button.setText("5");
		button.setVisible(true);
		button.setScript(null);
		this.add("button-5", button);
	}
}
