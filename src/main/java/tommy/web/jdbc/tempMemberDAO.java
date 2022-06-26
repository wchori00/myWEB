package tommy.web.jdbc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public class tempMemberDAO {
//	private final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
//	private final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
//	private final String USER = "mytest";
//	private final String PASS = "mytest";
//	private ConnectionPool pool = null;	// �߰�
//	public tempMemberDAO() {
//		try {
//			Class.forName(JDBC_DRIVER);
//			pool = ConnectionPool.getInstance();	// �߰�
//		}catch(Exception e) {
//			System.out.println("Error: JDBC ����̹� �ε� ����");
//			System.out.println("Error: Ŀ�ؼ� ������ ����");	// �߰�
//		}
//	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context init = new InitialContext();
			// ūȯ��(Context)�� ��ȯ��(InitialContext)���� �����ؼ� init
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/myOracle");
			// DataSource(�������̽�)�� init.lookup�޼ҵ�� ��� ã�Ƽ� 
			// �����(Object�� DataSource�� ����ȯ �ʿ�) ��� ds 
			conn = ds.getConnection();
			// ds.getConnection�޼ҵ�� Ŀ�ؼǾ� conn 
		}catch(NamingException ne) {
			ne.printStackTrace();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return conn;
	}
	
	public Vector<tempMemberVO> getMemberList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Vector<tempMemberVO> vecList = new Vector<tempMemberVO>();
		try {
//			conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
			conn = getConnection();	// �߰�
			String strQuery = "select * from tempMember";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(strQuery);
			while (rs.next()) {
				tempMemberVO vo = new tempMemberVO();
				vo.setId(rs.getString("id"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setName(rs.getString("name"));
				vo.setMem_num1(rs.getString("mem_num1"));
				vo.setMem_num2(rs.getString("mem_num2"));
				vo.setEmail(rs.getString("e_mail"));
				vo.setPhone(rs.getString("phone"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddress(rs.getString("address"));
				vo.setJob(rs.getString("job"));
				vecList.add(vo);
			}
		}catch(Exception ex) {
			System.out.println("Exception" + ex);
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(stmt != null) try {stmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();/*pool.releaseConnection(conn);*/}catch(SQLException e) {}
		}
		return vecList;
	}
}
