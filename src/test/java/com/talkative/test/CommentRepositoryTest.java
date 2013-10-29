package com.talkative.test;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.openejb.jee.SingletonBean;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

import com.talkative.repository.*;

@EnableServices(value = "jaxrs")
@RunWith(ApplicationComposer.class)
public class CommentRepositoryTest {

	@Module
    public SingletonBean app() {
        return (SingletonBean) new SingletonBean(CommentRepository.class).localBean();
    }

    @Test
    public void noComment() throws IOException {
        final Response message = WebClient.create("http://localhost:8080").path("/Talkative/comment_repository/0").get();
        assertEquals(204, message.getStatus());
    }

    @Test
    public void commentString() throws IOException {
    	String message = "{\"pseudo\":\"Toto\",\"mail\":\"toto@truc.fr\",\"message\":\"test\"}";
        final String response = WebClient.create("http://localhost:8080").path("/Talkative/comment_repository/").post(message, String.class);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String date = dateFormat.format(new Date());
        assertEquals("{\"pseudo\":\"Toto\",\"date\":\""+date+"\",\"message\":\"test\"}", response);
    }
    
    @Test
    public void commentResponse() throws IOException {
    	String message = "{\"pseudo\":\"Toto\",\"mail\":\"toto@truc.fr\",\"message\":\"test\"}";
        final Response response = WebClient.create("http://localhost:8080").path("/Talkative/comment_repository/").post(message, Response.class);
        assertEquals(200, response.getStatus());
    }
    
    @Test
    public void noCommentAdded() throws IOException {
    	String message = "{\"pseudo\":\"Toto\",\"mail\":\"\",\"message\":\"test\"}";
        Response response = WebClient.create("http://localhost:8080").path("/Talkative/comment_repository/").post(message, Response.class);
        assertEquals(400, response.getStatus());
        
        message = "{\"pseudo\":\"\",\"mail\":\"toto@truc.fr\",\"message\":\"test\"}";
        response = WebClient.create("http://localhost:8080").path("/Talkative/comment_repository/").post(message, Response.class);
        assertEquals(400, response.getStatus());
        
        message = "{\"pseudo\":\"Toto\",\"mail\":\"toto@truc.fr\",\"message\":\"\"}";
        response = WebClient.create("http://localhost:8080").path("/Talkative/comment_repository/").post(message, Response.class);
        assertEquals(400, response.getStatus());
    }

}
