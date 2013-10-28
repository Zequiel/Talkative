package com.talkative.test;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.openejb.jee.SingletonBean;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.junit.EnableServices;
import org.apache.openejb.junit.Module;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

import com.talkative.repository.*;

@SuppressWarnings("deprecation")
@EnableServices(value = "jaxrs")
@RunWith(ApplicationComposer.class)
public class AutoTest {

	@Module
    public SingletonBean app() {
        return (SingletonBean) new SingletonBean(CommentRepository.class).localBean();
    }

    @Test
    public void get() throws IOException {
        final String message = WebClient.create("http://localhost:8080").path("/Talkative/comment_repository/").get(String.class);
        assertEquals("Hi REST!", message);
    }

    @Test
    public void post() throws IOException {
        final String message = WebClient.create("http://localhost:8080").path("/Talkative/comment_repository/").post("Hi REST!", String.class);
        assertEquals("hi rest!", message);
    }

}
