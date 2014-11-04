package scenedata.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashSet;

import player.units.Unit;
import player.units.UnitsMng;
import builder.GameMapGenerator;
import misc.Const;
import misc.Enums;
import misc.Environment;
import recources.Recources;
import recources.nongl.Tile;

public class GameMap {
	
	// sizes
	private static final int nodeX = 32;
	private static final int nodeY = 32;
	private static final int atlasX = 32;
	private static final int atlasY = 32;
	
	// public params
	public long seed;
	public int sizeX;
	public int sizeY;
	
	// draw
	public int drawNodes = 0;
	public Enums.MapDrawMode drawMode = Enums.MapDrawMode.TERRAIN;
	protected Image imgTerrainWater;
	protected Image imgTerrainWaterBorder;
	protected Image imgTerrainLand;
	
	// data
	public UnitsMng units;
	public Node [][] map;
	public byte [][] height;
	
	// temperature
	public int tMin;
	public int tMax;
	
	public GameMap(long seed, int sizeX, int sizeY, int tMin, int tMax) {
		this.seed = seed;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		
		this.tMin = tMin;
		this.tMax = tMax;
		Recources.loadTemperatureColor(tMin, tMax);
		
		loadImg();
		
		generateMap();
		generateTerrain();
		generateMinimapImage();
		
		System.gc();
	}
	
	private void loadImg(){
		this.imgTerrainWater = Recources.getImage(Const.imgTerrainWater);
		this.imgTerrainWaterBorder = Recources.getImage(Const.imgTerrainWaterBorder);
		this.imgTerrainLand = Recources.getImage(Const.imgTerrainLand);
	}
	
	private void generateMap(){
		map = new Node[sizeX][sizeY];
		height = GameMapGenerator.buildHeightMap(seed, sizeX, sizeY);
		byte [][] geology = GameMapGenerator.buildGeologyMap(seed, sizeX, sizeY);
		byte [][] termal = GameMapGenerator.buildTermalMap(seed, sizeX, sizeY, tMin, tMax);
		
		for(int i = 0; i < sizeX; ++i){
			for(int j = 0; j < sizeY; ++j){
				map[i][j] = new Node();
				map[i][j].height = height[i][j];
				map[i][j].geology = geology[i][j];
				map[i][j].termal = termal[i][j];
			}
		}
	}
	
	private void generateTerrain(){
		for(int i = 0; i < sizeX; ++i){
			for(int j = 0; j < sizeY; ++j){
				// terrain atlas
				if(map[i][j].height == 0){
					map[i][j].terrainType = Enums.Terrain.WATER;
				}
				else{
					map[i][j].terrainType = Enums.Terrain.LAND;
				}
			}
		}
		
		for(int i = 0; i < sizeX; ++i){
			for(int j = 0; j < sizeY; ++j){				
				checkNodes(i, j);
			}
		}
	}

	private int getX(int i){
		// used for cycled map
		if(i < 0){
			return sizeX + i - 1;
		}
		
		if(i >= sizeX){
			return sizeX - i;
		}
		
		return i;
	}
	
	private boolean checkNode(Node self, Node node){
		//nodes[getX(i-1)][j-1].terrainType != nodes[i][j].terrainType
		
		if((self.terrainType != node.terrainType) || (node.height - self.height > 1)){
			return true;
		}
		else{
			return false;
		}
	}
	
	private void checkNodes(int i, int j){
		
		int data = 0;
		int counter = 0;
		
		// around nodes
		// 0 1 2
		// 3 4 5
		// 6 7 8
		
		for(int y = i - 1; y <= i + 1; ++y){
			for(int x = j - 1; x <= j + 1; ++x){

			switch(counter){
				case 0:
					if(j - 1 >= 0 && checkNode(map[getX(i-1)][j-1], map[i][j])) {
						data += 0b1;
					}
					break;
					
				case 1:
					if(j - 1 >= 0 && checkNode(map[i][j-1], map[i][j])) {
						data += 0b10;
					}
					break;
					
				case 2:
					if(j - 1 >= 0 && checkNode(map[getX(i+1)][j-1], map[i][j])) {
						data += 0b100;
					}
					break;
					
				case 3:
					if(checkNode(map[getX(i-1)][j], map[i][j])) {
						data += 0b1000;
					}
					break;
					
				case 4:
					// skip self node
					break;
					
				case 5:
					if(checkNode(map[getX(i+1)][j], map[i][j])) {
						data += 0b10000;
					}
					break;
					
				case 6:
					if(j + 1 < sizeY && checkNode(map[getX(i-1)][j+1], map[i][j])) {
						data += 0b100000;
					}
					break;
					
				case 7:
					if(j + 1 < sizeY && checkNode(map[i][j+1], map[i][j])){
						data += 0b1000000;
					}
					break;
				case 8:
					if(j + 1 < sizeY && checkNode(map[getX(i+1)][j+1], map[i][j])){
						data += 0b10000000;
					}
					break;
				}
					
				counter++;
			}
		}
		
		map[i][j].border = data;
	}
	
	public void draw(Graphics g, long tic) {
		int minX = Environment.cameraX;
		int minY = Environment.cameraY;
		
		int w = Environment.frameSizeX/nodeX;
		int h = Environment.frameSizeY/nodeY;
		
		int maxY = minY + h + 1;
		
		if(sizeX - minX > w){
			// one-piece map
			int maxX = minX + w + 1;
			
			for(int x = 0, i = minX; i < maxX; ++i, ++x){
				for(int y = 0, j = minY; j < maxY; ++j, ++y){
					if(y < sizeY && j < sizeY){
						draw(g, i, j, x, y, tic);
					}
				}
			}
		}
		else{
			// two-piece map
			int maxX = w + (sizeX - minX);
			
			for(int y = 0, j = minY; j < maxY; ++j, ++y){
				int x = 0;
				
				for(int i = minX; i < sizeX; ++i, ++x){
					if(y < sizeX && j < sizeY){
						draw(g, i, j, x, y, tic);
					}
				}
				
				for(int i = 0; i < maxX; ++i, ++x){
					if(y < sizeY && j < sizeY){
						draw(g, i, j, x, y, tic);
					}
				}
			}
		}
	}
	
	private void draw(Graphics g, int i, int j, int x, int y, long tic){
		switch(drawMode) {
			case HEIGHT:	
				g.drawImage(Recources.getImage("grey"+map[i][j].height), x*nodeX, y*nodeY, null);
				break;
			
			case TERMAL:
			case TERRAIN:
				// draw Terrain
				if(map[i][j].terrainType == Enums.Terrain.WATER){
					g.drawImage(imgTerrainWater, x*nodeX, y*nodeY, null);
				}
				else{
					drawNode(g, imgTerrainLand, i, j, x, y);
				}
			
				// draw border
				if(map[i][j].border != 0){
					drawBorder(g, imgTerrainWaterBorder, i, j, x, y);
				}
				
				if(drawMode == Enums.MapDrawMode.TERMAL){
					// draw termal map
					g.drawImage(Recources.getImage("temp"+map[i][j].termal), x*nodeX, y*nodeY, null);
				}
				break;
				
			case GEOLOGY:
				g.drawImage(Recources.getImage("geology" + map[i][j].geology), x*nodeX, y*nodeY, null);
				break;
		}
		
		// draw Waypoints
		if(map[i][j].haveWaypoints()){
			g.drawImage(Recources.getImage(Const.imgWaypoint), x*nodeX, y*nodeY, 32, 32, null);
		}
		
		// draw Units
		HashSet<Integer> nodeunits = map[i][j].getAll();
		
		for(Integer unitId: nodeunits){
			Unit unit = this.units.getUnit(unitId);
			unit.draw(g, x*nodeX, y*nodeY, tic);
		}
	}
	
	private void drawNode(Graphics g, Image atlas, int i, int j, int x, int y){
		drawFromAtlas(g, atlas, map[i][j].height, x, y);
	}

	private void drawBorder(Graphics g, Image atlas, int i, int j, int x, int y){
		int data = map[i][j].border;
		
		for(int bit = 1, counter = 0; counter < 8; ++counter, bit = bit << 1){
			if((data & bit) > 0){
				drawFromAtlas(g, imgTerrainWaterBorder, counter, x, y);
			}
		}
	}
	
	private void drawFromAtlas(Graphics g, Image atlas, int borderId, int x, int y){
		
		g.drawImage(atlas, x*nodeX, 
				 		   y*nodeY,
				 		   
				 		   x*nodeX + nodeX,
				 		   y*nodeY + nodeY,
				 
				 	       borderId * atlasX, 
				 		   0,
				 		   borderId * atlasX + atlasX,
				 		   atlasY,
				 		   
				 		   null);
	}
	
	private void generateMinimapImage(){
		BufferedImage img = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
		BufferedImage imgHeight = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
		BufferedImage imgGeology = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
		BufferedImage imgTemperature = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
		
		int rgb = 0;
		int rgbHeight = 0;
		int rgbGeology = 0;
		int rgbTemperature = 0;
		
		for(int i = 0; i < sizeX; ++i){
			for(int j = 0; j < sizeY; ++j){
				// height
				if(map[i][j].height == 0){
					rgb = 0x0000FF;
					rgbHeight = 0x0000FF;
				}
				else{
					rgb = 0x2CA757; // green
					rgbHeight = ((int)map[i][j].height*16 << 16)  + ((int)map[i][j].height*16 << 8) + (int)map[i][j].height*16;
				}
				
				// geology
				int geology = map[i][j].geology;
				if(geology % 4 == 0)
					rgbGeology = (int)geology*16 << 16 + (int)geology << 8 + (int)geology;
				if(geology % 4 == 1)
					rgbGeology = (int)geology << 16 + (int)geology*16 << 8 + (int)geology;
				if(geology % 4 == 2)
					rgbGeology = (int)geology << 16 + (int)geology << 8 + (int)geology*16;
				if(geology % 4 == 3)
					rgbGeology = (int)geology*16 << 16 + (int)geology*16 << 8 + (int)geology*16;
				
				// temperature
				BufferedImage tmp = (BufferedImage)(Recources.getImage("temp" + map[i][j].termal));
				rgbTemperature = tmp.getRGB(0, 0);
				
				// set colors
				img.setRGB(i, j, rgb);
				imgHeight.setRGB(i, j, rgbHeight);
				imgGeology.setRGB(i, j, rgbGeology);
				imgTemperature.setRGB(i, j, rgbTemperature);
			}
		}
		
		Recources.addImage(Const.imgMinimap, new Tile(img));
		Recources.addImage(Const.imgMinimapHeight, new Tile(imgHeight));
		Recources.addImage(Const.imgMinimapGeology, new Tile(imgGeology));
		Recources.addImage(Const.imgMinimapTemperature, new Tile(imgTemperature));
	}

	public void drawModeSwitch() {
		
		switch(drawMode){
			case TERRAIN:
				drawMode = Enums.MapDrawMode.HEIGHT;
				break;
				
			case HEIGHT:
				drawMode = Enums.MapDrawMode.GEOLOGY;
				break;
				
			case GEOLOGY:
				drawMode = Enums.MapDrawMode.TERMAL;
				break;
				
			case TERMAL:
				drawMode = Enums.MapDrawMode.TERRAIN;
				break;
		}
	}
}
