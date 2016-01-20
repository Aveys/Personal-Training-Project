package servlet;

import com.google.appengine.api.datastore.*;
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
 * Created by arthurveys on 19/01/16 for Personal-Training-Projet.
 */
public class WelcomeTextServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String message = null;
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		//r�cup�ration du service Cache
		Cache cache = null;
		Map props = new HashMap();
		props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
		props.put(MemcacheService.SetPolicy.ADD_ONLY_IF_NOT_PRESENT, true);
		try {
			CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
			cache = (Cache) cacheFactory.createCache(props);
		} catch (CacheException e) {
			e.printStackTrace();
		}
		if (cache.get("WELCOME_MSG") != null) {
			message= (String) cache.get("WELCOME_MSG");
		} else {
			String msgDatastore = null;
			Query q = new Query("WELCOMEMSG");
			PreparedQuery pq = datastore.prepare(q);

			for (Entity result : pq.asIterable()) {
				msgDatastore = (String) result.getProperty("WELCOME_MSG");
			}
			//udpate cache
			cache.put("WELCOME_MSG", msgDatastore);
			message=msgDatastore;
		}
		resp.setHeader("Content-Type","application/json");
		resp.getWriter().write("{\"message\":\""+message+"\"}");
	}
}
