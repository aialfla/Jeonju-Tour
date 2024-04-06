package dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBManager {
	// DB 접속용 데이터
	private String host; 		// 서버 주소 포트 db이름 세팅들
	private String uid; 		// db 계정
	private String upw;			// db 계정의 비밀번호
	
	// DB 연결 객체
	private Connection conn = null; 	// DB 연결 객체
	private Statement stmt = null; 		// SQL 문맥 객체
	private ResultSet result = null; 	// SQL 결과 객체
	
	// 생성자
	public DBManager() {
		this.host = "jdbc:mysql://192.168.0.70:3306/project";
		this.host+= "?useUnicode=true";
		this.host+= "?characterEncoding=UTF-8";
		this.host+= "?serverTimezone=UTC";
//		this.host+= "useSSL=false"; // SSL 옵션 해제(쓰는거 아님)
		
		this.uid = "root";
		this.upw = "ezen";
	}
	
	// ID, PW setter / host 정보도 setter 활용하여 수정할 수 있음
	public void setUID(String userID) {this.uid = userID;}
	public void setUPW(String userPW) {this.upw = userPW;}
	public Connection getConn() {return conn;}
	
	// DB 연결 메소드
	public boolean OpenDB() {
		try {
			// JDBC 드라이버를 로딩한다
			Class.forName("com.mysql.cj.jdbc.Driver");
			// DB에 연결한다
			this.conn = DriverManager.getConnection(this.host, this.uid, this.upw);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// DB 종료 메소드
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
	// SQL 실행 메소드 / Insert Update Delete SQL 구문 처리
	public int RunSQL(String sql) {
		try {
			// 문맥 객체를 할당받음
			this.stmt = this.conn.createStatement();
			// 인자로 받은 SQL 구문을 실행
			int count = this.stmt.executeUpdate(sql);
			// 문맥 객체를 닫음
			this.stmt.close();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	// 쿼리 실행 메소드
	public boolean OpenQuery(String sql) {
		try {
			// 문맥 객체를 할당받음
			this.stmt = this.conn.createStatement();
			// SQL 구문을 실행하여 결과를 받는다
			this.result = this.stmt.executeQuery(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// 쿼리 종료 메소드
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
			// 컬럼 이름으로 컬럼의 값을 가져옴
			return this.result.getString(colName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int GetInt(String colName) {
		try {
			// 컬럼 이름으로 컬럼의 값을 가져옴
			return this.result.getInt(colName);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	// 작은따옴표 처리 : 자바에서는 "문자열" 
	// 문자열 내부에 들어가는 SQL 구문에서는 '문자열'
	// SQL 인젝션 방어에 해당하는 내용을 추가할 수 있음
	public String _R(String value) {
		return value.replace("'","''");
	}
}