package scenedata.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import mapbuilder.GameMapGenerator;
import misc.Const;
import misc.Environment;
import recources.Recources;
import recources.nongl.Tile;

public class GameMap {
	
	public int sizeX;
	public int sizeY;
	
	public int drawNodes = 0;
	
	public Node [][] nodes;
	
	public GameMap(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		
		nodes = GameMapGenerator.buildMap(Const.mapSizeX, Const.mapSizeY);

		generateMinimapImage();
		System.gc();
	}

	public void draw(Graphics g) {
		int minX = Environment.cameraX;
		int minY = Environment.cameraY;
		
		int w = Environment.width/32;
		int h = Environment.height/32;
		
		int maxY = minY + h + 1;
		
		if(sizeX - minX > w){
			// one-piece map
			int maxX = minX + w + 1;
			
			for(int x = 0, i = minX; i < maxX; ++i, ++x){
				for(int y = 0, j = minY; j < maxY; ++j, ++y){
					if(y < sizeY && j < sizeY){
						draw(g, i, j, x, y);
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
						draw(g, i, j, x, y);
					}
				}
				
				for(int i = 0; i < maxX; ++i, ++x){
					if(y < sizeY && j < sizeY){
						draw(g, i, j, x, y);
					}
				}
			}
		}
	}
	
	private void draw(Graphics g, int i, int j, int x, int y){
		g.drawImage(Recources.getImage("grey"+nodes[i][j].height), x*32, y*32, 32, 32, null);
	}
	
	private void generateMinimapImage(){
		BufferedImage img = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
		int rgb = 0;
		
		for(int i = 0; i < sizeX; ++i){
			for(int j = 0; j < sizeY; ++j){

				if(nodes[i][j].height == 0){
					rgb = 0x0000FF;
				}
				else{
					rgb = ((int)nodes[i][j].height*16 << 16)  + ((int)nodes[i][j].height*16 << 8) + (int)nodes[i][j].height*16;
				}
			
				img.setRGB(i, j, rgb);
			}
		}
		
		Recources.addImage("minimap_height", new Tile(img));
	}
}
