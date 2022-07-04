package tommy.board.action;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tommy.board.model.BoardDAO;
import tommy.board.model.BoardVO;
// �Էµ� �� ó��
public class WriteProAction implements CommandAction {
   public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable{
      request.setCharacterEncoding("utf-8");//�ѱ� ���ڵ�
      BoardVO article = new BoardVO(); //������ó�� ��
      
      article.setNum(Integer.parseInt(request.getParameter("num")));
      article.setWriter(request.getParameter("writer"));
      article.setEmail(request.getParameter("email"));
      article.setSubject(request.getParameter("subject"));
      article.setPass(request.getParameter("pass"));
      article.setRegdate(new Timestamp(System.currentTimeMillis()));
      article.setRef(Integer.parseInt(request.getParameter("ref")));
      article.setStep(Integer.parseInt(request.getParameter("step")));
      article.setDepth(Integer.parseInt(request.getParameter("depth")));
      article.setContent(request.getParameter("content"));
      article.setIp(request.getRemoteAddr());
      BoardDAO dbPro = BoardDAO.getInstance(); //DBó��
      dbPro.insertArticle(article);
      return "/board/writePro.jsp";
   }
}