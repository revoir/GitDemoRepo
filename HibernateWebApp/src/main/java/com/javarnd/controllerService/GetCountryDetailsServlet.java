package com.javarnd.controllerService;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javarnd.dao.CountryDao;
import com.javarnd.model.Country;

	@WebServlet(urlPatterns = { "/countryView1"})
	public class GetCountryDetailsServlet extends HttpServlet {
	   private static final long serialVersionUID = 1L;

	   public GetCountryDetailsServlet() {
	       super();
	   }

	   @Override
	   protected void doGet(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {

	       
		   CountryDao c=new CountryDao();
		   List<Country>c1= c.getAllCountries();
		   
		   request.setAttribute("productList", c1);
		   
	       RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/productListView.jsp");
	       
	       dispatcher.forward(request, response);
	       
	   }

	   @Override
	   protected void doPost(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {
			/*
			 * CountryDao c=new CountryDao(); String Name = request.getParameter("name");
			 * String language = request.getParameter("language"); String
			 * city=request.getParameter("city"); String
			 * sport=request.getParameter("sport"); c.SaveCountry(Name,language,city,sport);
			 */
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/successRegisterView.jsp");
			dispatcher.forward(request, response);
			
			
			
			
	   }

	}




