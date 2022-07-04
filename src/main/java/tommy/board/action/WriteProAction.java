package tommy.board.action;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tommy.board.model.BoardDAO;
import tommy.board.model.BoardVO;
// 涝仿等 臂 贸府
public class WriteProAction implements CommandAction {
   public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable{
      request.setCharacterEncoding("utf-8");//茄臂 牢内爹
      BoardVO article = new BoardVO(); //单捞磐贸府 后
      
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
      BoardDAO dbPro = BoardDAO.getInstance(); //DB贸府
      dbPro.insertArticle(article);
      return "/board/writePro.jsp";
   }
}