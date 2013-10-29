package com.talkative.repository;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/comment_repository")
public class CommentRepository {
    @GET
    @Path("/{authorId}")
    public Response comment(@PathParam("authorId") int authorId) {
    	return Response.noContent().status(204).build();
    }
    
    @POST
    public Response addComment(String message){
    	return Response.noContent().status(400).build();
    }

}
