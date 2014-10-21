package map;

import java.util.HashSet;
import java.util.Set;

public class Node {
	
	// Layers
	public byte height = 0;
	public byte geology = 0;
	
	// Units data
	public Set<Integer> units;
	
	public Node(){
		units = new HashSet<Integer>();
	}
}
