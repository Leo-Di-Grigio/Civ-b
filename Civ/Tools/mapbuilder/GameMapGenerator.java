package mapbuilder;

import misc.Tools;
import scenedata.game.Node;

public class GameMapGenerator {
	
	public static Node [][] buildMap(int sizeX, int sizeY){
		Node [][] nodes = new Node[sizeX][sizeY];
		
		for(int i = 0; i < sizeX; ++i){
			for(int j = 0; j < sizeY; ++j){
				nodes[i][j] = new Node();
			}
		}
		
		for(int i = 0; i < sizeX*sizeY/20; ++i){
			nodes[Tools.rand(0, sizeX - 1)][Tools.rand(0, sizeY - 1)].height = (byte)Tools.rand(0, 15);
		}
		
		return nodes;
	}
}
