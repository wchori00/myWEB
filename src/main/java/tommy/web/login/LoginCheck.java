package tommy.web.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		// db에서 사용자 정보 조회... 이 부분의 코딩을 수정해서 만들어 볼 것
		// db에서 조회환 사용자의 아이디 비번이 일치하는 경우
		// 클라이언트의 정보를 HttpSession객체에 유지 시킨다
		
		System.out.println(id);
		System.out.println(pwd); // 값이 제대로 넘어 오는지 확인
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			System.err.println("드라이버 로딩 실패");
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1","mytest","mytest");
			pstmt = conn.prepareStatement("select pass from login where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String dbPass = rs.getString("pass");
				if(pwd.equals(dbPass)) {
					// HttpSession객체 얻기
					HttpSession session = request.getSession();
					// 클라이언트의 정보를 HttpSession객체에 저장
					session.setAttribute("user", id);
					System.out.println("로그인 성공");
				}else {
					System.out.println("비밀번호 틀림");
				}
			}else {
				System.out.println("아이디가 없슴");
			}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("쿼리문이 틀렸을 확률이 높다");
			
		}finally {
			try {if(rs != null) rs.close();}catch(SQLException e) {}
			try {if(pstmt != null) pstmt.close();}catch(SQLException e) {}
			try {if(conn != null) conn.close();}catch(SQLException e) {}
		}
		
		
//		String dbID = "admin";
//		String dbPWD = "1234";
//		if(dbID.equals(id) && dbPWD.equals(pwd)) {
//			// HttpSession객체 얻기
//			HttpSession session = request.getSession();
//			// 클라이언트의 정보를 HttpSession객체에 저장
//			session.setAttribute("user", id);
//		}
		response.sendRedirect("Login");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
