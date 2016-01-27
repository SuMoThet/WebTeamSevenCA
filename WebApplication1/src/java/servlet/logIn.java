/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import business.usersManager;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author sony
 */
@WebServlet(name = "logIn", urlPatterns = {"/logIn"})
public class logIn extends HttpServlet {
    @EJB private usersManager usm;
    @Override
  protected void doPost(HttpServletRequest req,HttpServletResponse resp)
          throws ServletException, IOException{
          doProcess(req,resp);
  }
//         String username = req.getParameter("username");
//	  String pwd = req.getParameter("password");
//          Boolean a=usm.checkPassword(username, pwd);
//          RequestDispatcher rd = null;
//	  resp.setStatus(HttpServletResponse.SC_ACCEPTED);
//	  resp.setContentType("text/plain");
//		if(username.equals("")||username==null){
//			req.setAttribute("loginMessage","Please enter UserName");
//			System.out.println(req.getAttribute("loginMessage"));
//			rd = req.getRequestDispatcher("/LogIn.jsp");//stay this page
//			rd.forward(req, resp);
//		}
//		else if(usm.checkPassword(username, pwd)){	
//                    System.out.println(usm.checkPassword(username, pwd).toString());
//				req.getSession().setAttribute("user", username);
//				
//				rd = req.getRequestDispatcher("/createAGame.jsp");//stay this page
//				rd.forward(req, resp);
//			}
//		else {
//                    System.out.println(usm.checkPassword(username, pwd).toString());
//			req.setAttribute("loginMessage","Not Found User");
//			System.out.println(req.getAttribute("loginMessage"));
//			rd = req.getRequestDispatcher("/LogIn.jsp");//stay this page
//			rd.forward(req, resp);
//		}
//  }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
             doProcess(request,response);
        }
//             String username = req.getParameter("username");
//	  String pwd = req.getParameter("password");
//          Boolean a=usm.checkPassword(username, pwd);
//          RequestDispatcher rd = null;
//	  resp.setStatus(HttpServletResponse.SC_ACCEPTED);
//	  resp.setContentType("text/plain");
//		if(username.equals("")||username==null){
//			req.setAttribute("loginMessage","Please enter UserName");
//			System.out.println(req.getAttribute("loginMessage"));
//			rd = req.getRequestDispatcher("/LogIn.jsp");//stay this page
//			rd.forward(req, resp);
//		}
//		else if(usm.checkPassword(username, pwd)){	
//                    System.out.println(usm.checkPassword(username, pwd).toString());
//				req.getSession().setAttribute("user", username);
//				
//				rd = req.getRequestDispatcher("/createAGame.jsp");//stay this page
//				rd.forward(req, resp);
//			}
//		else {
//                    System.out.println(usm.checkPassword(username, pwd).toString());
//			req.setAttribute("loginMessage","Not Found User");
//			System.out.println(req.getAttribute("loginMessage"));
//			rd = req.getRequestDispatcher("/LogIn.jsp");//stay this page
//			rd.forward(req, resp);
//		}
//  }
//  @Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
//			throws ServletException, IOException {
//                                doProcess(req,resp);
//    }
    
    


    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
          RequestDispatcher rd = null;
//          String username = req.getParameter("username");
//	  String pwd = req.getParameter("password");
//          resp.setStatus(HttpServletResponse.SC_ACCEPTED);
//	  resp.setContentType("text/plain");
                String username = request.getParameter("username");
                System.out.println(username);
	           String pwd = request.getParameter("password");
                   String photo =request.getParameter("photo");
                   System.out.println(pwd);
//                   Boolean a=usm.checkPassword(username, pwd);
//                   RequestDispatcher rd = null;

	          // resp.setStatus(HttpServletResponse.SC_ACCEPTED);
	           //resp.setContentType("text/plain");
		   if(username.equals("")||username==null){
		   request.setAttribute("loginMessage","Please enter UserName");
			System.out.println(request.getAttribute("loginMessage"));
	           rd = request.getRequestDispatcher("/LogIn.jsp");//stay this page
		   rd.forward(request, response);
		   }
		   else if(usm.checkPassword(username, pwd)){	
                            System.out.println(usm.checkPassword(username, pwd).toString());
                            
                            request.setAttribute("photo", photo);
			request.setAttribute("userName", username);
                         System.out.println("photo"+photo);
			rd = request.getRequestDispatcher("createAGame/displayGames");//stay this page
			rd.forward(request, response);
			}
		    else {
                           System.out.println(usm.checkPassword(username, pwd).toString());
			request.setAttribute("loginMessage","Not Found User");
			System.out.println(request.getAttribute("loginMessage"));
			rd = request.getRequestDispatcher("/LogIn.jsp");//stay this page
			rd.forward(request, response);
		}
    
//          String username = req.getParameter("username");
//	  String pwd = req.getParameter("password");
//          Boolean a=usm.checkPassword(username, pwd);
//          RequestDispatcher rd = null;
//	  resp.setStatus(HttpServletResponse.SC_ACCEPTED);
//	  resp.setContentType("text/plain");
//		if(username.equals("")||username==null){
//			req.setAttribute("loginMessage","Please enter UserName");
//			System.out.println(req.getAttribute("loginMessage"));
//			rd = req.getRequestDispatcher("/LogIn.jsp");//stay this page
//			rd.forward(req, resp);
//		}
//		else if(usm.checkPassword(username, pwd)){	
//                    System.out.println(usm.checkPassword(username, pwd).toString());
//				req.getSession().setAttribute("user", username);
//				
//				rd = req.getRequestDispatcher("/createAGame.jsp");//stay this page
//				rd.forward(req, resp);
//			}
//		else {
//                    System.out.println(usm.checkPassword(username, pwd).toString());
//			req.setAttribute("loginMessage","Not Found User");
//			System.out.println(req.getAttribute("loginMessage"));
//			rd = req.getRequestDispatcher("/LogIn.jsp");//stay this page
//			rd.forward(req, resp);
//		}
  }
}
		
		

