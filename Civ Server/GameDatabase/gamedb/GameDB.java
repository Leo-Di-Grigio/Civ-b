package gamedb;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDB {
	
	public String dbPath = "";
	
	private Connection connection;
	private Statement  statement;
	private ResultSet  result;
	
	public GameDB(String dbPath){
		this.dbPath = dbPath;
	}
	
	public boolean connect() {
		try {
			File file = new File(dbPath);
			if(!file.exists()){
				return false; // file does not exist
			}
			else{
				Class.forName("org.sqlite.JDBC");
				connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
				statement = connection.createStatement();
				return true; // normal connection to DB-file
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void showTable() throws SQLException{
		result = statement.executeQuery("SELECT * FROM Items;");
		
		while(result.next()){
			
            int id = result.getInt("id");
            String  title = result.getString("title");
            boolean usable = result.getBoolean("usable");
            int icon = result.getInt("icon");
            String text = result.getString("text");
             
            System.out.println("ID: " + id);
            System.out.println("name: " + title);
            System.out.println("usable: " + usable);
            System.out.println("icnon: " + icon);
            System.out.println("text: " + text);
            System.out.println();
        }	
	}
	
	@Override
	protected void finalize() throws Throwable {
		connection.close();
		statement.close();
		result.close();
		
		super.finalize();
	}
}
