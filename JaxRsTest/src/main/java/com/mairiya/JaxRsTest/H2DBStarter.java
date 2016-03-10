package com.mairiya.JaxRsTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mairiya.JaxRsTest.biz.dao.UsersDao;

public class H2DBStarter {
	private static final String DB_CONN_URL = "jdbc:h2:tcp://127.0.0.1:9094/./h2db;MODE=DB2";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "sa";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection(DB_CONN_URL, USERNAME, PASSWORD);

		return conn;
	}

	public static void h2init() {
		// String createStatement = "create table users (id int, name
		// varchar(50))";
		try { // (Connection conn = getConnection(); Reader reader = new
				// FileReader(filename);/*Statement stmt =
				// conn.createStatement();*/) {
			Connection conn = getConnection();
			// Reader reader = new FileReader(filename);
			// stmt.executeUpdate(createStatement);
			// ScriptRunner runner = new ScriptRunner(conn, false, false);
			// runner.setErrorLogWriter(null);
			// runner.setLogWriter(null);
			// ScriptRunner runner = new ScriptRunner(conn, false, false);
			// runner.runScript(reader);

			Statement stmt = conn.createStatement();

			ResultSet result = stmt.executeQuery("select id, name from users");

			while (result.next()) {
				int id = result.getInt("id");

				String name = result.getString("name");
				System.out.println(id);
				System.out.println(name);
			}
			conn.close();
			// reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		h2init();

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.SpringMain.xml");

		UsersDao dao = (UsersDao)context.getBean(UsersDao.class);
		
		System.out.println(dao);

		((AbstractApplicationContext) context).close();

	}
}
