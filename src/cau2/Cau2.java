package cau2;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Cau2 {
	public static void main(String[] args) {
		Cau2 cau2 = new Cau2();
		try {
			//createDatabase();
			//createTable();
			cau2.importData1();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static Connection getConnection() throws Exception {
		String connectionString = "jdbc:sqlserver://localhost; database=dbTourDuLich; integratedSecurity=true";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(connectionString);
			return connection;
		}
		catch(Exception e) {
			System.out.println("Ket noi den may chu khong thanh cong");
			System.out.println(e);
		}
		return null;
	}
	
	public static void createDatabase() throws Exception {
		try {
			String connectionString = "jdbc:sqlserver://localhost; integratedSecurity=true";
			Connection connection = DriverManager.getConnection(connectionString);
			PreparedStatement createDatabase = connection.prepareStatement("IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = dbTourDuLich) CREATE DATABASE dbTourDuLich");
			createDatabase.executeUpdate();
		}
		catch(Exception e) {
			System.out.println();
		}
	}
	
	public static void createTable() throws Exception {
		try {
			Connection connection = getConnection();
			PreparedStatement createTable = connection.prepareStatement(""
					+ "IF NOT EXISTS (select * from sysobjects where name='THANHVIEN' and xtype='U')"
					+ "CREATE TABLE THANHVIEN ("
					+ "MaThanhVien char(8) primary key,"
					+ "TenThanhVien char(100),"
					+ "NgaySinh date,"
					+ "DiaChi char(100),"
					+ "Email char(50),"
					+ "SoDienThoai char(15),"
					+ "ChiPhiNhan float)");
			createTable.executeUpdate();
			System.out.println("Tao bang thanh cong");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void importData1() {
		try (Scanner input = new Scanner(new File("C:\\Users\\LENOVO\\eclipse-workspace\\cuoiKy-Java\\src\\cau2\\data1.txt"))) {
			while(input.hasNextLine()) {
				String line;
				line = input.nextLine();
				//using words array to store each part of the record
				String[] words = line.split(",");
				
				try {
					Connection connection = getConnection();
					PreparedStatement insertStatement = connection.prepareStatement(""
							+ "insert into THANHVIEN ("
							+ "MaThanhVien,"
							+ "TenThanhVien,"
							+ "NgaySinh,"
							+ "DiaChi,"
							+ "Email,"
							+ "SoDienThoai)"
							+ "values (?,?,?,?,?,?)");
					insertStatement.setString(1, words[0]);
					insertStatement.setString(2, words[1]);
					insertStatement.setString(3, words[2]);
					insertStatement.setString(4, words[3]);
					insertStatement.setString(5, words[4]);
					insertStatement.setString(6, words[5]);
					insertStatement.executeUpdate();
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}
}
