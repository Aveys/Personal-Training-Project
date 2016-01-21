package servlet;

import com.google.appengine.api.datastore.*;
import com.google.appengine.repackaged.com.google.common.escape.ArrayBasedCharEscaper;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by arthurveys on 20/01/16 for Personal-Training-Projet.
 */
public class SearchServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String searchTerms = req.getParameter("search");

		if (searchTerms != null && !searchTerms.isEmpty()) {
			if (searchTerms.contains("+"))
				searchTerms = searchTerms.replace('+', ' ');
			System.out.println("Search Param : " + searchTerms);
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query.Filter filter = new Query.FilterPredicate("title", Query.FilterOperator.IN, Arrays.asList(searchTerms));
			Query plan = new Query("Plan").setFilter(filter);
			Query exercise = new Query("Exercise").setFilter(filter);
			Gson gson = new Gson();
			List<Entity> trainings = datastore.prepare(plan).asList(FetchOptions.Builder.withLimit(10));
			List<Entity> exercises = datastore.prepare(exercise).asList(FetchOptions.Builder.withLimit(10));
			Map<String, List<Entity>> list = new HashMap<>();
			list.put("trainings", trainings);
			list.put("exercises", exercises);
			System.out.println("object sent :" + list.toString());
			resp.setHeader("Content-Type", "application/json");
			resp.getWriter().write(gson.toJson(list));
		}
		else{
			resp.setHeader("Content-Type", "application/json");
			resp.getWriter().write("{\"status\":\"failed\",\"message\":\"search field cannot be empty\"}");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


	}
}
