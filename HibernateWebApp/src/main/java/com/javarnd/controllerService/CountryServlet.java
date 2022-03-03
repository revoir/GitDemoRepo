package com.javarnd.controllerService;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javarnd.dao.CountryDao;

//This servlet is being triggered once user click on RegisterInformation option

@WebServlet(urlPatterns = { "/register" })
public class CountryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CountryServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/registerView.jsp");

		dispatcher.forward(request, response);

	}

	// After saving data is stored using doPost() method

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CountryDao c = new CountryDao();
		String Name = request.getParameter("name");
		String language = request.getParameter("language");
		String city = request.getParameter("city");
		String sport = request.getParameter("sport");
		c.SaveCountry(Name, language, city, sport);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/successRegisterView.jsp");
		dispatcher.forward(request, response);

	}

}
