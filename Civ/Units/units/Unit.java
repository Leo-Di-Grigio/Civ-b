package units;

public class Unit {

	// id
	private static int ID;
	public int id;
	
	// params
	public int playerId;
	public int x;
	public int y;
	
	public Unit() {
		this.id = ID++;
	}
}
