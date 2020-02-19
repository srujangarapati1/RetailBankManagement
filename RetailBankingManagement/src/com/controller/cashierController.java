package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bean.Transfer;
import com.exceptions.AccountDoesnotExist;
import com.factory.BankFactory;
import com.service.Service;

/**
 * Servlet implementation class cashierController
 */
public class cashierController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER=Logger.getLogger(cashierController.class); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cashierController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		Service ser=BankFactory.getServiceInstance();
		HttpSession session=request.getSession(false);
		if(session!=null&&session.getAttribute("Role")!=null&&session.getAttribute("Role").equals("cashier")){
		if(action.equalsIgnoreCase("home")){
			LOGGER.info("this is going to home of cashier");
			response.sendRedirect("jsp/cashier.jsp");
		}
		else if(action.equalsIgnoreCase("trans")){
			LOGGER.info("this is in the transfer page");
			request.getRequestDispatcher("jsp/transfer.jsp").include(request, response);
		}
		else if(action.equalsIgnoreCase("transfer")){
			LOGGER.info("this is in Transfering amount");
			double amount=Double.parseDouble(request.getParameter("tAmount"));
			int sAcc=Integer.parseInt(request.getParameter("sAcc"));
			int tAcc=Integer.parseInt(request.getParameter("tAcc"));
			
			try {
				request.setAttribute("prevSouAmt", Double.toString(ser.getBalalnce(sAcc)));
				request.setAttribute("prevTarAmt",  Double.toString(ser.getBalalnce(tAcc)));
				Transfer t=new Transfer(sAcc, tAcc, amount);
				if(ser.transfer(t)){
					LOGGER.info("Amount has been transferd");
					request.setAttribute("Transfer", t);
					request.setAttribute("preSouAmt",  Double.toString(ser.getBalalnce(sAcc)));
					request.setAttribute("preTarAmt",  Double.toString(ser.getBalalnce(tAcc)));	
					request.getRequestDispatcher("jsp/tSuccess.jsp").include(request, response);
				}
				else{
					LOGGER.info("Amount is very large");
					request.getRequestDispatcher("jsp/tError.jsp").include(request, response);
				}
			} catch (AccountDoesnotExist e1) {
				// TODO Auto-generated catch block
				LOGGER.debug("Account doenot exist");
				request.getRequestDispatcher("jsp/AccountDoesnot.jsp").include(request, response);
			}
			
			catch(Exception e)
			{	
				LOGGER.debug("some exceptions has occured");
				request.getRequestDispatcher("jsp/Error.jsp").include(request, response);;
			}
		}
		else if(action.equalsIgnoreCase("statement")){
			LOGGER.info("Going to Statement page");
			response.sendRedirect("jsp/Statement.jsp");
		}
		else if(action.equalsIgnoreCase("getstatement")){
			LOGGER.info("in the getStatements method");
			int aid=Integer.parseInt(request.getParameter("accountid"));
			int count=Integer.parseInt(request.getParameter("count"));
			
			ArrayList<Transfer> list;
			try {
				list = ser.lastTransactions(aid, count);
				request.setAttribute("list", list);
				request.setAttribute("aid",aid);
				LOGGER.info("It has displayed the Account Statement");
				request.getRequestDispatcher("jsp/AccStatement.jsp").include(request, response);
			} catch (AccountDoesnotExist e) {
				// TODO Auto-generated catch block
				LOGGER.debug("The account id entered is not exist in the databaser");
				request.getRequestDispatcher("jsp/AccountDoesnot.jsp").include(request, response);
			}
			
		}
		else if(action.equalsIgnoreCase("logout")){
			LOGGER.info("Loggin out from cashier");
			session.invalidate();
			response.sendRedirect("jsp/login.jsp");
		}
	}else{
		session.invalidate();
		LOGGER.debug("as unotherised tried to load the page without logging in it redireted to login page");
		response.sendRedirect("jsp/login.jsp");
	}
		
	}

}
