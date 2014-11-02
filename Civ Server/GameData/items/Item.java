package items;

import database.DB;

public class Item {
	
	public int type;
	public int value;
	public int icon;
	
	public Item(int type, int value) {
		this.type = type;
		this.value = value;
		this.icon = DB.getItemIcon(type);
	}
}
