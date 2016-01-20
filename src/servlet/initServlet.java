package servlet;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by arthurveys on 20/01/16 for Personal-Training-Projet.
 */
public class initServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Entity welcomeMSG = new Entity("WELCOMEMSG");
		welcomeMSG.setProperty("WELCOME_MSG","Welcome to this amazin website who display some of the best training plan on the internet");
		datastore.put(welcomeMSG);
		resp.sendRedirect("/");
	}
}
