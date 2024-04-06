package dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBManager {
	// DB ���ӿ� ������
	private String host; 		// ���� �ּ� ��Ʈ db�̸� ���õ�
	private String uid; 		// db ����
	private String upw;			// db ������ ��й�ȣ
	
	// DB ���� ��ü
	private Connection conn = null; 	// DB ���� ��ü
	private Statement stmt = null; 		// SQL ���� ��ü
	private ResultSet result = null; 	// SQL ��� ��ü
	
	// ������
	public DBManager() {
		this.host = "jdbc:mysql://192.168.0.70:3306/project";
		this.host+= "?useUnicode=true";
		this.host+= "?characterEncoding=UTF-8";
		this.host+= "?serverTimezone=UTC";
//		this.host+= "useSSL=false"; // SSL �ɼ� ����(���°� �ƴ�)
		
		this.uid = "root";
		this.upw = "ezen";
	}
	
	// ID, PW setter / host ������ setter Ȱ���Ͽ� ������ �� ����
	public void setUID(String userID) {this.uid = userID;}
	public void setUPW(String userPW) {this.upw = userPW;}
	public Connection getConn() {return conn;}
	
	// DB ���� �޼ҵ�
	public boolean OpenDB() {
		try {
			// JDBC ����̹��� �ε��Ѵ�
			Class.forName("com.mysql.cj.jdbc.Driver");
			// DB�� �����Ѵ�
			this.conn = DriverManager.getConnection(this.host, this.uid, this.upw);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// DB ���� �޼ҵ�
	public boolean CloseDB() {
		try {
			this.stmt.close();
			this.conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	// SQL ���� �޼ҵ� / Insert Update Delete SQL ���� ó��
	public int RunSQL(String sql) {
		try {
			// ���� ��ü�� �Ҵ����
			this.stmt = this.conn.createStatement();
			// ���ڷ� ���� SQL ������ ����
			int count = this.stmt.executeUpdate(sql);
			// ���� ��ü�� ����
			this.stmt.close();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	// ���� ���� �޼ҵ�
	public boolean OpenQuery(String sql) {
		try {
			// ���� ��ü�� �Ҵ����
			this.stmt = this.conn.createStatement();
			// SQL ������ �����Ͽ� ����� �޴´�
			this.result = this.stmt.executeQuery(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// ���� ���� �޼ҵ�
	public boolean CloseQuery() {
		try {
			this.result.close();
			this.stmt.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// getNext
	public boolean GetNext() {
		try {
			return this.result.next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// getValue
	public String GetValue(String colName) {
		try {
			// �÷� �̸����� �÷��� ���� ������
			return this.result.getString(colName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int GetInt(String colName) {
		try {
			// �÷� �̸����� �÷��� ���� ������
			return this.result.getInt(colName);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	// ��������ǥ ó�� : �ڹٿ����� "���ڿ�" 
	// ���ڿ� ���ο� ���� SQL ���������� '���ڿ�'
	// SQL ������ �� �ش��ϴ� ������ �߰��� �� ����
	public String _R(String value) {
		return value.replace("'","''");
	}
}