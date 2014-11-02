package gameobject.unit.stats;

import net.Message;
import net.Message.Prefix;
import interfaces.Sentble;
import items.Item;

public class UntiInventory implements Sentble {

	public int unitId;
	
	public int slots;
	public Item [] items;
	
	public UntiInventory(int unitId, int slots) {
		this.unitId = unitId;
		this.items = new Item[slots];
		this.slots = slots;
	}
	
	public boolean addItem(Item item){
		for(int i = 0; i < items.length; ++i){
			if(items[i] == null){
				items[i] = item;
				return true;
			}
		}
		return true;
	}
	
	public boolean removeItem(int slot){
		if(slot < items.length && slot >= 0){
			items[slot] = null;
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public Message toMessage() {
		String data = new String("");
		
		// write
		data += unitId + ":";
		data += slots + ":";
		
		for(Item item: items){
			if(item != null){
				data += item.type + ":" + item.value + ":" + item.icon + ":";
			}
			else{
				break;
			}
		}
		
		return new Message(Prefix.OBJ_IVENTORY, data);
	}

	@Override
	public Message toMessageUpdate(String field) {
		String data = new String("");
		
		data += unitId + ":" + field + ":";
		
		switch (field) {
			case "id":
				data += unitId;
				break;

			case "items":
				data += slots + ":";
				for(Item item: items){
					if(item != null){
						data += item.type + ":" + item.value + ":" + item.icon + ":";
					}
					else{
						break;
					}
				}
				break;
		}
		
		return new Message(Prefix.UPD_INVENTORY, data);
	}

	@Override
	public void buildObj(String[] data) {}
	@Override
	public void updateObj(String[] data) {}
}
