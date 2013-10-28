package com.talkative.repository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/author_repository")
public class AuthorRepository {
    @GET
    @Path("/{authorId}")
    public Response author(@PathParam("authorId") int authorId) {
    	return Response.noContent().status(401).build();
    }

}
