package com.talkative.resource;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.SingletonBean;
import org.apache.openejb.jee.WebApp;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.EnableServices;
import org.apache.openejb.testing.Module;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.gson.JsonObject;
import com.talkative.repository.MockEditorRepository;

@RunWith(ApplicationComposer.class)
@EnableServices("jaxrs")
public class ArticlesResourceTest {

	@Module
	@Classes(TalkativeApplication.class)
	public WebApp webApp() {
		return new WebApp().contextRoot("Talkative");
	}
	
	@Module
	public EjbJar ejb(){
		return new EjbJar().enterpriseBean(new SingletonBean(MockEditorRepository.class));
	}
	
	@Test
	public void noCommentsForNewArticle(){
		WebClient client = createWebClient();
		String message = client.path("editors/truc/articles/www.test.com/article.html/comments").get(String.class);
		
        Assert.assertEquals(204, client.getResponse().getStatus());
        Assert.assertEquals("http://www.test.com/article.html; rel=\"article\"", client.getResponse().getMetadata().getFirst("Link"));
        Assert.assertNull(message);
	}
	
	@Test
	public void noCommentsFoundForUnknownEditor(){
		WebClient client = createWebClient();
		client.path("editors").path(MockEditorRepository.UNKNOWN_EDITOR).get();
		
		Assert.assertEquals(403, client.getResponse().getStatus());
	}
	
	@Test
	public void commentAddForNewArticle(){
		WebClient client = createWebClient();
		String json = "{\"comment\":{}}";
		
		
		client.path("editors/truc/articles/www.test.com/article.html/comments").header("Content-Type", "application/json").put(json);
		
		Assert.assertEquals(200, client.getResponse().getStatus());
	}

	/*public void commentInvalidForNewArticle(){
		WebClient client = createWebClient();

		
		
		client.path("editors/truc/articles/www.test.com/article.html/comments").post(json);
		
		Assert.assertEquals(400, client.getResponse().getStatus());
	}*/

	private WebClient createWebClient() {
        WebClient client = WebClient.create("http://localhost:4204/Talkative/api");
        ClientConfiguration config = WebClient.getConfig(client);
        config.getInInterceptors().add(new LoggingInInterceptor());
        config.getOutInterceptors().add(new LoggingOutInterceptor());
        return client;
	}
}
