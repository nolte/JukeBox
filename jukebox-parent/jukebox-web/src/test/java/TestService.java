import java.io.InputStream;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.multipart.FormDataMultiPart;

public class TestService {

	@Test
	public void upload() {
		upload("http://127.0.0.1:8080/jukebox-web/rest/playlist", "test.mp3");
	}

	public void upload(String url, String fileName) {
		InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);
		FormDataMultiPart part = new FormDataMultiPart().field("file", stream, MediaType.TEXT_PLAIN_TYPE);

		WebResource resource = Client.create().resource(url);
		String response = resource.type(MediaType.MULTIPART_FORM_DATA_TYPE).post(String.class, part);
		System.out.println(response);
	}
}
