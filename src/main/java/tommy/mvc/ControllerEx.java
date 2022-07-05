package tommy.mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerEx extends HttpServlet {
     private static final long serialVersionUID=1;
     //��ɾ�� ��ɾ� ó�� Ŭ������ ������ ����
     private Map<String, Object> commandMap = new HashMap<String, Object>();   //�������� Ű���� ���ڿ���, ����� ��ü�� ����
     //��ɾ�� ó��Ŭ������ ���εǾ� �ִ� properties������ �о Map��ü�� commandMap�� ����
     //��ɾ�� ó��Ŭ������ ���εǾ� �ִ� properties������ Command.properties ����
     @SuppressWarnings("unchecked") //�������� �����Ϸ��� ��� �����ϱ� ���� ����
     public void init(ServletConfig config) throws ServletException{   
    //web.xml���� propertyConfig�� �ش��ϴ� init-param�� ���� �о� ��
     String props = config.getInitParameter("propertyConfig"); //web.xml���� properties�� �޾ƿ���� ��(Command.properties) 
     //��ɾ�� ó��Ŭ������ ���������� ������ Properties��ü ����
     Properties pr = new Properties(); // Properties�� Ű���� value�� String ���� �Ǿ��ִ� Ư���� ���̴�.
     String path=config.getServletContext().getRealPath("/WEB-INF");  //���� ��ũ�� ���� ���..ContextPath = /myWeb, (C:\---\WEB-INF\)
     FileInputStream f = null; //InputStream�� ��ӹ�����, ���Ϸ� ���� ����Ʈ�� �Է¹޾� ����Ʈ ������ ����ϴ� Ŭ���� 
     try {
        //Command.properties������ ������ �о��
        f = new FileInputStream(new File(path, props));   //C:\--\WEB-INF\  + Command.properties
        //Command.properties������ ������ Properties��ü(pr)�� ����
        pr.load(f); //Ű�� ����� ����
     }catch(IOException e) {
        throw new ServletException(e);
     }finally {
        if(f != null)try {f.close();}catch(IOException ex) {}
     }
     //Iterator��ü�� Enumeration ��ü�� Ȯ���Ų ������ ��ü
     Iterator<Object> keyIter = pr.keySet().iterator();  //�����ָӴ� �����,, Map key��-> ��Ƽ� �ָӴϸ� ���� KeyIter(mvc.message.do)
     //��ü�� �ϳ��� ������ �� ��ü������ Properties ��ü�� ����� ��ü�� ����
     while(keyIter.hasNext()) {
        String command = (String)keyIter.next(); //Ű���� ������(key : mvc.message.do    in Command.properties)
        String className = pr.getProperty(command);   //Ű�� ����� ���ڿ��� ������(value : tommy.mvc.MessageProcess   in Command.properties)
        try {//�ش� ���ڿ��� Ŭ������ �����.
           Class commandClass = Class.forName(className); // Class.forName()�̷���Ŭ������ ã�����,, => tommy.mvc.MessageProcess ->ã�ư�
           Object commandInstance = commandClass.newInstance();//�ش� Ŭ������ ��ü�� ����  
           //newInstance()��,, commandInstance�� ���� ��ü�� �����.   
           //Map��ü�� commandMap�� ��ü ����
           //Ű�� : mvc.message.do, ��ü tommy.mvc.MessageProcess
           commandMap.put(command, commandInstance);    //Ű��, ���
        }catch(ClassNotFoundException e) {
           throw new ServletException(e);
        }catch(InstantiationException e) {
           throw new ServletException(e);
        }catch(IllegalAccessException e) {
           throw new ServletException(e);
        }
      }
    }  //init() ������ commandMap�ȿ� Ű���� ����� �ִ°�
       // ���� service() �� ���� doGet�� ���� ��û�� ������ ��� requestPro()�� ����
    public void doGet(//get����� ���� �޼���
          HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
       requestPro(request, response);
    }
    //������� ��û�� �м��ؼ� �ش� �۾��� ó�� (commanMap���� ���� ó�� ����ϴ�,,)
   private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String view = null;
      CommandProcess com= null; //Action�� �ش��ϴ� �������̽�
      try {
         String command = request.getRequestURI();  //uri : /myWeb/mvc/message.do
         if(command.indexOf(request.getContextPath())==0) {  //Ŀ��忡�� ContextPath(= "/myWeb")���� ã�Ƽ� ���� �ε��� ��ȣ�� �����ϴµ� ���� 0�̴�.(������-1��ȯ)
            command = command.substring(request.getContextPath().length());  //  "/myWeb"�� ����=6 
                            //substring()�� ��ü�� ���ۺ��� ������� ���ڿ��� �κ� ���ڿ� ��ȯ
                               //6�� ���� �� 7������ ������ �����Ѵ�.  = > /mvc/message.do
         }
         com = (CommandProcess)commandMap.get(command); //init()���� commadMap ������ Ű���� ������ Ű���� /mvc/message.do��  com��ü������ (Ű���� �ش��ϴ� ����� ã������)
         view = com.requestPro(request,response); //action�� execute�� �ش��ϴ� requestPro()�޼��忡 ��û�� ���䰪�� �����Ͽ�  view�� ���ڿ��� ����
      }catch(Throwable e) {
         throw new ServletException(e);
      }
      RequestDispatcher dispatcher = request.getRequestDispatcher(view);  //forward()������� ����ϱ����� RequestDispatcher ��ü ����
      dispatcher.forward(request, response); //��ü �ȿ� ��û�� ������ ��� ���   
       // Ű���� ������ url�� http://localhost:8080/myWeb/mvc/message.do ������, /mvc/message/do Ű���� ����� tommy.mvc.MessageProcess���� MessageProcess�� �̵�!
       // MessageProcess ������ action������ CommandProcess �������̽��� requestPro(=execute())�� �������̵� �Ͽ�
       // request.setAttribute("message", "��û �Ķ���ͷ� ��ɾ ����"); message��� Ű���� "��û �Ķ���ͷ� ��ɾ ����"�� ���
       // return "/mvc/process.jsp";    <- jsp�� ����!
       //  ����������  process.jsp������ message ���� �޾�. value�� ��� 
        // ó�� ���: <c:set var="message" value="${requestScope.message }"/>   - >  <c:out value="${message }"/>
       // �������ڸ�.. http://localhost:8080/myWeb/mvc/message.do ��� ġ�� ��� ���� MessageProcess���� requestPro()�޼��带 ó���Ͽ�
       // process.jsp�� Ű���� ���ڿ��� ������, jsp������ �װ� ����Ͽ� �Ʒ��� ���� ����Ž
       // =>  ���: ��û �Ķ���ͷ� ��ɾ ����
      
   }
}