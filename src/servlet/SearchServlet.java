package servlet;

import com.google.appengine.api.datastore.*;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by arthurveys on 20/01/16 for Personal-Training-Projet.
 */
public class SearchServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("Plan");
		Gson gson = new Gson();
		List<Entity> trainings = datastore.prepare(q).asList(FetchOptions.Builder.withLimit(10));
		resp.setHeader("Content-Type", "application/json");
		resp.getWriter().write(gson.toJson(trainings));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
