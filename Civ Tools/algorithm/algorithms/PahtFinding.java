package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import misc.ToolsEnums;

public class PahtFinding {	
	
	// key Nodes
	public Node startNode;
	public Node endNode;

	// consts
	private ToolsEnums.UnitMovementType movementType;
	private static final int straightCost = 10;
	private static final int diagCost = 14; // may be 14 if diagCost != strightCost
	
	// lists
	protected ArrayList<Node> openList;
	protected ArrayList<Node> closedList;
	
	protected boolean isFinded;
	protected ArrayList<Point> path;
	
	// map
	protected int mapSizeX;
	protected int mapSizeY;
	protected byte [][] map;

	protected class Node {
		protected byte height;
		public int x;
		public int y;
		
		protected int f; // path cost
		protected int h; // heuristic value
		protected int g; // stright cost
		
		protected int costMultipiller = 1;
		
		protected Node parent;
		
		protected Node(int x, int y, byte type){
			this.x = x;
			this.y = y;
			this.height = type;
		}
		
		// diagonal heuristic
		protected int h() {
			// need fix to cycling map
			int dx = Math.abs(this.x - endNode.x); 
			int dy = Math.abs(this.y - endNode.y);
			// 
			
			int diag = Math.min(dx, dy);
			int straight = dx + dy;
			
			return diagCost * diag + straightCost * (straight - 2 * diag);
		}
		
		protected void g(){
			this.g = straightCost;
		}
		
		protected boolean compare(Node node){
			if(this.x == node.x && this.y == node.y){
				return true;
			}
			else{
				return false;
			}
		}
		
		protected void setValues(int g, int h, int f){
			this.g = g;
			this.h = h;
			this.f = f;
		}
	}

	public ArrayList<Point> getPath(int x, int y, int toX, int toY, ToolsEnums.UnitMovementType movementType, byte [][] map, int mapSizeX, int mapSizeY){
		// init data
		this.movementType = movementType;
		this.map = map;
		openList = new ArrayList<Node>();
		closedList = new ArrayList<Node>();
		
		endNode = new Node(toX, toY, map[getX(toX)][toY]);
		startNode = new Node(x, y, map[getX(x)][y]);
		
		if(endNode.compare(startNode)){
			return null;
		}
		
		startNode.g = 0;
		startNode.h = startNode.h();
		startNode.f = startNode.g + startNode.h;
	
		// begin A*
		search();
		
		return path;
	}
	
	private int getX(int i){
		// used for cycled map
		if(i < 0){
			return mapSizeX + i - 1;
		}
		
		if(i >= mapSizeX){
			return mapSizeX - i;
		}
		
		return i;
	}
	
	private void search(){
		Node node = startNode;
		
		while(!node.compare(endNode)){
			
			int startY = Math.max(0, node.y - 1);
			int endY = Math.min(mapSizeY - 1, node.y + 1);
			
			for(int i = node.x - 1; i <= node.x + 1; ++i){
				for(int j = startY; j <= endY; ++j){
					Node test = new Node(i, j, map[getX(i)][j]);
					
					if(test.compare(endNode)){
						test = endNode;
					}
					
					if(test.compare(node) ||
					   !movementType.isPassable(test.height) ||
					   !isPassable(map[getX(test.x)][test.y], map[getX(node.x)][node.y]) )
					{
						continue;
					}
					
					int cost = straightCost;
					if(!((node.x == test.x) || (node.y == test.y))){
						cost = diagCost;
					}
					
					int g = node.g + cost * test.costMultipiller;
					int h = test.h;
					int f = g + h;
					
					if(isOpen(test) || isClosed(test)){
						if(test.f > f){
							test.setValues(g, h, f);
							test.parent = node;
						}
					}
					else{
						test.setValues(g, h, f);
						test.parent = node;
						openList.add(test);
					}
				}
			}
			
			closedList.add(node);
			
			if(openList.size() == 0){
				this.isFinded = false;
				return;
			}
			
			Collections.sort(openList, new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					if(o1.f < o2.f){
				    	return -1;
				    }
				    else{
				    	if(o1.f > o2.f) 
				    		return 1;
				    	else 
				    		return 0;
				    }
				}
			});
			
			node = openList.get(0);
			openList.remove(0);
		}

		buildPath();
		this.isFinded = true;
	}
	
	private void buildPath(){
		path = new ArrayList<Point>();
		Node node = endNode;
		path.add(new Point(node.x, node.y));
		
		while(node != null && !node.compare(startNode)){
			node = node.parent;
			
			if(!node.compare(startNode)){
				path.add(0, new Point(node.x, node.y));
			}
		}
		
		// clear
		openList = null;
		closedList = null;
	}
	
	private boolean isOpen(Node node){
		for(Node item: openList){
			if(item.compare(node)){
				return true;
			}
		}
		return false;
	}
	
	private boolean isClosed(Node node){
		for(Node item: closedList){
			if(item.compare(node)){
				return true;
			}
		}
		return false;
	}
	
	private boolean isPassable(byte heightFrom, byte heightTo){
		if(Math.abs(heightFrom - heightTo) > 1){
			return false;
		}
		else{
			return true;
		}
	}
}
