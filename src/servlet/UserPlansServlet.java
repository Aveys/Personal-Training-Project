package servlet;

import com.google.appengine.api.datastore.*;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Max TOMPOUCE on 22/01/2016.
 */
public class UserPlansServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();

        String email = request.getParameter("email");
        Query.Filter filter = new Query.FilterPredicate("email", Query.FilterOperator.EQUAL, email);
        Query userPlans = new Query("User").setFilter(filter);
        List<Entity> listPlans = datastore.prepare(userPlans).asList(FetchOptions.Builder.withLimit(10));
        Map<String, List<Entity>> list = new HashMap<>();
        list.put("plans", listPlans);
        System.out.println("object sent :" + list.toString());
        response.setHeader("Content-Type", "application/json");
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(list));
    }
}
