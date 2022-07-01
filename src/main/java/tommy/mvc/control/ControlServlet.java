package tommy.mvc.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tommy.mvc.action.Action;

public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cmd = request.getParameter("cmd"); // 2. ��û�� �м�
		if(cmd != null) {
			ActionFactory factory = ActionFactory.getInstance();
			Action action = factory.getAction(cmd); // 3. ����� ó���� �ش� Action ��ü�� ����
			ActionForward af = action.execute(request, response); // 4. ��ɿ� ���� ó���� ���� ������ �������� ..
			if(af.isRedirect()) { // 5. �ش��������� �̵���Ŵ
				response.sendRedirect(af.getUrl());
			}else {
				RequestDispatcher rd = request.getRequestDispatcher(af.getUrl());
				rd.forward(request, response);
			}
		}else { // ��ɾ �� �־��� ���
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head><title>Error</title></head>");
			out.println("<body>");
			out.println("<h4>�ùٸ� ��û�� �ƴմϴ�!</h4>");
			out.println("<h4>http://localhost:8080/mvc/test.do?cmd=��ûŰ����</h4>");
			out.println("</body>");
			out.println("</html>");
		}
	}
}
