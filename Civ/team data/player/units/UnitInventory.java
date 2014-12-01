package player.units;

import net.Message;
import interfaces.Sentble;

public class UnitInventory implements Sentble {

	public int unitId;
	public int slots;
	public Item [] items;
	
	public UnitInventory(int size) {
		items = new Item[size];
	}
	
	public class Item {
		public int type;
		public int value;
		public int icon;
		
		public Item(int type, int value, int icon){
			this.type = type;
			this.value = value;
			this.icon = icon;
		}
	};
	
	@Override
	public Message toMessage() { return null; }
	@Override
	public Message toMessageUpdate(String field) { return null;	}

	@Override
	public void buildObj(String [] arr) {
		this.unitId = Integer.parseInt(arr[0]);
		this.slots = Integer.parseInt(arr[1]);
		
		if(slots != 0){
			this.items = new Item[slots];
			
			for(int i = 0; i < slots; ++i){
				if(4 + i*3 < arr.length){
					int type = Integer.parseInt(arr[2 + i*3]);
					int value = Integer.parseInt(arr[3 + i*3]);
					int icon = Integer.parseInt(arr[4 + i*3]);
					items[i] = new Item(type, value, icon);
				}
				else{
					break;
				}
			}
		}
	}

	@Override
	public void updateObj(String [] arr) {
		this.unitId = Integer.parseInt(arr[0]);
		
		String field = arr[1];
		
		switch(field){
			case "id": 
				this.unitId = Integer.parseInt(arr[2]); 
				break;
			
			case "items":
				this.slots = Integer.parseInt(arr[2]);
				updateItems(arr);
				break;
		}
	}
	
	private void updateItems(String [] arr) {
		this.items = new Item[slots];
		
		for(int i = 0; i < slots; ++i){
			if(4 + i*3 < arr.length){
				int type = Integer.parseInt(arr[3 + i*3]);
				int value = Integer.parseInt(arr[4 + i*3]);
				int icon = Integer.parseInt(arr[5 + i*3]);
				items[i] = new Item(type, value, icon);
			}
			else{
				break;
			}
		}
	}
	
	public boolean haveItem(int type) {
		for(int i = 0; i < items.length; ++i){
			Item item = items[i];
			
			if(item != null && item.type == type){
				return true;
			}
		}
		
		return false;
	}
}
