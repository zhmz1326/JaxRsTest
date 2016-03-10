package com.mairiya.JaxRsTest;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.tools.Server;

import com.mairiya.JaxRsTest.util.ScriptRunner;

public class H2Starter {
	private static final String PORT = "9094"; 

	// private static final String DB_CONN_URL = "jdbc:h2:file:./h2db.h2;MODE=DB2";
	private static final String DB_CONN_URL = "jdbc:h2:tcp://127.0.0.1:" + PORT + "/./h2db;MODE=DB2";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "sa";

//	static {
//		createTableIfMissing();
//	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection(DB_CONN_URL, USERNAME, PASSWORD);

		return conn;
	}

	public static void h2init() {
		// String createStatement = "create table users (id int, name varchar(50))";
		String filename = "h2init.sql";
		try { //(Connection conn = getConnection(); Reader reader = new FileReader(filename);/*Statement stmt = conn.createStatement();*/) {
			Connection conn = getConnection(); 
			Reader reader = new FileReader(filename);
//			stmt.executeUpdate(createStatement);
//			ScriptRunner runner = new ScriptRunner(conn, false, false);  
//		    runner.setErrorLogWriter(null);  
//		    runner.setLogWriter(null);
			ScriptRunner runner = new ScriptRunner(conn, false, false);
			runner.runScript(reader);
			
			conn.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void h2start() throws SQLException {
		// start the TCP Server
		Server.createTcpServer("-tcpPort", PORT).start();
	}

	public static void main(String[] args) throws SQLException {
		h2start();
		h2init();
	}

}
