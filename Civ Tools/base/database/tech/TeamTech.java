package database.tech;

import java.util.HashMap;

public class TeamTech {
	
	public Tech root;
	protected HashMap<String, Tech> techList;
	protected HashMap<Integer, Tech> techListId;
	
	public TeamTech(){
		techList = new HashMap<String, Tech>();
		techListId = new HashMap<Integer, Tech>();
		root = new Tech("root", 0);
	}
	
	public Tech getTech(String title){
		return techList.get(title);
	}
	
	public Tech getTech(int id){
		return techListId.get(id);
	}

	public void learn(int id) {
		techListId.get(id).learn();
	}

	public HashMap<Integer, Tech> getList() {
		return techListId;
	}
	
	public String getData() {
		String data = "";
		
		for(Tech tech: techListId.values()){
			if(tech.learned()){
				data += "1";
			}
			else{
				data += "0";
			}
		}
		
		return data;
	}

	public void setData(String data) {
		for(int i = 0; i < data.length(); ++i){
			char code = data.charAt(i);
			
			if(code == '1'){
				techListId.get(i).learn();
			}
		}
	}

	public int learnPrehistory() {
		for(Tech tech: techListId.values()){
			if(!tech.learned()){
				for(Tech parent: tech.getParents()){
					if(!parent.learned()){
						return -1;
					}
				}
				tech.learn();
				return tech.getId();
			}
		}
		
		return -1;
	}
}
