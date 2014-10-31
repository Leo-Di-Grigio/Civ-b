package map;

import java.util.HashSet;

public class Node {
	
	// Layers
	public byte height = 0;
	public byte geology = 0;
	
	// Units data
	public HashSet<Integer> gameObjects;
	
	public Node(){
		gameObjects = new HashSet<Integer>();
	}
}
