package com.talkative.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.Encoded;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.codehaus.jettison.json.JSONArray;

import com.talkative.entity.Comment;
import com.talkative.repository.CommentRepository;
import com.google.gson.*;

public class ArticleResource {
	@GET
	@Path("{article:.+}/comments")
	public Response getComments(@PathParam("article") @Encoded String article, @PathParam("authorId") String authorId){
    	Gson gson = new Gson();
		ArrayList<Comment> authorComments = new ArrayList<Comment>();
    	JSONArray responseComments = new JSONArray();
    	for (Comment comment : authorComments)
    	{
    		if(comment.getIdAuthor() == Integer.parseInt(authorId))
    		{
    			authorComments.add(comment);
    			responseComments.put(gson.toJson(comment));
    		}
    	}
    	
    	if (authorComments.size() == 0)
    		return Response.noContent().header("Link", "http://"+article+"; rel=\"article\"").build();
    	else
    		return Response.status(200).entity(responseComments.toString()).build();
	}
	
	@PUT
	@Path("{article:.+}/comments")
	@Consumes({"application/json","application/xml"})
	@Produces({"application/json"})
	public Response addComment(Comment com){
		CommentRepository.comments.add(com);
    	return Response.status(200).entity(com).build();
    }
}
