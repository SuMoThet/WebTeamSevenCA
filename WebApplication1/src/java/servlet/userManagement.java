/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import business.usersManager;
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
@WebServlet(name = "createAUser", urlPatterns = {"/createAUser"})
public class userManagement extends HttpServlet {
    @EJB private usersManager usm;
    @Override
  protected void doPost(HttpServletRequest req,HttpServletResponse resp)
          throws ServletException, IOException{
          doProcess(req,resp);
  }

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
             doProcess(req,resp);
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
    
    


    private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
                   RequestDispatcher rd = null;
                   String newname = req.getParameter("newname");
	           String newpwd = req.getParameter("newpassword");
//                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
//  	           resp.setContentType("text/plain");
               if(newname.equals("")||newname==null){
		        req.setAttribute("createMessage","Please enter UserName");
			    System.out.println(req.getAttribute("createMessage"));
 	                rd = req.getRequestDispatcher("/createAUser.jsp");//stay this page
		        rd.forward(req, resp);
		    }
                    else if(newpwd.equals("")||newpwd==null){	
                        req.setAttribute("createMessage","Please enter password");
			System.out.println(req.getAttribute("createMessage"));
	                rd = req.getRequestDispatcher("/createAUser.jsp");//stay this page
		        rd.forward(req, resp);
			}
                    else
//                       u.setUserName(newname);
//                        u.setPassword(newpwd);
                        usm.createAUser(newname, newpwd);
//                        List<Users> ue =usm.getAll();
                        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
	                resp.setContentType("text/plain");
                           System.out.println(usm.getAll().toString());
			req.getSession().setAttribute("user", newname);
			rd = req.getRequestDispatcher("/index.html");//stay this page
			rd.forward(req, resp);    
          }
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
  

		
		

