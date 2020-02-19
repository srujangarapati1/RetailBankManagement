package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.factory.BankFactory;
import com.service.Service;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER=Logger.getLogger(Controller.class); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LOGGER.info("this is in Post of Controller");
		String action=request.getParameter("action");
		Service ser=BankFactory.getServiceInstance();
		if(action.equalsIgnoreCase("login")){
			
			
			String userId=request.getParameter("userId");
			String Password=request.getParameter("password");
			String Role=(ser.login(userId, Password));
			if(Role!=null&&Role.equals("cashier")){
				LOGGER.info("this is in cashier Login");
				HttpSession session=request.getSession();
				session.setAttribute("Role", Role);
				request.getRequestDispatcher("jsp/cashier.jsp").include(request, response);
			}
			else if(Role!=null&&Role.equals("execute")){
				LOGGER.info("this is in executive Login");
				HttpSession session=request.getSession();
				session.setAttribute("Role", "executive");
				request.getRequestDispatcher("jsp/executive.jsp").include(request, response);

			}
			else{
				LOGGER.debug("this is in wrong password or id");
				response.sendRedirect("jsp/Wlogin.jsp");
			
			}
		}
		
	
	}

}
