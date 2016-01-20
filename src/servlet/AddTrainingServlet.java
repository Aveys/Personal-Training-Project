package servlet;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.gson.Gson;
import model.Exercise;
import model.Plan;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by Max TOMPOUCE on 19/01/2016.
 */
public class AddTrainingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);

        DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();
        Gson gson = new Gson();
        Plan pl = gson.fromJson(req.getParameter("PARAM"),Plan.class);


        Entity plan = new Entity("Plan");

        plan.setProperty("title", pl.getTitle());
        plan.setProperty("description", pl.getDesc());
        plan.setProperty("domain", pl.getDomain());
        plan.setProperty("totalLength", pl.getTotalTime());

       Key planKey=datastore.put(plan);

        List<Exercise> listEx = pl.getExercises();
        Entity exercise;

        for (Exercise ex : listEx) {
            exercise = new Entity("Exercise", planKey);
            exercise.setProperty("title", ex.getTitle());
            exercise.setProperty("description", ex.getDesc());
            String length = ex.getLength();
            String[] splitStr = length.split(":");
            Integer seconds = Integer.parseInt(splitStr[0])*60 + Integer.parseInt(splitStr[1]);
            exercise.setProperty("length", seconds);
            exercise.setProperty("row", ex.getRow());
            datastore.put(exercise);
        }




    }
}
