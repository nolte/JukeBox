import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.multipart.FormDataMultiPart;

public class TestVoteService {

	@Test
	public void upload() {
		// upload("http://127.0.0.1:8080/jukebox-web/rest/playlist",
		// "kurz2.mp3");
		// upload("http://127.0.0.1:8080/jukebox-web/rest/playlist",
		// "kurz1.mp3");
		// upload("http://127.0.0.1:8080/jukebox-web/rest/playlist",
		// "kurz3.mp3");

		upload("http://127.0.0.1:8080/jukebox-web/rest/playlist/12382742706093", "test.mp3");
		// upload("http://127.0.0.1:8080/jukebox-web/rest/playlist",
		// "test2.mp3");
		// upload("http://127.0.0.1:8080/jukebox-web/rest/playlist",
		// "test3.mp3");
	}

	public void upload(String url, String fileName) {
		// InputStream stream =
		// getClass().getClassLoader().getResourceAsStream(fileName);
		FormDataMultiPart part = new FormDataMultiPart().field("points", "2", MediaType.TEXT_PLAIN_TYPE);

		Client c = Client.create();
		c.addFilter(new HTTPBasicAuthFilter("user", "user"));
		WebResource resource = c.resource(url);
		String response = resource.type(MediaType.MULTIPART_FORM_DATA_TYPE).post(String.class, part);
		System.out.println(response);
	}
}
