package com.javarnd.controllerService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javarnd.dao.CountryDao;
import com.javarnd.model.Country;

//This servlet is being triggered once use click on SearchByCountry option

@WebServlet(urlPatterns = { "/countryView" })

public class CountrySearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CountrySearchServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CountryDao c = new CountryDao();
		List<Country> c1 = c.getAllCountries();

		request.setAttribute("productList", c1);

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/productListView.jsp");

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String Name = request.getParameter("user_Name");
		CountryDao c1 = new CountryDao();
		List<Country> l1 = c1.getCountry(Name);
		try {
			out.println("<table width=60% border= 1   >");
			out.println("<tr><td colspan=4 ");
			out.println("<center><h2>Search By Country Result</h2></center>");
			out.println("</td></tr>");
			out.println("<tr>");
			out.println("<th>Country</th>");
			out.println("<th>Sport</th>");
			out.println("<th>Capital</th>");
			out.println("<th>Language</th>");

			out.println("</tr>");
			for (int i = 0; i < l1.size(); i++) {

				out.println("<tr>");
				out.println("<td>" + l1.get(i).getCountry_name() + "</td> ");
				out.println("<td>" + l1.get(i).getSport().get(0).getSport_name() + "</td> ");
				out.println("<td>" + l1.get(i).getCapital().getCity_name() + "</td> ");
				out.println("<td>" + l1.get(i).getLanguage().getLanguage_name() + "</td> ");
				out.println("</tr>");

			}
			out.println("</table>");
		} catch (Exception e) {
			throw new ServletException("error", e);
		}

	}

}
