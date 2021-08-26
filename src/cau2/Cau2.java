package cau2;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Cau2 {
	public static void main(String[] args) {
		try {
			createDatabase();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static Connection getConnection() throws Exception {
		String connectionString = "jdbc:sqlserver://localhost; integratedSecurity=true";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(connectionString);
			System.out.println("Ket noi thanh cong");
			return connection;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static void createDatabase() throws Exception {
		try {
			Connection connection = getConnection();
			PreparedStatement createDatabase = connection.prepareStatement("CREATE DATABASE dbTourDuLich");
			createDatabase.executeUpdate();
			System.out.println("Tao co so du lieu thanh cong");
		}
		catch(Exception e) {
			System.out.println();
		}
	}
	
	public static void createTable() throws Exception {
		
	}
}
