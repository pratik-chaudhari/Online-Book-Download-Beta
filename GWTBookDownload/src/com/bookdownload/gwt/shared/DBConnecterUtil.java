package com.bookdownload.gwt.shared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnecterUtil {

	private DBConnecterUtil() throws ClassNotFoundException, SQLException {
		Class.forName( DB_Driver ); 
		con = DriverManager.getConnection(DB_URL, DB_User, DB_Pwd);
		System.out.println("open database successfully");
	}

	public static DBConnecterUtil getInstance() 
			throws ClassNotFoundException, SQLException {
		if(instance==null){
			synchronized (DBConnecterUtil.class) {
				if (instance == null )
					instance = new DBConnecterUtil();
			}
		}
		return instance;	
	}

	public Connection getConnection() {        
		return con;
	}

	public void close() throws SQLException{
		con.close();
		instance = null;
	}

	private final String DB_URL = "jdbc:mysql://localhost:3306/onlinebooksdb";
	private final String DB_User = "root";
	private final String DB_Pwd = "root";
	private final String DB_Driver = "com.mysql.jdbc.Driver";
	private final Connection con;
	private static DBConnecterUtil instance = null;

}