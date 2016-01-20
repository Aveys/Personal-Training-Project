package servlet;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.gson.Gson;
import model.Plan;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by Max TOMPOUCE on 19/01/2016.
 */
public class AddTrainingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);

        DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();
        System.out.println(" param : "+req.getParameter("PARAM"));
        Gson gson = new Gson();
        Plan pl = gson.fromJson(req.getParameter("PARAM"),Plan.class);

        System.out.println(pl);
        /*if (req.getParameter("type") == null ? "plan" == null : req.getParameter("type").equals("plan")) {

            //TODO avec la liste des exercices
            Entity plan = new Entity("Plan");
            plan.setProperty("title", req.getParameter("title"));
            plan.setProperty("description", req.getParameter("desc"));
            plan.setProperty("domain", req.getParameter("domain"));
            plan.setProperty("totalLength", req.getParameter("totalTime"));


            Integer x = 0;
            String[] listEx = req.getParameterValues("exercises");
            Entity exercise = null;
            //TODO gérer en modulo4
            for (String ex : listEx) {
                switch (x) {
                    case 0:
                        exercise = new Entity("Exercise", plan.getKey());
                        exercise.setProperty("title", ex);
                        x=1;
                        break;
                    case 1:
                        exercise.setProperty("description", ex);
                        x=2;
                        break;
                    case 2:
                        String length = ex;
                        String[] splitStr = length.split(":");
                        Integer seconds = Integer.parseInt(splitStr[0])*60 + Integer.parseInt(splitStr[1]);
                        exercise.setProperty("length", seconds);
                        x=3;
                        break;
                    case 3:
                        exercise.setProperty("row", ex);
                        datastore.put(exercise);
                        exercise=null;
                        x=4;
                        break;
                }
            }

            datastore.put(plan);






        }
        else if (req.getParameter("type") == null ? "exercise" == null : req.getParameter("type").equals("exercise")) {
            *//*Entity exercise = new Entity("EXERCISE");
            exercise.setProperty("title", req.getParameter("title"));
            exercise.setProperty("description", req.getParameter("desc"));
            Integer seconds = Integer.parseInt(req.getParameter("mn"))*60 + Integer.parseInt(req.getParameter("s"));
            exercise.setProperty("length", seconds);
            exercise.setProperty("row", req.getParameter("row"));
            datastore.put(exercise);*//*

            //Vérif validiter form

            //if (req.getParameter("title").equals("") || req.getParameter("desc").equals("") || req.getParameter("row").equals("0")
        }*/

    }
}
