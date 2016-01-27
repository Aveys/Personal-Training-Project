package servlet;

import com.google.appengine.api.datastore.*;
import com.google.appengine.repackaged.com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by arthurveys on 22/01/16 for Personal-Training-Projet.
 */
public class PlanServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-Type", "application/json");
		if(request.getParameter("keyID")==null)
			response.getWriter().write("{\"status\":\"failed\",\"message\":\"keyID field cannot be empty\"}");
		else {
			Gson gson = new Gson();
			Key key = KeyFactory.stringToKey(request.getParameter("keyID"));
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query query = new Query()
					.setAncestor(key);
			List<Entity> results = datastore.prepare(query)
					.asList(FetchOptions.Builder.withDefaults());
			response.getWriter().write(gson.toJson(results));
		}
	}
}
