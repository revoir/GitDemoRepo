package com.javarnd.controllerService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javarnd.dao.LanguageDao;
import com.javarnd.model.Language;

//This servlet is being triggered once user click on Search By Language option

@WebServlet("/languageView")
public class LanguageSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LanguageSearchServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LanguageDao l = new LanguageDao();
		List<Language> c1 = l.getAllLanguages();

		request.setAttribute("LanguageList", c1);

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/countryListView.jsp");

		dispatcher.forward(request, response);

	}

//Once Language to be search submitted then final result is being triggered from doPost() method

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String Name = request.getParameter("language_Name");
		LanguageDao c1 = new LanguageDao();
		List<Language> l1 = c1.getLanguage(Name);

		ArrayList<String> arraylist = new ArrayList<String>();
		for (int i = 0; i < l1.size(); i++) {
			if (Name.equalsIgnoreCase(l1.get(i).getLanguage_name())) {
				arraylist.add(l1.get(i).getCountry().get(0).getCountry_name());
			}
		}
		Set<String> set = new HashSet<String>(arraylist);
		try {
			out.println("<table width=60% border= 1   >");
			out.println("<tr><td colspan=4 ");
			out.println("<center><h2>Search By Language Result</h2></center>");
			out.println("</td></tr>");
			out.println("<tr>");
			out.println("<th>List of Countries</th>");

			out.println("</tr>");
			Iterator<String> itObjectForSet = set.iterator();
			while (itObjectForSet.hasNext()) {

				out.println("<tr>");
				out.println("<td>" + itObjectForSet.next() + "</td> ");
				out.println("</tr>");

			}
			out.println("</table>");
		} catch (Exception e) {
			throw new ServletException("error", e);
		}

	}

}
