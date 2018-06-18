package it.uniroma3.siw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/richiestaLogin")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
						
		HttpSession session = request.getSession();
				
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username.equals("admin") && password.equals("admin")) {
			session.setAttribute("username", username);
			
			response.sendRedirect("pannelloControllo.jsp");
		}
		else {
			session.setAttribute("errorLogin", "Accesso non consentito.");
			
			response.sendRedirect("login.jsp");
		}		
		
	}
	
}
