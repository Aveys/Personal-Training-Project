package auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.stdimpl.GCacheFactory;
import com.google.appengine.repackaged.com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.appengine.repackaged.com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.appengine.repackaged.com.google.api.client.http.HttpTransport;
import com.google.appengine.repackaged.com.google.api.client.json.JsonFactory;
import com.google.appengine.repackaged.com.google.api.client.json.jackson2.JacksonFactory;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arthurveys on 20/01/16 for Personal-Training-Projet.
 */
public class GoogleTokenVerifier {
	private static String CLIENT_ID = "418187800373-a5n7urf2liqa6uahdadtj75oq5k2q3up.apps.googleusercontent.com";

	public static Map<String,String> verifyToken(String token) {


		Cache cache = null;
		HashMap<String,String> map = new HashMap<>();
		Map props = new HashMap();
		props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
		props.put(MemcacheService.SetPolicy.ADD_ONLY_IF_NOT_PRESENT, true);

		// INIT cache
		try {
			CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
			cache = (Cache) cacheFactory.createCache(props);
		} catch (CacheException e) {
			e.printStackTrace();

		}
		// check if token already in cache
		if(cache.get(token)!=null){
			try {
				ObjectMapper m = new ObjectMapper();
				JsonNode root =m.readTree((String)cache.get(token));
				map.put("name",root.path("name").textValue());
				map.put("mail",root.path("email").textValue());
				return map;

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		else{
			HttpTransport transport = null;
			try {
				transport = GoogleNetHttpTransport.newTrustedTransport();
			} catch (GeneralSecurityException | IOException e) {
				e.printStackTrace();
			}
			JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
					.setAudience(Arrays.asList(CLIENT_ID)).build();
			GoogleIdToken idToken = null;
			try {
				idToken = verifier.verify(token);
			} catch (GeneralSecurityException | IOException e) {
				e.printStackTrace();
			}
			if (idToken != null) {
				GoogleIdToken.Payload payload = idToken.getPayload();
				// Get profile information from payload
				String email = payload.getEmail();
				String name = (String) payload.get("name");
				map.put("mail",email);
				map.put("name",name);
				String json = "{\"name\":\""+name+"\",\"email\":\""+email+"\"}";
				cache.put(token,json);
				return map;

			} else {
				return null;
			}
		}
		return null;
	}
}

