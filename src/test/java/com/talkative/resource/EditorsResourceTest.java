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


public class EditorsResourceTest {
	public WebApp webApp() {
		return new WebApp().contextRoot("Talkative");
	}
	
	public EjbJar ejb(){
		return new EjbJar().enterpriseBean(new SingletonBean(MockEditorRepository.class));
	}

	/*public void editorAddForNewArticle(){
		WebClient client = createWebClient();
		JsonObject json = new JsonObject();
		json.addProperty("login", "test");
		json.addProperty("password", "test");
		json.addProperty("email", "test@test.fr");
		
		client.path("editors").post(json);
		
		Assert.assertEquals(201, client.getResponse().getStatus());
	}*/
	/*public void editorInvalidForNewArticle(){
		WebClient client = createWebClient();
		JsonObject json = new JsonObject();
		json.addProperty("login", "test");
		json.addProperty("password", "test");
		json.addProperty("email", "test@test.fr");
		
		client.path("editors").post(json);
		
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
