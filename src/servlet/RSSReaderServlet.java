package servlet;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.XML;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by arthurveys on 21/01/16 for Personal-Training-Projet.
 */
public class RSSReaderServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("test");
		String rssDoc = "";
		try {
			URL url = new URL("http://feeds.bbci.co.uk/sport/rss.xml");
			BufferedReader reader = new BufferedReader(new
					InputStreamReader(url.openStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				rssDoc += line;
			}
			reader.close();

			//System.out.println(rssDoc);

			JsonParser parser = new JsonParser();
			JsonObject rss = parser.parse( XML.toJSONObject(rssDoc).getJSONObject("rss").toString()).getAsJsonObject();
			JsonArray items = rss.getAsJsonObject("channel").getAsJsonArray("item");
			JsonObject item;

			String jsonRss = "{\"rss\":[";


			for(int i=0;i<items.size() && i < 5;i++){

				jsonRss += (i==0)?"{":",{";

				item = items.get(i).getAsJsonObject();

				jsonRss += "\"title\":" +item.get("title");
				jsonRss += ",\"description\":" +item.get("description").toString();
				jsonRss += ",\"link\":" +item.get("link").toString();
				jsonRss += "}";
			}

			jsonRss += "]}";
			//System.out.println(jsonRss);
			response.setHeader("Content-Type","application/json");
			response.getWriter().write(jsonRss);
			//System.out.println(item.toString());

		} catch (MalformedURLException e) {
			// Gestion d’exceptions d’ouverture de flux
		} catch (IOException e) {
			// Gestion d’exceptions de lecture de flux
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
