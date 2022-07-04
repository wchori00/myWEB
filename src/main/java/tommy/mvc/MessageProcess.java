package tommy.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// Controller로 부터 작업의 처리를 지시받아서 작업을 처리

public class MessageProcess implements CommandProcess {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable { 
	// 원래 여기에다가 필요에 따라서 VO, DAO를 초기화하고
	// 데이터베이스를 연동하고 아래와 같이 결과값을 셋팅한다
	// 그리고 이동할 페이지로 아래와 같이 넘겨준다
		request.setAttribute("message", "요청 파라미터로 명령어를 전달");
		return "/mvc/process.jsp";
	}
}
