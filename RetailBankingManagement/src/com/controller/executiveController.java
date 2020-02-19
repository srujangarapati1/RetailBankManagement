package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bean.Account;
import com.bean.Customer;
import com.exceptions.CostumerAlreadyExist;
import com.exceptions.CustomerIdDoesnotExists;
import com.exceptions.GlobalExceptions;
import com.factory.BankFactory;
import com.service.Service;

/**
 * Servlet implementation class executiveController
 */
public class executiveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER=Logger.getLogger(executiveController.class);    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public executiveController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		Service ser=BankFactory.getServiceInstance();
		HttpSession session=request.getSession(false);
		if(session!=null&&session.getAttribute("Role")!=null&&session.getAttribute("Role").equals("executive")){
		if(action.equalsIgnoreCase("home")){
			LOGGER.info("this is going to home of executive");
			request.getRequestDispatcher("jsp/executive.jsp").include(request, response);
		}
		else if(action.equalsIgnoreCase("customer")){
			LOGGER.info("this is going to Add Customer");
			request.getRequestDispatcher("jsp/registerCust.jsp").include(request, response);
		}
		else if(action.equalsIgnoreCase("register")){
			LOGGER.info("To Register the Coustomer method");
			Customer c=new Customer(Long.parseLong(request.getParameter("ssnid")), request.getParameter("name"), Integer.parseInt(request.getParameter("age")), request.getParameter("addLine1"), request.getParameter("addLine2"), request.getParameter("city"), request.getParameter("state"));
			long id;
			try {
				id = ser.addCustomer(c);
				request.setAttribute("id", String.valueOf(id));
				request.getRequestDispatcher("jsp/addSuccess.jsp").include(request, response);
			} catch (CostumerAlreadyExist e) {
				// TODO Auto-generated catch block
				LOGGER.debug("Costumer Alredy Present");
				request.getRequestDispatcher("jsp/cError.jsp").include(request, response);
				
			}
			catch(GlobalExceptions e)
			{
				LOGGER.debug("Some other Exception is occured");
				request.getRequestDispatcher("jsp/Error.jsp").include(request, response);
			}
			
		}
		else if(action.equalsIgnoreCase("account"))
		{
			LOGGER.info("this is going to Account Register form");
			request.getRequestDispatcher("jsp/registerAcc.jsp").include(request, response);

		}
		else if(action.equalsIgnoreCase("addaccount"))
		{
			LOGGER.info("this is TO add account to the customer");
			long custId=Long.parseLong(request.getParameter("custId"));
			String accounttype=request.getParameter("type");
			double amount=Double.parseDouble(request.getParameter("amount"));
			Account a=new Account(accounttype,amount);
			long id;
			try {
				id = ser.addAccount(custId, a);
				LOGGER.info("Account was added to the customer");
				request.setAttribute("id",Long.toString(id));
				request.getRequestDispatcher("jsp/accountsuccess.jsp").include(request, response);
			} catch (CustomerIdDoesnotExists e) {
				LOGGER.debug("The customer id doesnot exist to add account");
				request.getRequestDispatcher("jsp/Cnotexist.jsp").include(request, response);
			}catch(Exception e){
				LOGGER.debug("Some other exception is occured");
				request.getRequestDispatcher("jsp/Error.jsp").include(request, response);
			}

		
			
		}
		else if(action.equalsIgnoreCase("logout")){
			session.invalidate();
			LOGGER.info("this is going logout from executive");
			response.sendRedirect("jsp/login.jsp");
		}
		}else{
			
			session.invalidate();
			LOGGER.debug("as unotherised tried to load the page without logging in it redireted to login page");
			response.sendRedirect("jsp/login.jsp");
		}
	}

}
