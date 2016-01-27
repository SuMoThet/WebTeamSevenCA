/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uno.Game;
import uno.player;
import uno.Card;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import uno.GameList;

/**
 *
 * @author Little Rabit
 */
@WebServlet(name = "createAGame", urlPatterns = {"/createAGame/*"})
public class createAGame extends HttpServlet {

    @Inject GameList gameList;
    // static GameList games=new GameList();
    //static int i=0;
/*
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet createAGame</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet createAGame at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getPathInfo();
        System.out.println(path);

        HttpSession session  = request.getSession();
        RequestDispatcher rd;
        switch (path) {
            case "/create":
                int number = Integer.parseInt(request.getParameter("number"));
                Game g = new Game();
                g.setNumber(number);
                g.setDescription(request.getParameter("description").toString());
                gameList.getGameList().put(g.getGameId(), g);
                //   this.getServletContext().setAttribute("listOfGame", gameList);
                System.out.println("gameId in create"+g.getGameId());
 response.sendRedirect("../startAGame.html#"+g.getGameId()+request.getParameter("number"));
                // this.getServletContext().setAttribute("game", g);
                // System.out.println(((Game) (this.getServletContext().getAttribute("game"))).getGameId());
               // request.setAttribute("gameId", g.getGameId());
               // request.setAttribute("number", number);
               // rd = request.getRequestDispatcher("/startAGame.jsp");
               // rd.forward(request, response);
                break;
            case "/displayGames":
                // System.out.println(request.getParameter("username"));
                
                        request.setAttribute("photo",request.getAttribute("photo") );
                System.out.println("where is the LiLy"+request.getAttribute("userName"));
                request.setAttribute("userName",request.getAttribute("userName") );
                // request.setAttribute("userName",request.getParameter("username") );
                request.setAttribute("gameList", gameList.getListOfGameValue());
                rd = request.getRequestDispatcher("/ListOfGame.jsp");
                rd.forward(request, response);

                break;
          //  case "/joinSpecificGame":
              //  request.setAttribute("selectedGame", gameList.getAGame(request.getParameter("gameId")));
              //  System.out.println(request.getParameter("gameId"));
           
               
                //rd = request.getRequestDispatcher("/joinASpecificGame.jsp");
               // rd.forward(request, response);
               // break;

            case "/JoinAGame":
                   
               // session.setAttribute("player", new player(request.getParameter("userName")));
                //  Game a=(Game)  this.getServletContext().getAttribute("game");

                //  while(i<Integer.parseInt(this.getServletContext().getAttribute("number").toString()))
                // {  a.addPlayer((player)session.getAttribute("player"));
                //   System.out.println(((player)session.getAttribute("player")).getUserName());
                //  this.getServletContext().setAttribute("game", a);
                //  rd=request.getRequestDispatcher("/waitingAGame.jsp");
                // rd.forward(request, response);
                //  }
                //  i++;
               // (gameList.getAGame(request.getParameter("gameId"))).addPlayer((player) session.getAttribute("player"));
               System.out.println( request.getParameter("userName").toString());
                 System.out.println( "JoinAGame Photo"+request.getParameter("photo").toString());
                System.out.println( "WHERE IS MY USER"+request.getParameter("userName").toString());
               // request.setAttribute("userName",request.getParameter("userName") );
               //  request.setAttribute("gameId",request.getParameter("gameId"));
                response.sendRedirect("../cardOnHand.html#" +request.getParameter("gameId")+","+request.getParameter("photo").toString()+","+request.getParameter("userName").toString());
                System.out.println("after redirect");
                //response.sendRedirect("/cardOnHand.jsp");
                //rd = request.getRequestDispatcher("/cardOnHand.jsp");
               // rd = request.getRequestDispatcher("/cardOnHand.html#"+request.getParameter("gameId")+request.getParameter("userName"));
                //rd.forward(request, response);
                break;

            case "/startAGame":
                System.out.println("!!!!!!!!!!!!!!!!!!!"+request.getParameter("gameId"));
                (gameList.getAGame(request.getParameter("gameId"))).initialGame();
               // Card TopCard = (gameList.getAGame(request.getParameter("gameId"))).getDiscardPile().showTheTopCard();
               // int count = (gameList.getAGame(request.getParameter("gameId"))).getDrawPile().getUnoCard().size();
              //  this.getServletContext().setAttribute("count", count);
               // this.getServletContext().setAttribute("TopCard", TopCard);
               //  request.setAttribute("gameId",request.getParameter("gameId") );
                // rd = request.getRequestDispatcher("/table.jsp");
                response.sendRedirect("../table.html#" + request.getParameter("gameId"));
              //  rd = request.getRequestDispatcher("/table.html#"+request.getParameter("gameId"));
//////////////////////=======================PROBLEM=====================
              //  rd.forward(request, response);
                break;
            //case "/getCards":
              //  System.out.println("card on hand" + ((player) (session.getAttribute("player"))).getCardOnHand());
               // session.setAttribute("cardOnHand", ((player) (session.getAttribute("player"))).getCardOnHand());
              //  rd = request.getRequestDispatcher("/individualView.jsp");
              //  rd.forward(request, response);

              //  break;
            //create a game
        }
        // int i=0;
        // for( i=0; i<Integer.parseInt(number); i++)
        // { g.addPlayer(a);
        // }
        // while(i<Integer.parseInt(number))
        //  { g.addPlayer(new player());
        //    i++;}

        //add players 
//          g.initialGame();
        //initialGame
        // System.out.println(g.getDrawPile());
        //  for(player p:g.getPlayers()){
        //   System.out.println(p.getCardOnHand());}
        //  request.setAttribute("game", g);
        //  request.setAttribute("players", g.getPlayers());
        //  request.setAttribute("dicardCard", g.getDiscardPile().showTheTopCard());
        //  RequestDispatcher rd=request.getRequestDispatcher("/game.jsp");
        //  rd.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
