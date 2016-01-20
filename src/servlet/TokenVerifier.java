package servlet;

import auth.GoogleTokenVerifier;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.stdimpl.GCacheFactory;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arthurveys on 20/01/16 for Personal-Training-Projet.
 */
public class TokenVerifier extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String token = request.getParameter("tokenID");

		Map<String, String> id;
		if((id=GoogleTokenVerifier.verifyToken(token))!=null){
			response.getWriter().write("\n" +
					"    \"status\":\"success\",\n" +
					"    \"name\":\""+id.get("name")+"\",\n" +
					"    \"email\":\""+id.get("email")+"\"\n" +
					"}");
		}
		else {
			response.getWriter().write("{\"status\":\"failed\"}");
		}

	}
}
