package scene.prepare;

import misc.Enums;
import script.gui.button.gui_button_ChangeScene;
import script.gui.button.gui_button_CreateTeam;
import script.gui.button.gui_button_JoinTeam;
import script.gui.multitable.gui_multitable_Select;
import script.gui.table.gui_table_Select;
import gui.GUI;
import gui.elements.GuiElementButton;
import gui.elements.GuiElementMultiTable;
import gui.elements.GuiElementTable;
import gui.misc.MultiTableLine;

public class scenegui_Prepare extends GUI {

	public scenegui_Prepare() {
		super();
		
		// GUI init
		GuiElementButton button = null;
		
		// Players Table
		GuiElementTable players = new GuiElementTable(2);
		players.setCollumns(2);
		players.setSize(400, 400);
		players.setPositionType(Enums.GuiPosition.CENTER);
		players.setPosition(150, 0);
		players.setVisible(true);
		players.setScript(new gui_table_Select());
		this.add("players", players);
		
		// Players Table
		GuiElementMultiTable players2 = new GuiElementMultiTable();
		players2.setSize(400, 400);
		players2.setPositionType(Enums.GuiPosition.CENTER_LEFT);
		players2.setTexture("pane");
		players2.setPosition(50, 0);
		players2.setVisible(true);
		players2.setScript(new gui_multitable_Select());
		this.add("players2", players2);
		
		// test data
		MultiTableLine line1 = new MultiTableLine("0");
		line1.addLine(new MultiTableLine("1"));
		
		MultiTableLine line2 = new MultiTableLine("2");
		line2.addLine(new MultiTableLine("3"));
		line2.addLine(new MultiTableLine("4"));
		
		MultiTableLine line3 = new MultiTableLine("5");
		line3.addLine(new MultiTableLine("6"));
		line3.addLine(new MultiTableLine("7"));
		
		MultiTableLine line4 = new MultiTableLine("8");
		MultiTableLine line41 = new MultiTableLine("9");
		MultiTableLine line42 = new MultiTableLine("10");
		MultiTableLine line411 = new MultiTableLine("11");
		MultiTableLine line412 = new MultiTableLine("12");
		MultiTableLine line421 = new MultiTableLine("13");
		MultiTableLine line422 = new MultiTableLine("14");
		
		line41.addLine(line411);
		line41.addLine(line412);
		line42.addLine(line421);
		line42.addLine(line422);
		line4.addLine(line41);
		line4.addLine(line42);
		
		MultiTableLine line5 = new MultiTableLine("15");
		line5.addLine(new MultiTableLine("16"));
		line5.addLine(new MultiTableLine("17"));
		line5.addLine(new MultiTableLine("18"));
		
		
		MultiTableLine line6 = new MultiTableLine("19");
		line6.addLine(new MultiTableLine("20"));
		line6.addLine(new MultiTableLine("21"));
		line6.addLine(new MultiTableLine("22"));
		
		MultiTableLine line7 = new MultiTableLine("23");
		
		for(int i = 23; i < 100; ++i){
			line7.addLine(new MultiTableLine("" + i));
		}
		
		players2.addLine(line1);
		players2.addLine(line2);
		players2.addLine(line3);
		players2.addLine(line4);
		players2.addLine(line5);
		players2.addLine(line6);
		players2.addLine(line7);
		
		// Join the game		
		button = new GuiElementButton();
		button.setPosition(430, -185);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Join, MF!");
		button.setVisible(true);
		button.setScript(new gui_button_ChangeScene(Enums.Scene.GAME));
		this.add("new_game", button);
		
		// Create Team
		button = new GuiElementButton();
		button.setPosition(430, -138);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Create new Team");
		button.setVisible(true);
		button.setScript(new gui_button_CreateTeam());
		this.add("create_team", button);
		
		// Join the Team
		button = new GuiElementButton();
		button.setPosition(430, -100);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Join Team");
		button.setVisible(true);
		button.setScript(new gui_button_JoinTeam());
		this.add("join_team", button);
		
		// Back
		button = new GuiElementButton();
		button.setPosition(430, 185);
		button.setSize(128, 32);
		button.setPositionType(Enums.GuiPosition.CENTER);
		button.setTexture("button_menu");
		button.setTextureSelected("button_menu_select");
		button.setText("Back");
		button.setVisible(true);
		button.setScript(new gui_button_LeaveGame());
		this.add("back", button);
	}
}
