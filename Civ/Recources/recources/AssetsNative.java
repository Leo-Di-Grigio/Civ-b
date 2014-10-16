package recources;

import java.awt.Cursor;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import engine.Engine;
import recources.nongl.Tile;
import misc.Const;
import misc.Log;

public class AssetsNative extends Assets {

	private static HashMap<String, Tile> tiles;
	private static HashMap<String, Cursor> cursors;
	public static Font Font;
	public AssetsNative() throws FontFormatException, IOException{
		super();
		tiles = new HashMap<String, Tile>();
		cursors = new HashMap<String, Cursor>();
		Font=java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, AssetsNative.class.getResourceAsStream("..\\assets\\native\\Font.ttf"));
	}
	
	private void loadGui(){
		// GUI ELEMENTS
		addImage("null", Tile.getTile(Const.assetsNative+"gui\\null.png"));
		addImage("null_selected", Tile.getTile(Const.assetsNative+"gui\\null_selected.png"));
		
		// menu
		addImage(Const.imgMenu, Tile.getTile("recources\\assets\\menu.png"));
		
		// buttons
		addImage("button", Tile.getTile(Const.assetsNative+"gui\\button.png"));
		addImage("button_select", Tile.getTile(Const.assetsNative+"gui\\button_select.png"));
		
		addImage("button_menu", Tile.getTile(Const.assetsNative+"gui\\button_menu.png"));
		addImage("button_menu_select", Tile.getTile(Const.assetsNative+"gui\\button_menu_select.png"));
		
		// panes
		addImage("pane", Tile.getTile(Const.assetsNative+"gui\\pane.png"));
		
		// cursor
		addImage("cursor_nodeselect", Tile.getTile(Const.assetsNative + "cursor\\cursor_nodeselect_draw.png"));
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
	
	private void loadCursors(){
		Image img = null;
		Cursor cursor = null;
		
		img = Tile.getTile(Const.assetsNative + "cursor\\cursor.png").getImage();
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(img, new Point(0, 0), "blank cursor");
		cursors.put("cursor", cursor);
		
		img = Tile.getTile(Const.assetsNative + "cursor\\null.png").getImage();
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(img, new Point(0, 0), "blank cursor");
		cursors.put("null", cursor);
		
		// set default
		setCursor("cursor");
	}

	private void loadTerrain() {
		addImage(Const.imgTerrainWater, Tile.getTile(Const.assetsNative + "terrain\\water.png"));
		addImage(Const.imgTerrainWaterBorder, Tile.getTile(Const.assetsNative + "terrain\\waterBorder.png"));
		addImage(Const.imgTerrainLand, Tile.getTile(Const.assetsNative + "terrain\\land.png"));
	}

	private void loadUnits() {
		addImage(Const.imgUnitAvatar, Tile.getTile(Const.assetsNative + "units\\avatar.png"));
	}
	
	@Override
	public void init() {		
		loadGui();
		loadGreyTiles();
		loadTerrain();
		loadCursors();
		loadUnits();
		
		System.gc();
		Log.debug("Assets Native tiles loaded: " + tiles.size());
		Log.debug("Assets Native cursors loaded: " + cursors.size());
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
		if(cursors.containsKey(name)){
			Engine.frame.getContentPane().setCursor(cursors.get(name));
		}
	}
}
