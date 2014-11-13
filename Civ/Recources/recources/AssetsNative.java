package recources;

import java.awt.Cursor;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import database.DB;
import engine.Engine;
import recources.nongl.Tile;
import main.Config;
import misc.Const;
import misc.Log;

public class AssetsNative extends Assets {

	private static HashMap<String, Tile> tiles;
	private static HashMap<String, Cursor> cursors;
	private static Font font;
	
	public AssetsNative() {
		super();
		tiles = new HashMap<String, Tile>();
		cursors = new HashMap<String, Cursor>();
	}
	
	private void loadGui(){
		// Icon
		addImage(Const.imgIcon, Tile.getTile(Const.assetsNative + "icon.ico"));
		
		// GUI ELEMENTS
		addImage(Const.imgNull, Tile.getTile(Const.assetsNative+"gui/null.png"));
		addImage(Const.imgVoid, Tile.getTile(Const.assetsNative+"gui/void.png"));
		addImage("null_selected", Tile.getTile(Const.assetsNative+"gui/null_selected.png"));
		
		// menu
		addImage(Const.imgMenu, Tile.getTile(Const.assetsNative + "menu.png"));
		
		// buttons
		addImage(Const.imgButton, Tile.getTile(Const.assetsNative+"gui/button.png"));
		addImage(Const.imgButtonSelected, Tile.getTile(Const.assetsNative+"gui/button_select.png"));
		
		addImage("button_menu", Tile.getTile(Const.assetsNative+"gui/button_menu.png"));
		addImage("button_menu_select", Tile.getTile(Const.assetsNative+"gui/button_menu_select.png"));
		
		// slider
		addImage("slider", Tile.getTile(Const.assetsNative+"gui/slider.png"));
		
		// panes
		addImage(Const.imgPane, Tile.getTile(Const.assetsNative+"gui/pane.png"));
		
		// cursor
		addImage(Const.imgSelect, Tile.getTile(Const.assetsNative + "cursor/cursor_nodeselect_draw.png"));
		
		// window
		addImage(Const.imgWindow, Tile.getTile(Const.assetsNative+"gui/window.png"));
		
		// chat
		addImage(Const.imgChat, Tile.getTile(Const.assetsNative + "gui/chat.png"));
		addImage(Const.imgChatSelected, Tile.getTile(Const.assetsNative + "gui/chat_selected.png"));
		
		// inventory
		addImage(Const.imgInventorySlot, Tile.getTile(Const.assetsNative + "gui/inventory_slot.png"));
		
		// tooltip
		addImage(Const.imgToolTip, Tile.getTile(Const.assetsNative + "gui/tooltip_background.png"));
		
		// tech
		addImage(Const.imgTechUnlearn, Tile.getTile(Const.assetsNative + "gui/tech_unlearn.png"));
		addImage(Const.imgTechLearn, Tile.getTile(Const.assetsNative + "gui/tech_learn.png"));
		addImage(Const.imgTechAvaible, Tile.getTile(Const.assetsNative + "gui/tech_avaible.png"));
	}
	
	private void loadGreyTiles(){
		int rgb = 0;
		for(int i = 0; i < 16; ++i){
			BufferedImage img = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
			
			if(i == 0){
				rgb = 0x0000FF;
			}
			else{
				rgb = (i*16 << 16)  + (i*16 << 8) + i*16;
			}
			
			for(int x = 0; x < 32; ++x){
				for(int y = 0; y < 32; ++y){
					img.setRGB(x, y, rgb); 
				}
			}
			
			addImage("grey"+i, new Tile(img));
		}
	}

	private void loadGeologyTiles() {
		int rgb = 0;
		
		for(int i = 0; i < 128; ++i){
			BufferedImage img = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
			
			if(i % 4 == 0)
				rgb = (int)i*16 << 16 + (int)i << 8 + (int)i;
			if(i % 4 == 1)
				rgb = (int)i << 16 + (int)i*16 << 8 + (int)i;
			if(i % 4 == 2)
				rgb = (int)i << 16 + (int)i << 8 + (int)i*16;
			if(i % 4 == 3)
				rgb = (int)i*16 << 16 + (int)i*16 << 8 + (int)i*16;
			
			for(int x = 0; x < 32; ++x){
				for(int y = 0; y < 32; ++y){
					img.setRGB(x, y, rgb); 
				}
			}
			
			addImage("geology"+i, new Tile(img));
		}
	}	
	
	private void loadTermalTiles(int tMin, int tMax) {
		int tic = Math.abs(tMax - tMin + 5) / 5;
		int colorTic = 255/tic;
		
		int t = tMin;
		int r = 255;
		int g = 0;
		int b = 0;
		
		// r,0,b -> 0,0,b
		for(int i = 0; i < tic; ++i){
			r -= colorTic;
			setColor(r, g, b, t++);
		}
		
		// 0,0,b -> 0,g,b
		for(int i = 0; i < tic; ++i){
			g += colorTic;
			setColor(r, g, b, t++);
		}
		
		// 0,g,b -> 0,g,0
		for(int i = 0; i < tic; ++i){
			b -= colorTic;
			setColor(r, g, b, t++);
		}
		
		// 0,g,0 -> r,g,0
		for(int i = 0; i < tic; ++i){
			r += colorTic;
			setColor(r, g, b, t++);
		}
		// r,g,0 -> r,0,0
		for(int i = 0; i < tic; ++i){
			g -= colorTic;
			setColor(r, g, b, t++);
		}
	}
	
	private void setColor(int r, int g, int b, int t){
		BufferedImage img = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		int a = 48;
		int col = (a << 24) + (r << 16) + (g << 8) + b;
		
		for(int x = 0; x < 32; ++x){
			for(int y = 0; y < 32; ++y){
				img.setRGB(x, y, col);
			}
		}
		
		addImage("temp"+t, new Tile(img));
	}
	
	private void loadCursors(){
		if(Config.os != "Linux"){
			Image img = null;
			Cursor cursor = null;
	
			img = Tile.getTile(Const.assetsNative + "cursor/cursor.png").getImage();
			cursor = Toolkit.getDefaultToolkit().createCustomCursor(img, new Point(0, 0), "blank cursor");
			cursors.put("cursor", cursor);
		
			img = Tile.getTile(Const.assetsNative + "cursor/null.png").getImage();
			cursor = Toolkit.getDefaultToolkit().createCustomCursor(img, new Point(0, 0), "blank cursor");
			cursors.put("null", cursor);
		
			setCursor("cursor");
		}
	}

	private void loadTerrain() {
		addImage(Const.imgTerrainWater, Tile.getTile(Const.assetsNative + "terrain/water.png"));
		addImage(Const.imgTerrainWaterBorder, Tile.getTile(Const.assetsNative + "terrain/waterBorder.png"));
		addImage(Const.imgTerrainLand, Tile.getTile(Const.assetsNative + "terrain/land.png"));
	}

	private void loadUnits() {
		addImage(Const.imgUnitPlayerAtlas, Tile.getTile(Const.assetsNative + "units/playerColor.png"));
		addImage(Const.imgUnitAvatar, Tile.getTile(Const.assetsNative + "units/avatar.png"));
		addImage(Const.imgUnitRecruit, Tile.getTile(Const.assetsNative + "units/unit_recruit.png"));
		addImage(Const.imgUnitCity, Tile.getTile(Const.assetsNative + "units/unit_city.png"));
	}
	
	private void loadActions() {
		// actions
		addImage(Const.imgActionMoveto, Tile.getTile(Const.assetsNative + "actions/action_moveto.png"));
		addImage(Const.imgActionCityBuild, Tile.getTile(Const.assetsNative + "actions/action_citybuild.png"));
		addImage(Const.imgActionBuildRecruit, Tile.getTile(Const.assetsNative + "actions/action_buildrecruit.png"));
		addImage(Const.imgActionMine, Tile.getTile(Const.assetsNative + "actions/action_mine.png"));
		addImage(Const.imgActionInventory, Tile.getTile(Const.assetsNative + "actions/action_inventory.png"));
		addImage(Const.imgActionInteract, Tile.getTile(Const.assetsNative + "actions/action_interact.png"));
		
		// interact actions
		addImage(Const.imgInteractAttack, Tile.getTile(Const.assetsNative + "actions/interact_attack.png"));
		addImage(Const.imgInteractTalk, Tile.getTile(Const.assetsNative + "actions/interact_talk.png"));
		addImage(Const.imgInteractWorkAt, Tile.getTile(Const.assetsNative + "actions/interact_workat.png"));
		addImage(Const.imgInteractRepair, Tile.getTile(Const.assetsNative + "actions/interact_repair.png"));
		addImage(Const.imgInteractBuildUpdate, Tile.getTile(Const.assetsNative + "actions/interact_build.png"));
		addImage(Const.imgInteractBuildCityBuilding, Tile.getTile(Const.assetsNative + "actions/interact_build.png"));
	}
	
	private void loadFont() throws FontFormatException, IOException {
		if(Config.os != "Linux"){
			font = Font.createFont(Font.TRUETYPE_FONT, new File(Config.classPath + Const.assetsNative + "ttf/PTC75F.ttf")).deriveFont(12f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		}
	}

	private void loadItems() {
		addImage(Const.imgItemRecource, Tile.getTile(Const.assetsNative + "items/recource.png"));
		addImage(Const.imgItemCampPack, Tile.getTile(Const.assetsNative + "items/camppack.png"));
	}

	@Override
	public void init() throws FontFormatException, IOException  {		
		loadGui();
		loadGreyTiles();
		loadGeologyTiles();
		loadTerrain();
		loadCursors();
		loadUnits();
		loadActions();
		loadFont();
		loadItems();
		
		System.gc();
		Log.debug("Assets Native tiles loaded: " + tiles.size());
		Log.debug("Assets Native cursors loaded: " + cursors.size());
	}
	
	@Override
	public void loadTemperatureColor(int tMin, int tMax) {
		loadTermalTiles(tMin, tMax);
	}
	
	@Override
	public Image getImage(String name) {
		if(tiles.containsKey(name)){
			return tiles.get(name).getImage();
		} 
		else {
			Log.err("tile " + name + " is not found");
			
			if(tiles.containsKey("null")){
				return tiles.get("null").getImage();
			}
			else{
				return null;
			}
		}
	}

	@Override
	public void addImage(String name, Tile tile) {
		tiles.put(name, tile);
	}

	@Override
	public void setCursor(String name) {
		if(Config.os != "Linux"){
			if(cursors.containsKey(name)){
				Engine.frame.getContentPane().setCursor(cursors.get(name));
			}
		}
	}
	
	@Override
	public Font getFont(){
		return font;
	}

	@Override
	public Image getItem(int itemIcon) {
		switch(itemIcon){
			case DB.itemRecource: return getImage(Const.imgItemRecource);
			case DB.itemCampPack: return getImage(Const.imgItemCampPack);
			default: return getImage(Const.imgNull);
		}
	}
}
