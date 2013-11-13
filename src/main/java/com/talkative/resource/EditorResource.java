package com.talkative.resource;

import javax.ws.rs.Path;

public class EditorResource {
	@Path("articles")
	public ArticleResource getArticlesRessource(){
		return new ArticleResource();
	}
}
