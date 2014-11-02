package gamedb;

import gamedata.GameRules;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDB {
	
	public String dbFilePath = "";
	
	private Connection connection;
	private Statement  statement;
	private ResultSet  result;
	
	public GameDB(String dbFilePath){
		this.dbFilePath = dbFilePath;
	}
	
	public boolean connect() {
		try {
			File file = new File(dbFilePath);
			if(!file.exists()){
				return false; // file does not exist
			}
			else{
				Class.forName("org.sqlite.JDBC");
				connection = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath);
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

	@Override
	protected void finalize() throws Throwable {
		connection.close();
		statement.close();
		result.close();
		super.finalize();
	}

	public GameRules getRules() {
		//result = statement.executeQuery("SELECT * FROM Items;");
		return null;
	}
}
